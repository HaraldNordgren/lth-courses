%File        Cycles  Time
%ar_1_1.dzn  34
%ar_1_2.dzn  18      265msec
%ar_1_3.dzn  16      17msec
%ar_2_2.dzn  18      1s 174msec
%ar_2_3.dzn  15      25msec
%ar_2_4.dzn  11      17msec

include "globals.mzn";


int: del_add; int: del_mul;
int: number_add; int: number_mul;

int : n; % number of operations %n = 28;
set of int : last; %last = {13,14,27,28};
set of int : add; %add = {9,10,11,12,13,14,19,20,25,26,27,28};
set of int : mul; %mul = {1,2,3,4,5,6,7,8,15,16,17,18,21,22,23,24};

array[1..n] of set of int : dependencies;


set of int: numAdds = 1..length(add);
set of int: numMuls = 1..length(mul);

array[1..n] of var 0..100: nodes;
array[numAdds] of var int: adds = [nodes[i] | i in 1..n where i in add];
array[numMuls] of var int: muls = [nodes[i] | i in 1..n where i in mul];

constraint cumulative(adds, [del_add | i in numAdds], [1 | i in numAdds], number_add);
constraint cumulative(muls, [del_mul | i in numMuls], [1 | i in numMuls], number_mul);


array[numAdds] of var 1..number_add: resources_add;
array[numMuls] of var 1..number_mul: resources_mul;

constraint diff2(adds, resources_add, [del_add | i in numAdds], [1 | i in numAdds]);
constraint diff2(muls, resources_mul, [del_mul | i in numMuls], [1 | i in numMuls]);


constraint forall (i in 1..n)(
  forall (j in dependencies[i])(
    nodes[i] + bool2int(i in add)*del_add + bool2int(i in mul)*del_mul <= nodes[j]));

var int: end;
constraint forall (i in last)(
  nodes[i] + bool2int(i in add)*del_add + bool2int(i in mul)*del_mul <= end);


%solve minimize end;
solve :: seq_search([
		int_search(nodes, first_fail, indomain_min, complete),
		int_search(resources_add ++ resources_mul, smallest, indomain_min, complete)])
	minimize end;


output ["End:\t", show(end),"\n",
  "Adds:\t",show(adds),"\n",
  "Muls:\t",show(muls)];

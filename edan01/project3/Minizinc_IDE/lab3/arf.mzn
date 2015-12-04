%  File        Cycles  Time        Optimal
%  ar_1_2.dzn  18      313msec     Yes
%  ar_1_3.dzn  16      15msec      Yes
%  ar_2_2.dzn  18      1s 298msec  Yes
%  ar_2_3.dzn  15      18msec      Yes
%  ar_2_4.dzn  11      17msec      Yes


include "globals.mzn";

int : del_add; %del_add = 1;
int : del_mul; %del_mul = 2;

int : number_add; %number_add = 1;
int : number_mul; %number_mul = 1;

int : n; % number of operations %n = 28;
set of int : last; %last = {13,14,27,28};
set of int : add; %add = {9,10,11,12,13,14,19,20,25,26,27,28};
set of int : mul; %mul = {1,2,3,4,5,6,7,8,15,16,17,18,21,22,23,24};
array[1..n] of set of int : dependencies;


var int: end;

set of int: tot_add = 1..12;
set of int: tot_mul = 1..16;

array[tot_add] of var 0..50: adds;
array[tot_mul] of var 0..50: muls;

array[tot_add] of int: del_adds = [del_add | i in 1..12];
array[tot_mul] of int: del_muls = [del_mul | i in 1..16];

array[tot_add] of int: add_cost = [1 | i in 1..12];
array[tot_mul] of int: mul_cost = [1 | i in 1..16];

constraint cumulative(adds, del_adds, add_cost, number_add);
constraint cumulative(muls, del_muls, mul_cost, number_mul);

constraint (muls[1] + del_mul <= adds[1]);
constraint (muls[2] + del_mul <= adds[1]);
constraint (muls[3] + del_mul <= adds[2]);
constraint (muls[4] + del_mul <= adds[2]);
constraint (muls[5] + del_mul <= adds[3]);
constraint (muls[6] + del_mul <= adds[3]);
constraint (muls[7] + del_mul <= adds[4]);
constraint (muls[8] + del_mul <= adds[4]);
constraint (muls[9] + del_mul <= adds[7]);
constraint (muls[10] + del_mul <= adds[7]);
constraint (muls[11] + del_mul <= adds[8]);
constraint (muls[12] + del_mul <= adds[8]);
constraint (muls[13] + del_mul <= adds[9]);
constraint (muls[14] + del_mul <= adds[9]);
constraint (muls[15] + del_mul <= adds[10]);
constraint (muls[16] + del_mul <= adds[10]);

constraint (adds[1] + del_add <= adds[11]);
constraint (adds[2] + del_add <= adds[5]);
constraint (adds[3] + del_add <= adds[6]);
constraint (adds[4] + del_add <= adds[12]);
constraint (adds[5] + del_add <= muls[9]);
constraint (adds[5] + del_add <= muls[11]);
constraint (adds[6] + del_add <= muls[10]);
constraint (adds[6] + del_add <= muls[12]);
constraint (adds[7] + del_add <= muls[13]);
constraint (adds[7] + del_add <= muls[15]);
constraint (adds[8] + del_add <= muls[14]);
constraint (adds[8] + del_add <= muls[16]);
constraint (adds[9] + del_add <= adds[11]);
constraint (adds[10] + del_add <= adds[12]);

constraint (adds[11] + del_add <= end);
constraint (adds[12] + del_add <= end);

solve minimize end;

output ["End:\t", show(end),"\nMults:\t", show(muls), "\nAdds:\t", show(adds),
"\n\nStarts:\n",
"*6: ", show(muls[7]),
"\n*7: ", show(muls[8]),
"\n*2: ", show(muls[3]),
"\n*3: ", show(muls[4]),
"\n*4: ", show(muls[5]),
"\n*5: ", show(muls[6]),
"\n*1: ", show(muls[2]),
"\n*0: ", show(muls[1])];
% minizinc solution for the auto regression filter assignment
% by tpi11ebr

% results:
% 1 add, 1 mul: 34 cycles, 907ms.
% 1 add, 2 mul: 19 cycles, 886ms.
% 1 add, 3 mul: 16 cycles, 872ms.
% 2 add, 2 mul: 19 cycles, 854ms.
% 2 add, 3 mul: 15 cycles, 822ms.
% 2 add, 4 mul: 11 cycles, 856ms.


include "globals.mzn";

% end time

var int: end;

% number of adders and multipliers
int: adders;
int: multers;

% number of clock cycles required per operation
int: addClock;
int: multClock;

% total number of operations
set of int: totAdds;
set of int: totMults;

% Starting clock cycle for the add and mul operations.
array[totAdds] of var 0..50: adds;
array[totMults] of var 0..50: mults;


% arrays with clock cycle per operation. Is the same for all but need an
% array for use in cumulative.

array[totAdds] of int: addClocks = [addClock, addClock, addClock, addClock, addClock, addClock,
                                    addClock, addClock, addClock, addClock, addClock, addClock];

array[totMults] of int: mulClocks = [multClock, multClock, multClock, multClock, multClock, multClock,
                                    multClock, multClock, multClock, multClock, multClock, multClock,
                                    multClock, multClock, multClock, multClock];


% arrays for use in cumulative. Resource requirement for using an
% adder or multer is 1.

array[totAdds] of int: addCost = [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1];
array[totMults] of int: mulCost = [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1];

% operations order according to lab description.

% mults[1] *0
% mults[2] *1
% mults[3] *2
% mults[4] *3
% mults[5] *4
% mults[6] *5
% mults[7] *6
% mults[8] *7
% mults[9] *14
% mults[10] *15
% mults[11] *16
% mults[12] *17
% mults[13] *20
% mults[14] *21
% mults[15] *22
% mults[16] *23

% adds[1] +8
% adds[2] +9
% adds[3] +10
% adds[4] +11
% adds[5] +12
% adds[6] +13
% adds[7] +18
% adds[8] +19
% adds[9] +24
% adds[10] +25
% adds[11] +26
% adds[12] +27

% constraints

constraint cumulative(adds, addClocks, addCost, adders);
constraint cumulative(mults, mulClocks, mulCost, multers);

constraint (mults[1] + multClock <= adds[1]);
constraint (mults[2] + multClock <= adds[1]);

constraint (mults[3] + multClock <= adds[2]);
constraint (mults[4] + multClock <= adds[2]);

constraint (mults[5] + multClock <= adds[3]);
constraint (mults[6] + multClock <= adds[3]);

constraint (mults[7] + multClock <= adds[4]);
constraint (mults[8] + multClock <= adds[4]);

constraint (adds[2] + addClock <= adds[5]);

constraint (adds[3] + addClock <= adds[6]);

constraint (mults[9] + multClock <= adds[7]);
constraint (mults[10] + multClock <= adds[7]);

constraint (mults[11] + multClock <= adds[8]);
constraint (mults[12] + multClock <= adds[8]);

constraint (mults[13] + multClock <= adds[9]);
constraint (mults[14] + multClock <= adds[9]);

constraint (mults[15] + multClock <= adds[10]);
constraint (mults[16] + multClock <= adds[10]);

constraint (adds[1] + addClock <= adds[11]);
constraint (adds[9] + addClock <= adds[11]);

constraint (adds[4] + addClock <= adds[12]);
constraint (adds[10] + addClock <= adds[12]);

constraint (adds[5] + addClock <= mults[9]);

constraint (adds[6] + addClock <= mults[10]);

constraint (adds[5] + addClock <= mults[11]);

constraint (adds[6] + addClock <= mults[12]);

constraint (adds[7] + addClock <= mults[13]);

constraint (adds[8] + addClock <= mults[14]);

constraint (adds[7] + addClock <= mults[15]);

constraint (adds[8] + addClock <= mults[16]);

constraint (adds[11] + addClock <= end);
constraint (adds[12] + addClock <= end);

output [show(end), show(mults), show(adds)];

solve :: seq_search([
      int_search(adds ++ mults, first_fail, indomain_min, complete),
      int_search([end], first_fail, indomain_min, complete)])
    minimize end;



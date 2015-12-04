function [] = main(n)

A = banded(n);
b = b_vector(n);

[x, iterations] = jacobi(A, b, 0.5 * 10^-6)
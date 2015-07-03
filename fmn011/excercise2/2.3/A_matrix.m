function A = A_matrix(n)

A = zeros(n);
for i = 1:n
    for j = 1:n
        A(i,j) = 5 / (i + 2*j - 1);
    end
end
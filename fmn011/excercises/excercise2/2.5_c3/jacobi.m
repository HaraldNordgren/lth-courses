function [final_err, iter] = jacobi(A,b,tol)

n = length(b);
d = diag(A);
r = A - diag(d);

correct = ones(n,1);
iter = 0;

x = zeros(n,1);
while true
    iter = iter + 1;
    x = (b - r*x) ./ d;
    diff = x - correct;
    err = norm(diff,inf);
    if (err < tol)
        final_err = err;
        break;
    end
end
function [y, err] = fixed_point(f, x, tol)
while abs( f(x) - x ) > tol
    x = f(x);
end

y = x;
err = f(x)-x;
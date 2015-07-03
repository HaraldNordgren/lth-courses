function [c,possible_err,res] = bisection(f,a,b,tol)
while (b-a)/2 > tol
    c = (a+b)/2;
    if f(c)*f(a)>0
        a=c;
    elseif f(c)*f(b)>0
        b=c;
    else
        break
    end
end
possible_err = (b-a)/2;
res = f(c);
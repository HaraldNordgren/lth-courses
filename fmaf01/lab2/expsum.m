function [s,antaltermer]=expsum(x, tol)
%s anger närmevärdet av e^x
%tol = toleransen
a = 1;
s = 0;
k = 0;
while ((2*a> tol)|(k<11))
    s = s + a;
    a = a*x/(k+1);
    k=k+1;
end;
antaltermer=k;
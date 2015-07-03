function d = banded(n)

e = ones (n,1);
d = spdiags([-e 3*e -e],-1:1,n,n);
function z = create_taylor(y)
syms y;
f = sin(y);
T = taylor(f, 'Order', 15);
z = @(x) subs(T,x)
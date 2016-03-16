s = 0;
for k=1:100;
s = s + bkoeff(k) * sin(k*t);
plot(t,s), title(['delsumma s_{' int2str(k) '}'])
end
pause
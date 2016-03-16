% visaserie.m
%
% Beräknar och ritar delsummor till Fourierserie
% Fourierkoefficienterna finns i 
% anoll, akoeff, bkoeff .. 
% inparametrar: 
%     anoll, akoeff, bkoeff     trigonometriska Fourierkoefficienter
%     ymax, ymin                skalan på y-axeln
%
% (SSp 3 okt 1997, 30 sep 1998, SS 23 nov 2000)
%
clf
hold off
inresteg=0.05;
clear summa
clear term
axis([-10 10 -2 2])
t=-10:inresteg:10;
term=anoll/2*ones(size(t));
summa=term;
k = 0;
for k=1:100,
%   
   subplot(211)
   hold off
   plot(t,summa); % rita summa (i annan ruta)
   axis([-10 10 ymin ymax]) 
   hold on
   title([ 'delsumma s_{' int2str(k-1) '}' ])
   hold off
%
   subplot(212)
   plot(t,term,'r');
   hold on
   axis([-10 10 -1 1]) % skalan i y-led ändras vid behov 
   hold off
   title([ 'term u_{' int2str(k-1) '}' '(röd)' ])
%
   pause
   term=akoeff(k)*cos(k*t)+bkoeff(k)*sin(k*t); % uppdatera term
   summa=summa+term; % uppdatera summa
end


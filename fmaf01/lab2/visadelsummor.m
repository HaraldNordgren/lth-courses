% visadelsummor.m
%
% Beräknar och ritar delsummor till Fourierserie
% Fourierkoefficienterna finns i 
% anoll, akoeff, bkoeff .. , ev beräknade i fourkoeff
% funktionen ges som en sträng i funktion med variabeln tid
% inparametrar: 
%     anoll, akoeff, bkoeff     trigonometriska Fourierkoefficienter
%     funktion   funktionen som sträng
% utparametrar:
%     fourf      de komplexa fourierkoefficienterna
%     anoll     a_0
%     akoeff    a_k, 1 =< k =< 127   
%     bkoeff    b_k, 1 =< k =< 127
%
% (SSp 3 okt 1997)
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
subplot(311) % dela grafiken i tre delar (vertikalt)
k = 0;
tid=0:inresteg:2*pi;
fintern=eval(funktion);
maximum = max(2,max(fintern)+0.2*max(abs(fintern)));
minimum = min(-2,min(fintern)-0.2*(max(abs(fintern))));
% maximum och minimum behövs för att få lagom skalor i figurerna
for k=1:100,
%   
   subplot(311)
   hold off
   plot(t,summa); % rita summa (i annan ruta)
   axis([-10 10 minimum maximum])
   hold on
   title([ 'delsumma s_{' int2str(k-1) '}' ])
   hold off
%
   subplot(312)
   plot(tid-2*pi,term((t>=0)&(t<2*pi)),'r');
   hold on
   plot(tid-4*pi,term((t>=0)&(t<2*pi)),'r');
   plot(tid,fintern-summa((t>=0)&(t<2*pi)),'b') % rita restterm 
   plot(tid+2*pi,fintern-summa((t>=0)&(t<2*pi)),'b') % rita restterm 
   axis([-10 10 -2 2])
   hold off
   title([ 'term u_{' int2str(k-1) '}' '(röd) och restterm r_{' int2str(k-1) '} (blå)' ])
%
   subplot(313)
   hold off
   plot(tid-4*pi,fintern,'g');
   hold on
   plot(tid-2*pi,fintern,'g');
   plot(tid,fintern,'g'); % rita den ursprungliga funktionen
   plot(tid+2*pi,fintern,'g');
   axis([-10 10 minimum maximum ])
   title(funktion);
%
   pause
   term=akoeff(k)*cos(k*t)+bkoeff(k)*sin(k*t); % uppdatera term
   summa=summa+term; % uppdatera summa
end


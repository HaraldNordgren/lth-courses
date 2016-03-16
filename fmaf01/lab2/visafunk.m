% visafunk.m
%
% Ritar upp en funktion av tiden
% inparametrar:
%        tid        tidsintervallet   
%        funktion   funktionen som sträng
%                   exempel: funktion = 'cos(tid) + tid.*tid'
%
% (SSp 3 okt 1997)
%
clf
f=eval(funktion);
maxabs  = max(abs(f));
maximum = max(f);
minimum = min(f);
plot(tid,f);
% välj axlar så att funktionskurvan får lagom plats
axis([0 2*pi minimum - 0.1*maxabs maximum + 0.1*maxabs ])
title(funktion);



% fourkoeff.m
%
% best�mmer Fourierkoefficienter f�r en reell (obs!) funktion
% ritar som kontroll upp funktionen
% funktionen ges som en str�ng med variabeln tid
% exempel: funktion = '(tid-pi).*(tid-pi)./(tid.^2 + pi*pi)'
% inparametrar: 
%     funktion   funktionen som str�ng
% utparametrar:
%     fourf      de komplexa fourierkoefficienterna
%     anoll     a_0
%     akoeff    a_k, 1 =< k =< 127
%     bkoeff    b_k, 1 =< k =< 127
%
%
% (SSp 3 okt 1997)
%
n = 8; % 256 punkter
N=2^n;
T=2*pi;
delta=T/N;
tid=0:delta:(N-1)*delta;
f=eval(funktion);
% fft �r en numerisk metod f�r ber�kning av Fourierkoefficienter
fourf  = fft(f)/N; 
% Best�mning av trigonometriska koefficienter (om funktion �r reell)
rfourf = real(fourf);
ifourf = imag(fourf);
akoeff =  2*rfourf(2:2^(n-1));
bkoeff = -2*ifourf(2:2^(n-1));
anoll  =  2*fourf(1);
visafunk;



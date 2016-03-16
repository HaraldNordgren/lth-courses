% fourkoeff.m
%
% bestämmer Fourierkoefficienter för en reell (obs!) funktion
% ritar som kontroll upp funktionen
% funktionen ges som en sträng med variabeln tid
% exempel: funktion = '(tid-pi).*(tid-pi)./(tid.^2 + pi*pi)'
% inparametrar: 
%     funktion   funktionen som sträng
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
% fft är en numerisk metod för beräkning av Fourierkoefficienter
fourf  = fft(f)/N; 
% Bestämning av trigonometriska koefficienter (om funktion är reell)
rfourf = real(fourf);
ifourf = imag(fourf);
akoeff =  2*rfourf(2:2^(n-1));
bkoeff = -2*ifourf(2:2^(n-1));
anoll  =  2*fourf(1);
visafunk;



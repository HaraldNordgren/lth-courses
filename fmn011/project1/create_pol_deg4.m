tic
%% Fit: 'Polynomial deg 4'.

fitresult = fit( data_x, data_y, fittype('poly4'), 'Normalize', 'on' );
h = plot( fitresult, data_x, data_y );
legend( h, 'Unemployment', 'Polynomial deg 4', 'Location', 'NorthEast' );
% Label axes
xlabel Time
ylabel 'Unemployment (×1000)'
grid on

toc
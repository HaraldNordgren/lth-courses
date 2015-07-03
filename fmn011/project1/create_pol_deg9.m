tic
%% Fit: 'Polynomial deg 9'.

% Set up fittype and options.
ft = fittype( 'poly9' );

% Fit model to data.
[fitresult, gof] = fit( data_x, data_y, ft, 'Normalize', 'on' );

% Plot fit with data.
figure( 'Name', 'Polynomial deg 9' );
h = plot( fitresult, data_x, data_y );
legend( h, 'Unemployment', 'Polynomial deg 9', 'Location', 'NorthEast' );
% Label axes
xlabel Time
ylabel 'Unemployment (×1000)'
grid on

toc
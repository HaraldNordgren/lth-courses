tic

%% Fit: 'Trigonoteric polynomial deg 8, yearly'.

% Set up fittype and options.
ft = fittype( 'fourier8' );
opts = fitoptions( 'Method', 'NonlinearLeastSquares' );
opts.Display = 'Off';
opts.Lower = [-Inf -Inf -Inf -Inf -Inf -Inf -Inf -Inf -Inf -Inf -Inf -Inf -Inf -Inf -Inf -Inf -Inf 0.5235];
opts.Normalize = 'on';
opts.StartPoint = [0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0.910639694838952];
opts.Upper = [Inf Inf Inf Inf Inf Inf Inf Inf Inf Inf Inf Inf Inf Inf Inf Inf Inf 0.5237];

% Fit model to data.
[fitresult, gof] = fit( data_x, data_y, ft, opts );

% Plot fit with data.
figure( 'Name', 'Trigonoteric polynomial deg 8, yearly' );
h = plot( fitresult, data_x, data_y );
legend( h, 'Unemployment', 'Trigonoteric polynomial deg 8, yearly', 'Location', 'NorthEast' );
% Label axes
xlabel Time
ylabel 'Unemployment (×1000)'
grid on

toc
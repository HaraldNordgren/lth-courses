tic

%% Fit: 'Trigonoteric polynomial deg 8, quarterly'.

% Set up options.
opts = fitoptions( 'Method', 'NonlinearLeastSquares' );
opts.Lower = [-Inf -Inf -Inf -Inf -Inf -Inf -Inf -Inf -Inf -Inf -Inf -Inf -Inf -Inf -Inf -Inf -Inf 1.5707];
opts.Upper = [Inf Inf Inf Inf Inf Inf Inf Inf Inf Inf Inf Inf Inf Inf Inf Inf Inf 1.5709];
opts.Normalize = 'on';
fitresult = fit( data_x, data_y, fittype('fourier8'), opts );

% Plot fit with data.
figure( 'Name', 'Trigonoteric polynomial deg 8, quarterly' );
h = plot( fitresult, data_x, data_y );
legend( h, 'Unemployment', 'Trigonoteric polynomial deg 8, quarterly', 'Location', 'NorthEast' );
% Label axes
xlabel Time
ylabel 'Unemployment (×1000)'
grid on

toc
read_data;

%c = polyfit(data_x, data_y, data_size(1)-1);
%c = polyfit(data_x, data_y, 50);
%x = 1:0.01:data_size(1);
%y = polyval(c, x);
%plot(data_x, data_y, 'o', x, y)

fitresult = fit(data_x, data_y, fittype('poly8'), 'Normalize', 'on');
plot(fitresult, data_x, data_y)

legend( 'data_y vs. data_x', 'polynomial deg 8', 'Location', 'NorthEast' );
% Label axes
xlabel data_x
ylabel data_y
grid on
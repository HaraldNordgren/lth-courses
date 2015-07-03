%% read_data
DATA = xlsread('frenchUnemployment.xlsx');
data_size = size(DATA);
interval = 1:data_size(1);

data_x = DATA(interval,1);
data_y = DATA(interval,2);

%plot(data_x, data_y)
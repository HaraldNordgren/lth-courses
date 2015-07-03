LOWER_BOUND = 6;
UPPER_BOUND = 15;

ns = LOWER_BOUND:UPPER_BOUND;
sz = size(ns);
indices = 1:size(2);

cond_nbrs = zeros(sz);
relative_errors = zeros(sz);
sig_digits = zeros(sz);

for n = ns
    A = A_matrix(n);
    x = ones(n,1);

    %abs_vector = arrayfun(@(x) abs(x), A(:));
    %max_norm = max(abs_vector);
    
    index = n - LOWER_BOUND + 1;
    
    b = A * x;
    x_reconstructed = A \ b;
    
    sig_digits(index) = significant_digits(x, x_reconstructed);
    
    relative_errors(index) = norm(x - x_reconstructed) / norm(x);
    cond_nbrs(index) = cond(A);
end

if true
    semilogy(ns, relative_errors, '-bo');
    hold on
    semilogy(ns, cond_nbrs, '-ro');

    xlabel('n');
    legend('relative error', 'conditional numbers', 'Location', 'Northwest');
end
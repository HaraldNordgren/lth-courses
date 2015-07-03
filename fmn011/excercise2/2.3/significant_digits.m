function result = significant_digits(x, x_reconstructed)

max = norm(x - x_reconstructed, inf);
digits = log10(max);

if (digits > 0)
    result = 0;
else
    result = -digits;
end

%sz = size(x);
%
%result = true;
%for i = 1:sz(1)
%    if (v(i) >= 1)
%        result = false;
%        break
%    end
%end
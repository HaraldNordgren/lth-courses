#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

unsigned canonize(unsigned n);
unsigned digit_sum(unsigned n);

bool happy(unsigned n) {
	n = canonize(n);
	unsigned sum = digit_sum(n);
	while (sum != 1 && sum != n) {
		sum = digit_sum(sum);
		sum = canonize(sum);
	}
	return sum == 1;
}

unsigned canonize(unsigned n) {
	vector<unsigned> digits;
	while (n != 0) {
		unsigned digit = n % 10;
		if (digit != 0)
			digits.push_back(digit);
		n /= 10;
	}

	sort(digits.begin(),digits.end());
	unsigned result = 0;
	for (auto it = digits.cbegin(); it != digits.cend(); ++it) {
		result *= 10;
		result += *it;
	}
	return result;
}

unsigned digit_sum(unsigned n) {
	unsigned result = 0;
	while (n != 0) {
		result *= 10;
		result += n % 10;
		n /= 10;
	}
	return result;
}

int main () {
	for (unsigned i = 1; i != 100; ++i) {
		cout << "Is " << i << " happy?: " << happy(i) << endl;
	}
}

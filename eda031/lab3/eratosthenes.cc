#include <iostream>

using namespace std;

string sieve (unsigned int);

int main() {
	unsigned int MAX_SIZE = 100000;
	unsigned int SIZE = 200;
	string primes = sieve(MAX_SIZE);
	
	cout << "Primes between 0 and " << SIZE << ":\n{ ";
	for (unsigned int i = 0; i < SIZE; i++) {
		if (primes[i] == 'P') {
			cout << i << " ";
		}
	}
	cout << "}" << endl << endl;
	
	cout << "Largest prime below " << MAX_SIZE << ":\n";
	cout << primes.rfind("P") << endl;
}

string sieve (unsigned int n) {
	string result;
	
	result += "CC";
	for (unsigned int i = 2; i < n+1; i++) {
		result += "P";
	}
	//result[0] = 'C';
	//result[1] = 'C';
	
	for (unsigned int i = 2; i < result.length(); i++) {
		if (result[i] == 'P') {
			for (unsigned int j = 2*i; j < result.length(); j += i) {
				result[j] = 'C';
			}
		}
	}
	
	return result;
}

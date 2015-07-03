#include <iostream>
using namespace std;

class X { };

bool operator==(const X&, const int) {
	return true;
}

int main() {
	X x;
	cout << boolalpha << (x == 1 && x == 2) << endl;
}

#include <iostream>
#include <string>
#include <stdexcept>
#include <ostream>
#include <fstream>
#include <sstream>
#include "date.h"

using namespace std;

template <class T> string to_string (T t) {
	ostringstream oss;
	oss << t;
	return oss.str();

}

int main() {
	double d = 1.234;
	cout << to_string(d) << endl;
	Date today;
	string s = to_string(today);
	cout << "Today: " << s << endl;
}

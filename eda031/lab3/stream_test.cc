#include <iostream>
#include <ostream>
#include <fstream>
#include <sstream>
#include <string>
#include <stdexcept>
#include "date.h"

using namespace std;

int main() {
	try {
		Date date1;
		"2015-01-10" >> date1;
		cout << "Today: " << date1 << endl;
		
		string s = "2015-01-10";
		istringstream iss (s);
		Date date;
		iss >> date;
		cout << date << endl;
		
		Date date2;
		"20x5-02-19" >> date2;
		cout << date2;
	} catch (invalid_argument& e) {
		cerr << "Error: " << e.what() << endl;
	}
}

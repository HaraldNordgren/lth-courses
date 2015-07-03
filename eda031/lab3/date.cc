#include <iostream>
#include <ctime>  // time and localtime
#include <ostream>
#include <fstream>
#include <sstream>
#include <iomanip>
#include <string>
#include <stdexcept>
#include "date.h"

using namespace std;

int Date::daysPerMonth[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

Date::Date() {
	time_t timer = time(0); // time in seconds since 1970-01-01
	tm* locTime = localtime(&timer); // broken-down time
	year = 1900 + locTime->tm_year;
	month = 1 + locTime->tm_mon;
	day = locTime->tm_mday;
}

Date::Date(int y, int m, int d) {
	year = y;
	month = m;
	day = d;
}

int Date::getYear() const {
	return year;
}

int Date::getMonth() const {
	return month;
}

int Date::getDay() const {
	return day;
}

void Date::next() {
	day++;
	
	if (day > daysPerMonth[month-1]) {
		day = 1;
		month++;
	}
	
	if (month == 13) {
		month = 1;
		year++;
	}
}

ostream& operator<<(ostream& os, Date& date) {
	os << setw(4) << setfill('0') << date.getYear() << '-';
    os << setw(2) << setfill('0') << date.getMonth() << '-';
    os << setw(2) << setfill('0') << date.getDay();
	return os;
}

bool is_number(const std::string& s)
{
	    std::string::const_iterator it = s.begin();
		    while (it != s.end() && std::isdigit(*it)) ++it;
			    return !s.empty() && it == s.end();
}


void operator>>(string s, Date& date) {
	istringstream iss (s);
	iss >> date;
	if (iss.fail()) {
		throw invalid_argument("Invalid argument");
	}
}

istream& operator>>(istream& is, Date& date) {
	int daysPerMonth[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	string s;
	is >> s;
	
	if (s.length() != 10 || s[4] != '-' || s[7] != '-') {
		is.setstate(ios_base::failbit);
	} else {
		string a = s.substr(0,4);
        string b = s.substr(5,2);
        string c = s.substr(8,2);
		if (a.length() != 4 || b.length() != 2 || c.length() != 2) {
			is.setstate(ios_base::failbit);
		} else if(!is_number(a) || !is_number(b) || !is_number(c)) {	
			is.setstate(ios_base::failbit);
		} else {
			int y = stoi(a);
			int m = stoi(b);
			int d = stoi(c);
			if (m > 12 || d > daysPerMonth[m]) {
				is.setstate(ios_base::failbit);
			} else {
				date = Date(y,m,d);
			}
		}
	}
	
	//format error
	return is;
}

/*string to_string(Date date) {
	ostringstream oss;
	oss << date;
	return oss.str();
}*/

Date string_cast(string s) {
	istringstream iss (s);
	Date date;
	iss >> date;
	if (iss.fail()) {
		throw invalid_argument("Invalid argument");
	}
	return date;
}

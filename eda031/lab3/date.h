#ifndef DATE_H
#define DATE_H

using namespace std;

class Date {
public:
	Date();                    // today's date
	Date(int y, int m, int d); // yyyy-mm-dd
	int getYear() const;       // get the year
	int getMonth() const;      // get the month
	int getDay() const;        // get the day
	void next();               // advance to next day
private:
	int year;  // the year (four digits)
	int month; // the month (1-12)
	int day;   // the day (1-..)
	static int daysPerMonth[12]; // number of days in each month
	bool is_digits(const std::string &str);
};



ostream& operator<<(ostream& os, Date& date);
istream& operator>>(istream& is, Date& date);
void operator>>(string s, Date& date);
//string to_string(Date date);
Date string_cast(string s);

#endif

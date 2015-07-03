#include <iostream>
#include <cstring>
#include <string>
#include <sstream>

using namespace std;

class String {
public:
	String(const char* s) : chars(new char[strlen(s) + 1]) {
		strcpy(chars, s);
		cout << "Creating " << id() << endl;
	}
	
	~String() {
		cout << "Destroying " << id() << endl;
		delete[] chars;
	}
	
	/*** This is a correct assignment operator ***/
	String& operator=(const String& rhs) {
		cout << "Assigning " << rhs.id() << " to " << id() << endl;
		if (&rhs == this) {
			return *this;
		}
		delete[] chars;
		chars = new char[strlen(rhs.chars) + 1];
		strcpy(chars, rhs.chars);
		return *this;
	}
	
private:
	char* chars;
	string id() const {
		ostringstream oss;
		oss << chars << " (" << this << ")";
		return oss.str();
	}
};

int main() {
	String s1("stack object 1");
	String s2("stack object 2");
	s2 = s1;
}

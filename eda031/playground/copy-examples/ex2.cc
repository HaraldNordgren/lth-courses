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
	
	/*** This is the same as the synthesized copy constructor ***/
	String(const String& rhs) : chars(rhs.chars) {
		cout << "Copying " << rhs.id() << " to " << id() << endl;
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
	String s2 = s1;
}

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
	
private:
	char* chars;
	string id() const {
		ostringstream oss;
		oss << chars << " (" << this << ")";
		return oss.str();
	}
};

String global("global object");

int main() {
	String s1("stack object 1");
	String s2("stack object 2");
	String* pa = new String("heap object");
	
	cout << endl << "..." << endl << endl;
	
	delete pa;
}

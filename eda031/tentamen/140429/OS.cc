#include <iostream>
#include <cstddef>

using namespace std;

template <typename T> class OS {
	public:
		OS (ostream& stream, const char* ca = nullptr) : os(stream), d(ca) {}
		//OS (ostream&, const char*);
		OS& operator=(const T val) {
			os << val;
			if (d != nullptr)
				os << d;
			return *this;
		}
		OS& operator*() { return *this; }
		OS& operator++() { return *this; }
		OS& operator++(int) { return *this; }

	private:
		ostream& os;
		const char* d;
};

//OS::OS (ostream& stream, const char* ca = nullptr) : os(stream), d(ca) {}

/*OS& OS::operator=(const T val) {
	os << val;
	if (d != nullptr)
		os << d;
	return *this;
}*/

int main() {
	OS<int> o(cout);
	o = 1;
	cout << endl;
}

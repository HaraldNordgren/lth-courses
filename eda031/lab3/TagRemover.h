#include <istream>
#include <fstream>

using namespace std;

class TagRemover {
public:
	TagRemover(istream& instream);	
	void print(ostream& os);
private:
	istream* instream;
	string s;
};

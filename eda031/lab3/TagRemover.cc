#include <iostream>
#include <fstream>
#include <regex>
#include "TagRemover.h"

using namespace std; 

TagRemover::TagRemover(istream& is) {
	instream = &is;
	
	string file = "test.html";
	//*instream >> file;
	ifstream infile(file);
	
	string tmp;
	getline(infile,tmp);
	while (!infile.eof()) {
		s += tmp;
		s += '\n';
		getline(infile,tmp);
	}
	s.resize(s.length() - 1);
	
	auto start = s.find("<");
	auto end = s.find(">",start+1);
	while (start != string::npos && end != string::npos) {
		auto length = end - start + 1;
		
		if (s[start-1] == ' ' && s[end+1] == ' ') {
			s.erase(start, length+1);
		} else {
			s.erase(start, length);
		}
		start = s.find("<",start);
		end = s.find(">",start+1);
	}

	start = s.find("&");
	end = s.find(";",start+1);
	while (start != string::npos && end != string::npos) {
		auto length = end - start + 1;
		
		string substring = s.substr(start+1,length-2);
		s.erase(start, length);
		
		if (substring == "lt") {
			s.insert(start,"<");
		} else if (substring == "gt") {
			s.insert(start,">");
		} else if (substring == "nbsp") {
			s.insert(start," ");
		} else if (substring == "amp") {
			s.insert(start,"&");
		}
		start = s.find("&",start+1);
		end = s.find(";",start+1);
	}
}

void TagRemover::print(ostream& os) {
	os << s << endl;
}

#include <iostream>
#include <fstream>
#include <cstring>
#include "coding.h"

using namespace std;

unsigned char Coding::decode(unsigned char c) {
	return c - 1;
}

int main (int argc, char* argv[]) {
	if (argc != 2) {
		std::cout << "wrong number of args";
		return 1;
	}
	
	std::ifstream infile(argv[1]);
	
	string ss (argv[1]); 	
	string::size_type pos = ss.find(".enc");
	string s = ss.substr(0,pos) + ".dec";
	std::ofstream outfile(s);
	
	char ch;
	infile.get(ch);
	while (!infile.eof()) {
		//std::cout << Coding::decode(ch);
		outfile.put(Coding::decode(ch));
		infile.get(ch);
	}
}

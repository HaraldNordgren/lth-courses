#include <iostream>
#include <fstream>
#include <cstring>
#include <string>
#include "coding.h"

using namespace std;

unsigned char Coding::encode(unsigned char c) {
	return c + 1;
}

int main (int argc, char* argv[]) {
	if (argc != 2) {
		std::cout << "wrong number of args";
		return 1;
	}
	
	std::ifstream infile(argv[1]);
	
	string s (argv[1]);
	s += ".enc";
	//strcat(argv[1], ".enc");
	std::ofstream outfile(s);
	
	char ch;
	infile.get(ch);
	while (!infile.eof()) {
		//std::cout << Coding::encode(ch);
		outfile.put(Coding::encode(ch));
		infile.get(ch);
	}
}

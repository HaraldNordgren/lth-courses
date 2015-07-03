#include <string>
#include <vector>
#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>

using namespace std;

int main () {
	std::ifstream infile("/usr/share/dict/words");
	std::ofstream outfile("words.txt");
	
	string s;
	infile >> s;
	while (!infile.eof()) {
		for (unsigned int i = 0; i < s.length(); i++)
			s[i] = tolower(s[i]);
		outfile << s;
		
		if (s.length() < 3) {
			outfile << " 0";
		} else {
			const int t = s.length() - 2;
			vector<string> trigrams;
			
			outfile << " " << t;
			for (int i = 0; i < t; i++)
				trigrams.push_back(s.substr(i,3));
			
			sort (trigrams.begin(), trigrams.end());
			for (int i = 0; i < t; i++)
				outfile << " " << trigrams[i];
			
		}
		outfile << "\n";
		infile >> s;
	}
}

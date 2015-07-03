#include <iostream>
#include <vector>
#include <string>
#include <map>

using namespace std;

int main() {
	map<string,vector<int>> words;

	string line;
	int line_nbr = 1;
	while (getline(cin,line)) {
		size_t start_pos = 0;
		size_t end_pos = line.find(" ", start_pos);
		while (end_pos != string::npos) {
			size_t length = end_pos - start_pos;
			string word = line.substr(start_pos,length);
			words[word].push_back(line_nbr);

			start_pos = end_pos;
			end_pos = line.find(" ", start_pos);
		}
		++line_nbr;
	}
}

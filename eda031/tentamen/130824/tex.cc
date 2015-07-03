#include <string>
#include <iostream>
#include <fstream>

using namespace std;

int main() {
	string file_name;
	cin >> file_name;

	ifstream in(file_name);
	ofstream out(file_name + ".res");

	string text, line;
	while (getline(in,line)) {
		text += line;
		text += "\n";
	}

	unsigned line_counter = 0;
	auto start = text.find("\\section{");
	auto end = text.find(start, "}");
	while (start != string::npos) {
		text.erase(end, 1);
		text.erase(start, 9);
		text.insert(to_string(++line_counter) + " ", start);
		start = text.find(start, "\\section{");
		end = text.find(start, "}");
	}

	map<string,unsigned> labels;
	start = text.find("\\label{");
	end = text.find(start,"}");
	while (start != string::npos) {
		auto length = end - start;
		string label = text.substr(start + 7, length - 7);
		text.erase(start,length);
		if (labels.count(label) == 0) {
			labels.insert({label,

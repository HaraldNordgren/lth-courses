#include <string>
#include <iostream>
#include <random>
#include <ctime>

using namespace std;

void replace_vowels(string& s) {
	const static string vowels = "aeiouåäöAEIOUYÅÄÖ";
	const static string curses = "@$!*#&%";

	uniform_int_distribution<string::size_type> u(0, curses.size()-1);
	default_random_engine e(time(0));

	for (auto &c : s) {
	       auto vowel = vowels.find(c);
	       if (vowel != string::npos) {
		       c = curses[u(e)];
	       }
	}
}

int main() {
	string s = "abcdefghijklmnopqrstuvx";
	replace_vowels(s);
	cout << s << endl;
}

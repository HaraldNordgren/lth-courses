#include <string>
#include <vector>
#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>
#include "word.h"

using namespace std;

Word::Word(const string& w, const vector<string>& t) {
	word = w;
	trigrams = t;
}

string Word::get_word() const {
	return word;
}

unsigned int Word::get_matches(const vector<string>& t) const {
	int matches = 0;
	unsigned int j = 0;
	for (unsigned int i = 0; i < t.size(); i++) {
		while (j < trigrams.size() && trigrams[j] < t[i]) {
			j++;
		}
		
		if (j < trigrams.size() && t[i] == trigrams[j]) {
			matches++;
		}
	}
	return matches;
}

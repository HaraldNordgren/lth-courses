#ifndef DICTIONARY_H
#define DICTIONARY_H

#include <string>
#include <vector>
#include <unordered_set>
#include "word.h"

using namespace std;

class Dictionary {
public:
	const static int LENGTH = 25;
	
	unordered_set<string> words;
	vector<Word> full_words[LENGTH];
	
	Dictionary();
	bool contains(const string& word) const;
	
	vector<string> get_suggestions(const string& word) const;
	
	void add_trigram_suggestions(vector<string>& suggestions, const string& word) const;
	void compare_trigrams(unsigned int l, vector<string>& t, vector<string>& suggestions) const;
	
	void rank_suggestions(vector<string>& suggestions, const string& word) const;
	int d(const string& p, const string& q) const;
	
	void trim_suggestions(vector<string>& suggestions) const;
private:
};

#endif

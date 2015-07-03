#include <string>
#include <vector>
#include <fstream>
#include <iostream>
#include <algorithm>
#include <unordered_set>
#include "word.h"
#include "dictionary.h"

using namespace std;

Dictionary::Dictionary() {
	ifstream infile("words.txt");
	string s;
        getline(infile,s);
        while (!infile.eof()) {
		string::size_type pos = s.find(' ');
		vector<string> trigrams;
		int t = stoi(s.substr(pos));
		if (t) {
			string ss = s.substr(pos+1);
			string::size_type pos1 = ss.find(' ');
			for (int i = 0; i < t; i++) {
				string::size_type pvar = pos1 + 1 + 4*i;
				trigrams.push_back(ss.substr(pvar,3));
			}
			Word w (s.substr(0,pos),trigrams);
			//words.insert(s.substr(0,pos));
			full_words[pos].push_back(w);
			
		}
		getline(infile,s);
	}
}

bool Dictionary::contains(const string& word) const {	
	return words.find(word) != words.end();
}

vector<string> Dictionary::get_suggestions(const string& word) const {
	vector<string> suggestions;
	add_trigram_suggestions(suggestions, word);
	rank_suggestions(suggestions, word);
	trim_suggestions(suggestions);
	return suggestions;
}

void Dictionary::add_trigram_suggestions(vector<string>& suggestions, const string& word) const {
	unsigned int length = word.length();
		
	vector<string> t;
	for (unsigned int i = 0; i < length - 2; i++) {
		t.push_back(word.substr(i,3));
	}
	sort(t.begin(),t.end());
	

	if (length > LENGTH - 1)
		return;

	if (length > 3)
		compare_trigrams(length - 1, t, suggestions);
		compare_trigrams(length, t, suggestions);
	if (length < LENGTH - 2)
		compare_trigrams(length + 1, t, suggestions);
}

void Dictionary::compare_trigrams(unsigned int l, vector<string>& t, vector<string>& suggestions) const {
	for ( Word w : full_words[l] ) {
		unsigned int m = w.get_matches(t);
		if (m > 0 && m >= t.size() / 2) {
			suggestions.push_back(w.get_word());
		}
	}
}

void Dictionary::rank_suggestions(vector<string>& suggestions, const string& word) const {
	vector<pair<int, string>> distances;
	for (string s : suggestions) {
		int dist = d(s,word);
		distances.push_back(make_pair(dist, s));
	}

	sort(distances.begin(),distances.end());
	
	for (unsigned int i = 0; i < suggestions.size(); i++) {
		suggestions[i] = distances[i].second;
	}
}

int Dictionary::d(const string& p, const string& q) const {
	unsigned int d[26][26];
	for (unsigned int i = 0; i < p.length(); i++) {
	for (unsigned int j = 0; j < q.length(); j++) {
		if (j == 0) {
			d[i][j] = i;
		} else if (i == 0) {
			d[i][j] = j;
		} else {
			unsigned int nbr;
			if (p[i] == q[j]) {
				nbr = d[i-1][j-1];
			} else {
				nbr = d[i-1][j-1] + 2;
			}
			//nbr = d[i-1][j-1] + 2 * (p[i] != q[j]);
			
			d[i][j] = min( min( d[i-1][j]+1, d[i][j-1]+1), nbr);
		}
	}
	}
	
	return d[p.length()-1][q.length()-1];	
}

void Dictionary::trim_suggestions(vector<string>& suggestions) const {
	if (suggestions.size() > 5)
		suggestions.resize(5);
}

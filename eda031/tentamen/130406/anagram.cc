int main() {
	map<string,vector<string>> anagrams;

	string word;
	while (getline(in,word)) {
		vector<string> letters;
		for (unsigned i = 0; i != word.size(); i++) {
			letters.push_back(word[i]);
		}

		sort(letters.begin(),letters.end());
		string base_word;
		for (unsigned i = 0; i != letters.size(); i++) {
			base_word += letters[i];
		}


		auto it = map.find(base_word);


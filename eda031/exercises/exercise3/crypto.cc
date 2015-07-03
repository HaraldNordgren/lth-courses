#include "crypto.h"

#include <string>
#include <random>

using namespace std;

string Crypto::encrypt(const string& text, unsigned int key) {
	default_random_engine generator;
	generator.seed(key);
	
	string result = text;
	for (unsigned int i = 0; i < text.size(); i++) {
		result[i] = (text[i] + generator()) % 256;
	}
	return result;
}

string Crypto::decrypt(const string& crypt, unsigned int key) {
	default_random_engine generator;
	generator.seed(key);
	
	string result = crypt;
	for (unsigned int i = 0; i < crypt.size(); i++) {
		result[i] = (crypt[i] - generator()) % 256;
	}
	return result;
}

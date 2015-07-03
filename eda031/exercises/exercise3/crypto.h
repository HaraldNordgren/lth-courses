#ifndef CRYPTO_H
#define CRYPTO_H

#include <string>
#include <random>


class Crypto {
public:
	static std::string encrypt(const std::string& text, unsigned key);
	static std::string decrypt(const std::string& crypt, unsigned key);
};

#endif

#include "bitbuffer.h"

#include <iostream>

using namespace std;

BitBuffer::BitBuffer(std::ostream& out) : os(out) {}

void BitBuffer::put(bool b) {
	bits = (bits << 1) + b;
	if(++index > 7) {
		index = 0;
		put(bits);
	}
}

void BitBuffer::put(unsigned char ch) {
	os << ch << endl;
}

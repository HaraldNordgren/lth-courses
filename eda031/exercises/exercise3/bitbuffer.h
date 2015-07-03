#ifndef BIT_BUFFER_H
#define BIT_BUFFER_H

#include <iostream>

class BitBuffer {
public:
	BitBuffer(std::ostream& out);
	void put(bool b);
private:
	unsigned char bits = 0;
	size_t index = 0;
	std::ostream& os;
	void put(unsigned char ch);
};

#endif

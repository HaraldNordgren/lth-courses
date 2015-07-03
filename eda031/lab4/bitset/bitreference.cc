#include "bitreference.h"
#include "bitset.h"

BitReference& BitReference::operator=(bool b) {
	if (b) {
		*p_bits |= 1L << pos;
	} else {
		*p_bits &= ~ (1L << pos);
	}

	//
	// *** IMPLEMENT ***
	// This corresponds to the set() function in SimpleBitset.
	//
	return *this;
}

BitReference& BitReference::operator=(const BitReference& rhs) {
	if (rhs) {
		*p_bits |= 1L << pos;
	} else {
		*p_bits &= ~ (1L << pos);
	}

	//
	// *** IMPLEMENT ***
	// Same as operator=(bool), but the bit is picked from rhs
	//
	return *this;
}

BitReference::operator bool() const {
	return (*p_bits & (1L << pos)) != 0;	
}

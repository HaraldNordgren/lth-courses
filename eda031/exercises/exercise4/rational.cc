#include <ostream>
#include <fstream>
#include <sstream>
#include "rational.h"

using namespace std;

int binary_gcd(int u, int v);

void Rational::simplify() {
	int gcd = binary_gcd(numerator,denominator);
	numerator   /= gcd;
	denominator /= gcd;
}

Rational::Rational() {
	numerator   = 0;
	denominator = 1;
}

Rational::Rational(int a, int b) {
	int gcd = binary_gcd(a,b);
	numerator   = a / gcd;
	denominator = b / gcd;
}

Rational::Rational(int a) {
	numerator   = a;
	denominator = 1;
}

Rational& Rational::operator=(int a) {
	numerator   = a;
	denominator = 1;
	return *this;
}

Rational operator+(Rational& lhs, Rational& rhs) {
	Rational result = lhs;
	result += rhs;
	return result;
}

/*Rational operator+(int nbr) {
	numerator += nbr * denominator;
	simplify();

	return *this;
}*/

Rational& Rational::operator+=(Rational& rhs) {
	numerator = numerator * rhs.denominator + rhs.numerator * denominator;
        denominator = denominator * rhs.denominator;

	return *this;
}

ostream& operator<<(ostream& os, Rational& rhs) {
	os << rhs.getNumerator();
	if (rhs.getDenominator() != 1)
		os << "/" << rhs.getDenominator();
	return os;
}

ostream& operator<<(ostream& os, Rational rhs) {
	os << rhs.getNumerator();
	if (rhs.getDenominator() != 1)
		os << "/" << rhs.getDenominator();
	return os;
}

int Rational::getNumerator() {
	return numerator;
}

int Rational::getDenominator() {
	return denominator;
}

int binary_gcd(int u, int v) {
	int shl = 0;
		
	while ( u && v && u!=v ) {
		bool eu = !(u & 1);
		bool ev = !(v & 1);
		
		if ( eu && ev ) {
      			++shl;
        		u >>= 1;
          		v >>= 1;
      		} else if ( eu && !ev ) u >>= 1;
		else if ( !eu && ev ) v >>= 1;
		else if ( u>=v ) u = (u-v)>>1;
		else {
			int tmp = u;
        		u = (v-u)>>1;
          		v = tmp;
      		}
	}
	return !u? v<<shl : u<<shl;
}

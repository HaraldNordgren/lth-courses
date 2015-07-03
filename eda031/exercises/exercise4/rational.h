#include <ostream>
#include <fstream>
#include <sstream>

class Rational
{
	public:
		Rational();
		Rational(int a, int b);
		Rational(int a);
		Rational& operator=(int);
		Rational& operator+=(Rational&);
		Rational operator+(Rational&);
		//Rational& operator+(int);
		void simplify();
		int getNumerator();
		int getDenominator();
	private:
		int numerator;
		int denominator;
};

//Rational& operator+(Rational& lhs, Rational& rhs);
//Rational& operator+(Rational& lhs, int rhs);
std::ostream& operator<<(std::ostream& os, Rational& rhs);

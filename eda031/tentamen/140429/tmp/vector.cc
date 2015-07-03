#include "Vector.h"

int main() {
	Vector v1(10);		//Vector(size_t)
	Vector v2(v1);		//Vector(const Vector&)
	Vector v3 = v1;		//operator=
	
	v3 += v1;		//operator+=
	size_t s = v1.size();
	int nbr1 = v1[2];	//operator[]

	const Vector v4 = v1;
	int nbr2 = v4[2];	//operator[] const

	Vector v5 = v1 + v2;	//operator+
}

Vector operator+(Vector lhs, Vector rhs) {
	Vector result(lhs.size());
	for (size_t i = 0; i != result.size(); ++i) {
		result[i] = lhs[i] + rhs[i];
	}
	return result;
}

Vector& Vector::operator+=(Vector rhs) {
	for (size_t i = 0; i != n; ++i) {
		v[i] += rhs[i];
	}
	return this;
}

Vector operator+(Vector lhs, Vector rhs) {
	Vector result = lhs;
	result += rhs;
	return result;
}

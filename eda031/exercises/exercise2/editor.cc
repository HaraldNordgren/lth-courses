#include "editor.h"

#include <string>
#include <vector>
#include <iostream>

using namespace std;

string::size_type Editor::find_left_par(string::size_type pos) const {
	char right_par = text[pos], left_par;
	if (right_par == ')')
		left_par = '(';
	else if (right_par == ']')
		left_par = '[';
	else if (right_par == '}')
		left_par = '{';
	else
		return string::npos;

	vector<string::size_type> stack;
	string::size_type p = 0;
	while (p < text.size()) {
		if (text[p] == left_par) {
			stack.push_back(p);
		} else if (text[p] == right_par) {
			if (stack.empty()) {
				return string::npos;
			} else if (p == pos) {
				return stack.back();
			} else {
				stack.pop_back();
			}
		}
		++p;
	}
	return string::npos;
}

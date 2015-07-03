#include <iostream>
#include <random>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
	/*default_random_engine e;
	uniform_real_distribution<double> u(0,1);
	for (size_t i = 0; i != 10; ++i) {
		cout << u(e) << endl;
	}*/

	vector<size_t> path(50);
	iota(path.begin(),path.end(),0);
	random_shuffle(path.begin(),path.end());
	for (size_t i = 0; i != path.size(); ++i) {
		cout << path[i] << endl;
	}

	//cout << endl;
}

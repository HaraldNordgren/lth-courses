#include <string>
#include <iostream>
#include <vector>
#include <random>
#include <fstream>
#include <cmath>
#include <cstdlib>
#include <ctime>

using namespace std;

class Map {
	public:
		Map(size_t n);
		Map(const string& file_name);
		size_t size() const;
		pair<double, double> get_coords(size_t i) const;
		double get_dist(size_t i, size_t j) const;
	private:
		vector<pair<double,double>> towns;
		//double
};

Map::Map(size_t n) {
	uniform_real_distribution<double> u(0,1);
	default_random_engine e(time(0));

	for (size_t i = 0; i != n; ++i) {
		towns.push_back( {u(e),u(e)} );
	}
}

Map::Map(const string& file_name) {
	ifstream is(file_name);
	double x,y;
	while (is >> x >> y) {
		towns.push_back({x,y});
	}
}

size_t Map::size() const {
	return towns.size();
}

pair<double, double> Map::get_coords(size_t i) const {
	return towns[i];
}

double Map::get_dist(size_t i, size_t j) const {
	double x = towns[i].first  - towns[j].first;
	double y = towns[i].second - towns[j].second;
	return sqrt(x*x + y*y);
}

int main() {
	Map m(100);
	cout << m.get_coords(0).first << endl;
}

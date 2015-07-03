//#include "Window"
#include "Map.cc"
#include <algorithm>

class Tour {
	public:
		Tour(const Map& m);
		void create_random_tour();
		void closest_neighbor();
		//void draw(Window& w) const;
	private:
		Map& m;
		vector<int> tour;
};

void Tour::create_random_tour() {
	for (size_t i = 0; i != m.size(); ++i) {
		tour.push_back(i);
	}
	random_shuffle(tour.begin(),tour.end());
}

void Tour::create_closest_neighbor_tour() {
	vector<bool> visited(m.size());
	size_t current = 0;
	tour[0] = current;
	visited[0] = true;

	double length = 0;
	for (size_t i = 1; i != visited.size(); ++i) {
		double min = 1E10;
		size_t min_index = -1;
		for (size_t j = 1; j != visited.size(); ++j) {
			if (current != j && !visited[j]) {
				double dist = m.get_dist(current,j);
				if (dist < min) {
					min = dist;
					min_index = j;
				}
			}
		}
		//length += min;
		current = min_index;
		tour[i] = current;
		visited[min_index] = true;
	}
	//length += m.get_dist(current,0);
}

/*void Tour::draw(Window& w) {
	pair<double,double> t1 = towns[path[0]];
	pair<double,double> t2 = towns[path[1]];

	for (size_t i = 2; i != path.size(); ++i) {
		w->line(t1.first, t1.second, t2.first, t2.second);
		t1 = t2;
		t2 = towns[path[i]];
	}
	pair<double,double> start = towns[path[0]];
	w->line(t2.first, t2.second, start.first, start.second);
}*/

vector<int> Tour::create_optimal_tour() {
	iota(tour.begin(),tour.end(),0);

	double min = get_length();
	vector<int> opt = tour;
	while (next_permutation(tour.begin()+1,tour.end())) {
		double length = get_length();
		if (length < min) {
			length = min;
			opt = tour;
		}
	}
	return opt;
}

void Tour:switch_two() {
	uniform_int_distribution<size_t> u(0,tour.size()-1);
	default_random_engine e(time(0));
	
	double min_length = get_length();
	vector<int> best_tour = tour;

	for (unsigned i = 0; i != 2000; ++i) {
		size_t t1 = u(e);
		size_t t2 = u(e);
		while (t1 == t2)
			t2 = u(e);

		int tmp = tour[t1];
		tour[t2] = tour[t1];
		tour[t1] = tour[t2];

		double l = get_length();
		if (l < min_length) {
			min_length = l;
			best_tour = tour;
		} else {
			tour = best_tour;
		}
	}
	return best_tour;
}



double Tour::get_length() {
	double sum = 0;

	for (size_t i = 0; i != tour.size() - 1; ++i) {
		sum += m.get_dist(tour[i],tour[i+1]);
	}
	sum += m.get_dist(tour[tour.size()-1], tour[0]);
	sum += m.get_dist(tour[tour.size()-1], tour[0]);
	return sum;
}

void Tour::opt2() {
	for (size_t i = 0; i != tour.size()-3; ++i) {
		int t11 = tour[i];
		int t12 = tour[i+1];

		for (size_t j = i+2; j != tour.size()-1; ++j) {
			int t21 = tour[j];
			int t22 = tour[j+1];

			if (get_dist(t11,t21) + get_dist(t12,t22) < 
					get_dist(t11,t12) + get_dist(t21,t22)) {
				int tmp = 

int main() {
	Map map(50);
	Tour tour(map);
	tour.closes_neighbor();
}

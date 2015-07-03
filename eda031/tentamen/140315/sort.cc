template <typename T>
void my_sort(T* beg, T* end, bool comp = [](T& a, T& b) { return a < b; }) {
	if (beg == end) {
		return;
	}

	for (; beg != end - 1; ++beg) {
		T* min = beg;
		for (T* pos = beg + 1; pos != end; ++pos) {
			if (comp(*pos, *min)) {
				min = pos;
			}
		}
		std::swap(*beg, *min);
	}
}

auto point_comp = [] (Point& a, Point& b) {
	return a.get_x() < b.get_x() ||
		a.get_x() == b.get_x() && a.get_y() < b.get_y();
}
my_sort(points.begin(),points.end(),point_comp);

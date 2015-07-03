class IntSet {
	public:
		IntSet();
		bool contains(int nbr) const { return nbrs.count(nbr) == 1; }
		void insert(int nbr) { nbrs.insert(nbr); }
	private:
		set<int> nbrs;
};

class IntSet {
	struct Element {
		Element(int minimum, int maximum) : min(minimum), max(maximum) { }
		int min, max;
	};
	public:
		IntSet() {
			nbrs.push_front(Element(numeric_limits<int>::min(),numeric_limits<int>::min()));
			nbrs.push_back(Element(numeric_limits<int>::max(),numeric_limits<int>::max()));
		}	
		bool contains(int nbr) const {
			auto it = nbrs.begin();
			while (nbr < it->min) {
				++it;
			}
			return nrb <= it->max;
		}
		void insert(int nbr) {
			auto it = nbrs.begin();
			while (nbr < it->min) {
				++it;
			}

	private:
		list<Element> nbrs;
};

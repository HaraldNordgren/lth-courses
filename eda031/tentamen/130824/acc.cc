class Accumulator {
	public:
		Accumulator() : sum(0) {}
		Accumulator& operator+=(const int nbr) {
			sum += nbr;
			log.push(nbr);
			return *this;
		}
		void undo() {
			if (log.empty()) {
				return;
			}
			sum -= log.top();
			log.pop();
		}
		void commit() {
			log.clear();
		}
		void rollback() {
			while (!log.empty()) {
				sum -= log.top();
				log.pop();
			}
		}
	private:
		int sum;
		stack<int> log;
}

ostream& operator<< (ostream& os, const Accumulator& accum) {
	os << accum.sum;
	return os;
}

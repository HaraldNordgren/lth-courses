template <typename T>
class Ptr {
	public:
		Ptr(T* p) : curr(p) {}
		//T operator*() const { return *curr; }
		T& operator*() const { return *curr; }
		bool operator!=(const T* p) const { return curr != p; }
		Ptr& operator++() { return ++curr; }
	private:
		T* curr;
};

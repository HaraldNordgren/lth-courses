copy_list(const List& rhs) {
	Node* n = rhs.first;
	if (n == nullptr) {
		first = nullptr;
		return;
	}

	first = new Node(n->value, nullptr);

	Node* current = first;
	n = n->next;
	while (n != nullptr) {
		current->next = new Node(n->value, nullptr);
		current = current->next;
		n = n->next;
	}
	current->next = nullptr;
}

List(const List& rhs) {
	copy_list(rhs);
}

List& operator=(const List& rhs) {
	if (&rhs == this) {
		return *this;
	}

	Node* current = first;
	while (current != nullptr) {
		Node* next = current->next;
		delete current;
		current = next;
	}

	copy_list(rhs);
	return *this;
}

void f() {
	//Create l1 and l2;

	List l3(l1);
	l3 = l2;
}

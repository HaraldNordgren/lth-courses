#include <iostream>
#include "list.h"

List::List() {
	first = NULL;
}

List::~List() {
	/*while (first != NULL) {
		Node* tmp = first;
		first = first -> next;
		delete tmp;
	}*/
}

bool List::exists(int d) const {
	Node* a = first;
	while (a != NULL) {
		if (a -> value == d) {
			return true;
		}
		a = a -> next;
	}
	return false;
}

int List::size() const {
	int size = 0;
	Node* a = first;
	while (a != NULL) {
		size++;
		a = a -> next;
	}
	return size;
}

bool List::empty() const {
	return size() == 0;
}

void List::insertFirst(int d) {
	first = new Node(d, first);
}

bool List::test (int nbr, int d, List::DeleteFlag df) {
	if (df == List::DeleteFlag::EQUAL) {
		return nbr == d;	
	} else if (df == List::DeleteFlag::LESS) {
		return nbr < d;
	} else if (df == List::DeleteFlag::GREATER) {
		return nbr > d;
	} else {
		return false;
	}
}

void List::remove(int d, DeleteFlag df) {
	if (empty()) {
		return;
	}
	
	while (first != NULL && test(first -> value, d, df)) {
		Node* tmp = first;
		first = first -> next;
		delete tmp;
	}
	
	Node* a = first;
	if (a != NULL) {
		Node* b = a -> next;
		while (b != NULL) {		
			if (test(b -> value, d, df)) {
				a -> next = b -> next;
				delete b;
			} else {
				a = b;
			}
			b = a -> next;
		}		
	}
}

void List::print() const {
	std::cout << "[ ";
	Node* a = first;
	while (a != NULL) {
		std::cout << (a -> value) << " ";
		a = a -> next;
	}
	std::cout << "]";
}


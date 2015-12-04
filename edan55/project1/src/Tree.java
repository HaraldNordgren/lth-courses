import java.util.HashSet;

public class Tree {
	boolean[] nodes;
	HashSet<Integer> nodesLeft;
	
	Tree(int size) {
		nodes = new boolean[size];
		nodesLeft = new HashSet<Integer>();
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = false;
			nodesLeft.add(i);
		}
	}
	
	void mark(int n) {
		if (!nodes[n]) {
			nodes[n] = true;
			nodesLeft.remove(n);
			
			if (n > 0) {
				if (nodes[parent(n)]) {
					mark(sibling(n));
				} else if (nodes[sibling(n)]) {
					mark(parent(n));
				}
			}
			if (hasChildren(n)) {
				if(nodes[leftChild(n)]) {
					mark(rightChild(n));
				} else if (nodes[rightChild(n)]) {
					mark(leftChild(n));
				}
				
			}
		}
	}
	
	int parent (int n) {
		return (n-1) / 2;
	}
	
	int sibling(int n) {
		if (n%2 == 0) {
			return n-1;
		} else {
			return n+1;
		}
	}
	
	int leftChild(int n) {
		return n*2 + 1;
	}
	
	int rightChild(int n) {
		return n*2 + 2;
	}
	
	boolean hasChildren(int n) {
		return n < nodes.length / 2;
	}
	
	void print() {
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i]) {
				System.out.print(i + " ");
			}
		}
		System.out.println();
	}
	
	boolean done() {
		return nodesLeft.isEmpty();
	}
	
	HashSet<Integer> nodesLeft() {
		return nodesLeft;
	}
}
package org.jacop.lab1;

import org.jacop.constraints.*;
import org.jacop.core.*;
import org.jacop.search.*;

public class Burger {
	public static void main(String[] args) {
		long T1, T2, T;
		T1 = System.currentTimeMillis();
		
		send();
		
		T2 = System.currentTimeMillis();
		T = T2 - T1;
		System.out.println("\n\t***Execution time = " + T + " ms");
	}
	
	static void send() {
	
		Store store = new Store();
		
		IntVar patty = new IntVar(store, "Pa", 1, 5);
		IntVar bun = new IntVar(store, "B",  1, 5);
		IntVar cheese = new IntVar(store, "C",  1, 5);
		IntVar onions = new IntVar(store, "O",  1, 5);
		IntVar pickles = new IntVar(store, "Pi", 1, 5);
		IntVar lettuce = new IntVar(store, "L",  1, 5);
		IntVar ketchup = new IntVar(store, "K",  1, 5);
		IntVar tomatoes = new IntVar(store, "T",  1, 5);
		
		IntVar[] vars = new IntVar[] {patty, bun, cheese, onions, pickles, lettuce, ketchup, tomatoes};
		IntVar digits[] = {patty, bun, cheese, onions, pickles, lettuce, ketchup, tomatoes};
		
		store.impose(new Linear(store, vars, new int[] {50, 330, 310, 1, 260, 3, 160, 3}, "<", 3000));
		store.impose(new Linear(store, vars, new int[] {17, 9, 6, 2, 0, 0, 0, 0}, "<", 150));
		store.impose(new Linear(store, vars, new int[] {220, 260, 70, 10, 5, 4, 20, 9}, "<", 3000));
		store.impose(new XeqY(ketchup,lettuce));
		store.impose(new XeqY(pickles,tomatoes));
		
		store.impose(new Linear(store, vars, new int[] {25, 15, 20, 9, 3, 4, 2, 4}, ">", 312));
		
		//IntVar cost = new IntVar(store, "Pa", 1, 5);
		
		System.out.println("Number of variables: "+ store.size() +"\nNumber of constraints: " + store.numberConstraints());
		
		Search<IntVar> label = new DepthFirstSearch<IntVar>();
		SelectChoicePoint<IntVar> select = new SimpleSelect<IntVar>(digits, new SmallestDomain<IntVar>(),new IndomainMin<IntVar>());
		label.setSolutionListener(new PrintOutListener<IntVar>());
		label.getSolutionListener().searchAll(true);
		
		boolean Result = label.labeling(store, select);
		
		if (Result) {
			System.out.println("\n*** Yes");
			System.out.println("Solution : "+ java.util.Arrays.asList(digits));
		} else System.out.println("\n***No");
	}
}

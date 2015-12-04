package org.jacop.lab1;

import java.util.*;

import org.jacop.constraints.*;
import org.jacop.core.*;
import org.jacop.search.*;

public class main {

	public static void main(String[] args) {
		Store store = new Store();
		solve(store);
	}

	private static void solve(Store store) {
		
		int[] required = {5,7,7,10,16,18,12};
		int[] pay = {300, 500};
		
		IntVar[] fullTime = new IntVar[7];
		IntVar[] partTime = new IntVar[7];
		for (int i = 0; i < fullTime.length; i++) {
			fullTime[i] = new IntVar(store, "full" + i, 0, 20);
			partTime[i] = new IntVar(store, "part" + i, 0, 20);
		}
		
		IntVar[] totalEmployees = new IntVar[7];
		for (int i = 0; i < 7; i++) {
			totalEmployees[i] = new IntVar(store, "day" + i, required[i], 30);
			IntVar[] schedule = new IntVar[] {partTime[i], partTime[(i+6) % 7],
				fullTime[i], fullTime[(i+6) % 7], fullTime[(i+5) % 7],
				fullTime[(i+4) % 7], fullTime[(i+3) % 7]};
			store.impose(new Sum(schedule, totalEmployees[i]));
		}
		
		IntVar[] costPerDay = new IntVar[7];
		for (int i = 0; i < 7; i++) {
			costPerDay[i] = new IntVar(store, 0, 100000);
			store.impose(new SumWeight(new IntVar[] {partTime[i], fullTime[i]}, pay, costPerDay[i]));
		}


		IntVar cost = new IntVar(store,0,10000);
		store.impose(new Sum(costPerDay, cost));
		
		IntVar[] employees = new IntVar[14];
		for (int i = 0; i < 7; i++) {
			employees[i] = fullTime[i];
			employees[i+7] = partTime[i];
		}
		
		Search<IntVar> label = new DepthFirstSearch<IntVar>();
		SelectChoicePoint<IntVar> select = new SimpleSelect<IntVar>(employees, new SmallestDomain<IntVar>(), new IndomainMin<IntVar>());
		label.setSolutionListener(new PrintOutListener<IntVar>());
		label.getSolutionListener().searchAll(true);
		
		boolean result = label.labeling(store, select, cost);
		if(result){
			System.out.println("\nWe did it! \nSolution: " + Arrays.asList(totalEmployees));
		} else
			System.out.println(":(");
		System.out.println(store.consistency());
	}
}

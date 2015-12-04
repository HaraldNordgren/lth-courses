package org.jacop.lab2;

import java.util.*;

import org.jacop.constraints.*;
import org.jacop.core.*;
import org.jacop.search.*;

/**
 * Jobshop problem
 * 
 * @author Sane
 *
 */
public class main {
	
	public static void main(String[] args) {
		Store store = new Store();
		solve(store);
	}

	private static void solve(Store store) {
		int[][] durs = {{1, 3,  6,  7,  3, 6}, 
		                {8, 5, 10, 10, 10, 4},
                                {5, 4,  8,  9,  1, 7}, 
                                {5, 5,  5,  3,  8, 9}, 
                                {9, 3,  5,  4,  3, 1}, 
                                {3, 3,  9, 10,  4, 1}};
		
		IntVar[] endTimes = new IntVar[6];
		IntVar[][] jobs = new IntVar[6][6];
		for (int i = 0; i < jobs.length; i++) {
			jobs[i] = new IntVar[6];
			for(int j = 0; j < jobs[i].length; j++) {
				jobs[i][j] = new IntVar(store, "taskH_" + i + j, i, 100);
			}

			endTimes[i] = new IntVar(store, "End_Time_Job_H_" + i + 1, 1, 100);	
			for (int j = 0; j < jobs[i].length - 1; j++) {
				store.impose(new XplusClteqZ(jobs[i][j], durs[i][j], jobs[i][j+1]));
			}
			store.impose(new XplusCeqZ(jobs[i][jobs.length - 1], durs[i][jobs.length - 1], endTimes[i]));
		}

		IntVar totalTime = new IntVar(store, "Time_for_job_that_took_the_most_time", 1, 100);
		store.impose(new Max(endTimes, totalTime));
		
		
		IntVar[] machines = new IntVar[6];
		for(int i = 0; i < machines.length; i++) {
			machines[i] = new IntVar(store, "machine_" + i, 1, 1);
		}
		return machines;
		// Next we create machine constraints (only one task per machine 
		// at a given time and which machines should be used for every task)
		IntVar[] machineDuration0 = createMachineDurations(store, new int[] {3, 10, 9, 5, 3, 10});
		IntVar[] machineDuration1 = createMachineDurations(store, new int[] {6, 8, 1, 5, 3, 3});
		IntVar[] machineDuration2 = createMachineDurations(store, new int[] {1, 5, 5, 5, 9, 1});
		IntVar[] machineDuration3 = createMachineDurations(store, new int[] {7, 4, 4, 3, 1, 3});
		IntVar[] machineDuration4 = createMachineDurations(store, new int[] {6, 10, 7, 8, 5, 4});
		IntVar[] machineDuration5 = createMachineDurations(store, new int[] {3, 10, 8, 9, 4, 9});
	
		IntVar[][] machineDurs = new IntVar[6][6];
		for (int i = 0; i < 6; i++) {
			machineDurs[i] = 
		return new IntVar[] {new IntVar(store, durations[0],durations[0]), new IntVar(store, durations[1],durations[1]), 
				new IntVar(store, durations[2], durations[2]), new IntVar(store, durations[3], durations[3]), 
				new IntVar(store, durations[4], durations[4]), new IntVar(store, durations[5], durations[5])};

	
		IntVar[] durationList = new IntVar[machineDuration0.length * 6];
		System.arraycopy(machineDuration0, 0, durationList, 0, jobs.length);
		System.arraycopy(machineDuration1, 0, durationList, machineDuration0.length, machineDuration0.length);
		System.arraycopy(machineDuration2, 0, durationList, machineDuration0.length * 2, machineDuration1.length);
		System.arraycopy(machineDuration3, 0, durationList, machineDuration0.length * 3, machineDuration2.length);
		System.arraycopy(machineDuration4, 0, durationList, machineDuration0.length * 4, machineDuration3.length);
		System.arraycopy(machineDuration5, 0, durationList, machineDuration0.length * 5, machineDuration4.length);
		
		
		// Impose the Cumulatives!
		
		//		public Cumulative(IntVar[] starts,
		//        IntVar[] durations,
		//        IntVar[] resources,
		//        IntVar limit)
		//It creates a cumulative constraint.
		//Parameters:
		//starts - variables denoting starts of the tasks.
		//durations - variables denoting durations of the tasks.
		//resources - variables denoting resource usage of the tasks.
		//limit - the overall limit of resources which has to be used.
		



		IntVar limit = new IntVar(store, 1, 1);
		store.impose(new Cumulative(jobs[0], machineDuration0, machines, limit));
		store.impose(new Cumulative(jobs[1], machineDuration1, machines, limit));
		store.impose(new Cumulative(jobs[2], machineDuration2, machines, limit));
		store.impose(new Cumulative(jobs[3], machineDuration3, machines, limit));
		store.impose(new Cumulative(jobs[4], machineDuration4, machines, limit));
		store.impose(new Cumulative(jobs[5], machineDuration5, machines, limit));

		IntVar[] allTimes = new IntVar[jobs.length * 6];

		System.arraycopy(jobs[0], 0, allTimes, 0, jobs.length);
		System.arraycopy(jobs[1], 0, allTimes, jobs.length, jobs.length);
		System.arraycopy(jobs[2], 0, allTimes, jobs.length * 2, jobs.length);
		System.arraycopy(jobs[3], 0, allTimes, jobs.length * 3, jobs.length);
		System.arraycopy(jobs[4], 0, allTimes, jobs.length * 4, jobs.length);
		System.arraycopy(jobs[5], 0, allTimes, jobs.length * 5, jobs.length);
		
		IntVar[] machineDurations = new IntVar[36];
		
		System.arraycopy(machineDuration0, 0, machineDurations, 0, machineDuration0.length);
		System.arraycopy(machineDuration1, 0, machineDurations, machineDuration0.length, machineDuration0.length);
		System.arraycopy(machineDuration2, 0, machineDurations, machineDuration0.length * 2, machineDuration0.length);
		System.arraycopy(machineDuration3, 0, machineDurations, machineDuration0.length * 3, machineDuration0.length);
		System.arraycopy(machineDuration4, 0, machineDurations, machineDuration0.length * 4, machineDuration0.length);
		System.arraycopy(machineDuration5, 0, machineDurations, machineDuration0.length * 5, machineDuration0.length);
		
		ArrayList<IntVar> limitList = new ArrayList<IntVar>();
		for(int i = 0; i < 36; i++) {
			limitList.add(new IntVar(store,1,1));
		}
//		System.out.println(allTimes.length);
//		System.out.println(machines.length);
//		System.out.println(durationList.length);
//		System.out.println(limitList.size());
//		Diff2 diff = new Diff2(new ArrayList<IntVar>(Arrays.asList(allTimes)), new ArrayList<IntVar>(Arrays.asList(machineDurations)), new ArrayList<IntVar>(Arrays.asList(durationList)), limitList);
//		
//		
//		store.impose(diff);
		
		Search<IntVar> label = new DepthFirstSearch<IntVar>();
		SelectChoicePoint<IntVar> select = new SimpleSelect<IntVar>(allTimes, new SmallestDomain<IntVar>(), new IndomainMin<IntVar>());
		
		label.setSolutionListener(new PrintOutListener<IntVar>());
		label.getSolutionListener().searchAll(true);
		boolean result = label.labeling(store, select, totalTime);
		if(result){
			System.out.println("\nWe did it! \nSolution: " + Arrays.asList(jobs[0]));
		} else
			System.out.println(":(");
	}
	
	/**
	 * Creates an IntVar array for the durations argument to Cumulative
	 * @param store
	 * @param durations job durations
	 * @return
	 */
	private static IntVar[] createMachineDurations(Store store, int[] durations) {
		return new IntVar[] {new IntVar(store, durations[0],durations[0]), new IntVar(store, durations[1],durations[1]), 
				new IntVar(store, durations[2], durations[2]), new IntVar(store, durations[3], durations[3]), 
				new IntVar(store, durations[4], durations[4]), new IntVar(store, durations[5], durations[5])};
	}
}

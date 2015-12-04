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
public class main_works {
	
	public static void main(String[] args) {
		Store store = new Store();
		solve(store);
	}

	private static void solve(Store store) {
		// Create variables
		// Create tasks for all jobs (
		IntVar[] endTimes = new IntVar[6];
		IntVar[] job1 = createJobs(store, new int[] {1, 3, 6, 7, 3, 6}, endTimes, 0);
		IntVar[] job2 = createJobs(store, new int[] {8, 5, 10, 10, 10, 4}, endTimes, 1);
		IntVar[] job3 = createJobs(store, new int[] {5, 4, 8, 9, 1, 7}, endTimes, 2);
		IntVar[] job4 = createJobs(store, new int[] {5, 5, 5, 3, 8, 9}, endTimes, 3);
		IntVar[] job5 = createJobs(store, new int[] {9, 3, 5, 4, 3, 1}, endTimes, 4);
		IntVar[] job6 = createJobs(store, new int[] {3, 3, 9, 10, 4, 1}, endTimes, 5);
		
		// We are interested in knowing how long time it took for the slowest job to execute, since that equals the total time.
		IntVar totalTime = new IntVar(store, "Time_for_job_that_took_the_most_time", 1, 100);
		store.impose(new Max(endTimes, totalTime));
		
		
		IntVar[] machines = createMachines(store);
		// Next we create machine constraints (only one task per machine 
		// at a given time and which machines should be used for every task)
		IntVar[] machineDuration0 = createMachineDurations(store, new int[] {3, 10, 9, 5, 3, 10});
		IntVar[] machineDuration1 = createMachineDurations(store, new int[] {6, 8, 1, 5, 3, 3});
		IntVar[] machineDuration2 = createMachineDurations(store, new int[] {1, 5, 5, 5, 9, 1});
		IntVar[] machineDuration3 = createMachineDurations(store, new int[] {7, 4, 4, 3, 1, 3});
		IntVar[] machineDuration4 = createMachineDurations(store, new int[] {6, 10, 7, 8, 5, 4});
		IntVar[] machineDuration5 = createMachineDurations(store, new int[] {3, 10, 8, 9, 4, 9});
		
		IntVar[] durationList = new IntVar[machineDuration0.length * 6];
		System.arraycopy(machineDuration0, 0, durationList, 0, job1.length);
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
		store.impose(new Cumulative(job1, machineDuration0, machines, limit));
		store.impose(new Cumulative(job2, machineDuration1, machines, limit));
		store.impose(new Cumulative(job3, machineDuration2, machines, limit));
		store.impose(new Cumulative(job4, machineDuration3, machines, limit));
		store.impose(new Cumulative(job5, machineDuration4, machines, limit));
		store.impose(new Cumulative(job6, machineDuration5, machines, limit));

		IntVar[] allTimes = new IntVar[job1.length * 6];

		System.arraycopy(job1, 0, allTimes, 0, job1.length);
		System.arraycopy(job2, 0, allTimes, job1.length, job2.length);
		System.arraycopy(job3, 0, allTimes, job1.length * 2, job3.length);
		System.arraycopy(job4, 0, allTimes, job1.length * 3, job4.length);
		System.arraycopy(job5, 0, allTimes, job1.length * 4, job5.length);
		System.arraycopy(job6, 0, allTimes, job1.length * 5, job6.length);
		
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
			System.out.println("\nWe did it! \nSolution: " + Arrays.asList(job1));
		} else
			System.out.println(":(");
	}
	
	/**
	 * Creates the resources array to Cumulative
	 * @param store
	 * @return
	 */
	private static IntVar[] createMachines(Store store) {
		IntVar[] machines = new IntVar[6];
		for(int i = 0; i < machines.length; i++) {
			machines[i] = new IntVar(store, "machine_" + i, 1, 1);
		}
		return machines;
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
	
	/**
	 * Creates the starts argument to Cumulative.
	 * If it only was this easy to create jobs. 
	 * 
	 * @return jobs for a given machine
	 */
	private static IntVar[] createJobs(Store store, int[] durations, IntVar[] endTimes, int j)
	{
		// There are six tasks for every job. We create all tasks and make a constraint stating that the sum of the tasks 
		// (duration) to the left of a task must be less than or equal than the task to the right.
		IntVar[] job = new IntVar[6];
		for(int i = 0; i < job.length; i++) {
			// It's possible to optimize the lower bound (i), but since this is constraints programming it shouldn't be necessary?
			job[i] = new IntVar(store, "task_" + i + j, i, 100); //job 6, task 6 har duration 1, men 5 <= job[5] <= 10.
		}
		endTimes[j] = new IntVar(store, "End_Time_Job_" + j + 1, 1, 100);
		// X+C<=Z
		// task 1's duration must be less than the duration of task 2
		store.impose(new XplusClteqZ(job[0], durations[0], job[1]));
		// The duration of task 1 + the duration of task 2 must be less than the duration of task 3 and so forth.
		store.impose(new XplusClteqZ(job[1], durations[1], job[2]));
		store.impose(new XplusClteqZ(job[2], durations[2], job[3]));
		store.impose(new XplusClteqZ(job[3], durations[3], job[4]));
		store.impose(new XplusClteqZ(job[4], durations[4], job[5]));
		store.impose(new XplusCeqZ(job[5], durations[5], endTimes[j]));
		return job;
	}
}

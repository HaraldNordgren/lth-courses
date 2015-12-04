package org.jacop.lab1;

import java.util.ArrayList;

import org.jacop.constraints.*;
import org.jacop.constraints.knapsack.Knapsack;
import org.jacop.core.IntDomain;
import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.examples.fd.*;

public class Popsicle11 extends ExampleFD {

    public IntVar[] x;

    public int n = 2; // number of worker types
    public int m = 7; // number of days in a week

    public String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday",
    "Friday","Saturday", "Sunday"};

    public String[] types = {"Full-time", "Part-time"};

    public int[] workersReq   = {5, 7, 7, 10, 16, 18, 12};
    public int[] salaries  = {100, 150};  // per day per worker type

    /**
     *
     *  Imposes the model of the problem.
     *
     */
    @Override
	public void model() {

        store = new Store();
        
        IntVar[][] y = new IntVar[m][n];
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		y[i][j] = new IntVar(store, "y" + i + j, 0, 18);
        	}
        }
        
        for (int i = 0; i < m; i++) {
        	IntVar sum = new IntVar(store, "sum" + i);
        	store.impose(new Sum(new IntVar[] {y[i][0], y[i][1], y[(i-1)%m][0], y[(i-1)%m][1], y[(i-2)%m][0], y[(i-3)%m][0], y[(i-4)%m][0]}, sum));
        	store.impose(new XgteqC(sum, workersReq[i]));
        }
        
        System.out.println(store);
        
        // create x before using it in SumWeight
        x = new IntVar[m];
        for(int i = 0; i < m; i++) {
            x[i] = new IntVar(store, "x_" + i, 1, 5);
        }

        // Cost to minimize: x * workersReq
        cost = new IntVar(store, "cost", -360, 0);
        store.impose( new SumWeight(x, workersReq, cost) );

        vars = new ArrayList<IntVar>();
        for(IntVar v : x) 
            vars.add(v);


    } 

	public static void printLastSolution(Popsicle11 diet) {

		System.out.println("Cost: " + diet.cost.value());
        for(int i = 0; i < diet.m; i++) {
            System.out.println(diet.days[i] + ": " + diet.x[i].value());
        }
		
	}
    
    /**
     * It executes the program optimizing the diet.
     * @param args no argument is used.
     */
    public static void main(String args[]) {

      Popsicle11 diet = new Popsicle11();
      diet.model();

	  System.out.println("Searching for optimal using sum weight constraints");
      if (diet.searchOptimal()) {
          printLastSolution(diet);
      }  else {
          System.out.println("No solution.");
      }

    } 

}

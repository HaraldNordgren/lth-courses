/**
 *  Popsicle2.java 
 *  This file is part of JaCoP.
 *
 *  JaCoP is a Java Constraint Programming solver. 
 *	
 *	Copyright (C) 2008 Hakan Kjellerstrand and Radoslaw Szymanek
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *  
 *  Notwithstanding any other provision of this License, the copyright
 *  owners of this work supplement the terms of this License with terms
 *  prohibiting misrepresentation of the origin of this work and requiring
 *  that modified versions of this work be marked in reasonable ways as
 *  different from the original version. This supplement of the license
 *  terms is in accordance with Section 7 of GNU Affero General Public
 *  License version 3.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.jacop.lab1;

import java.util.ArrayList;

import org.jacop.constraints.*;
import org.jacop.constraints.knapsack.Knapsack;
import org.jacop.core.IntDomain;
import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.examples.fd.*;

/**
 *
 * It specifies a simple popsicle problem.
 * 
 * Problem from http://www.mcs.vuw.ac.nz/courses/OPRE251/2006T1/Labs/lab09.pdf
 * 
 *  My popsicle requires that all the food I eat come from one of the four .basic 
 *  food groups. (chocolate cake, ice cream, soft drink, and cheesecake). 
 *  Each (large) slice of chocolate cake costs 50c, 
 *  each scoop of chocolate ice cream costs 20c, 
 *  each bottle of cola costs 30c, 
 *  and each piece of pineapple cheesecake costs 80c. 
 *
 *  Each day, I must ingest at least 500 calories, 
 *  6 oz of chocolate, 
 *  10 oz of sugar, 
 *  and 8 oz of fat.
 *  The nutritional content per unit of each food is shown in the table below. 
 * 
 *  Formulate a linear programming model that can be used to satisfy my daily 
 *  nutritional requirement at minimum cost.

 *  Type of                        Calories   Chocolate    Sugar    Fat
 *  Food                                      (ounces)     (ounces) (ounces)
 *  Chocolate Cake (1 slice)       400           3            2      2
 *  Chocolate ice cream (1 scoop)  200           2            2      4
 *  Cola (1 bottle)                150           0            4      1
 *  Pineapple cheesecake (1 piece) 500           0            4      5
 *
 * """  
 *
 * Compare with my MiniZinc model:
 * http://www.hakank.org/minizinc/popsicle1.mzn
 *
 */

public class Popsicle2 extends ExampleFD {

    public IntVar[][] x;

    public int workerTypes = 2; // number of worker types (ingredients)
    public int weekdays = 7; // number of weekdays (food types)

    public String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
    "Saturday", "Sunday"};

    public String[] types = {"Fulltime", "Parttime"};

    public int[] salary   = {100, 150}; // in dollars
    public int[] limits  = {5, 7, 7, 10, 16, 18, 12};  // minimum required workers per day

                // Food: 0   1     2    3
    /*public int[][] matrix = {{400, 200, 150, 500},  // calories
                      		{  3,   2,   0,   0},  // chocolate
                      		{  2,   2,   4,   4},  // sugar
                      		{  2,   4,   1,   5}}; // fat*/

    /**
     *
     *  Imposes the model of the problem.
     *
     */
    @Override
	public void model() {

        store = new Store();

        // create x before using it in SumWeight
        x = new IntVar[weekdays][workerTypes];
        for(int i = 0; i < weekdays; i++) {
        	for(int j = 0; j < workerTypes; j++) {
            	x[i][j] = new IntVar(store, "x_" + i + j, 0, 18);
            }
        }

        IntVar[] workersPerDay = new IntVar[weekdays];
        IntVar[] perDaySalaries = new IntVar[weekdays];
        for(int i = 0; i < weekdays; i++) {
        	store.impose(new Sum(x[i], workersPerDay[i]));
            //workersPerDay[i] = new IntVar(store, "workers_" + i, 0, 36);
            store.impose(new SumWeight(x[i], salary, perDaySalaries[i]));
            store.impose(new XgteqC(workersPerDay[i], limits[i]));
        }

        // Cost to minimize: x * price
        cost = new IntVar(store, "cost", 0, 7*2*150*18);
        store.impose( new Sum(perDaySalaries, cost) );

        /*vars = new ArrayList<IntVar>();
        for(IntVar v : x) 
            vars.add(v);*/


    } 


    /**
    *
    *  Imposes the model of the problem.
    *
    */
	/*public void modelKnapsack() {

       store = new Store();

       // create x before using it in SumWeight
       x = new IntVar[m];
       for(int i = 0; i < m; i++) {
           x[i] = new IntVar(store, "x_" + i, 0, 10);
       }

       // Cost to minimize: x * price
       cost = new IntVar(store, "cost", 0, 120);

       for(int i = 0; i < n; i++) {
           IntVar minReq = new IntVar(store, "limit" + i, limits[i], IntDomain.MaxInt);
           if (i != 1)
        	   store.impose(new Knapsack(matrix[i], price, x, cost, minReq));
           else {
        	   // this category has some items with zero profit, violates knapsack conditions so it is not used.
               store.impose(new SumWeight(x, matrix[i], minReq));
           }
       }

       vars = new ArrayList<IntVar>();
       for(IntVar v : x) 
           vars.add(v);

   } */

	public static void printLastSolution(Popsicle2 popsicle) {

		System.out.println("Cost: " + popsicle.cost.value());
        for(int i = 0; i < popsicle.weekdays; i++) {
        	for(int j = 0; j < popsicle.workerTypes; j++) {
            	System.out.print(popsicle.x[i][j] + " " /*+ popsicle.x[i].value()*/);
            }
            System.out.println();
        }
		
	}
    
    /**
     * It executes the program optimizing the popsicle.
     * @param args no argument is used.
     */
    public static void main(String args[]) {

      Popsicle2 popsicle = new Popsicle2();
      popsicle.model();

	  System.out.println("Searching for optimal using sum weight constraints");
      if (popsicle.searchOptimal()) {
          printLastSolution(popsicle);
      }  else {
          System.out.println("No solution.");
      }

      
      /*popsicle = new Popsicle2();
      popsicle.modelKnapsack();

	  System.out.println("Searching for optimal using knapsack constraints");
      if (popsicle.searchOptimal()) {
    	  printLastSolution(popsicle);
      }  else {
          System.out.println("No solution.");
      }

      popsicle = new Popsicle2();
      popsicle.model();

	  System.out.println("Searching for all solutions using sum weight constraints");

      if (popsicle.searchAllAtOnce()) {
    	  printLastSolution(popsicle);
      }  else {
          System.out.println("No solution.");
      }

      
      popsicle = new Popsicle2();
      popsicle.modelKnapsack();

	  System.out.println("Searching for all solutions using knapsack constraints");
      if (popsicle.searchAllAtOnce()) {
    	  printLastSolution(popsicle);
      }  else {
          System.out.println("No solution.");
      }

      */
    } 

}

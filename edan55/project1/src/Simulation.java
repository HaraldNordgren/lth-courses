import java.util.Random;

public class Simulation {
	static final int ROUNDS = 1000;
	
	public static void main(String[] args) {
		for (int i = 2; i <= 10/*20*/; i++) {
			int size = (int) (Math.pow(2, i) - 1);
			
			System.out.println("Size " + size + ":");
			for (int j = 1; j <= 2; j++) {
				System.out.println("R" + j + ": " + runTest(j, size));
			}
			System.out.println();
		}
	}
	
	static String runTest(int nbr, int size) {
		int[] rounds = new int[ROUNDS];
		
		double sum = 0;
		for (int i = 0; i < rounds.length; i++) {
			rounds[i] = test(nbr, size);
			sum += rounds[i];
		}
		
		double mean = sum / rounds.length;
		double squareSum = 0;
		for (int i = 0; i < rounds.length; i++) {
			double diff = mean - rounds[i];
			squareSum += diff*diff;
		}
		
		double stddev = Math.sqrt(squareSum / rounds.length);
		return (mean + " ± " + stddev);
	}
	
	static int test(int nbr, int size) {
		if (nbr == 1) {
			return r1(size);
		} else if (nbr == 2) {
			return r2(size);
		} else {
			return r3(size);
		}
	}
	
	static int r1(int size) {
		Tree tree = new Tree(size);
		Random rand = new Random();
		
		int rounds = 0;
		
		while (!tree.done()) {
			tree.mark(rand.nextInt(size));
			rounds++;
		}
		return rounds;
	}
	
	static int r2(int size) {
		Tree tree = new Tree(size);
		int[] nbrs = knuth(size);
		int rounds = 0;
		while (!tree.done()) {
			tree.mark(nbrs[rounds]);
			rounds++;
		}
		return rounds;
	}
	
	static int r3(int size) {
		Tree tree = new Tree(size);
		Random rand = new Random();
		int rounds = 0;
		while (!tree.done()) {
			Object[] array = tree.nodesLeft().toArray();
			tree.mark((int) array[rand.nextInt(array.length)]);
			rounds++;
		}
		return rounds;
	}
	
	static int[] knuth(int size) {
		int[] nbrs = new int[size];
		for (int i=0; i < nbrs.length; i++) {
			nbrs[i] = i;
		}
		Random rand = new Random();
		for (int i=0; i < nbrs.length; i++) {
			int j = i + rand.nextInt(nbrs.length-i);
			int temp = nbrs[i];
			nbrs[i] = nbrs[j];
			nbrs[j] = temp;
		}
		return nbrs;
	}
}

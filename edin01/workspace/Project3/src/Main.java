import java.util.ArrayList;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int L = 13;
		int N_size = 193;
		int MOD_BASE = 2;
		int[] START_VALUES = new int[13];
		int[] coeff = {1,0,1,1,0,0,1,1,0,1,0,1,1};
		int constant = 1;
		CorrelationSeq guessSeq = new CorrelationSeq(MOD_BASE, coeff, constant);
		ArrayList<Integer> list = guessSeq.getNextValue();
		for(int i: list){
			System.out.print(i);
		}
		
	}

}

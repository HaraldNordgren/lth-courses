import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	final static int MOD_BASE = 5;
	
	static boolean[] WheresThatZeroElement = new boolean[4445];
	public static void main(String[] args) throws IOException {
		final int[] COEFF = {4,3,0,3};
		final int K = 1;
		final int[] START_VALUES = {0,0,0,0};
		final int[] zeroNumbers = {1,1,1,1};
		final int[] preZeroNumbers = {1,1,1,2};
		final int postZeroNumber = 4;
		
		final int MOD_BASE2 = 2;
		final int[] COEFF2 = {1,0,0,1};
		final int K2 = 1;
		final int[] START_VALUES2 = {0,0,0,0};
		final int[] zeroNumbers2 = {1,1,1,1};
		final int[] preZeroNumbers2 = {1,1,1,0};
		final int postZeroNumber2 = 0;
		PrintWriter writer = new PrintWriter("seq");
		DeBruijnSeq seq2 = new DeBruijnSeq(MOD_BASE2,COEFF2,K2,START_VALUES2, zeroNumbers2, preZeroNumbers2, postZeroNumber2);
		
		DeBruijnSeq seq = new DeBruijnSeq(MOD_BASE,COEFF,K,START_VALUES, zeroNumbers, preZeroNumbers, postZeroNumber);
		
		writer.print("000");
		int count = 0;
		for(int i=0; i < Math.pow(MOD_BASE2,4); i++){
			for(int k = 0; k < Math.pow(MOD_BASE,4); k++){
				int[] seq1Value = seq.getNextValue();
				int[] seq2Value = seq2.getNextValue();
				
				System.out.print(count + ":\t");
				for(int m = 0; m < 4; m++){
					System.out.print(seq1Value[m] + MOD_BASE * seq2Value[m]);
				}
				
				writer.print(seq1Value[0]+seq2Value[0]*MOD_BASE);
				System.out.println();
				count++;
			}
		}
		writer.close();
	}
}

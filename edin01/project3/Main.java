import java.util.ArrayList;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] N = {0,1,1,1,0,0,1,0,1,0,0,0,1,1,0,0,1,1,1,1,1,1,1,1,0,0,1,1,0,0,0,1,0,0,0,0,1,1,0,0,1,1,0,1,0,0,0,0,0,1,
				1,1,0,0,1,1,1,1,0,1,0,0,1,1,1,0,1,0,0,0,0,1,1,1,1,0,1,1,0,0,0,1,1,1,1,1,1,1,0,1,1,1,1,0,1,0,0,0,1,1,
				0,0,1,1,0,1,0,1,0,0,1,1,1,1,1,1,1,1,1,0,1,0,1,1,1,0,1,1,0,0,0,1,1,1,0,1,1,1,0,1,0,0,0,1,0,1,1,1,0,0,
				1,1,0,1,1,0,0,1,1,0,1,0,0,1,0,1,1,0,0,0,0,1,1,0,0,0,0,1,1,0,1,1,1,0,1,0,1,1,1,1,0,1,1};          //Detta är vår sekvens


		int[] alt13 = getKeyofLSFR13(N);
		int[] alt15 = getKeyofLSFR15(N);
		int[] alt17 = getKeyofLSFR17(N);


		boolean bestMatch = true;
		boolean worstMatch = true;
		for(int d = 0; d < N.length; d++){
			int output = alt13[d]+alt15[d]+alt17[d]; 
			if((output > 1 && N[d] == 0) || (output < 2 && N[d] == 1 )){
				bestMatch = false;
				//break;
			}
			else{
				worstMatch = false;
			}
		}
		if(bestMatch){
			System.out.println("EVERYTHING MATCHES!!!");
		}
		if(worstMatch){
			System.out.println("NOTHING MATCHES!?!");
		}
	}


	private static int[] getKeyofLSFR13(int[] N) {
		int L = 13;
		int MOD_BASE = 2;
		int[] coeff = {1,0,1,1,0,0,1,1,0,1,0,1,1}; //framlänges
		//int[] coeff = {1,1,0,1,0,1,1,0,0,1,1,0,1};   //baklänges
		int constant = 1;
		double breakpoint = 0.1;
		return getKeyofLSFR(N, L, MOD_BASE, coeff, constant, breakpoint);
	}
	private static int[] getKeyofLSFR15(int[] N) {
		int L = 15;
		int MOD_BASE = 2;
		int[] coeff = {1,0,1,0,1,1,0,0,1,1,0,1,0,1,0};  //framlänges
		//int[] coeff = {0,1,0,1,0,1,1,0,0,1,1,0,1,0,1};  //baklänges
		int constant = 1;
		double breakpoint = 0.1;
		return getKeyofLSFR(N, L, MOD_BASE, coeff, constant, breakpoint);
	}
	private static int[] getKeyofLSFR17(int[] N) {
		int L = 17;
		int MOD_BASE = 2;
		int[] coeff = {1,1,0,0,1,0,0,1,0,1,0,0,1,1,0,1,0}; //framlänges
		//int[] coeff = {0,1,0,1,1,0,0,1,0,1,0,0,1,0,0,1,1};   //baklänges
		int constant = 1;
		double breakpoint = 0.1;
		return getKeyofLSFR(N, L, MOD_BASE, coeff, constant, breakpoint);
	}

	private static int[] getKeyofLSFR(int[] N, int L, int MOD_BASE, int[] coeff,
			int constant, double breakpoint) {
		CorrelationSeq guessSeq = new CorrelationSeq(MOD_BASE, coeff, constant);
		ArrayList<Integer> list = guessSeq.getAllValue();

		double bestCorr = 0.5;
		int[] bestGuess = new int[N.length];
		for(int i = 0; i < list.size(); i++ ){
			double hemmingDist = 0;
			for(int m = 0; m < N.length; m++){
				if(N[m] != list.get((i+m)%list.size())){
					hemmingDist++;
				}
			}					
			double p = 1-(hemmingDist/N.length);
			if(Math.abs(0.5-p) > Math.abs(0.5-bestCorr)){
				bestCorr = p;
				int[] bestMatch = new int[N.length];
				for(int k = 0; k < N.length; k++){
					bestMatch[k] = list.get(((i+k)%list.size()));					
				}
				bestGuess = bestMatch;
			}
		}
		System.out.print(" Key for LNSR of length "+ L+": ");
		for(int a = 0; a < L; a++)
			System.out.print( bestGuess[a]);	
		System.out.println(" with a p* at "+bestCorr);
		return bestGuess;
	}


}

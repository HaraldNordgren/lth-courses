import java.util.ArrayList;

public class CorrelationSeq {
	private  int MOD_BASE;
	private  int[] COEFF;
	private  int K;
	ArrayList<Integer> list;
	private int[] START_VALUES;
	
	public CorrelationSeq(int MOD_BASE,int[] COEFF, int K){
		START_VALUES = new int[COEFF.length];
		START_VALUES[START_VALUES.length - 1] = 1;
		this.COEFF = COEFF;
		this.MOD_BASE = MOD_BASE; 
		this.K = K; 
		list = new ArrayList<Integer>();
		for (int i = 0; i < COEFF.length; i++) {
			list.add(START_VALUES[i]);
		}
	}
	
	int k = 0;
	public ArrayList<Integer> getNextValue(){
		for( int m = COEFF.length; m < Math.pow(2,COEFF.length); m++){
			int sum = K;
			
			int tmp = list.size()-COEFF.length;
			for (int i = tmp; i < list.size(); i++) {
				sum += COEFF[i-tmp]*list.get(i);
			}
			list.add(sum % MOD_BASE);
		}
		return list;

	}
}

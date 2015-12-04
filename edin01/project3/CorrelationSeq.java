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
	public ArrayList<Integer> getAllValue(){
		for( int m = COEFF.length; m < Math.pow(2,COEFF.length)-1; m++){
			int sum = K;
			
			int firstElementInRegister = list.size()-COEFF.length;
			for (int i = firstElementInRegister; i < list.size(); i++) {
				sum += COEFF[i-firstElementInRegister]*list.get(i);
			}
			list.add(sum % MOD_BASE);
		}
		return list;

	}
}

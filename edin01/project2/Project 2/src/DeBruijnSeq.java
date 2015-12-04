import java.util.LinkedList;


public class DeBruijnSeq {
	private  int MOD_BASE;
	private  int[] COEFF;
	private  int K;
	LinkedList<Integer> list;
	private int[] zeroNumbers;
	private int[] preZeroNumbers;
	private int postZeroNumber;
	
	public DeBruijnSeq(int MOD_BASE,int[] COEFF, int K, int[] START_VALUES, int[] zeroNumbers, int[] preZeroNumbers, int postZeroNumber){
		this.COEFF = COEFF;
		this.MOD_BASE = MOD_BASE; 
		this.K = K; 
		this.zeroNumbers = zeroNumbers;
		this.preZeroNumbers = preZeroNumbers;
		this.postZeroNumber = postZeroNumber;
		list = new LinkedList<Integer>();
		for (int i = 0; i < COEFF.length; i++) {
			list.add(START_VALUES[i]);
		}
	}
	
	public int[] getNextValue(){
		boolean zeroNbr = true;
		boolean preZeroNbr = true;
		int sum = K;
		for (int i = 0; i < COEFF.length; i++) {
			sum += COEFF[i]*list.get(i);
			if(list.get(i) != preZeroNumbers[i]){
				preZeroNbr = false;
			}
			if(list.get(i) != zeroNumbers[i]){
				zeroNbr = false;
			}
		}
		if(zeroNbr){
			list.addFirst(postZeroNumber);	
		}else if(preZeroNbr){		
			list.addFirst(zeroNumbers[0]);	
		}else{
			list.addFirst(sum % MOD_BASE);			
		}
		list.removeLast();
		int[] nbrs =  new int[4];
		for (int k = 0; k < 4; k++) {
			nbrs[k] = list.get(k);
		}
		return nbrs;
		
	}
}

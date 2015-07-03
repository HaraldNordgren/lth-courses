import java.math.BigInteger;

public class Numbers {
	public static BigInteger[] generatePrimes(int n) {
		BigInteger[] primes = new BigInteger[n];
		primes[0] = new BigInteger("2");
		int i = 1;
		
		BigInteger nbr = new BigInteger("3");
		while (i < n) {
			int j = 0;
			while (j != i && !nbr.divideAndRemainder(primes[j])[1].equals(BigInteger.ZERO)) {
				j++;
			}
			if (j == i) {
				primes[i] = nbr;
				i++;
			}
			nbr = nbr.add(BigInteger.ONE);
		}
		return primes;
	}
	
	public static BigInteger sqrt(BigInteger x) { 
		BigInteger right = x, left = BigInteger.ZERO, mid; 
		while(right.subtract(left).compareTo(BigInteger.ONE) > 0) { 
			mid = (right.add(left)).shiftRight(1);
			if(mid.multiply(mid).compareTo(x) > 0) 
				right = mid; 
			else 
				left = mid; 
		} 
		return left; 
	}
}

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

public class Main {

	//final static BigInteger N = new BigInteger("323");
	//final static BigInteger N = new BigInteger("307561");
	//final static BigInteger N = new BigInteger("392742364277");
	final static BigInteger N = new BigInteger("157348258477351482477463");
	
	final static int B = 1024;
	final static int L = B + 5;
	final static String INPUT = "./input";
	final static String OUTPUT = "./output";

	public static void main(String[] args) throws IOException {
		final BigInteger[] F = Numbers.generatePrimes(B);
		int[][] exponents = new int[L][B];
		BigInteger[] rs = new BigInteger[L];
		int n = 0;

		int k = 1;
		while (n < L) {
			int j = 1;
			BigInteger bigK = new BigInteger(String.valueOf(k));
			while (n < L && j <= k) {
				BigInteger r = Numbers.sqrt(bigK.multiply(N)).add(new BigInteger(String.valueOf(j)));
				BigInteger s = r.multiply(r).mod(N);
				
				int[] e = new int[B];
				for(int i = 0; i < F.length; i++) {
					BigInteger[] nbr = s.divideAndRemainder(F[i]);
					while (nbr[1].equals(BigInteger.ZERO)) {
						e[i]++;
						s = nbr[0];
						nbr = s.divideAndRemainder(F[i]);
					}
				}

				if (s.equals(BigInteger.ONE)) {
					int i = 0;
					while (i < n && !compare(exponents[i], e)) {
						i++;
					}
					if (i == n) {
						exponents[n] = e;
						rs[n] = r;
						n++;
					}
				}
				j++;
			}
			k++;
		}

		write(exponents);
		Runtime r = Runtime.getRuntime();
		r.exec("./GaussBin " + INPUT + " " + OUTPUT);
		int[][] xs = read("./output");
		for (int[] sol : xs){
			int[] y2 = new int[B];
			BigInteger x22 = BigInteger.ONE;
			for(int p = 0; p < y2.length; p++){
				if(sol[p] == 1){
					x22 = x22.multiply(rs[p]);
					for(int m = 0; m < y2.length; m++){
						/*if(M[p][m]){
							y2[m] += exponents[p][m];
						}*/
						if (exponents[p][m] > 0) {
							y2[m] += exponents[p][m];
						}
					}

				}
			}

			BigInteger y22 = BigInteger.ONE;
			for(int t = 0; t < y2.length; t++){
				if (y2[t] > 0) {
					y22 = y22.multiply(F[t].pow((y2[t])));
					//System.out.println(y22 + ": " + primes[t] + "^" + y2[t]);
				}
			}


			BigInteger y = Numbers.sqrt(y22).mod(N);
			BigInteger x = x22.mod(N);
			//System.out.println(y + " " + x + " (" + (y.subtract(x)).mod(N) + ")");
			/*for (BigInteger nbr : rs) {
					System.out.println(nbr);
				}*/

			BigInteger factor = ((y.subtract(x)).gcd(N));
			if (factor.compareTo(BigInteger.ONE) != 0) {
				System.out.println(factor);
			}
		}
	}

	static boolean compare(int[] a, int[] b) {
		int i = 0;
		while (i < a.length && (a[i] % 2 == b[i] % 2)) {
			i++;
		}
		return i == a.length;
	}

	static void write(int[][] exponents) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(INPUT);
		writer.println(exponents.length + " " + exponents[0].length);
		for (int[] a : exponents) {
			for (int b : a) {
				if(b % 2 == 1) {
					writer.print(1 + " ");
				} else {
					writer.print(0 + " ");
				}
			}
			writer.println();
		}
		writer.close();
	}

	static int[][] read(String file) throws IOException {
		FileReader fr = new FileReader(OUTPUT);
		BufferedReader br = new BufferedReader(fr);

		while(!br.ready()) {}
		int size = Integer.parseInt(br.readLine());
		int[][] xs = new int[size][L];

		for (int[] x : xs) {
			while(!br.ready()) {}
			String[] tmp = br.readLine().split(" ");
			for (int i = 0; i < tmp.length; i++) {
				if (tmp[i].equals("1")) {
					x[i] = 1;
				} else {
					x[i] = 0;
				}
			}
		}
		br.close();
		fr.close();
		return xs;
	}
}
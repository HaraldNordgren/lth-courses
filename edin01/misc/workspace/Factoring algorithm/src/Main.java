import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

public class Main {

	//final static BigInteger N = new BigInteger("187");
	//final static BigInteger N = new BigInteger("323");
	//final static BigInteger N = new BigInteger("307561");
	final static BigInteger N = new BigInteger("392742364277");
	//final static BigInteger N = new BigInteger("157348258477351482477463");
	
	final static int B = 35;
	final static int L = B + 2;
	final static String INPUT = "./input.txt";
	final static String OUTPUT = "./output.txt";

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		final BigInteger[] F = Numbers.generatePrimes(B);
		int[][] exponents = new int[L][B];
		BigInteger[] rs = new BigInteger[L];

		System.out.println("hej");
		prospectiveSolutions(F, exponents, rs);
		System.out.println("hej");
		write(exponents);
		Runtime.getRuntime().exec("./GaussBin " + INPUT + " " + OUTPUT);
		findFactors(F, exponents, rs, read(OUTPUT));
		System.out.println("Time: " + (System.currentTimeMillis() - startTime) + "ms (424 ms)");
	}

	private static void prospectiveSolutions(final BigInteger[] F, int[][] exponents,
			BigInteger[] rs) {
		int n = 0;
		int k = 1;
		while (n < L) {
			BigInteger bigK = new BigInteger(String.valueOf(k));
			BigInteger kN = bigK.multiply(N);
			int j = 1;
			while (n < L && j <= k) {
				BigInteger r = Numbers.sqrt(kN).add(new BigInteger(String.valueOf(j)));
				BigInteger s = (r.multiply(r)).mod(N);
				int[] e = new int[B];
				BigInteger a = BigInteger.ONE;
				for(int i = 0; i < F.length; i++) {
					/*int c = 0;
					BigInteger b = BigInteger.ONE;
					while (b.gcd(s).equals(b)) {
						b = b.multiply(F[i]);
						c++;
					}
					e[i] = c;*/
					
					BigInteger[] nbr = s.divideAndRemainder(F[i]);
					while (nbr[1].equals(BigInteger.ZERO)) {
						e[i]++;
						s = nbr[0];
						nbr = s.divideAndRemainder(F[i]);
					}
				}
				if (s.equals(BigInteger.ONE)) {
					int i = 0;
					while (i < n && originalRow(e, exponents[i])) {
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
	}

	private static void findFactors(final BigInteger[] F, int[][] exponents,
			BigInteger[] rs, int[][] xs) throws IOException {
		boolean done = false;
		while (!done) {
			for (int[] x : xs) {
				int[] y = new int[B];
				BigInteger xProduct = BigInteger.ONE;
				for(int i = 0; i < y.length; i++) {
					if(x[i] == 1) {
						xProduct = xProduct.multiply(rs[i]);
						for(int j = 0; j < y.length; j++) {
							y[j] += exponents[i][j];
						}
					}
				}

				BigInteger yProduct = BigInteger.ONE;
				for(int i = 0; i < y.length; i++){
					yProduct = yProduct.multiply(F[i].pow((y[i])));
				}

				BigInteger factor = Numbers.sqrt(yProduct).subtract(xProduct).gcd(N);
				if (!factor.equals(BigInteger.ONE) && !factor.equals(N)) {
					System.out.println(N + " = " + factor + " * " + N.divide(factor));
					done = true;
					break;
				}
			}
		}
	}

	static boolean originalRow(int[] a, int[] b) {
		int i = 0;
		while (i < a.length) {
			if ((a[i] + b[i]) % 2 != 0) {
				return true;
			}
			i++;
		}
		return false;
	}

	static void write(int[][] exponents) throws IOException {
		PrintWriter writer = new PrintWriter(INPUT);
		writer.println(exponents.length + " " + exponents[0].length);
		for (int[] e : exponents) {
			for (int nbr : e) {
				writer.print(nbr % 2 + " ");
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
			String[] line = br.readLine().split(" ");
			for (int i = 0; i < line.length; i++) {
				if (line[i].equals("1")) {
					x[i] = 1;
				} else if (line[i].equals("0")) {
					x[i] = 0;
				}
			}
		}
		br.close();
		fr.close();
		return xs;
	}
}
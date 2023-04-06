package Study.Week1.공통문제;

import java.math.BigInteger;
import java.util.Scanner;

public class BOJ1914_하노이탑 {

	static long N;

	public static BigInteger hanoi(Long n) {
		if (n == 1) {
			return BigInteger.valueOf(1);
		}
		return BigInteger.valueOf(2).multiply(hanoi(n - 1)).add(BigInteger.valueOf(1));
	}

	public static void printRoute(long n, int start, int end, int etc) {
		if (n == 1) {
			System.out.println(start + " " + end);
			return;
		}
		printRoute(n - 1, start, etc, end);
		System.out.println(start + " " + end);
		printRoute(n - 1, etc, end, start);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextLong();
		System.out.println(hanoi(N));
		if (N <= 20) {
			printRoute(N, 1, 3, 2);
		}
	}
}
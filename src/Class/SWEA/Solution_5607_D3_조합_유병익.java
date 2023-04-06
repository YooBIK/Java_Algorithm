package Class.SWEA;

import java.util.Scanner;

public class Solution_5607_D3_조합_유병익 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();
		for (int testCase = 1; testCase <= T; testCase++) {

			int N = scanner.nextInt();
			int R = scanner.nextInt();

			long[] factorial = new long[N + 1];
			factorial[0] = 1;
			for (int i = 1; i <= N; i++) {
				factorial[i] = (factorial[i - 1] * i) % 1234567891;
			}

			long bottom = (factorial[N - R] % 1234567891) * (factorial[R] % 1234567891) % 1234567891;
			bottom = find(bottom, 1234567889);

			long result = ((factorial[N] % 1234567891) * (bottom % 1234567891)) % 1234567891;
			System.out.println("#" + testCase + " " + result);

		}
		scanner.close();
	}

	private static long find(long number, int power) {

		if (power == 0) {
			return 1;
		}

		long half = find(number, power / 2) % 1234567891;

		if (power % 2 == 0) {
			return (half * half) % 1234567891;
		} else {
			return (((number * half) % 1234567891) * half) % 1234567891;
		}

	}

}

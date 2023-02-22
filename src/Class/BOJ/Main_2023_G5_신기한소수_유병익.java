package Class.BOJ;

import java.util.Scanner;

public class Main_2023_G5_신기한소수_유병익 {

	static int N;
	static int[] primeNumber = { 2, 3, 5, 7 };
	static StringBuilder builder = new StringBuilder();

	public static boolean checkPrime(int number) {
		if (number == 0 || number == 1)
			return false;

		int route = (int) Math.sqrt(number);
		for (int i = 2; i <= route; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static void recursive(int depth, int number) {
		if (checkPrime(number)) {
			if (depth == N) {
				builder.append(number).append("\n");
				return;
			}
			for (int i = 1; i <= 9; i++) {
				recursive(depth + 1, number * 10 + i);
			}
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();

		for (int i = 0; i < 4; i++) {
			recursive(1, primeNumber[i]);
		}
		System.out.println(builder);
	}

}

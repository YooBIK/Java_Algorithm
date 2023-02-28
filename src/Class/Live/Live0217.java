package Class.Live;

import java.util.Arrays;
import java.util.Scanner;

public class Live0217 {

	static int N;
	static int R;
	static int[] input;
	static int[] numbers;
	static boolean[] isUsed;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		R = scanner.nextInt();
		input = new int[N];
		isUsed = new boolean[N];
		numbers = new int[R];
		for (int i = 0; i < N; i++) {
			input[i] = scanner.nextInt();
		}

		System.out.println("===Permutation===");
		permutation(0);
		Arrays.fill(isUsed, false);

		System.out.println("===Combination===");
		combination(0, 0);
		Arrays.fill(isUsed, false);

		System.out.println("===SubSet===");
		subset(0);

		scanner.close();

	}

	private static void permutation(int depth) {
		if (depth == R) {
			System.out.println(Arrays.toString(numbers));
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!isUsed[i]) {
				isUsed[i] = true;
				numbers[depth] = input[i];
				permutation(depth + 1);
				isUsed[i] = false;
			}
		}
	}

	private static void combination(int depth, int startIndex) {
		if (depth == R) {
			System.out.println(Arrays.toString(numbers));
			return;
		}

		for (int i = startIndex; i < N; i++) {
			numbers[depth] = input[i];
			combination(depth + 1, i + 1);
		}
	}

	private static void subset(int depth) {
		if (depth == N) {
			for (int i = 0; i < N; i++) {
				if (isUsed[i]) {
					System.out.printf(" %d", input[i]);
				}
			}
			System.out.println();
			return;
		}

		isUsed[depth] = true;
		subset(depth + 1);

		isUsed[depth] = false;
		subset(depth + 1);
	}

}

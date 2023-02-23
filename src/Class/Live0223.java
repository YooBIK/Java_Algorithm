package Class;

import java.util.Arrays;
import java.util.Scanner;

public class Live0223 {

	static int N;
	static int R;
	static int[] numbers;
	static int[] selectedNumbers;
	static boolean[] isUsed;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		R = scanner.nextInt();
		numbers = new int[N];
		isUsed = new boolean[N];
		selectedNumbers = new int[R];
		for (int i = 0; i < N; i++) {
			numbers[i] = scanner.nextInt();
		}

//		System.out.println("========= 순열 =========");
//		permutation(0);

//		System.out.println("========= 조합 =========");
//		combination(0, 0);

		System.out.println("========= 부분 집합 =========");
		subset(0);
	}

	public static void permutation(int depth) {

		if (depth == R) {
			System.out.println(Arrays.toString(selectedNumbers));
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!isUsed[i]) {
				selectedNumbers[depth] = numbers[i];
				isUsed[i] = true;
				permutation(depth + 1);
				isUsed[i] = false;
			}
		}
	}

	public static void combination(int depth, int startIndex) {
		if (depth == R) {
			System.out.println(Arrays.toString(selectedNumbers));
			return;
		}

		for (int i = startIndex; i < N; i++) {
			selectedNumbers[depth] = numbers[i];
			combination(depth + 1, i + 1);
		}
	}

	public static void subset(int depth) {
		if (depth == N) {
			for (int i = 0; i < N; i++) {
				if (isUsed[i]) {
					System.out.print(numbers[i] + " ");
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

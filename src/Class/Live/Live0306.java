package Class.Live;

import java.util.Arrays;
import java.util.Scanner;

public class Live0306 {

	static int N, R;
	static int[] numbers;
	static int[] selectedNumbers;
	static boolean[] isSelected;

	static int[] parents;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		R = scanner.nextInt();

		numbers = new int[N];
		selectedNumbers = new int[R];
		isSelected = new boolean[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = scanner.nextInt();
		}

//		permutation(0);
//
//		combination(0, 0);
//
//		subset(0);

		do {
			System.out.println(Arrays.toString(numbers));
		} while (nextPermutation());

		int V = scanner.nextInt();
		parents = new int[V];
	}

	private static void permutation(int depth) {

		if (depth == R) {
			System.out.println(Arrays.toString(selectedNumbers));
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!isSelected[i]) {
				selectedNumbers[depth] = numbers[i];
				isSelected[i] = true;
				permutation(depth + 1);
				isSelected[i] = false;
			}
		}
	}

	private static void combination(int depth, int startIndex) {

		if (depth == R) {
			System.out.println(Arrays.toString(selectedNumbers));
			return;
		}

		for (int i = startIndex; i < N; i++) {
			selectedNumbers[depth] = numbers[i];
			combination(depth + 1, i + 1);
		}
	}

	private static void subset(int depth) {
		if (depth == N) {
			for (int i = 0; i < N; i++) {
				if (isSelected[i]) {
					System.out.print(numbers[i] + " ");
				}
			}
			System.out.println();
			return;
		}

		isSelected[depth] = true;
		subset(depth + 1);
		isSelected[depth] = false;
		subset(depth + 1);

	}

	private static boolean nextPermutation() {

		int i = N - 1;
		while (numbers[i - 1] >= numbers[i]) {

			i--;
			if (i == 0) {
				return false;
			}
		}

		int j = N - 1;
		while (true) {
			if (numbers[i - 1] < numbers[j]) {
				break;
			}
			j--;
		}

		int temp = numbers[i - 1];
		numbers[i - 1] = numbers[j];
		numbers[j] = temp;

		j = N - 1;
		while (i < j) {
			temp = numbers[i];
			numbers[i] = numbers[j];
			numbers[j] = temp;
			i++;
			j--;
		}
		return true;
	}

	private static int findRood(int vertex) {

		if (parents[vertex] == vertex) {
			return vertex;
		}

		return parents[vertex] = findRood(parents[vertex]);
	}

	private static void union(int first, int second) {
		int firstRoot = findRood(first);
		int secondRoot = findRood(second);
		if (firstRoot != secondRoot) {
			parents[firstRoot] = secondRoot;
		}
	}

}

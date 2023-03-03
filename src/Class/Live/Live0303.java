package Class.Live;

import java.util.Arrays;
import java.util.Scanner;

public class Live0303 {

	static int N, R;
	static int[] numbers;
	static boolean isUsed[];
	static int[] selectedNumbers;
	static int[] parents;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		R = scanner.nextInt();
		numbers = new int[N];
		selectedNumbers = new int[R];
		isUsed = new boolean[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = scanner.nextInt();
		}
//		System.out.println("======== 순 열 ========");
//		permutation(0);
//
		System.out.println("======== 조 합 ========");
		combination(0, 0);
//
//		System.out.println("======== 부분집합 ========");
//		subset(0);

		do {
			System.out.println(Arrays.toString(numbers));
		} while (nextPermutation());
		scanner.close();
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
				if (!isUsed[i]) {
					System.out.print(numbers[i] + " ");
				}
			}
			System.out.println();
			return;
		}

		isUsed[depth] = false;
		subset(depth + 1);
		isUsed[depth] = true;
		subset(depth + 1);
	}

	public static boolean nextPermutation() {
		int i = N - 1;

		while (i > 0 && numbers[i - 1] >= numbers[i]) {
			i--;
		}

		if (i == 0)
			return false;

		int j = N - 1;
		while (numbers[j] <= numbers[i - 1]) {
			j--;
		}

		swap(numbers, i - 1, j);

		j = N - 1;
		while (i < j) {
			swap(numbers, i++, j--);
		}
		return true;

	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	private static void makeSet() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}

	private static int findRoot(int vertex) {
		if (parents[vertex] == vertex) {
			return vertex;
		}

		return parents[vertex];
	}

	private static void union(int first, int second) {
		int firstRoot = findRoot(first);
		int secondRoot = findRoot(second);

		if (firstRoot != secondRoot) {
			parents[firstRoot] = secondRoot;
		}
	}

}

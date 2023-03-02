package Class.Live;

import java.util.Arrays;
import java.util.Scanner;

public class Live0302 {

	static int N, R;
	static int[] numbers;
	static boolean isUsed[];
	static int[] selectedNumbers;

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

//		do {
//			System.out.println(Arrays.toString(numbers));
//		} while (nextPermutation());
		scanner.close();
	}

	public static void permutation(int depth) {
		if (depth == R) {
			System.out.println(Arrays.toString(selectedNumbers));
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!isUsed[i]) {
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
		int n = N - 1;
		// 1.꼭대기 찾기
		int i = N - 1;
		while (i > 0 && numbers[i - 1] >= numbers[i]) {
			i--;
		}
		if (i == 0)
			return false;

		// 2.꼭대기 이전보다 큰 값 뒤에서부터 찾기
		int j = N - 1;
		while (numbers[i - 1] >= numbers[j]) {
			j--;
		}

		// 3.꼭대기 이전과 2에서 찾은 값 바꾸기
		swap(numbers, i - 1, j);

		// 꼭대기부터 마지막까지 정렬
		j = n;
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

}

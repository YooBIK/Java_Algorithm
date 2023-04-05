package Class.Live;

import java.util.Scanner;

public class Live0405_NP {

	static int[] numbers;
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		numbers = new int[N];

		for (int i = 0; i < N; i++) {
			numbers[i] = sc.nextInt();
		}
		sc.close();
	}

	private static boolean nextPermutation() {

		int i = N - 1;

		while (i > 0 && numbers[i - 1] >= numbers[i]) {
			i--;
		}

		if (i == 0) {
			return false;
		}

		int j = N - 1;

		while (numbers[j] <= numbers[i - 1]) {
			j--;
		}

		// 3.꼭대기 이전과 2에서 찾은 값 바꾸기
		swap(numbers, i - 1, j);

		// 꼭대기부터 마지막까지 정렬
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
}

package Class.Live;

import java.util.Arrays;
import java.util.Scanner;

public class Live0407_NP {

	static int[] numbers;
	static int N;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		numbers = new int[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = scanner.nextInt();
		}

		do {
			System.out.println(Arrays.toString(numbers));
		} while (nextPermutation());

	}

	private static boolean nextPermutation() {

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

		swap(i - 1, j);

		j = N - 1;

		while (i < j) {
			swap(i++, j--);
		}

		return true;
	}

	private static void swap(int i, int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}

}

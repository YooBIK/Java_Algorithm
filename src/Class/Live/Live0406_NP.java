package Class.Live;

import java.util.Arrays;
import java.util.Scanner;

public class Live0406_NP {

	static int[] numbers;
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		numbers = new int[N];

		for (int i = 0; i < N; i++) {
			numbers[i] = sc.nextInt();
		}

		do {
			System.out.println(Arrays.toString(numbers));
		} while (nextPermutation());
		sc.close();
	}

	private static boolean nextPermutation() {

		int i = N - 1;

		while (i > 0 && numbers[i - 1] >= numbers[i]) {
			i--;
		}

		if (i == 0)
			return false;

		int j = N - 1;

		while (numbers[i - 1] >= numbers[j]) {
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

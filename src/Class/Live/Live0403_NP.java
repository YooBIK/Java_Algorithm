package Class.Live;

import java.lang.Character.Subset;
import java.util.Arrays;
import java.util.Scanner;

public class Live0403_NP {

	static int[] parents;

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

}

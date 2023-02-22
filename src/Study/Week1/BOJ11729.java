package Study.Week1;

import java.util.Scanner;

public class BOJ11729 {

	static Scanner scanner = new Scanner(System.in);
	static StringBuilder stringBuilder = new StringBuilder();
	static int N;
	static int count = 0;

	public static void hanoi(int plates, int start, int end, int temp) {
		if (plates == 1) {
			stringBuilder.append(start).append(" ").append(end).append("\n");
			count++;
			return;
		}

		hanoi(plates - 1, start, temp, end);
		stringBuilder.append(start).append(" ").append(end).append("\n");
		count++;
		hanoi(plates - 1, temp, end, start);
	}

	public static void main(String[] args) {
		N = scanner.nextInt();
		hanoi(N, 1, 3, 2);
		System.out.println(count);
		System.out.println(stringBuilder);
	}
}

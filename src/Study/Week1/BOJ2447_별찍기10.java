package Study.Week1;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ2447_별찍기10 {
	static char[][] map;

	private static void blank(int row, int col, int depth) {
		for (int i = row; i < row + depth; i++) {
			for (int j = col; j < col + depth; j++) {
				map[i][j] = ' ';
			}
		}
	}

	private static void star(int row, int col, int depth) {
		if (depth == 1) {
			return;
		}

		for (int i = row; i < row + depth; i += depth / 3) {
			for (int j = col; j < col + depth; j += depth / 3) {
				if (i == (row + (depth / 3)) && j == (col + (depth / 3))) {
					blank(i, j, depth / 3);
				} else {
					star(i, j, depth / 3);
				}
			}

		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int size = scanner.nextInt();

		map = new char[size][size];
		for (int i = 0; i < size; i++) {
			Arrays.fill(map[i], '*');
		}
		star(0, 0, size);
		StringBuilder builder = new StringBuilder();
		for (char[] charArray : map) {
			for (char c : charArray) {
				builder.append(c);
			}
			builder.append("\n");
		}
		System.out.println(builder);
	}
}

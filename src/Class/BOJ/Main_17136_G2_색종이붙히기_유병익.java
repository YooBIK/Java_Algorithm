package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17136_G2_색종이붙히기_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int[][] map;
	static int[] count = { 0, 5, 5, 5, 5, 5 };

	static int paperCount = 0;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		map = new int[10][10];

		for (int i = 0; i < 10; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				if (map[i][j] == 1)
					paperCount++;
			}
		}
		dfs();

		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}

	}

	private static void dfs() {

		if (paperCount == 0) {
			answer = Math.min(answer, getUsePapers());
			return;
		}

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (map[i][j] == 1) {
					for (int size = 5; size >= 1; size--) {
						if (count[size] > 0 && isPossible(i, j, size)) {
							fill(i, j, size, 0);
							paperCount -= size * size;
							count[size]--;
							dfs();
							fill(i, j, size, 1);
							paperCount += size * size;
							count[size]++;
						}
					}
					return;
				}
			}
		}

	}

	private static int getUsePapers() {
		int result = 0;
		for (int i = 1; i <= 5; i++) {
			result += (5 - count[i]);
		}
		return result;
	}

	private static boolean isPossible(int row, int col, int size) {
		if (row + size - 1 > 9 || col + size - 1 > 9)
			return false;
		for (int i = row; i < row + size; i++) {
			for (int j = col; j < col + size; j++) {
				if (map[i][j] == 0)
					return false;
			}
		}
		return true;
	}

	private static void fill(int row, int col, int size, int value) {
		for (int i = row; i < row + size; i++) {
			for (int j = col; j < col + size; j++) {
				map[i][j] = value;
			}
		}
	}

}

package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2630 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int N;
	static int[][] map;

	static int whiteCount = 0;
	static int blueCount = 0;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bufferedReader.readLine());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}

		dfs(0, 0, N);
		System.out.println(whiteCount);
		System.out.println(blueCount);

	}

	private static void dfs(int row, int col, int size) {

		if (isPossible(row, col, size)) {
			if (map[row][col] == 0) {
				whiteCount++;
			} else {
				blueCount++;
			}
			return;
		}

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				dfs(row + i * (size / 2), col + j * (size / 2), size / 2);
			}
		}

	}

	private static boolean isPossible(int row, int col, int size) {
		int color = map[row][col];
		for (int i = row; i < row + size; i++) {
			for (int j = col; j < col + size; j++) {
				if (map[i][j] != color)
					return false;
			}
		}
		return true;
	}

}

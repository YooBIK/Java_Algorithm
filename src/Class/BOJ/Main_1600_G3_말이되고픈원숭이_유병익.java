package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1600_G3_말이되고픈원숭이_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int[][] map;
	static int[][][] check;

	static int[] dirRow = { 0, 0, -1, 1 };
	static int[] dirCol = { -1, 1, 0, 0 };

	static int[] dirHorseRow = { -1, -2, -2, -1, 1, 2, 2, 1 };
	static int[] dirHorseCol = { -2, -1, 1, 2, 2, 1, -1, -2 };

	static int R, C;
	static int K;

	public static void main(String[] args) throws IOException {
		K = Integer.parseInt(bufferedReader.readLine());
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		C = Integer.parseInt(stringTokenizer.nextToken());
		R = Integer.parseInt(stringTokenizer.nextToken());
		map = new int[R][C];
		check = new int[R][C][K + 1];
		for (int i = 0; i < R; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}

		bfs(0, 0);

		int result = Integer.MAX_VALUE;
		for (int i = 0; i <= K; i++) {
			if (check[R - 1][C - 1][i] != 0) {
				result = Math.min(result, check[R - 1][C - 1][i]);
			}
		}

		if (result == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(result - 1);
		}

	}

	private static void bfs(int i, int j) {
		Queue<int[]> queue = new ArrayDeque<>();
		check[i][j][0] = 1;
		queue.offer(new int[] { i, j, 0 });

		while (!queue.isEmpty()) {

			int[] curPosition = queue.poll();
			int curRow = curPosition[0];
			int curCol = curPosition[1];
			int useK = curPosition[2];

			for (int dir = 0; dir < 4; dir++) {
				int newRow = curRow + dirRow[dir];
				int newCol = curCol + dirCol[dir];
				if (boundaryCheck(newRow, newCol)) {
					if (check[newRow][newCol][useK] == 0) {
						check[newRow][newCol][useK] = check[curRow][curCol][useK] + 1;
						queue.offer(new int[] { newRow, newCol, useK });
					} else {
						if (check[newRow][newCol][useK] > check[curRow][curCol][useK] + 1) {
							check[newRow][newCol][useK] = check[curRow][curCol][useK] + 1;
							queue.offer(new int[] { newRow, newCol, useK });
						}
					}
				}
			}

			if (useK < K) {
				for (int dir = 0; dir < 8; dir++) {
					int newRow = curRow + dirHorseRow[dir];
					int newCol = curCol + dirHorseCol[dir];
					if (boundaryCheck(newRow, newCol)) {
						if (check[newRow][newCol][useK + 1] == 0) {
							check[newRow][newCol][useK + 1] = check[curRow][curCol][useK] + 1;
							queue.offer(new int[] { newRow, newCol, useK + 1 });
						} else {
							if (check[newRow][newCol][useK + 1] > check[curRow][curCol][useK] + 1) {
								check[newRow][newCol][useK + 1] = check[curRow][curCol][useK] + 1;
								queue.offer(new int[] { newRow, newCol, useK + 1 });
							}
						}
					}
				}
			}
		}
	}

	private static boolean boundaryCheck(int newRow, int newCol) {
		return newRow >= 0 && newRow < R && newCol >= 0 && newCol < C && map[newRow][newCol] == 0;
	}

}

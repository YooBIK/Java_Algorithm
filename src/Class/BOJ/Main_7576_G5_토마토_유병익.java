package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main_7576_G5_토마토_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int R, C;
	static int[] dirRow = { 0, 0, -1, 1 };
	static int[] dirCol = { -1, 1, 0, 0 };

	static int[][] map;

	public static void main(String[] args) throws IOException {

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		C = Integer.parseInt(stringTokenizer.nextToken());
		R = Integer.parseInt(stringTokenizer.nextToken());

		map = new int[R][C];
		List<int[]> startPositions = new ArrayList<>();
		for (int i = 0; i < R; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < C; j++) {
				int curValue = Integer.parseInt(stringTokenizer.nextToken());
				if (curValue == 1) {
					startPositions.add(new int[] { i, j });
				}
				map[i][j] = curValue;
			}
		}
		bfs(startPositions);
		System.out.println(getResult());
	}

	private static int getResult() {
		int maxValue = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 0) {
					return -1;
				}

				maxValue = Math.max(maxValue, map[i][j]);
			}
		}
		return maxValue - 1;
	}

	private static void bfs(List<int[]> startPositions) {
		Deque<int[]> queue = new ArrayDeque<>();
		for (int i = 0; i < startPositions.size(); i++) {
			int[] curPosition = startPositions.get(i);
			queue.offer(curPosition);
		}

		while (!queue.isEmpty()) {
			int[] curPosition = queue.poll();
			int curRow = curPosition[0];
			int curCol = curPosition[1];

			for (int i = 0; i < 4; i++) {
				int newRow = curRow + dirRow[i];
				int newCol = curCol + dirCol[i];
				if (check(newRow, newCol)) {
					map[newRow][newCol] = map[curRow][curCol] + 1;
					queue.offer(new int[] { newRow, newCol });
				}
			}
		}
	}

	private static boolean check(int row, int col) {
		return row >= 0 && row < R && col >= 0 && col < C && map[row][col] == 0;
	}

}

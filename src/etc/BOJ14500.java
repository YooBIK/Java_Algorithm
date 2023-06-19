package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14500 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int R, C;
	static int[][] map;

	static int[] dirRow = { 0, 1, 0, -1 };
	static int[] dirCol = { 1, 0, -1, 0 };

	static int answer = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		R = Integer.parseInt(stringTokenizer.nextToken());
		C = Integer.parseInt(stringTokenizer.nextToken());
		map = new int[R][C];

		for (int i = 0; i < R; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		getResult();
		System.out.println(answer);
	}

	private static void getResult() {
		boolean[][] visit = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				visit[i][j] = true;
				search(i, j, 1, map[i][j], visit);
				visit[i][j] = false;
				searchㅗ(i, j);
			}
		}
	}

	private static void searchㅗ(int curRow, int curCol) {

		for (int i = 0; i < 4; i++) {
			int curDirection = i;
			int sum = map[curRow][curCol];
			boolean isPossible = true;
			for (int j = 0; j < 3; j++) {
				int nextDirection = curDirection + j;
				int newRow = curRow + dirRow[nextDirection % 4];
				int newCol = curCol + dirCol[nextDirection % 4];
				if (!boundaryCheck(newRow, newCol)) {
					isPossible = false;
					break;
				} else {
					sum += map[newRow][newCol];
				}
			}
			if (isPossible) {
				answer = Math.max(answer, sum);
			}
		}
	}

	private static void search(int curRow, int curCol, int depth, int curValue, boolean[][] visit) {
		if (depth == 4) {
			answer = Math.max(answer, curValue);
			return;
		}

		for (int i = 0; i < 4; i++) {
			int newRow = curRow + dirRow[i];
			int newCol = curCol + dirCol[i];
			if (boundaryCheck(newRow, newCol) && !visit[newRow][newCol]) {
				visit[newRow][newCol] = true;
				search(newRow, newCol, depth + 1, curValue + map[newRow][newCol], visit);
				visit[newRow][newCol] = false;
			}
		}
	}

	private static boolean boundaryCheck(int row, int col) {
		return row >= 0 && row < R && col >= 0 && col < C;
	}

}

package Study.Week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17144 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int R, C, T;
	static int[][] map;

	static int[] dirRow = { 0, 0, 1, -1 };
	static int[] dirCol = { 1, -1, 0, 0 };

	static List<int[]> acPos = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		R = Integer.parseInt(stringTokenizer.nextToken());
		C = Integer.parseInt(stringTokenizer.nextToken());
		T = Integer.parseInt(stringTokenizer.nextToken());

		map = new int[R][C];

		for (int i = 0; i < R; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());

				if (map[i][j] == -1) {
					acPos.add(new int[] { i, j });
				}
			}
		}

		int curTime = 0;
		while (curTime < T) {
			int[][] temp = new int[R][C];
			for (int i = 0; i < acPos.size(); i++) {
				temp[acPos.get(i)[0]][acPos.get(i)[1]] = -1;
			}

			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (map[i][j] > 0) {
						int count = 0;
						for (int dir = 0; dir < 4; dir++) {
							int newRow = i + dirRow[dir];
							int newCol = j + dirCol[dir];
							if (boundaryCheck(newRow, newCol)) {
								count++;
							}
						}
						int diffusion = map[i][j] / 5;
						map[i][j] -= diffusion * count;

						for (int dir = 0; dir < 4; dir++) {
							int newRow = i + dirRow[dir];
							int newCol = j + dirCol[dir];
							if (boundaryCheck(newRow, newCol)) {
								temp[newRow][newCol] += diffusion;
							}
						}
					}
				}
			}

			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (temp[i][j] != -1) {
						temp[i][j] = map[i][j] + temp[i][j];
					}
				}
				map[i] = temp[i];
			}
			rotateDown();
			rotateTop();
			curTime++;

		}
		System.out.println(getResult());

	}

	private static int getResult() {
		int result = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0) {
					result += map[i][j];
				}
			}
		}
		return result;
	}

	private static void rotateTop() {

		for (int i = acPos.get(0)[0] - 1; i > 0; i--) {
			map[i][0] = map[i - 1][0];
		}

		for (int i = 0; i < C - 1; i++) {
			map[0][i] = map[0][i + 1];
		}

		for (int i = 0; i < acPos.get(0)[0]; i++) {
			map[i][C - 1] = map[i + 1][C - 1];
		}

		for (int i = C - 1; i > 1; i--) {
			map[acPos.get(0)[0]][i] = map[acPos.get(0)[0]][i - 1];
		}
		map[acPos.get(0)[0]][1] = 0;
	}

	private static void rotateDown() {

		for (int i = acPos.get(1)[0] + 1; i < R - 1; i++) {
			map[i][0] = map[i + 1][0];
		}

		for (int i = 0; i < C - 1; i++) {
			map[R - 1][i] = map[R - 1][i + 1];
		}

		for (int i = R - 1; i > acPos.get(1)[0]; i--) {
			map[i][C - 1] = map[i - 1][C - 1];
		}

		for (int i = C - 1; i > 1; i--) {
			map[acPos.get(1)[0]][i] = map[acPos.get(1)[0]][i - 1];
		}
		map[acPos.get(1)[0]][1] = 0;
	}

	private static boolean boundaryCheck(int newRow, int newCol) {
		return newRow >= 0 && newRow < R && newCol >= 0 && newCol < C && map[newRow][newCol] != -1;
	}

}
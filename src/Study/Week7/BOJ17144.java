package Study.Week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17144 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int R, C, T;
	static int[][] map;

	static int[] dirRow = { 0, 0, 1, -1 };
	static int[] dirCol = { 1, -1, 0, 0 };

	static List<int[]> acPos = new ArrayList<>();
	static List<int[]> dustList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		R = Integer.parseInt(stringTokenizer.nextToken());
		C = Integer.parseInt(stringTokenizer.nextToken());
		T = Integer.parseInt(stringTokenizer.nextToken());

		for (int i = 0; i < R; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());

				if (map[i][j] > 0) {
					dustList.add(new int[] { i, j });
				}

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

			Queue<int[]> queue = new ArrayDeque<>(dustList);
			while (!queue.isEmpty()) {
				int[] curDustPos = queue.poll();

				int count = 0;
				for (int i = 0; i < 4; i++) {
					int newRow = curDustPos[0];
					int newCol = curDustPos[1];
					if (boundaryCheck(newRow, newCol)) {
						count++;
					}
				}
				int diffusion = map[curDustPos[0]][curDustPos[1]] / 5;
				map[curDustPos[0]][curDustPos[1]] -= diffusion * count;

				for (int i = 0; i < 4; i++) {
					int newRow = curDustPos[0];
					int newCol = curDustPos[1];
					if (boundaryCheck(newRow, newCol)) {
						temp[newRow][newCol] += diffusion;
					}
				}
			}
			dustList.clear();
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (temp[i][j] != -1) {
						temp[i][j] = map[i][j] + temp[i][j];
					}
				}
				map[i] = temp[i];
				for (int j = 0; j < C; j++) {
					if (map[i][j] > 0) {
						dustList.add(new int[] { i, j });
					}
				}
			}

			rotateReverse();
			rotate();

		}

	}

	private static boolean boundaryCheck(int newRow, int newCol) {
		return newRow >= 0 && newRow < R && newCol >= 0 && newCol < C && map[newRow][newCol] != -1;
	}

}

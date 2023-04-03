package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_3055_G4_탈출_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int R, C;
	static char[][] map;
	static boolean[][] visit;

	static int answer = Integer.MAX_VALUE;

	static int[] dirRow = { 0, 0, 1, -1 };
	static int[] dirCol = { -1, 1, 0, 0 };

	static Queue<int[]> waterQueue = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());

		R = Integer.parseInt(stringTokenizer.nextToken());
		C = Integer.parseInt(stringTokenizer.nextToken());

		map = new char[R][C];
		visit = new boolean[R][C];
		int[] curPos = new int[3];

		for (int i = 0; i < R; i++) {
			map[i] = bufferedReader.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'S') {
					curPos[0] = i;
					curPos[1] = j;
					curPos[2] = 0;
					map[i][j] = '.';
				}
				if (map[i][j] == '*') {
					waterQueue.offer(new int[] { i, j });
				}
			}
		}

		bfs(curPos);
		if (answer == Integer.MAX_VALUE) {
			System.out.println("KAKTUS");
		} else {
			System.out.println(answer);
		}

	}

	private static void bfs(int[] startPosition) {

		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(startPosition);
		visit[startPosition[0]][startPosition[1]] = true;

		while (!queue.isEmpty()) {

			int size = waterQueue.size();
			for (int i = 0; i < size; i++) {
				int[] curWaterPos = waterQueue.poll();
				for (int dir = 0; dir < 4; dir++) {
					int newWaterRow = curWaterPos[0] + dirRow[dir];
					int newWaterCol = curWaterPos[1] + dirCol[dir];
					if (checkWater(newWaterRow, newWaterCol)) {
						map[newWaterRow][newWaterCol] = '*';
						waterQueue.offer(new int[] { newWaterRow, newWaterCol });
					}
				}
			}

			Queue<int[]> nextQueue = new ArrayDeque<>();
			while (!queue.isEmpty()) {
				int[] curPosition = queue.poll();

				if (map[curPosition[0]][curPosition[1]] == 'D') {
					answer = Math.min(answer, curPosition[2]);
					return;
				}

				for (int i = 0; i < 4; i++) {
					int newRow = curPosition[0] + dirRow[i];
					int newCol = curPosition[1] + dirCol[i];
					if (check(newRow, newCol)) {
						visit[newRow][newCol] = true;
						nextQueue.offer(new int[] { newRow, newCol, curPosition[2] + 1 });
					}
				}
			}
			queue = nextQueue;
		}

	}

	private static boolean check(int newRow, int newCol) {
		return newRow >= 0 && newRow < R && newCol >= 0 && newCol < C
				&& (map[newRow][newCol] == '.' || map[newRow][newCol] == 'D') && !visit[newRow][newCol];
	}

	private static boolean checkWater(int newWaterRow, int newWaterCol) {

		return newWaterRow >= 0 && newWaterRow < R && newWaterCol >= 0 && newWaterCol < C
				&& map[newWaterRow][newWaterCol] == '.';
	}

}

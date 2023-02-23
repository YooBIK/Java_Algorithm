package Study.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14502 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static int R, C;
	static int[][] map;
	static boolean[][] visit;

	static int[] dirRow = { 0, 0, 1, -1 };
	static int[] dirCol = { 1, -1, 0, 0 };

	static List<int[]> safePositions = new ArrayList<>();
	static List<int[]> virusPositions = new ArrayList<>();

	static int result = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException, InterruptedException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		R = Integer.parseInt(stringTokenizer.nextToken());
		C = Integer.parseInt(stringTokenizer.nextToken());
		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < C; j++) {
				int curVal = Integer.parseInt(stringTokenizer.nextToken());
				map[i][j] = curVal;
				if (curVal == 0) {
					safePositions.add(new int[] { i, j });
				}
				if (curVal == 2) {
					virusPositions.add(new int[] { i, j });
				}
			}
		}
		buildWall(0, 0);
		System.out.println(result);
	}

	private static void buildWall(int depth, int startIndex) throws InterruptedException {
		if (depth == 3) {
			int[][] temp = new int[R][C];
			for (int i = 0; i < R; i++) {
				temp[i] = map[i].clone();
			}
			visit = new boolean[R][C];
			bfs(temp);
			updateResult(temp);
			return;
		}
		for (int i = startIndex; i < safePositions.size(); i++) {
			int[] curPos = safePositions.get(i);
			map[curPos[0]][curPos[1]] = 1;
			buildWall(depth + 1, i + 1);
			map[curPos[0]][curPos[1]] = 0;
		}
	}

	private static void updateResult(int[][] temp) {
		int sum = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (temp[i][j] == 0) {
					sum++;
				}
			}
		}
		result = Math.max(result, sum);
	}

	private static void bfs(int[][] temp) {
		Queue<int[]> queue = new ArrayDeque<>(virusPositions);
		for (int i = 0; i < virusPositions.size(); i++) {
			int[] curPos = virusPositions.get(i);
			visit[curPos[0]][curPos[1]] = true;
		}

		while (!queue.isEmpty()) {
			int[] curPos = queue.poll();
			int curRow = curPos[0];
			int curCol = curPos[1];

			for (int k = 0; k < 4; k++) {
				int newRow = curRow + dirRow[k];
				int newCol = curCol + dirCol[k];
				if (check(newRow, newCol)) {
					temp[newRow][newCol] = 2;
					visit[newRow][newCol] = true;
					queue.offer(new int[] { newRow, newCol });
				}
			}
		}
	}

	private static boolean check(int newRow, int newCol) {
		return newRow >= 0 && newRow < R && newCol >= 0 && newCol < C && !visit[newRow][newCol]
				&& map[newRow][newCol] == 0;
	}

}

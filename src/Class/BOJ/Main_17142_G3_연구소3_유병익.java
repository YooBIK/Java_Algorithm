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

public class Main_17142_G3_연구소3_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int N, M;
	static int NUMBER_OF_VIRUS = 0;
	static int[][] map;
	static int[][] visit;

	static int[] dirRow = { 0, 0, -1, 1 };
	static int[] dirCol = { 1, -1, 0, 0 };

	static List<int[]> virusPositions = new ArrayList<>();
	static List<int[]> selectedViruses;
	static List<int[]> unselectedViruses = new ArrayList<>();
	static List<int[]> maxDistPositions = new ArrayList<>();

	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				if (map[i][j] == 2) {
					NUMBER_OF_VIRUS++;
					virusPositions.add(new int[] { i, j });
				}
			}
		}

		selectedViruses = new ArrayList<>(virusPositions);
		combination(0, 0);
		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}

	}

	private static void combination(int depth, int startPosition) {
		if (depth == NUMBER_OF_VIRUS - M) {
			int[][] temp = copyMap();
			visit = new int[N][N];

			bfs(temp);

			int result = getMaxValue(temp);

			if (result != Integer.MIN_VALUE) {
				if (check(result, temp)) {
					result -= 1;
				} else {
					result -= 2;
				}
				answer = Math.min(answer, result);
			}
			return;
		}

		for (int i = startPosition; i < NUMBER_OF_VIRUS; i++) {
			int[] curPosition = virusPositions.get(i);
			map[curPosition[0]][curPosition[1]] = -1;
			unselectedViruses.add(curPosition);
			selectedViruses.remove(curPosition);
			combination(depth + 1, i + 1);
			map[curPosition[0]][curPosition[1]] = 2;
			unselectedViruses.remove(curPosition);
			selectedViruses.add(curPosition);
		}

	}

	private static boolean check(int result, int[][] temp) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (temp[i][j] == result) {
					if (!unselectedViruses.contains(new int[] { i, j })) {
						return false;
					}
				}
			}
		}
		return true;
	}

	private static int getMaxValue(int[][] temp) {
		int result = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (temp[i][j] == 0)
					return Integer.MIN_VALUE;
				result = Math.max(result, visit[i][j]);
			}
		}
		return result;
	}

	private static void bfs(int[][] temp) {
		Queue<int[]> queue = new ArrayDeque<>();
		for (int i = 0; i < selectedViruses.size(); i++) {
			int[] cur = selectedViruses.get(i);
			queue.offer(cur);
			visit[cur[0]][cur[1]]++;
		}
		while (!queue.isEmpty()) {
			int[] curPos = queue.poll();
			int curRow = curPos[0];
			int curCol = curPos[1];

			for (int i = 0; i < 4; i++) {
				int newRow = curRow + dirRow[i];
				int newCol = curCol + dirCol[i];
				if (newRow >= 0 && newRow < N && newCol >= 0 && newCol < N) {
					if (visit[newRow][newCol] == 0 && temp[newRow][newCol] != 1) {
						visit[newRow][newCol] = visit[curRow][curCol] + 1;
						temp[newRow][newCol] = 2;
						queue.offer(new int[] { newRow, newCol });

					}
				}
			}
		}
	}

	private static int[][] copyMap() {
		int[][] temp = new int[N][N];
		for (int i = 0; i < N; i++) {
			temp[i] = map[i].clone();
		}
		return temp;
	}

}

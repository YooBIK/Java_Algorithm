package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_17135_G3_캐슬디펜스_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int[] dirRow = { 0, -1, 0 };
	static int[] dirCol = { -1, 0, 1 };

	static int R, C, RANGE;
	static int[][] map;
	static boolean[][] visit;

	static int count;
	static int result = Integer.MIN_VALUE;

	static List<Integer> curPositions = new ArrayList<>();

	static Set<List<Integer>> attack = new HashSet<>();

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());

		R = Integer.parseInt(stringTokenizer.nextToken());
		C = Integer.parseInt(stringTokenizer.nextToken());
		RANGE = Integer.parseInt(stringTokenizer.nextToken());
		map = new int[R + 2][C];
		for (int i = 1; i <= R; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		combination(0, 0);
		System.out.println(result);
	}

	private static void combination(int depth, int startIndex) {
		if (depth == 3) {
			playGame();
		}

		for (int i = startIndex; i < C; i++) {
			curPositions.add(i);
			combination(depth + 1, i + 1);
			curPositions.remove(curPositions.size() - 1);
		}
	}

	private static void playGame() {
		int[][] temp = new int[R + 2][C];
		for (int i = 0; i < R + 2; i++) {
			temp[i] = map[i].clone();
		}

		count = 0;
		for (int i = 0; i < R; i++) { // R번 수행
			attack.clear();
			for (int k = 0; k < curPositions.size(); k++) { // 현재 조합
				visit = new boolean[R + 2][C];
				bfs(temp, R + 1, curPositions.get(k));
			}
			count += attack.size();
			for (List<Integer> list : attack) {
				temp[list.get(0)][list.get(1)] = 0;
			}

			for (int j = R; j > 0; j--) {
				temp[j] = temp[j - 1];
			}
		}
		result = Math.max(result, count);
	}

	private static void bfs(int[][] temp, int startRow, int startCol) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { startRow, startCol });
		visit[startRow][startCol] = true;
		while (!queue.isEmpty()) {

			int[] curInfo = queue.poll();
			int curRow = curInfo[0];
			int curCol = curInfo[1];

			if (temp[curRow][curCol] == 1) {
				attack.add(Arrays.asList(curRow, curCol));
				return;
			}

			for (int i = 0; i < 3; i++) {
				int newRow = curRow + dirRow[i];
				int newCol = curCol + dirCol[i];
				if (check(newRow, newCol) && Math.abs(newRow - startRow) + Math.abs(newCol - startCol) <= RANGE) {
					visit[newRow][newCol] = true;
					queue.offer(new int[] { newRow, newCol });
				}
			}
		}

	}

	private static boolean check(int newRow, int newCol) {
		return newCol >= 0 && newCol < C && newRow > 0 && !visit[newRow][newCol];
	}

}

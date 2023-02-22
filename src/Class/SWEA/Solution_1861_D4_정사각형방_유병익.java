package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_1861_D4_정사각형방_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder builder = new StringBuilder();

	static int[] dirRow = { 0, 0, 1, -1 };
	static int[] dirCol = { 1, -1, 0, 0 };

	static int N;
	static int[][] map;
	static boolean[][] visit;
	static int result = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(bufferedReader.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(bufferedReader.readLine());
			map = new int[N][N];
			visit = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				}
			}

			int result = Integer.MIN_VALUE;
			List<int[]> resultList = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visit[i][j]) {
						int point = map[i][j];
						int count = dfs(i, j, i, j, 1);
						resultList.add(new int[] { point, count });
					}
				}
			}
			Collections.sort(resultList,
					(o1, o2) -> o2[1] == o1[1] ? Integer.compare(o1[0], o2[0]) : Integer.compare(o2[1], o1[1]));
			builder.append("#").append(testCase).append(" ").append(resultList.get(0)[0]).append(" ")
					.append(resultList.get(0)[1]).append("\n");
		}
		System.out.println(builder);
	}

	private static int dfs(int curRow, int curCol, int startRow, int startCol, int depth) {
		visit[curRow][curCol] = true;

		for (int i = 0; i < 4; i++) {
			int newRow = curRow + dirRow[i];
			int newCol = curCol + dirCol[i];
			if (newRow >= 0 && newRow < N && newCol >= 0 && newCol < N) {
				if (map[newRow][newCol] - map[curRow][curCol] == 1) {
					if (visit[newRow][newCol]) {
						return depth + dfs(newRow, newCol, newRow, newCol, 1);
					} else {
						return dfs(newRow, newCol, startRow, startCol, depth + 1);
					}
				}
			}
		}
		return depth;
	}

}

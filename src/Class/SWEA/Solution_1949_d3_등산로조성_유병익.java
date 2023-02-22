package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1949_d3_등산로조성_유병익 {

	static int n;
	static int k;

	static int[] dirRow = { 0, 0, 1, -1 };
	static int[] dirCol = { -1, 1, 0, 0 };
	static int[][] map;
	static boolean[][] visit;
	static StringTokenizer stringTokenizer;
	static int result = -1;
	static StringBuilder builder = new StringBuilder();

	public static void dfs(int row, int col, boolean isUsed, int depth) {

		for (int i = 0; i < 4; i++) {
			int newRow = row + dirRow[i];
			int newCol = col + dirCol[i];
			if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n) {
				if (map[newRow][newCol] < map[row][col]) {
					if (!visit[newRow][newCol]) {
						visit[row][col] = true;
						dfs(newRow, newCol, isUsed, depth + 1);
						visit[row][col] = false;
					}
				} else {
					if (!isUsed) {
						if (!visit[newRow][newCol]) {
							for (int j = 1; j <= k; j++) {
								if (map[newRow][newCol] - j < map[row][col]) {
									visit[row][col] = true;
									map[newRow][newCol] -= j;
									dfs(newRow, newCol, true, depth + 1);
									visit[row][col] = false;
									map[newRow][newCol] += j;
									break;
								}
							}
						}
					}
				}
			}
		}

		result = Math.max(result, depth);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bufferedReader.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			result = -1;
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			n = Integer.parseInt(stringTokenizer.nextToken());
			k = Integer.parseInt(stringTokenizer.nextToken());
			map = new int[n][n];
			int maxHeight = -1;
			for (int i = 0; i < n; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
					maxHeight = Math.max(maxHeight, map[i][j]);
				}
			}
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] == maxHeight) {
						visit = new boolean[n][n];
						visit[i][j] = true;
						dfs(i, j, false, 1);
						visit[i][j] = false;
					}
				}
			}
			builder.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.println(builder.toString());
	}
}

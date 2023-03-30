package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_4485_G4_녹색옷입은애가젤다지_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static int N;
	static int[][] map;

	static int[] dirRow = { 0, 0, 1, -1 };
	static int[] dirCol = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		int testCase = 0;
		while (true) {
			testCase++;

			N = Integer.parseInt(bufferedReader.readLine());
			if (N == 0) {
				break;
			}

			map = new int[N + 2][N + 2];
			int[][] dp = new int[N + 2][N + 2];

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					dp[i][j] = Integer.MAX_VALUE;
				}
			}

			for (int i = 1; i <= N; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				for (int j = 1; j <= N; j++) {
					map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				}
			}
			Queue<int[]> queue = new ArrayDeque<>();
			queue.offer(new int[] { 1, 1 });
			dp[1][1] = map[1][1];

			while (!queue.isEmpty()) {
				int[] cur = queue.poll();
				int curRow = cur[0];
				int curCol = cur[1];

				for (int i = 0; i < 4; i++) {
					int newRow = curRow + dirRow[i];
					int newCol = curCol + dirCol[i];
					if (dp[newRow][newCol] > dp[curRow][curCol] + map[newRow][newCol]) {
						dp[newRow][newCol] = dp[curRow][curCol] + map[newRow][newCol];
						queue.offer(new int[] { newRow, newCol });
					}
				}

			}

			stringBuilder.append("Problem ").append(testCase).append(": ").append(dp[N][N]).append("\n");
		}
		System.out.println(stringBuilder);
	}

}

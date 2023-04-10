package Study.Week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1956_운동 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static final int INF = 987654321;

	public static void main(String[] args) throws IOException {

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int N = Integer.parseInt(stringTokenizer.nextToken());
		int M = Integer.parseInt(stringTokenizer.nextToken());

		int[][] dp = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i != j) {
					dp[i][j] = INF;
				}
			}
		}

		for (int i = 0; i < M; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int from = Integer.parseInt(stringTokenizer.nextToken()) - 1;
			int to = Integer.parseInt(stringTokenizer.nextToken()) - 1;
			int weight = Integer.parseInt(stringTokenizer.nextToken());

			dp[from][to] = weight;
		}

		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				if (i == k)
					continue;
				for (int j = 0; j < N; j++) {
					if (j == k || i == j)
						continue;
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);

				}
			}
		}
		int answer = INF;
		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				if (i == j)
					continue;
				answer = Math.min(answer, dp[i][j] + dp[j][i]);

			}
		}
		if (answer >= INF) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
	}
}

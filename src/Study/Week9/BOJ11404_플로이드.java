package Study.Week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11404_플로이드 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	public static void main(String[] args) throws IOException {

		int N = Integer.parseInt(bufferedReader.readLine());
		int M = Integer.parseInt(bufferedReader.readLine());

		int[][] dp = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j)
					continue;
				dp[i][j] = 987654321;
			}
		}

		for (int i = 0; i < M; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int from = Integer.parseInt(stringTokenizer.nextToken()) - 1;
			int to = Integer.parseInt(stringTokenizer.nextToken()) - 1;
			int weight = Integer.parseInt(stringTokenizer.nextToken());
			dp[from][to] = Math.min(dp[from][to], weight);
		}

		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				if (k == i)
					continue;
				for (int j = 0; j < N; j++) {
					if (j == k || i == j)
						continue;
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);

				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (dp[i][j] >= 987654321) {
					System.out.print("0 ");
				} else {
					System.out.print(dp[i][j] + " ");
				}
			}
			System.out.println();
		}
	}

}

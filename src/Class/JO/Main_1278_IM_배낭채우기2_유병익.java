package Class.JO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1278_IM_배낭채우기2_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int N, W;

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken());
		W = Integer.parseInt(stringTokenizer.nextToken());

		int[][] items = new int[N + 1][2];

		for (int i = 1; i <= N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			items[i][0] = Integer.parseInt(stringTokenizer.nextToken());
			items[i][1] = Integer.parseInt(stringTokenizer.nextToken());
		}
		int[][] dp = new int[N + 1][W + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= W; j++) {
				if (items[i][0] > j) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - items[i][0]] + items[i][1]);
				}
			}
		}
		System.out.println(dp[N][W]);

	}

}

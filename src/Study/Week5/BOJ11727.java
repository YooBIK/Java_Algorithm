package Study.Week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11727 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder stringBuilder = new StringBuilder();

	static int[] dp;

	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(bufferedReader.readLine());
		dp = new int[N + 1];
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i <= N; i++) {
			dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % 10007;
		}
		System.out.println(dp[N]);

	}

}

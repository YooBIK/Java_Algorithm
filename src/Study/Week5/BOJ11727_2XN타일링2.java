package Study.Week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11727_2XN타일링2 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder stringBuilder = new StringBuilder();

	static int[] dp;

	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(bufferedReader.readLine());
		dp = new int[N + 1];
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i <= N; i++) {
			dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % 10007;	// i-1개의 각 경우의 수에 한개짜리 추가 + i-2개의 각 경우의수에 2개짜리 추가(2개 짜리 놓는 방법은 2개임)
		}
		System.out.println(dp[N]);

	}

}

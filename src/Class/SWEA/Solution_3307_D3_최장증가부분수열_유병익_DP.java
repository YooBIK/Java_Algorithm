package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3307_D3_최장증가부분수열_유병익_DP {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	public static void main(String[] args) throws IOException {

		int T = Integer.parseInt(bufferedReader.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {

			int N = Integer.parseInt(bufferedReader.readLine());
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());

			int[] numbers = new int[N];
			int[] dp = new int[N];
			for (int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
			}

			int answer = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				dp[i] = 1;
				for (int j = 0; j < i; j++) {
					if (numbers[j] < numbers[i]) {
						dp[i] = Math.max(dp[i], dp[j] + 1);
					}
				}
				answer = Math.max(answer, dp[i]);
			}
			stringBuilder.append("#").append(testCase).append(" ").append(answer).append("\n");
		}
		System.out.println(stringBuilder);
	}

}

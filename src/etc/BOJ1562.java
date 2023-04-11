package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1562 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static int N;

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(bufferedReader.readLine());
		long[][][] dp = new long[N + 1][10][1 << 11];

		dp[1][0][1 << 1] = 0; // [자리수][끝나는수][사용한수들 bitmask] [1][2][011(2진수)] 1자리수 중 2로끝나고, 0과 1을 사용한 경우
		for (int i = 1; i <= 9; i++) {
			dp[1][i][1 << (i + 1)] = 1;
		}

		for (int i = 2; i <= N; i++) {
			dp[i][0][1 << 0] += dp[i - 1][1][1 << 1];
			for (int k = 0; k < 1024; k++) {
				dp[i][0][k & (1 << 1)] = dp[i - 1][1][k] % 1000000000; // 길이 i-1인 1로 끝나는 수의 개수 중 [k] 는 사용한 숫자들의 경우에 해당하는
			}
			for (int j = 1; j <= 8; j++) {
				for (int k = 0; k < 1024; k++) {
					dp[i][j][k & (1 << (j + 1))] += dp[i - 1][j - 1][k] % 1000000000;
					dp[i][j][k & (1 << (j + 1))] += dp[i - 1][j + 1][k] % 1000000000;
				}
			}
			for (int k = 0; k < 1024; k++) {
				dp[i][9][k & (1 << 10)] = dp[i - 1][8][k] % 1000000000; // 길이 i-1인 1로 끝나는 수의 개수 중 [k] 는 사용한 숫자들의 경우에
																		// 해당하는
			}
		}

		long result = 0;
		int bit = 1023;
		for (int i = 0; i < 10; i++) {
			result = (result + dp[N][i][bit]) % 1000000000;
		}

		System.out.println(result % 1000000000);

	}

}

package Class.BOJ;

import java.util.Scanner;

public class Main_1463_S3_1로만들기_유병익 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();

		int[] dp = new int[N + 1];

		for (int i = N; i >= 1; i--) {
			if (i % 3 == 0) {
				if (dp[i / 3] == 0) {
					dp[i / 3] = dp[i] + 1;
				} else {
					dp[i / 3] = Math.min(dp[i / 3], dp[i] + 1);
				}
			}

			if (i % 2 == 0) {
				if (dp[i / 2] == 0) {
					dp[i / 2] = dp[i] + 1;
				} else {
					dp[i / 2] = Math.min(dp[i / 2], dp[i] + 1);
				}
			}

			if (dp[i - 1] == 0) {
				dp[i - 1] = dp[i] + 1;
			} else {
				dp[i - 1] = Math.min(dp[i - 1], dp[i] + 1);
			}
		}

		System.out.println(dp[1]);

	}

}

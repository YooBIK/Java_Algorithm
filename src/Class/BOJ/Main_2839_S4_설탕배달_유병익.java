package Class.BOJ;

import java.util.Arrays;
import java.util.Scanner;

public class Main_2839_S4_설탕배달_유병익 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();

		int[] dp = new int[5001];
		Arrays.fill(dp, -1);
		dp[3] = 1;
		dp[5] = 1;
		for (int i = 6; i <= N; i++) {
			if (dp[i - 3] == -1 && dp[i - 5] == -1) {
				dp[i] = -1;
			} else if (dp[i - 3] == -1) {
				dp[i] = dp[i - 5] + 1;
			} else if (dp[i - 5] == -1) {
				dp[i] = dp[i - 3] + 1;
			} else {
				dp[i] = Math.min(dp[i - 5], dp[i - 3]) + 1;
			}
		}
		System.out.println(dp[N]);

	}

}

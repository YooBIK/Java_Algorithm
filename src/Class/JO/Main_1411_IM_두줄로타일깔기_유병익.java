package Class.JO;

import java.util.Scanner;

public class Main_1411_IM_두줄로타일깔기_유병익 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();

		long[] dp = new long[N + 1];
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i <= N; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2] * 2) % 20100529;
		}
		System.out.println(dp[N]);

	}
}

package Study.Week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10844_쉬운계단수 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(bufferedReader.readLine());
        dp = new long[N + 1][10];

        dp[1][0] = 0;
        for (int i = 1; i <= 9; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            dp[i][0] = dp[i - 1][1] % 1000000000;
            for (int j = 1; j <= 8; j++) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 1000000000;
            }
            dp[i][9] = dp[i - 1][8] % 1000000000;
        }
        long result = 0;
        for (int i = 0; i <= 9; i++) {
            result += dp[N][i] % 1000000000;
        }
        System.out.println(result % 1000000000);
    }
}

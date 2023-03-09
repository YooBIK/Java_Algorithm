package Study.Week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1003 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder stringBuilder = new StringBuilder();
    static int T;
    static int[][] dp;
    static int countZero, countOne;

    public static void main(String[] args) throws IOException {
        dp = new int[41][2];
        dp[0][0] = 1;
        dp[1][1] = 1;
        for (int i = 2; i <= 40; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
            dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
        }

        T = Integer.parseInt(bufferedReader.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int n = Integer.parseInt(bufferedReader.readLine());
            countZero = 0;
            countOne = 0;
            stringBuilder.append(dp[n][0]).append(" ").append(dp[n][1]).append("\n");
        }
        System.out.println(stringBuilder);
    }

}

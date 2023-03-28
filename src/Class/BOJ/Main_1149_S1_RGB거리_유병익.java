package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1149_S1_RGB거리_유병익 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;

    static int N;
    static int[][] dp;
    static int[][] rgb;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        dp = new int[N][3];
        rgb = new int[N][3];
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < 3; j++) {
                rgb[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        for (int i = 0; i < 3; i++) {
            dp[0][i] = rgb[0][i];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j] = Math.min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3]) + rgb[i][j];
            }
        }


        System.out.println(Math.min(dp[N - 1][0], Math.min(dp[N - 1][1], dp[N - 1][2])));

    }
}

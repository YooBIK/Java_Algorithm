package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2293 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;

    static int N, K;

    public static void main(String[] args) throws IOException {
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        int[] coinArray = new int[N + 1];
        int[] dp = new int[K + 1];

        for (int i = 1; i <= N; i++) {
            coinArray[i] = Integer.parseInt(bufferedReader.readLine());
        }

        for (int i = 1; i <= N; i++) {
            int curCoin = coinArray[i];
            if (curCoin > K) continue;
            dp[curCoin]++;
            for (int j = 1; j <= K; j++) {
                if (j - curCoin > 0)
                    dp[j] = dp[j] + dp[j - curCoin];
            }
        }
        System.out.println(dp[K]);
    }
}

package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2294 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int N, K;
	static int[] dp;
	static List<Integer> coinList = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken());
		K = Integer.parseInt(stringTokenizer.nextToken());

		for (int i = 0; i < N; i++) {
			coinList.add(Integer.parseInt(bufferedReader.readLine()));
		}

		dp = new int[K + 1];
		Arrays.fill(dp, 987654321);

		for (int i = 0; i < N; i++) {
			int curCoin = coinList.get(i);
			if (curCoin > K)
				continue;
			dp[curCoin] = 1;
			for (int j = curCoin; j <= K; j++) {
				dp[j] = Math.min(dp[j], dp[j - curCoin] + 1);
			}
		}
		if (dp[K] == 987654321) {
			System.out.println(-1);
		} else {
			System.out.println(dp[K]);
		}
	}

}

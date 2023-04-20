package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2293 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int N, K;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());

		N = Integer.parseInt(stringTokenizer.nextToken());
		K = Integer.parseInt(stringTokenizer.nextToken());
		List<Integer> coinList = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			coinList.add(Integer.parseInt(bufferedReader.readLine()));
		}

		dp = new int[K + 1];

		for (int j = coinList.get(0); j <= K; j += coinList.get(0)) {
			dp[j]++;
		}
		System.out.println(Arrays.toString(dp));

		for (int i = 1; i < N; i++) {
			int curCoin = coinList.get(i);

			for (int j = curCoin; j <= K; j += curCoin) {
				dp[j]++;
			}

			for (int j = K; j >= curCoin; j--) {
				if (dp[j - curCoin] == 0)
					continue;
				dp[j] += dp[j - curCoin];
			}
			System.out.println(Arrays.toString(dp));
		}

		System.out.println(dp[K]);

	}

}

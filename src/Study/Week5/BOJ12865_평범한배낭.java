package Study.Week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ12865_평범한배낭 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static int[] dp;
	static int N, K;
	static int temp = 0;

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken());
		K = Integer.parseInt(stringTokenizer.nextToken());

		dp = new int[K + 1];

		List<int[]> itemList = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());

			int weight = Integer.parseInt(stringTokenizer.nextToken());
			int value = Integer.parseInt(stringTokenizer.nextToken());
			itemList.add(new int[] { weight, value });
		}

		for (int i = 0; i < N; i++) {
			int[] curInfo = itemList.get(i);
			int curWeight = curInfo[0];
			int curValue = curInfo[1];

			for (int j = K; j >= curWeight; j--) {
				dp[j] = Math.max(dp[j - curWeight] + curValue, dp[j]);
			}
		}

		System.out.println(dp[K]);

	}

}

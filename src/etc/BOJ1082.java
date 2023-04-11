package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1082 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	public static void main(String[] args) throws IOException {

		int N = Integer.parseInt(bufferedReader.readLine());
		int[] price = new int[N];

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 0; i < N; i++) {
			price[i] = Integer.parseInt(stringTokenizer.nextToken());
		}
		int M = Integer.parseInt(bufferedReader.readLine());

		String[] dp = new String[M + 1];
		Arrays.fill(dp, "0");

		for (int i = N - 1; i >= 0; i--) {
			for (int j = 1; j <= M; j++) {
				if (j >= price[i]) {

					String first = removeZero(dp[j - price[i]].concat(Integer.toString(i)));
					String second = removeZero(dp[j]);

					if (compare(first, second)) {
						dp[j] = first;
					}
				}
			}
		}
		System.out.println(dp[M]);
	}

	private static String removeZero(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != '0') {
				return str.substring(i);
			}
		}
		return "0";
	}

	private static boolean compare(String first, String second) {
		if (first.length() > second.length())
			return true;

		if (first.length() < second.length())
			return false;

		if (first.compareTo(second) > 0) { // first가 사전순으로 뒤에있다는 뜻, 즉 first > second
			return true;
		} else {
			return false;
		}

	}

}

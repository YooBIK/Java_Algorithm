package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_9229_D3_한빈이와SpotMart_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static int N, M;
	static int result;
	static int[] snacks;

	private static void combination(int depth, int start, int curWeight) {
		if (depth == 2) {
			if (curWeight <= M) {
				result = Math.max(result, curWeight);
			}
			return;
		}

		for (int i = start; i < N; i++) {
			combination(depth + 1, i + 1, curWeight + snacks[i]);
		}
	}

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(bufferedReader.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			N = Integer.parseInt(stringTokenizer.nextToken());
			M = Integer.parseInt(stringTokenizer.nextToken());
			snacks = new int[N];

			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int i = 0; i < N; i++) {
				snacks[i] = Integer.parseInt(stringTokenizer.nextToken());
			}

			result = -1;
			combination(0, 0, 0);
			stringBuilder.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.println(stringBuilder);
	}

}

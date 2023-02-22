package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5215_D3_햄버거다이어트_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static int N;
	static int MaxCalorie;
	static int[][] foods;
	static boolean[] isSelected;
	static int result;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(bufferedReader.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			N = Integer.parseInt(stringTokenizer.nextToken());
			MaxCalorie = Integer.parseInt(stringTokenizer.nextToken());
			foods = new int[N][2];
			for (int i = 0; i < N; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				foods[i][0] = Integer.parseInt(stringTokenizer.nextToken());
				foods[i][1] = Integer.parseInt(stringTokenizer.nextToken());
			}
			result = Integer.MIN_VALUE;
			dfs(0, 0, 0);
			stringBuilder.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.println(stringBuilder);
	}

	private static void dfs(int start, int sumCalorie, int score) {

		if (sumCalorie > MaxCalorie) {
			return;
		} else {
			result = Math.max(score, result);
		}

		for (int i = start; i < N; i++) {
			dfs(i + 1, sumCalorie + foods[i][1], score + foods[i][0]);
		}
	}
}

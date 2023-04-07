package Study.Week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16953_AtoB {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static long A, B;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		A = Long.parseLong(stringTokenizer.nextToken());
		B = Long.parseLong(stringTokenizer.nextToken());
		dfs(A, 1);
		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}

	}

	private static void dfs(long curNum, int depth) {

		if (curNum > B) {
			return;
		}

		if (curNum == B) {
			answer = Math.min(answer, depth);
			return;
		}

		dfs(curNum * 2, depth + 1);
		dfs(curNum * 10 + 1, depth + 1);
	}
}

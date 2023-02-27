package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_7465_D4_창용마을무리의개수_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static int N, M;

	static List<Integer>[] connection;
	static boolean[] check;

	private static void init() {
		check = new boolean[N + 1];
		connection = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			connection[i] = new ArrayList<>();
		}
	}

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(bufferedReader.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			N = Integer.parseInt(stringTokenizer.nextToken());
			M = Integer.parseInt(stringTokenizer.nextToken());
			init();

			for (int i = 0; i < M; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				int firstNode = Integer.parseInt(stringTokenizer.nextToken());
				int secondNode = Integer.parseInt(stringTokenizer.nextToken());
				connection[firstNode].add(secondNode);
				connection[secondNode].add(firstNode);
			}
			int result = 0;
			for (int i = 1; i <= N; i++) {
				if (!check[i]) {
					result++;
					dfs(i);
				}
			}

			stringBuilder.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.println(stringBuilder);
	}

	private static void dfs(int curNode) {
		List<Integer> curList = connection[curNode];
		for (int i = 0; i < curList.size(); i++) {
			int nextNode = curList.get(i);
			if (!check[nextNode]) {
				check[nextNode] = true;
				dfs(nextNode);
			}
		}

	}

}

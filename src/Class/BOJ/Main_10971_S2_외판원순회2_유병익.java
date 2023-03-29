package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_10971_S2_외판원순회2_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int N;
	static int answer = Integer.MAX_VALUE;
	static int[][] connection;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bufferedReader.readLine());
		connection = new int[N][N];
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < N; j++) {
				connection[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		visited = new boolean[N];
		for (int startPosition = 0; startPosition < N; startPosition++) {
			visited[startPosition] = true;
			dfs(startPosition, startPosition, 0, 1);
			visited[startPosition] = false;
		}
		System.out.println(answer);
	}

	private static void dfs(int startPosition, int curPos, int cost, int depth) {
		if (depth == N) {
			if (connection[curPos][startPosition] != 0) {
				cost += connection[curPos][startPosition];
				answer = Math.min(answer, cost);
			}
			return;
		}

		for (int i = 0; i < N; i++) {
			if (connection[curPos][i] != 0 && !visited[i]) {
				visited[i] = true;
				dfs(startPosition, i, cost + connection[curPos][i], depth + 1);
				visited[i] = false;
			}
		}

	}

}

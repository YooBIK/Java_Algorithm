package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1260_S2_DFS와BFS_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder stringBuilder = new StringBuilder();
	static StringTokenizer stringTokenizer;

	static int N;
	static int M;
	static boolean[][] connection;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());
		int startIndex = Integer.parseInt(stringTokenizer.nextToken());
		connection = new boolean[N + 1][N + 1];
		visited = new boolean[N + 1];

		for (int i = 0; i < M; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int start = Integer.parseInt(stringTokenizer.nextToken());
			int end = Integer.parseInt(stringTokenizer.nextToken());
			connection[start][end] = true;
			connection[end][start] = true;
		}

		dfs(startIndex);
		stringBuilder.append("\n");

		Arrays.fill(visited, false);
		bfs(startIndex);

		System.out.println(stringBuilder);

	}

	private static void dfs(int current) {
		visited[current] = true;
		stringBuilder.append(current).append(" ");

		for (int i = 1; i <= N; i++) {
			if (connection[current][i] && !visited[i]) {
				dfs(i);
			}
		}
	}

	private static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int current = queue.poll();
			stringBuilder.append(current).append(" ");
			for (int i = 1; i <= N; i++) {
				if (connection[current][i] && !visited[i]) {
					visited[i] = true;
					queue.offer(i);
				}
			}
		}
	}
}

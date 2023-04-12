package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1865_G3_웜홀_유병익 {

	static class Edge {
		int from;
		int to;
		int weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}

	}

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder stringBuilder = new StringBuilder();
	static StringTokenizer stringTokenizer;

	static int V, E, W;

	static List<Edge> edgeList;
	static int[][] adjArray;
	static final int INF = 987654321;

	public static void main(String[] args) throws IOException {

		int T = Integer.parseInt(bufferedReader.readLine());

		for (int testCase = 0; testCase < T; testCase++) {

			stringTokenizer = new StringTokenizer(bufferedReader.readLine());

			V = Integer.parseInt(stringTokenizer.nextToken());
			E = Integer.parseInt(stringTokenizer.nextToken());
			W = Integer.parseInt(stringTokenizer.nextToken());

			adjArray = new int[V][V];
			for (int i = 0; i < V; i++) {
				for (int j = 0; j < V; j++) {
					if (i == j)
						continue;
					adjArray[i][j] = 987654321;
				}
			}

			edgeList = new ArrayList<>();
			for (int i = 0; i < E; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				int start = Integer.parseInt(stringTokenizer.nextToken()) - 1;
				int end = Integer.parseInt(stringTokenizer.nextToken()) - 1;
				int weight = Integer.parseInt(stringTokenizer.nextToken());
				adjArray[start][end] = Math.min(adjArray[start][end], weight);
				adjArray[end][start] = Math.min(adjArray[end][start], weight);
			}

			for (int i = 0; i < W; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				int start = Integer.parseInt(stringTokenizer.nextToken()) - 1;
				int end = Integer.parseInt(stringTokenizer.nextToken()) - 1;
				int weight = Integer.parseInt(stringTokenizer.nextToken());
				adjArray[start][end] = -weight;
			}

			if (!check()) {
				stringBuilder.append("YES");
			} else {
				stringBuilder.append("NO");
			}
			stringBuilder.append("\n");
		}
		System.out.println(stringBuilder);

	}

	private static boolean check() {
		for (int k = 0; k < V; k++) {
			for (int i = 0; i < V; i++) {
				if (i == k)
					continue;
				for (int j = 0; j < V; j++) {
					adjArray[i][j] = Math.min(adjArray[i][j], adjArray[i][k] + adjArray[k][j]);
					if (adjArray[i][j] + adjArray[j][i] < 0)
						return false;
				}
			}
		}

		return true;
	}

}

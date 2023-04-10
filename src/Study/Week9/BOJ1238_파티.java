package Study.Week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1238_파티 {

	static class Edge {
		int to;
		int weight;

		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int N, M, X;
	static List<Edge>[] adjList;
	static int answer = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());
		X = Integer.parseInt(stringTokenizer.nextToken());

		adjList = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int from = Integer.parseInt(stringTokenizer.nextToken());
			int to = Integer.parseInt(stringTokenizer.nextToken());
			int weight = Integer.parseInt(stringTokenizer.nextToken());
			adjList[from].add(new Edge(to, weight));
		}

		for (int i = 1; i <= N; i++) {
			int curResult = dijkstra(i, X) + dijkstra(X, i);
			answer = Math.max(answer, curResult);
		}
		System.out.println(answer);

	}

	private static int dijkstra(int startNode, int endNode) {
		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.weight, o2.weight));
		boolean[] visit = new boolean[N + 1];
		int[] D = new int[N + 1];
		Arrays.fill(D, Integer.MAX_VALUE);

		pq.offer(new Edge(startNode, 0));
		D[startNode] = 0;

		while (!pq.isEmpty()) {
			Edge e = pq.poll();

			if (visit[e.to])
				continue;

			for (int i = 0; i < adjList[e.to].size(); i++) {
				Edge next = adjList[e.to].get(i);
				if (!visit[next.to] && D[next.to] > D[e.to] + next.weight) {
					D[next.to] = D[e.to] + next.weight;
					pq.offer(new Edge(next.to, D[next.to]));
				}
			}
			visit[e.to] = true;
		}
		return D[endNode];
	}

}

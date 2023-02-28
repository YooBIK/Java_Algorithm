package Class.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MST2_Prim2 {

	public static class Edge implements Comparable<Edge> {
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
			StringBuilder builder = new StringBuilder();
			builder.append("Edge [from=").append(from).append(", to=").append(to).append(", weight=").append(weight)
					.append("]");
			return builder.toString();
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(weight, o.weight);
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int N = Integer.parseInt(stringTokenizer.nextToken());
		int E = Integer.parseInt(stringTokenizer.nextToken());
		boolean[] visit = new boolean[N];

		List<Edge>[] adjList = new ArrayList[E];
		for (List<Edge> list : adjList) {
			list = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int from = Integer.parseInt(stringTokenizer.nextToken());
			int to = Integer.parseInt(stringTokenizer.nextToken());
			int weight = Integer.parseInt(stringTokenizer.nextToken());
			if (adjList[from] == null) {
				adjList[from] = new ArrayList<>();
			}

			if (adjList[to] == null) {
				adjList[to] = new ArrayList<>();
			}
			adjList[from].add(new Edge(from, to, weight));
			adjList[to].add(new Edge(to, from, weight));
		}

		PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
		priorityQueue.addAll(adjList[0]);
		visit[0] = true;

		int count = 1;
		int result = 0;
		while (count != N) {
			Edge curMinEdge = priorityQueue.poll();

			if (!visit[curMinEdge.to]) {
				result += curMinEdge.weight;
				priorityQueue.addAll(adjList[curMinEdge.to]);
				visit[curMinEdge.to] = true;
				count++;
			}
		}
		System.out.println(result);
	}
}

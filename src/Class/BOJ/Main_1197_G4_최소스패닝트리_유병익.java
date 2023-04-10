package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1197_G4_최소스패닝트리_유병익 {

	static class Edge {
		int from;
		int to;
		int weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int V, E;

	static int[] parents;
	static List<Edge>[] adjList;

	public static void main(String[] args) throws IOException {

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());

		V = Integer.parseInt(stringTokenizer.nextToken());
		E = Integer.parseInt(stringTokenizer.nextToken());

		parents = new int[V];
		adjList = new ArrayList[V];

		for (int i = 0; i < V; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int from = Integer.parseInt(stringTokenizer.nextToken()) - 1;
			int to = Integer.parseInt(stringTokenizer.nextToken()) - 1;
			int weight = Integer.parseInt(stringTokenizer.nextToken());
			adjList[from].add(new Edge(from, to, weight));
			adjList[to].add(new Edge(to, from, weight));
		}

		makeSet();
		System.out.println(kruskal(0));
	}

	private static long kruskal(int startNode) {

		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.weight, o2.weight));
		for (int i = 0; i < adjList[startNode].size(); i++) {
			pq.offer(adjList[startNode].get(i));
		}

		long result = 0;
		int count = 0;

		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			if (findRoot(e.from) != findRoot(e.to)) {
				count++;
				result += e.weight;
				union(e.from, e.to);

				if (count == V - 1)
					break;

				for (int i = 0; i < adjList[e.to].size(); i++) {
					Edge next = adjList[e.to].get(i);
					pq.offer(adjList[e.to].get(i));
				}
			}
		}
		return result;

	}

	private static void makeSet() {
		for (int i = 0; i < V; i++) {
			parents[i] = i;
		}
	}

	private static int findRoot(int vertex) {
		if (parents[vertex] == vertex) {
			return vertex;
		}
		return parents[vertex] = findRoot(parents[vertex]);
	}

	private static void union(int first, int second) {
		int firstRoot = findRoot(first);
		int secondRoot = findRoot(second);

		if (firstRoot != secondRoot) {
			parents[firstRoot] = secondRoot;
		}
	}

}

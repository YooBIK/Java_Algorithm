package Study.Week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1922_네트워크연결 {

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

	static int N, M;
	static int[] parents;
	static PriorityQueue<Edge> edgePriorityQueue = new PriorityQueue<>(
			(o1, o2) -> Integer.compare(o1.weight, o2.weight));

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bufferedReader.readLine());
		M = Integer.parseInt(bufferedReader.readLine());

		for (int i = 0; i < M; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int from = Integer.parseInt(stringTokenizer.nextToken()) - 1;
			int to = Integer.parseInt(stringTokenizer.nextToken()) - 1;
			int weight = Integer.parseInt(stringTokenizer.nextToken());
			edgePriorityQueue.offer(new Edge(from, to, weight));
		}

		System.out.println(getResult());

	}

	private static long getResult() {
		makeSet();

		long result = 0;

		while (!edgePriorityQueue.isEmpty()) {
			Edge curEdge = edgePriorityQueue.poll();

			if (findRoot(curEdge.from) != findRoot(curEdge.to)) {
				result += curEdge.weight;
				union(curEdge.from, curEdge.to);
			}
		}

		return result;
	}

	private static void makeSet() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}

	private static int findRoot(int vertex) {
		if (parents[vertex] == vertex) {
			return vertex;
		}
		return findRoot(parents[vertex]);
	}

	private static void union(int first, int second) {
		int firstRoot = findRoot(first);
		int secondRoot = findRoot(second);

		if (firstRoot != secondRoot) {
			parents[firstRoot] = secondRoot;
		}
	}

}

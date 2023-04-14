package Study.Week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ16398_행성연결 {

	static class Edge {
		int start;
		int end;
		long weight;

		public Edge(int start, int end, long weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
	}

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int N;
	static long[][] adjMatrix;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bufferedReader.readLine());

		adjMatrix = new long[N][N];
		parents = new int[N];

		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < N; j++) {
				adjMatrix[i][j] = Long.parseLong(stringTokenizer.nextToken());
			}
		}

		System.out.println(getCost());
	}

	private static long getCost() {
		makeSet();
		PriorityQueue<Edge> pq = makePriorityQueue();
		long result = 0;
		int count = 0;

		while (!pq.isEmpty()) {
			Edge curEdge = pq.poll();

			if (findRoot(curEdge.start) != findRoot(curEdge.end)) {
				union(curEdge.start, curEdge.end);
				result += curEdge.weight;
				count++;
			}

			if (count == N - 1) {
				break;
			}

		}
		return result;
	}

	private static void union(int first, int second) {

		int firstRoot = findRoot(first);
		int secondRoot = findRoot(second);

		if (firstRoot != secondRoot) {
			parents[firstRoot] = secondRoot;
		}
	}

	private static int findRoot(int vertex) {
		if (parents[vertex] == vertex) {
			return vertex;
		}
		return findRoot(parents[vertex]);
	}

	private static PriorityQueue<Edge> makePriorityQueue() {
		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.weight, o2.weight));
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				pq.offer(new Edge(i, j, adjMatrix[i][j]));
			}
		}
		return pq;
	}

	private static void makeSet() {
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}

}

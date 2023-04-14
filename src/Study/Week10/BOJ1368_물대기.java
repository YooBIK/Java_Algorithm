package Study.Week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1368_물대기 {

	static class Edge {
		int start;
		int end;
		int weight;

		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
	}

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int N;
	static int[] costArray;
	static int[][] adjMatrix;
	static int[] parents;
	static int[] initArray;

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(bufferedReader.readLine());

		costArray = new int[N];
		adjMatrix = new int[N][N];
		parents = new int[N];

		for (int i = 0; i < N; i++) {
			costArray[i] = Integer.parseInt(bufferedReader.readLine());
		}

		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < N; j++) {
				adjMatrix[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		makeSet();
		System.out.println(getResult());

	}

	private static int getResult() {

		PriorityQueue<Edge> edgePriorityQueue = getPriorityQueue();

		int result = 0;

		HashMap<Integer, Integer> costMap = new HashMap<>();
		for (int i = 0; i < N; i++) {
			costMap.put(i, costArray[i]);
		}

		while (!edgePriorityQueue.isEmpty()) {
			Edge curEdge = edgePriorityQueue.poll();

			if (findRoot(curEdge.start) != findRoot(curEdge.end)
					&& costMap.get(findRoot(curEdge.start)) + costMap.get(findRoot(curEdge.end)) > Math
							.min(costMap.get(findRoot(curEdge.start)), costMap.get(findRoot(curEdge.end)))
							+ curEdge.weight) {

				int minVal = Math.min(costMap.get(findRoot(curEdge.start)), costMap.get(findRoot(curEdge.end)));

				union(curEdge.start, curEdge.end);
				costMap.put(findRoot(curEdge.start), minVal);
				result += curEdge.weight;
			}
		}
		for (int i = 0; i < N; i++) {
			if (parents[i] == i) {
				result += costMap.get(i);
			}
		}

		return result;
	}

	private static PriorityQueue<Edge> getPriorityQueue() {
		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.weight, o2.weight));
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j)
					continue;
				pq.offer(new Edge(i, j, adjMatrix[i][j]));
			}
		}
		return pq;
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

	private static void makeSet() {
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}

}

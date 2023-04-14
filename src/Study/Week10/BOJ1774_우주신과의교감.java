package Study.Week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1774_우주신과의교감 {

	static class Edge {
		int start;
		int end;
		double weight;

		public Edge(int start, int end, double weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
	}

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int N, M;
	static int[] parents;

	public static void main(String[] args) throws IOException {

		List<int[]> positionList = new ArrayList<>();

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			positionList.add(new int[] { Integer.parseInt(stringTokenizer.nextToken()),
					Integer.parseInt(stringTokenizer.nextToken()) });
		}
		makeSet();
		for (int i = 0; i < M; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			union(Integer.parseInt(stringTokenizer.nextToken()) - 1, Integer.parseInt(stringTokenizer.nextToken()) - 1);
		}

		System.out.println(String.format("%.2f", getResult(positionList)));

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

	private static double getResult(List<int[]> positionList) {
		PriorityQueue<Edge> edgePriorityQueue = getPriorityQueue(positionList);

		double result = 0;

		while (!edgePriorityQueue.isEmpty()) {
			Edge curEdge = edgePriorityQueue.poll();

			if (findRoot(curEdge.start) != findRoot(curEdge.end)) {
				union(curEdge.start, curEdge.end);
				result += curEdge.weight;
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

	private static PriorityQueue<Edge> getPriorityQueue(List<int[]> positionList) {
		PriorityQueue<Edge> edgePriorityQueue = new PriorityQueue<>((o1, o2) -> Double.compare(o1.weight, o2.weight));
		for (int i = 0; i < positionList.size(); i++) {
			int[] firstPos = positionList.get(i);
			for (int j = i + 1; j < positionList.size(); j++) {
				int[] secondPos = positionList.get(j);
				double distance = Math
						.sqrt(Math.pow(firstPos[0] - secondPos[0], 2) + Math.pow(firstPos[1] - secondPos[1], 2));
				edgePriorityQueue.offer(new Edge(i, j, distance));
			}
		}
		return edgePriorityQueue;
	}

}

package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 크루스칼
 */
public class Solution_3124_D4_최소스패닝트리_유병익1 {

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
	static StringBuilder stringBuilder = new StringBuilder();

	static int V, E;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(bufferedReader.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			V = Integer.parseInt(stringTokenizer.nextToken());
			E = Integer.parseInt(stringTokenizer.nextToken());
			List<Edge> edgeList = new ArrayList<>();
			makeSet();
			for (int i = 0; i < E; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				int from = Integer.parseInt(stringTokenizer.nextToken());
				int to = Integer.parseInt(stringTokenizer.nextToken());
				int weight = Integer.parseInt(stringTokenizer.nextToken());
				edgeList.add(new Edge(from, to, weight));
			}

			Collections.sort(edgeList, (o1, o2) -> Integer.compare(o1.weight, o2.weight));
			long result = 0;
			int count = 0;
			for (int i = 0; i < edgeList.size(); i++) {
				Edge curMinEdge = edgeList.get(i);
				int curFrom = curMinEdge.from;
				int curTo = curMinEdge.to;
				long curWeight = curMinEdge.weight;

				if (findRoot(curFrom) == findRoot(curTo)) {
					continue;
				} else {
					union(curFrom, curTo);
					result += curWeight;
					if (++count == V - 1) {
						break;
					}
				}
			}
			stringBuilder.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.println(stringBuilder);
	}

	private static void makeSet() {
		parents = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			parents[i] = i;
		}
	}

	private static int findRoot(int curVertex) {
		if (parents[curVertex] == curVertex) {
			return curVertex;
		}
		return parents[curVertex] = findRoot(parents[curVertex]);
	}

	private static void union(int first, int second) {
		int firstRoot = findRoot(first);
		int secondRoot = findRoot(second);
		if (firstRoot != secondRoot) {
			parents[firstRoot] = secondRoot;
		}
	}

}

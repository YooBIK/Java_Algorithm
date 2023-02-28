package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 프림
 */
public class Solution_3124_D4_최소스패닝트리_유병익2 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static boolean[] visit;
	static List<Edge>[] connection;

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

	public static void main(String[] args) throws IOException {

		int T = Integer.parseInt(bufferedReader.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int V = Integer.parseInt(stringTokenizer.nextToken());
			int E = Integer.parseInt(stringTokenizer.nextToken());
			connection = new ArrayList[V + 1];

			for (int i = 1; i <= V; i++) {
				connection[i] = new ArrayList<>();
			}

			for (int i = 0; i < E; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				int from = Integer.parseInt(stringTokenizer.nextToken());
				int to = Integer.parseInt(stringTokenizer.nextToken());
				int weight = Integer.parseInt(stringTokenizer.nextToken());
				connection[from].add(new Edge(from, to, weight));
				connection[to].add(new Edge(to, from, weight));
			}

			visit = new boolean[V + 1];

			PriorityQueue<Edge> priorityQueue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.weight, o2.weight));

			priorityQueue.addAll(connection[1]);
			visit[1] = true;
			int cnt = 1;
			long result = 0;

			while (cnt != V) {
				Edge curEdge = priorityQueue.poll();
				int curFrom = curEdge.from;
				int curTo = curEdge.to;
				int curWeight = curEdge.weight;

				if (visit[curTo]) {
					continue;
				}

				priorityQueue.addAll(connection[curTo]);
				visit[curTo] = true;
				result += curWeight;
				cnt++;
			}
			stringBuilder.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.println(stringBuilder);
	}

}

package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1167_G2_트리의지름_유병익 {

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

	static int V;
	static List<Edge>[] adjList;

	public static void main(String[] args) throws IOException {

		V = Integer.parseInt(bufferedReader.readLine());
		adjList = new ArrayList[V];

		for (int i = 0; i < V; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < V; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int curNode = Integer.parseInt(stringTokenizer.nextToken());
			while (true) {
				int next = Integer.parseInt(stringTokenizer.nextToken());
				if (next == -1) {
					break;
				}
				int weight = Integer.parseInt(stringTokenizer.nextToken());
				adjList[curNode - 1].add(new Edge(next - 1, weight));
			}
		}

		boolean[] visit = new boolean[V];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { 0, 0 });
		visit[0] = true;
		System.out.println(findMaxDistance(queue, visit));
	}

	private static int findMaxDistance(Queue<int[]> queue, boolean[] visit) {

		int result = Integer.MIN_VALUE;
		int start = -1;

		while (!queue.isEmpty()) {

			int[] curInfo = queue.poll();

			for (int i = 0; i < adjList[curInfo[0]].size(); i++) {
				Edge next = adjList[curInfo[0]].get(i);
				if (!visit[next.to]) {
					queue.offer(new int[] { next.to, curInfo[1] + next.weight });
					visit[next.to] = true;
				}
			}

			if (curInfo[1] > result) {
				result = curInfo[1];
				start = curInfo[0];
			}
		}

		visit = new boolean[V];
		queue.offer(new int[] { start, 0 });
		visit[start] = true;

		result = Integer.MIN_VALUE;

		while (!queue.isEmpty()) {
			int[] curInfo = queue.poll();

			for (int i = 0; i < adjList[curInfo[0]].size(); i++) {
				Edge next = adjList[curInfo[0]].get(i);
				if (!visit[next.to]) {
					queue.offer(new int[] { next.to, curInfo[1] + next.weight });
					visit[next.to] = true;
				}
			}

			if (curInfo[1] > result) {
				result = Math.max(result, curInfo[1]);
			}
		}
		return result;
	}

}
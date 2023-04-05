package Class.Live;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Live0403_Dijk {

	static class Edge {
		int vertex;
		int weight;

		@Override
		public String toString() {
			return "vertex=" + vertex + ", weight=" + weight;
		}

		public Edge(int vertex, int weight) {
			super();
			this.vertex = vertex;
			this.weight = weight;
		}
	}

	static int V, E;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		E = sc.nextInt();

		List<Edge>[] adjList = new ArrayList[V];

		for (int i = 0; i < V; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			adjList[sc.nextInt()].add(new Edge(sc.nextInt(), sc.nextInt()));
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.weight, o2.weight));

		boolean[] visit = new boolean[V];
		Edge[] D = new Edge[V];
		for (int i = 0; i < V; i++) {
			if (i == 0) {
				D[i] = new Edge(i, 0);
			} else {
				D[i] = new Edge(i, Integer.MAX_VALUE);
			}
			pq.add(D[i]);
		}

		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			for (Edge next : adjList[now.vertex]) {
				if (D[next.vertex].weight > D[now.vertex].weight + next.weight) {
					D[next.vertex].weight = D[now.vertex].weight + next.weight;
					pq.remove(D[next.vertex]);
					pq.add(D[next.vertex]);
				}
			}
		}

	}

}

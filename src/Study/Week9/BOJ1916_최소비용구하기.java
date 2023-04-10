package Study.Week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1916_최소비용구하기 {

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

	public static void main(String[] args) throws IOException {

		int N = Integer.parseInt(bufferedReader.readLine());
		int M = Integer.parseInt(bufferedReader.readLine());

		ArrayList<Edge>[] connectionList = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			connectionList[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int from = Integer.parseInt(stringTokenizer.nextToken()); // 시작점
			int to = Integer.parseInt(stringTokenizer.nextToken()); // 도착점
			int weight = Integer.parseInt(stringTokenizer.nextToken()); // 가중치

			connectionList[from].add(new Edge(to, weight));
		}

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());

		int start = Integer.parseInt(stringTokenizer.nextToken());
		int end = Integer.parseInt(stringTokenizer.nextToken());

		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.weight, o2.weight));

		boolean[] visit = new boolean[N + 1];
		int[] D = new int[N + 1];

		Arrays.fill(D, Integer.MAX_VALUE);

		D[start] = 0;
		pq.offer(new Edge(start, 0));

		while (!pq.isEmpty()) {
			Edge e = pq.poll();

			if (visit[e.to])
				continue;
			
			
			visit[e.to] = true;
			for (int i = 0; i < connectionList[e.to].size(); i++) {
				Edge next = connectionList[e.to].get(i);

				if (!visit[next.to] && D[e.to] + next.weight < D[next.to]) {
					D[next.to] = D[e.to] + next.weight;
					pq.offer(new Edge(next.to, D[next.to]));
				}
			}
		}

		System.out.println(D[end]);

	}

}

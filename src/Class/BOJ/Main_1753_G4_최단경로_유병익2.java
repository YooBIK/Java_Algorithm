package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1753_G4_최단경로_유병익2 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static final int INF = Integer.MAX_VALUE;
	static int V, E;
	static List<int[]>[] edgeList;
	static int[] distance;
	static boolean[] isUsed;

	public static void main(String[] args) throws IOException {

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		V = Integer.parseInt(stringTokenizer.nextToken());
		E = Integer.parseInt(stringTokenizer.nextToken());

		edgeList = new List[V + 1];
		distance = new int[V + 1];
		isUsed = new boolean[V + 1];

		int startVertex = Integer.parseInt(bufferedReader.readLine());
		for (int i = 1; i <= V; i++) {
			edgeList[i] = new ArrayList<>();
		}
		Arrays.fill(distance, INF);

		for (int i = 0; i < E; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int from = Integer.parseInt(stringTokenizer.nextToken());
			int to = Integer.parseInt(stringTokenizer.nextToken());
			int weight = Integer.parseInt(stringTokenizer.nextToken());
			edgeList[from].add(new int[] { to, weight });
		}

		PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
		priorityQueue.offer(new int[] { startVertex, 0 });
		distance[startVertex] = 0;

		while (!priorityQueue.isEmpty()) {

			int[] cur = priorityQueue.poll();
			int curNode = cur[0];
			int curDistance = cur[1];

			for (int[] edge : edgeList[curNode]) {
				if (distance[edge[0]] > curDistance + edge[1]) {
					distance[edge[0]] = curDistance + edge[1];
					priorityQueue.offer(new int[] { edge[0], distance[edge[0]] });
				}
			}
		}

		for (int i = 1; i <= V; i++) {
			if (distance[i] == INF) {
				stringBuilder.append("INF").append("\n");
			} else {
				stringBuilder.append(distance[i]).append("\n");
			}
		}

		System.out.println(stringBuilder);

	}

}
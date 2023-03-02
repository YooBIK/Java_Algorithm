package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_1753_G4_최단경로_유병익1 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static final int INF = Integer.MAX_VALUE;
	static int V, E;
	static HashMap<Integer, Integer>[] edgeList;
	static int[] distance;
	static boolean[] isUsed;

	public static void main(String[] args) throws IOException {

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		V = Integer.parseInt(stringTokenizer.nextToken());
		E = Integer.parseInt(stringTokenizer.nextToken());

		edgeList = new HashMap[V + 1];
		distance = new int[V + 1];
		isUsed = new boolean[V + 1];
		Arrays.fill(distance, INF);

		int startVertex = Integer.parseInt(bufferedReader.readLine());
		distance[startVertex] = 0;

		for (int i = 0; i < E; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int from = Integer.parseInt(stringTokenizer.nextToken());
			int to = Integer.parseInt(stringTokenizer.nextToken());
			int weight = Integer.parseInt(stringTokenizer.nextToken());
			if (edgeList[from] == null) {
				edgeList[from] = new HashMap<>();
			}

			if (edgeList[from].containsKey(to)) {
				if (edgeList[from].get(to) > weight) {
					edgeList[from].put(to, weight);
				}
			} else {
				edgeList[from].put(to, weight);
			}
		}

		for (Integer i : edgeList[startVertex].keySet()) {
			distance[i] = edgeList[startVertex].get(i);
		}
		int count = 1;
		isUsed[startVertex] = true;

		while (count != V) {
			int curNode = -1;
			int minValue = INF;
			for (int i = 1; i <= V; i++) {
				if (distance[i] < minValue && !isUsed[i]) {
					curNode = i;
					minValue = distance[i];
				}
			}
			
			if(curNode == -1 ) break;
			isUsed[curNode] = true;
			count++;
			if (edgeList[curNode] == null) {
				continue;
			}
			for (Integer i : edgeList[curNode].keySet()) {
				distance[i] = Math.min(distance[i], distance[curNode] + edgeList[curNode].get(i));
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

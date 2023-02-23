package Study.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * # 해결 로직 
 * - 각 노드의 연결 정보를 2차원 배열에 저장 -> 메모리 초과 -> ArrayList의 배열에, 해당 노드와 연결된 노드들의 번호를 저장
 * - 루트는 1이므로 1부터 탐색을 시작 
 * - 부모부터 탐색하므로, 현재 노드와 연결된 노드들 중 방문한 적 없는 노드는 자식 노드 - 이들의 부모를 배열에 저장
 */
public class BOJ11725 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static int N;
	static List<Integer>[] connection;
	static int[] result;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bufferedReader.readLine());
		connection = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			connection[i] = new ArrayList<>();
		}
		result = new int[N + 1];

		for (int i = 0; i < N - 1; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int start = Integer.parseInt(stringTokenizer.nextToken());
			int end = Integer.parseInt(stringTokenizer.nextToken());
			connection[start].add(end);
			connection[end].add(start);
		}

		bfs(1);
		for (int i = 2; i <= N; i++) {
			stringBuilder.append(result[i]).append("\n");
		}
		System.out.println(stringBuilder);
	}

	private static void bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(start);

		while (!queue.isEmpty()) {
			int curNode = queue.poll();
			List<Integer> curList = connection[curNode];

			for (int i = 0; i < curList.size(); i++) {
				int curNeighbor = curList.get(i);
				if (result[curNeighbor] == 0) {
					queue.offer(curNeighbor);
					result[curNeighbor] = curNode;
				}
			}
		}
	}

}

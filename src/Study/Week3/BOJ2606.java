package Study.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * #풀이 방법 - 시작 컴퓨터는 1번 컴퓨터임 
 * - 연결 정보를 2차원 배열로 변환해서 연결 여부를 확인 
 * - bfs 사용, queue에 offer될 때 마다 결과값 증가
 */
public class BOJ2606 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static int N, M;
	static boolean[][] connection;
	static boolean[] visit;
	static int result = 0;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bufferedReader.readLine());
		M = Integer.parseInt(bufferedReader.readLine());
		connection = new boolean[N][N];
		visit = new boolean[N];
		for (int i = 0; i < M; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int start = Integer.parseInt(stringTokenizer.nextToken()) - 1;
			int end = Integer.parseInt(stringTokenizer.nextToken()) - 1;
			connection[start][end] = true;
			connection[end][start] = true;
		}
		bfs(0);
		System.out.println(result);
	}

	private static void bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(start);
		visit[start] = true;
		while (!queue.isEmpty()) {
			int cur = queue.poll();

			for (int i = 0; i < N; i++) {
				if (connection[cur][i] == true && !visit[i]) {
					queue.offer(i);
					visit[i] = true;
					result++;
				}
			}
		}

	}

}

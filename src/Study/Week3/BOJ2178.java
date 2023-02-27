package Study.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * # 조건
 *  - 입력 지도의 좌상단에서 우하단으로 이동
 *  - 인접한 칸으로만 이동 가능
 *  - 1로 표시된 칸으로만 이동 가능
 * 
 * # 해결 로직 
 * 	최단 거리를 출력 -> 단순 BFS로 구현 0,0 에서 출발해서 R-1,C-1에 도착하면 BREAK!
 *  visit 배열에 방문 여부 + 거리를 입력
 *  	 방문 가능하면 이전 위치까지의 거리 +1을 저장
 * 
 */
public class BOJ2178 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static int[] dirRow = { 0, 0, 1, -1 };
	static int[] dirCol = { 1, -1, 0, 0 };

	static int R, C;
	static char[][] map;
	static int[][] visit;

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());

		R = Integer.parseInt(stringTokenizer.nextToken());
		C = Integer.parseInt(stringTokenizer.nextToken());

		map = new char[R][C];
		visit = new int[R][C];

		for (int i = 0; i < R; i++) {
			map[i] = bufferedReader.readLine().toCharArray();
		}

		bfs(0, 0);
		System.out.println(visit[R - 1][C - 1]);
	}

	private static void bfs(int startRow, int startCol) {

		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { startRow, startCol });
		visit[startRow][startCol] = 1;

		while (!queue.isEmpty()) {
			int[] curPosition = queue.poll();
			int curRow = curPosition[0];
			int curCol = curPosition[1];

			for (int i = 0; i < 4; i++) {
				int newRow = curRow + dirRow[i];
				int newCol = curCol + dirCol[i];
				if (newRow >= 0 && newRow < R && newCol >= 0 && newCol < C) {
					if (map[newRow][newCol] == '1' && visit[newRow][newCol] == 0) {
						queue.offer(new int[] { newRow, newCol });
						visit[newRow][newCol] = visit[curRow][curCol] + 1;
					}
				}
			}

		}
	}

}

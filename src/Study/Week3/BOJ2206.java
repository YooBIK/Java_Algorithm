package Study.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * # 조건 - 입력 지도의 좌상단에서 우하단으로 이동 - 인접한 칸으로만 이동 가능 - 1로 표시된 칸으로만 이동 가능
 * 
 * # 해결 로직 - BFS를 수행할 때, 좌표값과 함께 벽을 부술 수 있는지 함께 저장한다. - 벽을 만났을 때, 벽을 부술 수 있다면
 * 부수고 전진해본다. - 이를 반복하면서 최단 경로를 출력한다.
 * 
 */
public class BOJ2206 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static int[] dirRow = { 1, 0, -1, 0 };
	static int[] dirCol = { 0, 1, 0, -1 };

	static int R, C;
	static char[][] map;
	static int[][] visit;
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());

		R = Integer.parseInt(stringTokenizer.nextToken());
		C = Integer.parseInt(stringTokenizer.nextToken());

		map = new char[R][C];
		visit = new int[R][C];

		for (int i = 0; i < R; i++) {
			map[i] = bufferedReader.readLine().toCharArray();
		}

		visit[0][0] = 1;
		bfs(0, 0);

		if (result == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(result);
		}
	}

	private static void bfs(int curRow, int curCol) {

		Queue<int[]> queue = new ArrayDeque<>();

		int count = 0;
		for (int i = 0; i < 4; i++) {
			int newRow = curRow + dirRow[i];
			int newCol = curCol + dirCol[i];
			if (newRow >= 0 && newRow < R && newCol >= 0 && newCol < C) {
				if (visit[newRow][newCol] == 0) {
					if (map[newRow][newCol] == '0') {
						if (visit[newRow][newCol] == 0) {
							visit[newRow][newCol] = visit[curRow][curCol] + 1;
							visit[newRow][newCol] = 0;
						} else {
							if (visit[newRow][newCol] < visit[curRow][curCol] + 1) {

							}

						}

					}

				}
			}
		}

	}

}

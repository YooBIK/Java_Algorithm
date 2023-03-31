package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1194_G1_달이차오른다가자_유병익2 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder stringBuilder = new StringBuilder();
	static StringTokenizer stringTokenizer;

	static byte R, C;

	static char[][] map;
	static int[][][] visit;

	static byte[] dirRow = { 0, 0, -1, 1 };
	static byte[] dirCol = { -1, 1, 0, 0 };

	static HashMap<Character, List<int[]>> doorPositionMap = new HashMap<>();

	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		R = Byte.parseByte(stringTokenizer.nextToken());
		C = Byte.parseByte(stringTokenizer.nextToken());

		for (int i = 0; i < 6; i++) {
			doorPositionMap.put((char) ('A' + i), new ArrayList<>());
		}

		map = new char[R][C];
		visit = new int[R][C][65];

		int startRow = -1;
		int startCol = -1;

		for (int i = 0; i < R; i++) {
			map[i] = bufferedReader.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (map[i][j] == '0') {
					startRow = i;
					startCol = j;
					map[i][j] = '.';
				}

				if (map[i][j] >= 'A' && map[i][j] <= 'F') {
					doorPositionMap.get(map[i][j]).add(new int[] { i, j });
				}
			}
		}

		bfs(startRow, startCol);

		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer - 1);
		}
	}

	private static void bfs(int startRow, int startCol) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { startRow, startCol, 0 });
		visit[startRow][startCol][0]++;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int curRow = cur[0];
			int curCol = cur[1];
			int curKey = cur[2];

			if (map[curRow][curCol] == '1') {
				answer = Math.min(answer, visit[curRow][curCol][curKey]);
			}

			for (int i = 0; i < 4; i++) {
				int newRow = curRow + dirRow[i];
				int newCol = curCol + dirCol[i];
				if (boundaryCheck(newRow, newCol) && visit[newRow][newCol][curKey] == 0) {
					if (map[newRow][newCol] >= 'A' && map[newRow][newCol] <= 'F') {
						if (((1 << (map[newRow][newCol] - 'A')) & curKey) != 0) {
							visit[newRow][newCol][curKey] = visit[curRow][curCol][curKey] + 1;
							queue.offer(new int[] { newRow, newCol, curKey });
						}
					} else if ((map[newRow][newCol] >= 'a' && map[newRow][newCol] <= 'f')) {
						char key = map[newRow][newCol];
						if (((1 << (key - 'a')) & curKey) == 0) {
							int newKey = (1 << (key - 'a')) + curKey;
							visit[newRow][newCol][newKey] = visit[curRow][curCol][curKey] + 1;
							queue.offer(new int[] { newRow, newCol, newKey });
						} else {
							visit[newRow][newCol][curKey] = visit[curRow][curCol][curKey] + 1;
							queue.offer(new int[] { newRow, newCol, curKey });
						}
					} else if (map[newRow][newCol] == '.' || map[newRow][newCol] == '1') {
						visit[newRow][newCol][curKey] = visit[curRow][curCol][curKey] + 1;
						queue.offer(new int[] { newRow, newCol, curKey });
					}
				}

			}
		}
	}

	private static boolean boundaryCheck(int newRow, int newCol) {
		return newRow >= 0 && newRow < R && newCol >= 0 && newCol < C;
	}

}

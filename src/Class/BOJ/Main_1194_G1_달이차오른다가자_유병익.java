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

public class Main_1194_G1_달이차오른다가자_유병익 {

	static class CurInfo {
		int row;
		int col;
		int dist;

		public CurInfo(int row, int col, int dist) {
			this.row = row;
			this.col = col;
			this.dist = dist;
		}

		public CurInfo() {
			super();
		}
	}

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder stringBuilder = new StringBuilder();
	static StringTokenizer stringTokenizer;

	static byte R, C;

	static char[][] map;

	static byte[] dirRow = { 0, 0, -1, 1 };
	static byte[] dirCol = { -1, 1, 0, 0 };

	static HashMap<Character, List<int[]>> keyPositionMap = new HashMap<>();
	static HashMap<Character, List<int[]>> doorPositionMap = new HashMap<>();

	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		R = Byte.parseByte(stringTokenizer.nextToken());
		C = Byte.parseByte(stringTokenizer.nextToken());

		map = new char[R][C];

		for (int i = 0; i < 6; i++) {
			doorPositionMap.put((char) ('A' + i), new ArrayList<>());
			keyPositionMap.put((char) ('a' + i), new ArrayList<>());
		}

		CurInfo curInfo = new CurInfo();
		for (int i = 0; i < R; i++) {
			map[i] = bufferedReader.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (map[i][j] >= 'A' && map[i][j] <= 'F') {
					doorPositionMap.get(map[i][j]).add(new int[] { i, j });
				}

				if (map[i][j] >= 'a' && map[i][j] <= 'f') {
					keyPositionMap.get(map[i][j]).add(new int[] { i, j });
				}

				if (map[i][j] == '0') {
					curInfo.row = i;
					curInfo.col = j;
					curInfo.dist = 0;
					map[i][j] = '.';
				}
			}
		}
		
		bfs(curInfo, new boolean[R][C]);

		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
	}

	private static void bfs(CurInfo curInfo, boolean[][] visit) {
		Queue<CurInfo> queue = new ArrayDeque<>();
		queue.offer(curInfo);

		visit[curInfo.row][curInfo.col] = true;
		while (!queue.isEmpty()) {
			CurInfo cur = queue.poll();

			if (map[cur.row][cur.col] == '1') {
				answer = Math.min(answer, cur.dist);
				return;
			}

			if (cur.dist > answer) {
				return;
			}

			if (map[cur.row][cur.col] >= 'a' && map[cur.row][cur.col] <= 'f') {
				char key = map[cur.row][cur.col];

				List<int[]> keyPosition = keyPositionMap.get(key);
				List<int[]> doorPosition = doorPositionMap.get((char) (key + ('A' - 'a')));

				map[cur.row][cur.col] = '.';
				for (int[] curPos : doorPosition) {
					map[curPos[0]][curPos[1]] = '.';
				}

				for (int[] curPos : keyPosition) {
					map[curPos[0]][curPos[1]] = '.';
				}
				bfs(new CurInfo(cur.row, cur.col, cur.dist), new boolean[R][C]);

				for (int[] curPos : doorPosition) {
					map[curPos[0]][curPos[1]] = (char) (key + ('A' - 'a'));
				}

				for (int[] curPos : keyPosition) {
					map[curPos[0]][curPos[1]] = key;
				}
				continue;
			}

			for (int i = 0; i < 4; i++) {
				int newRow = cur.row + dirRow[i];
				int newCol = cur.col + dirCol[i];
				if (boundaryCheck(newRow, newCol, visit)) {
					visit[newRow][newCol] = true;
					queue.offer(new CurInfo(newRow, newCol, cur.dist + 1));
				}
			}
		}

	}

	private static boolean boundaryCheck(int newRow, int newCol, boolean[][] visit) {
		return newRow >= 0 && newRow < R && newCol >= 0 && newCol < C && !visit[newRow][newCol]
				&& (map[newRow][newCol] == '.' || (map[newRow][newCol] >= 'a' && map[newRow][newCol] <= 'f')
						|| map[newRow][newCol] == '1');
	}

}

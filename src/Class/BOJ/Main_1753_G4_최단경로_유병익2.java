package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main_1753_G4_최단경로_유병익2 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int N;
	static int[][] map;
	static boolean[][] visit;

	static int[] dirRow = { -1, 0, 0, 1 };
	static int[] dirCol = { 0, -1, 1, 0 };

	static int startRow, startCol, curSize;
	static int distance = 0;
	static int eatCount = 0;

	static HashMap<Integer, Integer> fishMap = new HashMap<>();

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(bufferedReader.readLine());

		map = new int[N][N];
		for (int i = 1; i <= 6; i++) {
			fishMap.put(i, 0);
		}

		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				if (map[i][j] == 9) {
					startRow = i;
					startCol = j;
					curSize = 2;
					map[i][j] = 0;
				}
				if (map[i][j] > 0 && map[i][j] < 7) {
					fishMap.put(map[i][j], fishMap.get(map[i][j]) + 1);
				}
			}
		}

		bfs();

		if (eatCount == 0 && curSize == 0) {
			System.out.println(0);
		} else {
			System.out.println(distance);
		}
	}

	private static void bfs() {
		visit = new boolean[N][N];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { startRow, startCol, 0 });

		visit[startRow][startCol] = true;
		int beforeDist = 0;
		TreeSet<int[]> treeSet = new TreeSet<>(
				(o1, o2) -> o1[0] == o2[0] ? Integer.compare(o1[1], o2[1]) : Integer.compare(o1[0], o2[0]));
		while (!queue.isEmpty()) {

			int canEat = 0;
			for (int i : fishMap.keySet()) {
				if (i < curSize) {
					canEat += fishMap.get(i);
				}
			}

			if (canEat == 0) {
				break;
			}

			int[] curInfo = queue.poll();
			int curRow = curInfo[0];
			int curCol = curInfo[1];
			int curDist = curInfo[2];

			System.out.println(curRow + " " + curCol);
			System.out.println(curDist + " " + beforeDist);

			if (curDist > beforeDist) {
				if (!treeSet.isEmpty()) {
					int[] nextInfo = treeSet.first();
					int nextRow = nextInfo[0];
					int nextCol = nextInfo[1];
					int dist = nextInfo[2];
					System.out.println(Arrays.toString(nextInfo));
					System.out.println(map[nextRow][nextCol]);
					fishMap.put(map[nextRow][nextCol], fishMap.get(map[nextRow][nextCol]) - 1);
					distance += dist;
					eatCount++;

					if (eatCount == curSize) {
						curSize++;
						eatCount = 0;
					}

					queue.clear();
					visit = new boolean[N][N];
					visit[nextRow][nextCol] = true;
					map[nextRow][nextCol] = 0;
					queue.offer(new int[] { nextRow, nextCol, 0 });
					beforeDist = 0;
					continue;
				} else {
					beforeDist = curDist;
					treeSet.clear();
				}
			}

			for (int i = 0; i < 4; i++) {
				int newRow = curRow + dirRow[i];
				int newCol = curCol + dirCol[i];
				if (check(newRow, newCol)) {
					if (map[newRow][newCol] == 0 || map[newRow][newCol] == curSize) {
						visit[newRow][newCol] = true;
						queue.add(new int[] { newRow, newCol, curDist + 1 });
					} else if (map[newRow][newCol] > 0 && map[newRow][newCol] < curSize) {
						treeSet.add(new int[] { newRow, newCol, curDist + 1 });
					}
				}
			}
		}
	}

	private static boolean check(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}

}

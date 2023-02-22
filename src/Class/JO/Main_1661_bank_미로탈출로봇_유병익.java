package Class.JO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1661_bank_미로탈출로봇_유병익 {

	static int[] dirRow = { 0, 0, 1, -1 };
	static int[] dirCol = { 1, -1, 0, 0 };

	static int row;
	static int col;

	static char[][] map;
	static int[][] visit;

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		col = Integer.parseInt(stringTokenizer.nextToken());
		row = Integer.parseInt(stringTokenizer.nextToken());
		map = new char[row][col];
		visit = new int[row][col];

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int startCol = Integer.parseInt(stringTokenizer.nextToken()) - 1;
		int startRow = Integer.parseInt(stringTokenizer.nextToken()) - 1;
		int destCol = Integer.parseInt(stringTokenizer.nextToken()) - 1;
		int destRow = Integer.parseInt(stringTokenizer.nextToken()) - 1;

		for (int i = 0; i < row; i++) {
			map[i] = bufferedReader.readLine().toCharArray();
		}

		/*
		 * BFS
		 */
		bfs(startRow, startCol, destRow, destCol);
		System.out.println(visit[destRow][destCol] - 1);

		/*
		 * DFS
		 */
//		visit[startRow][startCol] = 1;
//		dfs(startRow, startCol, destRow, destCol);
//		for (int[] arr : visit) {
//			System.out.println(Arrays.toString(arr));
//		}
//		System.out.println(visit[destRow][destCol] - 1);

	}

	public static void bfs(int startRow, int startCol, int destRow, int destCol) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(startRow);
		queue.offer(startCol);
		queue.offer(0);
		visit[startRow][startCol] = 1;

		while (!queue.isEmpty()) {
			int curRow = queue.poll();
			int curCol = queue.poll();

			if (curRow == destRow && curCol == destCol) {
				break;
			}

			for (int i = 0; i < 4; i++) {
				int newRow = curRow + dirRow[i];
				int newCol = curCol + dirCol[i];
				if (checkBFS(newRow, newCol)) {
					queue.offer(newRow);
					queue.offer(newCol);
					visit[newRow][newCol] = visit[curRow][curCol] + 1;
				}
			}
		}
	}

	public static void dfs(int curRow, int curCol, int targetRow, int targetCol) {
		if (curRow == targetRow && curCol == targetRow) {
			return;
		}

		for (int i = 0; i < 4; i++) {
			int newRow = curRow + dirRow[i];
			int newCol = curCol + dirCol[i];
			if (checkDFS(newRow, newCol)) {
				if (visit[newRow][newCol] == 0) {
					visit[newRow][newCol] = visit[curRow][curCol] + 1;
					dfs(newRow, newCol, targetRow, targetCol);
				} else {
					if (visit[newRow][newCol] > visit[curRow][curCol] + 1) {
						visit[newRow][newCol] = visit[curRow][curCol] + 1;
						dfs(newRow, newCol, targetRow, targetCol);
					}
				}
			}
		}
	}

	public static boolean checkBFS(int curRow, int curCol) {
		if (curRow >= 0 && curRow < row && curCol >= 0 && curCol < col && map[curRow][curCol] == '0'
				&& visit[curRow][curCol] == 0) {
			return true;
		}
		return false;
	}

	public static boolean checkDFS(int curRow, int curCol) {
		if (curRow >= 0 && curRow < row && curCol >= 0 && curCol < col && map[curRow][curCol] == '0') {
			return true;
		}
		return false;
	}

}

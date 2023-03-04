package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_1767_프로세서연결하기_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static int N;
	static int[] dirRow = { -1, 1, 0, 0 };
	static int[] dirCol = { 0, 0, -1, 1 };

	static int[][] map;
	static List<int[]> processList;

	static int curLines = Integer.MAX_VALUE;

	static List<int[]> selectedList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(bufferedReader.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(bufferedReader.readLine());
			processList = new ArrayList<>();
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				for (int j = 0; j < N; j++) {
					int cur = Integer.parseInt(stringTokenizer.nextToken());
					map[i][j] = cur;
					if (cur == 1 && (i != 0 && j != 0)) {
						processList.add(new int[] { i, j });
					}
				}
			}
			for (int i = processList.size(); i >= 0; i--) {
				curLines = Integer.MAX_VALUE;
				combination(0, 0, i);
				if (curLines != Integer.MAX_VALUE) {
					break;
				}
			}

			stringBuilder.append("#").append(testCase).append(" ").append(curLines).append("\n");
		}
		System.out.println(stringBuilder);
	}

	private static void combination(int startIndex, int depth, int targetDepth) {
		if (depth == targetDepth) {
			dfs(0, 0);
			return;
		}

		for (int i = startIndex; i < processList.size(); i++) {
			selectedList.add(processList.get(i));
			combination(i + 1, depth + 1, targetDepth);
			selectedList.remove(selectedList.size() - 1);
		}
	}

	private static void dfs(int depth, int lines) {
		if (depth == selectedList.size()) {
			curLines = Math.min(curLines, lines);
			return;
		}

		int[] curProcess = selectedList.get(depth);
		for (int i = 0; i < 4; i++) {
			if (canConnect(curProcess[0], curProcess[1], i)) {
				lines += connect(curProcess[0], curProcess[1], i, 0);
				dfs(depth + 1, lines);
				lines -= recover(curProcess[0], curProcess[1], i, 0);
			}
		}
	}

	private static int recover(int curRow, int curCol, int dir, int depth) {
		if (curRow == 0 || curRow == N - 1 || curCol == 0 || curCol == N - 1) {
			return depth;
		}

		int newRow = curRow + dirRow[dir];
		int newCol = curCol + dirCol[dir];
		map[newRow][newCol] = 0;
		return recover(newRow, newCol, dir, depth + 1);
	}

	private static int connect(int curRow, int curCol, int dir, int depth) {
		if (curRow == 0 || curRow == N - 1 || curCol == 0 || curCol == N - 1) {
			return depth;
		}

		int newRow = curRow + dirRow[dir];
		int newCol = curCol + dirCol[dir];
		map[newRow][newCol] = 2;
		return connect(newRow, newCol, dir, depth + 1);
	}

	private static boolean canConnect(int startRow, int startCol, int dir) {
		if (startRow == 0 || startCol == 0 || startRow == N - 1 || startCol == N - 1) {
			return true;
		}
		int newRow = startRow + dirRow[dir];
		int newCol = startCol + dirCol[dir];
		if (map[newRow][newCol] == 0) {
			return canConnect(newRow, newCol, dir);
		}
		return false;
	}
}

package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_SW역량테스트_Prob2_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static int N;
	static int[][] map;

	static HashSet<List<Integer>> hashSet = new HashSet<>();

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(bufferedReader.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {

			N = Integer.parseInt(bufferedReader.readLine());
			map = new int[N][N];
			int startRow = -1;
			int startCol = -1;
			for (int i = 0; i < N; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
					if (map[i][j] == 2) {
						startRow = i;
						startCol = j;
						map[i][j] = 0;
					}
				}
			}
			dfs(startRow, startCol, 0);
			stringBuilder.append("#").append(testCase).append(" ").append(hashSet.size()).append("\n");
			hashSet.clear();
		}
		System.out.println(stringBuilder);
	}

	private static void dfs(int curRow, int curCol, int depth) {

		if (depth == 3) {
			return;
		}

		int index = -1;
		for (int i = curRow - 1; i > 0; i--) { // 위쪽 방향 탐색
			if (map[i][curCol] == 1) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			for (int i = index - 1; i >= 0; i--) {
				int beforeVal = map[i][curCol];
				map[i][curCol] = 0;
				dfs(i, curCol, depth + 1);
				map[i][curCol] = beforeVal;
				if (map[i][curCol] == 1) { // 해당 위치가 1이면 여기까지 밖에 못감 그리고 먹을 수 있는 놈이니까 set에 추가
					hashSet.add(Arrays.asList(i, curCol));
					break;
				}
			}
		}

		index = -1;
		for (int i = curRow + 1; i < N - 1; i++) {// 아래쪽 방향 탐색
			if (map[i][curCol] == 1) {
				index = i;
				break;
			}
		}
		if (index != -1) { // 갈 수 있다면
			for (int i = index + 1; i < N; i++) {
				int beforeVal = map[i][curCol];
				map[i][curCol] = 0;
				dfs(i, curCol, depth + 1);
				map[i][curCol] = beforeVal;
				if (map[i][curCol] == 1) {
					hashSet.add(Arrays.asList(i, curCol));
					break;
				}
			}
		}

		index = -1;
		for (int i = curCol - 1; i > 0; i--) {// 왼쪽 방향 탐색
			if (map[curRow][i] == 1) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			for (int i = index - 1; i >= 0; i--) {
				int beforeVal = map[curRow][i];
				map[curRow][i] = 0;
				dfs(curRow, i, depth + 1);
				map[curRow][i] = beforeVal;
				if (map[curRow][i] == 1) {
					hashSet.add(Arrays.asList(curRow, i));
					break;
				}
			}
		}

		index = -1;
		for (int i = curCol + 1; i < N - 1; i++) {// 오른쪽 방향 탐색
			if (map[curRow][i] == 1) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			for (int i = index + 1; i < N; i++) {
				int beforeVal = map[curRow][i];
				map[curRow][i] = 0;
				dfs(curRow, i, depth + 1);
				map[curRow][i] = beforeVal;
				if (map[curRow][i] == 1) {
					hashSet.add(Arrays.asList(curRow, i));
					break;
				}
			}
		}
	}
}

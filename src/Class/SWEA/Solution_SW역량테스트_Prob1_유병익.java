package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_SW역량테스트_Prob1_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static int N;

	static int[] dirRow = { 0, 1, 0, -1 };
	static int[] dirCol = { 1, 0, -1, 0 };

	static int[][] map;
	static int answer;

	static List<int[]> appleList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(bufferedReader.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(bufferedReader.readLine());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
					if (map[i][j] != 0) {
						appleList.add(new int[] { i, j, map[i][j] });
					}
				}
			}
			Collections.sort(appleList, (o1, o2) -> Integer.compare(o1[2], o2[2]));
			answer = 0;
			bfs(0, 0);
			stringBuilder.append("#").append(testCase).append(" ").append(answer).append("\n");
			appleList.clear();
		}
		System.out.println(stringBuilder);
	}

	private static void bfs(int startRow, int startCol) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { startRow, startCol, 0, 0 }); // 행, 열, 방향, 회전 횟수

		int index = 0;
		int[] curTarget = appleList.get(index); // 현재 찾을 사과 좌표
		int targetRow = curTarget[0];
		int targetCol = curTarget[1];

		while (!queue.isEmpty()) {
			int[] curInfo = queue.poll();
			int curRow = curInfo[0];
			int curCol = curInfo[1];
			int curDir = curInfo[2];
			int curRotateCount = curInfo[3];

			if (curRow == targetRow && curCol == targetCol) {
				answer += curRotateCount;
				index++;
				if (index == appleList.size()) {
					return;
				}
				curTarget = appleList.get(index);
				targetRow = curTarget[0];
				targetCol = curTarget[1];
				queue.clear();
				queue.offer(new int[] { curRow, curCol, curDir, 0 });
				continue;
			}

			for (int i = 0; i < 2; i++) {
				int nextDir = (curDir + i) % 4;
				int newRow = curRow + dirRow[nextDir];
				int newCol = curCol + dirCol[nextDir];

				if (boundaryCheck(newRow, newCol)) {
					if (i == 0) {
						queue.add(new int[] { newRow, newCol, nextDir, curRotateCount });
					} else {
						queue.add(new int[] { newRow, newCol, nextDir, curRotateCount + 1 });
					}
				}
			}
		}
	}

	private static boolean boundaryCheck(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}
}

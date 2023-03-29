package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17070_G5_파이프옮기기1_유병익2 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int[] dirRow = { 0, 1, 1 };
	static int[] dirCol = { 1, 1, 0 };

	static int N;
	static int[][] map;

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(bufferedReader.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}

		int answer = 0;

		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { 0, 1, 0 });
		while (!queue.isEmpty()) {
			int[] curInfo = queue.poll();
			int curRow = curInfo[0];
			int curCol = curInfo[1];	
			int curDir = curInfo[2];
			if (curRow == N - 1 && curCol == N - 1) {
				answer++;
				continue;
			}

			for (int i = 0; i < 3; i++) {
				if ((curDir == 0 && i == 2) || (curDir == 2 && i == 0))
					continue;

				int newRow = curRow + dirRow[i];
				int newCol = curCol + dirCol[i];
				if (i == 1) {
					if (boundaryCheck(newRow, newCol) && boundaryCheck(curRow + 1, curCol)
							&& boundaryCheck(curRow, curCol + 1)) {
						queue.offer(new int[] { newRow, newCol, 1 });
					}
				} else {
					if (boundaryCheck(newRow, newCol)) {
						queue.offer(new int[] { newRow, newCol, i });
					}
				}

			}
		}
		System.out.println(answer);
	}

	private static boolean boundaryCheck(int row, int col) {

		return row >= 0 && row < N && col >= 0 && col < N && map[row][col] == 0;
	}

}

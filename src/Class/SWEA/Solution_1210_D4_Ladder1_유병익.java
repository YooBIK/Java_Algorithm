package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1210_D4_Ladder1_유병익 {

	static boolean[][] visit = new boolean[100][100];

	static int[][] map = new int[100][100];
	static StringTokenizer stringTokenizer;
	static StringBuilder builder = new StringBuilder();
	static int curTest;

	public static void recursive(int row, int col) {
		if (row == 0) {
			builder.append("#").append(curTest).append(" ").append(col).append("\n");
			return;
		}

		int leftCol = col - 1;
		int rightCol = col + 1;
		int upRow = row - 1;

		if (leftCol >= 0 && leftCol < 100 && map[row][leftCol] == 1 && !visit[row][leftCol]) {
			visit[row][col] = true;
			recursive(row, leftCol);
			visit[row][col] = false;
			return;
		}

		if (rightCol >= 0 && rightCol < 100 && map[row][rightCol] == 1 && !visit[row][rightCol]) {
			visit[row][col] = true;
			recursive(row, rightCol);
			visit[row][col] = false;
			return;
		}

		if (upRow >= 0 && upRow < 100 && map[upRow][col] == 1 && !visit[upRow][col]) {
			visit[row][col] = true;
			recursive(row - 1, col);
			visit[row][col] = false;
			return;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		for (int testCase = 1; testCase <= 10; testCase++) {
			curTest = Integer.parseInt(bufferedReader.readLine());
			int startRow = -1;
			int startCol = -1;
			for (int i = 0; i < 100; i++) {
				String input = bufferedReader.readLine();
				stringTokenizer = new StringTokenizer(input);
				for (int j = 0; j < 100; j++) {
					map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
					if (map[i][j] == 2) {
						startRow = i;
						startCol = j;
					}
				}
			}
			recursive(startRow, startCol);
		}
		System.out.println(builder.toString());
	}

}

package Class.SWEA;

import java.util.Scanner;

public class Solution_1954_D2_달팽이숫자_유병익 {

	static int[] dirRow = { 0, 1, 0, -1 };
	static int[] dirCol = { 1, 0, -1, 0 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder builder = new StringBuilder();
		int T = sc.nextInt();
		for (int testCase = 1; testCase <= T; testCase++) {
			int N = sc.nextInt();
			int[][] map = new int[N][N];
			int curRow = 0;
			int curCol = 0;
			map[curRow][curCol] = 1;
			int curDir = 0;
			for (int i = 2; i <= N * N; i++) {
				int newRow = curRow + dirRow[curDir % 4];
				int newCol = curCol + dirCol[curDir % 4];
				if ((newRow >= 0 && newRow < N && newCol >= 0 && newCol < N) && map[newRow][newCol] == 0) {
					curRow = newRow;
					curCol = newCol;
					map[newRow][newCol] = i;

				} else {
					curDir++;
					newRow = curRow + dirRow[curDir % 4];
					newCol = curCol + dirCol[curDir % 4];
					if ((newRow >= 0 && newRow < N && newCol >= 0 && newCol < N) && map[newRow][newCol] == 0) {
						curRow = newRow;
						curCol = newCol;
						map[newRow][newCol] = i;
					}
				}
			}
			builder.append("#").append(testCase).append("\n");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					builder.append(map[i][j]).append(" ");
				}
				builder.append("\n");
			}

		}
		System.out.println(builder.toString());

	}

}

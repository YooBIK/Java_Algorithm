package Study.Week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ17779 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int N;

	static int[][] map;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(bufferedReader.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}

		for (int i = 1; i < N - 1; i++) {
			for (int j = 1; j < N - 1; j++) {
				divideArea(i, j);
			}
		}

		System.out.println(answer);

	}

	private static void divideArea(int row, int col) {

		for (int i = 1; i < N - 1; i++) {
			for (int j = 1; j < N - 1; j++) {
				if (isValid(row, col, i, j)) {
					int leftRow = row + i;
					int leftCol = col - i;

					int bottomRow = row + i + j;
					int bottomCol = col - i + j;

					int rightRow = row + j;
					int rightCol = col + j;

					int[][] areaCheck = new int[N][N];

					for (int curRow = 0; curRow < N; curRow++) {
						for (int curCol = 0; curCol < N; curCol++) {
							if (curRow < leftRow && curCol <= col && curRow + curCol < row + col) {
								areaCheck[curRow][curCol] = 1;
							} else if (curRow <= rightRow && curCol > col && curRow - curCol < row - col) {
								areaCheck[curRow][curCol] = 2;
							} else if (curRow >= leftRow && curCol < bottomCol
									&& curRow - curCol > bottomRow - bottomCol) {
								areaCheck[curRow][curCol] = 3;
							} else if (curRow > rightRow && curCol >= bottomCol
									&& curRow + curCol > bottomRow + bottomCol) {
								areaCheck[curRow][curCol] = 4;
							} else {
								areaCheck[curRow][curCol] = 5;
							}
						}
					}
					answer = Math.min(getCurResult(areaCheck), answer);
				}
			}
		}

	}

	private static int getCurResult(int[][] areaCheck) {
		int[] count = new int[5];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				count[areaCheck[i][j] - 1] += map[i][j];
			}
		}

		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 5; i++) {
			max = Math.max(count[i], max);
			min = Math.min(count[i], min);
		}

		return max - min;

	}

	private static boolean isValid(int row, int col, int i, int j) {

		int leftRow = row + i;
		int leftCol = col - i;

		int bottomRow = row + i + j;
		int bottomCol = col - i + j;

		int rightRow = row + j;
		int rightCol = col + j;

		return boundaryCheck(leftRow, leftCol) && boundaryCheck(bottomRow, bottomCol)
				&& boundaryCheck(rightRow, rightCol);
	}

	private static boolean boundaryCheck(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}

}

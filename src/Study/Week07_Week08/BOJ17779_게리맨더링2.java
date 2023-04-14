package Study.Week07_Week08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이 방법
 * - 나눌 수 있는 모든 경우마다 최대 최소의 차이를 구하고 그 차이가 가장 작은 값을 찾으면 된다.
 * - 범위를 나누는 것이 어려웠다. -> 대각선 위치의 좌표들의 인덱스 관계를 찾아서 구역을 나눠주었다.
 */
public class BOJ17779_게리맨더링2 {

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
			for (int j = 1; j < N - 1; j++) { // x,y를 i,j로 뒀을때, 나눌 수 있는 모든 구역을 찾는다.
				divideArea(i, j);
			}
		}

		System.out.println(answer);

	}

	private static void divideArea(int row, int col) {

		for (int i = 1; i < N - 1; i++) { // 문제에서 d1, 1~N-2
			for (int j = 1; j < N - 1; j++) { // 문제에서 d2, 1~N-2
				if (isValid(row, col, i, j)) { // 해당 d1,d2로 분할 가능한 경우
					int leftRow = row + i; // 나머지 꼭지점들을 구한다.
					int leftCol = col - i;

					int bottomRow = row + i + j;
					int bottomCol = col - i + j;

					int rightRow = row + j;
					int rightCol = col + j;

					int[][] areaCheck = new int[N][N];

					/**
					 * 전체 맵을 순회하면서 인덱스 범위를 토대로 구역을 분할한다.
					 */
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
					// 정답 갱신 (최소값으로!)
					answer = Math.min(getCurResult(areaCheck), answer);
				}
			}
		}

	}

	// count 배열을 사용해 최댓값과 최솟값을 구하고, 그 차이를 반환한다.
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

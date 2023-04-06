package Study.Week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이 해당 위치를 덮을 수 있는 색종이로 덮는다 (가장 큰 색종이부터 덮어본다.) 덮을 수 있는 경우가 있다면 다음으로 덮을 구역을
 * 탐색한다. 탐색이 끝났는데 덮은 영역의 수가 덮을 영역의 수와 일치하면 결과를 업데이트하고 아니면 그냥 종료해버린다.
 */
public class BOJ17136_색종이붙히기 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static int answer = Integer.MAX_VALUE;
	static int paperArea = 0;
	static int[] paperArray = { 0, 5, 5, 5, 5, 5 };
	static int[][] map = new int[10][10];

	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 10; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				if (map[i][j] == 1) {
					paperArea++;
				}
			}
		}

		dfs(0, 0);
		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}

	}

	private static void dfs(int depth, int coveredArea) {

		if (depth > answer) {
			return;
		}

		if (coveredArea == paperArea) {
			answer = Math.min(answer, depth);
			return;
		}

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (map[i][j] == 1) {
					for (int size = 5; size >= 1; size--) {
						if (canCover(i, j, size) && paperArray[size] > 0) { // 해당 사이즈의 종이로 덮을 수 있고, 종이가 남아 있다면,
							cover(i, j, size);
							paperArray[size]--;
							dfs(depth + 1, coveredArea + (size * size));
							recover(i, j, size);
							paperArray[size]++;
						}
					}
					return;
				}
			}
		}
	}

	private static void cover(int startRow, int startCol, int size) { // 덮기
		for (int i = startRow; i < startRow + size; i++) {
			for (int j = startCol; j < startCol + size; j++) {
				map[i][j] = 0;
			}
		}
	}

	private static void recover(int startRow, int startCol, int size) { // 원상복구
		for (int i = startRow; i < startRow + size; i++) {
			for (int j = startCol; j < startCol + size; j++) {
				map[i][j] = 1;
			}
		}
	}

	private static boolean canCover(int row, int col, int size) { // 덮을 수 있는지 확인
		if (row + size > 10 || col + size > 10)
			return false;
		for (int i = row; i < row + size; i++) {
			for (int j = col; j < col + size; j++) {
				if (map[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

}

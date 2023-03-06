package Study.Week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ17136 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static int answer = Integer.MAX_VALUE;
	static int paperArea = 0;
	static boolean findFlag = false;
	static int[] paperArray = { 0, 5, 5, 5, 5, 5 };
	static int[][] map = new int[10][10];
	static List<Integer>[][] canDo = new ArrayList[10][10];

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

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				canDo[i][j] = new ArrayList<>();
				for (int k = 5; k >= 1; k--) {
					if (canCover(i, j, k)) {
						canDo[i][j].add(k);
					}
				}
			}
		}

		dfs(0,0);
		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}

	}

	private static void dfs(int depth, int coveredArea) {
		
		if(depth > answer) {
			return;
		}
		
		if (coveredArea == paperArea) {
			answer = Math.min(answer, depth);
			return;
		}

//		System.out.println(depth);
//		System.out.println(Arrays.toString(paperArray));
//		for (int[] arr : map) {
//			System.out.println(Arrays.toString(arr));
//		}
//		System.out.println();
//		try {
//			Thread.sleep(500);
//		} catch (InterruptedException e) {
//			throw new RuntimeException(e);
//		}

		boolean flag = false;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (map[i][j] == 1) {
					for (int size = 5; size >= 1; size--) {
						if (canCover(i, j, size) && paperArray[size] > 0) {
							flag = true;
							cover(i, j, size);
							paperArray[size]--;
							dfs(depth + 1, coveredArea + (size * size));
							recover(i, j, size);
							paperArray[size]++;
						}
					}
					if (flag) {
						return;
					}
				}
			}
		}
	}

	private static void cover(int startRow, int startCol, int size) {
		for (int i = startRow; i < startRow + size; i++) {
			for (int j = startCol; j < startCol + size; j++) {
				map[i][j] = 0;
			}
		}
	}

	private static void recover(int startRow, int startCol, int size) {
		for (int i = startRow; i < startRow + size; i++) {
			for (int j = startCol; j < startCol + size; j++) {
				map[i][j] = 1;
			}
		}
	}

	private static boolean canCover(int row, int col, int size) {
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

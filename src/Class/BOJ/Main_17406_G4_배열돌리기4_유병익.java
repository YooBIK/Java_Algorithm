package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_17406_G4_배열돌리기4_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder stringBuilder = new StringBuilder();
	static StringTokenizer stringTokenizer;

	static List<int[]> infoList = new ArrayList<>();
	static int[][] map;
	static int N;
	static int R;
	static int C;

	static int result = Integer.MAX_VALUE;
	static boolean[] isUsed;

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		R = Integer.parseInt(stringTokenizer.nextToken());
		C = Integer.parseInt(stringTokenizer.nextToken());
		map = new int[R][C];
		N = Integer.parseInt(stringTokenizer.nextToken());
		isUsed = new boolean[N];

		for (int i = 0; i < R; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int r = Integer.parseInt(stringTokenizer.nextToken()) - 1;
			int c = Integer.parseInt(stringTokenizer.nextToken()) - 1;
			int s = Integer.parseInt(stringTokenizer.nextToken());
			infoList.add(new int[] { r, c, s });

		}
		dfs(0);
		System.out.println(result);

	}

	private static void rotate(int LTRow, int LTCol, int RDRow, int RDCol) {
		if ((LTRow == RDRow && LTCol == RDCol) || (LTRow > RDRow && LTCol > RDCol)) {
			return;
		}
		int rt = map[LTRow][RDCol];
		int rd = map[RDRow][RDCol];
		int ld = map[RDRow][LTCol];

		for (int i = RDCol; i > LTCol; i--) {
			map[LTRow][i] = map[LTRow][i - 1];
		}
		for (int i = RDRow; i > LTRow; i--) {
			map[i][RDCol] = map[i - 1][RDCol];
		}
		for (int i = LTCol; i < RDCol; i++) {
			map[RDRow][i] = map[RDRow][i + 1];
		}
		for (int i = LTRow; i < RDRow; i++) {
			map[i][LTCol] = map[i + 1][LTCol];
		}
		map[LTRow + 1][RDCol] = rt;
		map[RDRow][RDCol - 1] = rd;
		map[RDRow - 1][LTCol] = ld;

		rotate(LTRow + 1, LTCol + 1, RDRow - 1, RDCol - 1);

	}

	private static void rotateReverse(int LTRow, int LTCol, int RDRow, int RDCol) {
		if ((LTRow == RDRow && LTCol == RDCol) || (LTRow > RDRow && LTCol > RDCol)) {
			return;
		}

		int lt = map[LTRow][LTCol];
		int rd = map[RDRow][RDCol];
		int ld = map[RDRow][LTCol];

		for (int i = LTCol; i < RDCol; i++) {
			map[LTRow][i] = map[LTRow][i + 1];
		}
		for (int i = RDRow; i > LTRow; i--) {
			map[i][LTCol] = map[i - 1][LTCol];
		}
		for (int i = RDCol; i > LTCol; i--) {
			map[RDRow][i] = map[RDRow][i - 1];
		}
		for (int i = LTRow; i < RDRow; i++) {
			map[i][RDCol] = map[i + 1][RDCol];
		}

		map[LTRow + 1][LTCol] = lt;
		map[RDRow][LTCol + 1] = ld;
		map[RDRow - 1][RDCol] = rd;

		rotateReverse(LTRow + 1, LTCol + 1, RDRow - 1, RDCol - 1);
	}

	private static void dfs(int depth) {
		if (depth == N) {
			result = Math.min(result, getMinValue());
		}

		for (int i = 0; i < infoList.size(); i++) {
			if (!isUsed[i]) {
				int r, c, s;
				r = infoList.get(i)[0];
				c = infoList.get(i)[1];
				s = infoList.get(i)[2];

				rotate(r - s, c - s, r + s, c + s);
				isUsed[i] = true;
				dfs(depth + 1);
				isUsed[i] = false;
				rotateReverse(r - s, c - s, r + s, c + s);
			}
		}

	}

	private static int getMinValue() {
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < R; i++) {
			int curValue = 0;
			for (int j = 0; j < C; j++) {
				curValue += map[i][j];
			}
			result = Math.min(result, curValue);
		}
		return result;
	}
}

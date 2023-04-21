package Study.Week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ15683_감시 {

	static class CCTV {
		int row;
		int col;
		int curDir;
		int number;

		public CCTV(int row, int col, int number, int curDir) {
			this.row = row;
			this.col = col;
			this.curDir = curDir;
			this.number = number;
		}
	}

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int R, C;

	static int[][] map;

	// 우 하 좌 상
	static int[] dirRow = { 0, 1, 0, -1 };
	static int[] dirCol = { 1, 0, -1, 0 };

	static List<CCTV> CCTVList = new ArrayList<>();
	static int answer = Integer.MAX_VALUE;
	static int zeroCount = 0;

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		R = Integer.parseInt(stringTokenizer.nextToken());
		C = Integer.parseInt(stringTokenizer.nextToken());
		map = new int[R][C];

		for (int i = 0; i < R; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				if (map[i][j] > 0 && map[i][j] < 6) {
					CCTVList.add(new CCTV(i, j, 0, map[i][j])); // Row, Col, Dir, 종류;
				}

				if (map[i][j] == 0) {
					zeroCount++;
				}
			}
		}

		subSet(0);

	}

	private static void subSet(int depth) {
		if (depth == CCTVList.size()) {

			answer = Math.min(answer, zeroCount);
			return;
		}

		for (int i = 0; i < 4; i++) {
			CCTVList.get(i).curDir = i;
			remove(CCTVList.get(i));
			subSet(depth + 1);
			recover(CCTVList.get(i));
		}

	}

	private static void recover(CCTV cctv) {

	}

	private static void remove(CCTV cctv) {

	}

}

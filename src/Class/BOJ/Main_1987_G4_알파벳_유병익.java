package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1987_G4_알파벳_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int R;
	static int C;
	static char[][] map;
	static boolean[] check = new boolean[26];

	static int[] dirRow = { 0, 0, 1, -1 };
	static int[] dirCol = { 1, -1, 0, 0 };

	static int result = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		R = Integer.parseInt(stringTokenizer.nextToken());
		C = Integer.parseInt(stringTokenizer.nextToken());

		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = bufferedReader.readLine().toCharArray();
		}

		check[map[0][0] - 'A'] = true;
		dfs(0, 0, 1);
		System.out.println(result);
	}

	public static void dfs(int curRow, int curCol, int depth) {

		result = Math.max(result, depth);

		for (int i = 0; i < 4; i++) {
			int newRow = curRow + dirRow[i];
			int newCol = curCol + dirCol[i];
			if (newRow >= 0 && newRow < R && newCol >= 0 && newCol < C) {
				char curAlphabet = map[newRow][newCol];
				if (!check[curAlphabet - 'A']) {
					check[curAlphabet - 'A'] = true;
					dfs(newRow, newCol, depth + 1);
					check[curAlphabet - 'A'] = false;
				}
			}
		}
	}

}

package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_3109_G2_빵집_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int[] dirRow = { -1, 0, 1 };
	static int R;
	static int C;
	static char[][] map;
	static boolean[][] visit;
	static boolean flag = false;
	static int result = 0;

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		R = Integer.parseInt(stringTokenizer.nextToken());
		C = Integer.parseInt(stringTokenizer.nextToken());

		map = new char[R][C];
		visit = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			map[i] = bufferedReader.readLine().toCharArray();
		}

		for (int i = 0; i < R; i++) {
			flag = false;
			dfs(i, 0);
		}

		System.out.println(result);

	}

	public static void dfs(int curRow, int curCol) {

		visit[curRow][curCol] = true;

		if (curCol == C - 1) {
			result++;
			flag = true;
			return;
		}

		for (int i = 0; i < 3; i++) {
			int newRow = curRow + dirRow[i];
			int newCol = curCol + 1;
			if (newRow >= 0 && newRow < R && newCol >= 0 && newCol < C) {
				if (!visit[newRow][newCol] && map[newRow][newCol] == '.') {
					dfs(newRow, newCol);
					if (flag) {
						return;
					}
				}
			}
		}
		visit[curRow][curCol] = false;
	}

}

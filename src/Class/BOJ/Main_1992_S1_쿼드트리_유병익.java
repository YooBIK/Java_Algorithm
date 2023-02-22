package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1992_S1_쿼드트리_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static int N;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bufferedReader.readLine());
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = bufferedReader.readLine().toCharArray();
		}
		divide(0, 0, N);
		System.out.println(stringBuilder);
	}

	public static void divide(int startRow, int startCol, int size) {

		if (check(startRow, startCol, size)) {
			stringBuilder.append(map[startRow][startCol]);
			return;
		} else {
			stringBuilder.append("(");
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					int newSize = size / 2;
					int newRow = startRow + (i * newSize);
					int newCol = startCol + (j * newSize);
					divide(newRow, newCol, newSize);
				}
			}
			stringBuilder.append(")");
		}
	}

	public static boolean check(int startRow, int startCol, int size) {
		char start = map[startRow][startCol];
		for (int i = startRow; i < startRow + size; i++) {
			for (int j = startCol; j < startCol + size; j++) {
				if (map[i][j] != start) {
					return false;
				}
			}
		}
		return true;
	}

}

package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4014_모의_활주로건설_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static int N, X;
	static int[][] map;

	public static void main(String[] args) throws IOException {

		int T = Integer.parseInt(bufferedReader.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			N = Integer.parseInt(stringTokenizer.nextToken());
			X = Integer.parseInt(stringTokenizer.nextToken());
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				}
			}

			int answer = 0;
			for (int i = 0; i < N; i++) {
				if (rowCheck(i)) {
					answer++;
				}

				if (colCheck(i)) {
					answer++;
				}
			}
			stringBuilder.append("#").append(testCase).append(" ").append(answer).append("\n");

		}
		System.out.println(stringBuilder);

	}

	private static boolean colCheck(int curCol) {
		boolean[] build = new boolean[N];
		for (int i = 0; i < N - 1; i++) {
			if (map[i][curCol] == map[i + 1][curCol]) {
				continue;
			} else if (map[i][curCol] == map[i + 1][curCol] + 1) { // 다음칸이 1 작을 때
				if (i + X >= N) {
					return false;
				}

				for (int j = i + 1; j <= i + X - 1; j++) { // i+1 ~ i+X 까지 평평한지 체크
					if (map[j][curCol] != map[j + 1][curCol]) { // 평평하지 않으니 FALSE!!
						return false;
					}
				}

				for (int j = i + 1; j <= i + X; j++) { // 평평한 경우 경사로 체크
					build[j] = true;
				}
				i = i + X - 1;
			} else if (map[i][curCol] == map[i + 1][curCol] - 1) { // 다음칸이 1 클 때,
				if (i - X + 1 < 0) {
					return false;
				}
				int curHeight = map[i][curCol];
				for (int j = i; j >= i - X + 1; j--) {
					// 평평하지 않거나, 이미 다른 경사로가 있음
					if (map[j][curCol] != curHeight || build[j]) {
						return false;
					} else {
						build[j] = true;
					}
				}
			} else {
				return false;
			}
		}
		return true;
	}

	private static boolean rowCheck(int curRow) {
		boolean[] build = new boolean[N];
		for (int i = 0; i < N - 1; i++) {
			if (map[curRow][i] == map[curRow][i + 1]) {
				continue;
			} else if (map[curRow][i] == map[curRow][i + 1] + 1) { // 다음칸이 1 작을 때
				if (i + X >= N) {
					return false;
				}

				for (int j = i + 1; j < i + X; j++) { // i+1 ~ i+X 까지 평평한지 체크
					if (map[curRow][j] != map[curRow][j + 1]) { // 평평하지 않으니 FALSE!!
						return false;
					}
				}

				for (int j = i + 1; j <= i + X; j++) { // 평평한 경우 경사로 체크
					build[j] = true;
				}
				i = i + X - 1;
			} else if (map[curRow][i] == map[curRow][i + 1] - 1) { // 다음칸이 1 클 때,
				if (i - X + 1 < 0) {
					return false;
				}
				int curHeight = map[curRow][i];
				for (int j = i; j >= i - X + 1; j--) {
					// 평평하지 않거나, 이미 다른 경사로가 있음
					if (map[curRow][j] != curHeight || build[j]) {
						return false;
					} else {
						build[j] = true;
					}
				}
			} else {
				return false;
			}
		}
		return true;
	}

}

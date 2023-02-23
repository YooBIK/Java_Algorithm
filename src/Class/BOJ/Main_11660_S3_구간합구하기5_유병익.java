package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11660_S3_구간합구하기5_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static int n;
	static int m;

	static int[][] sums;

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		sums = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 1; j <= n; j++) {
				sums[i][j] = sums[i - 1][j] + sums[i][j - 1] - sums[i - 1][j - 1]
						+ Integer.parseInt(stringTokenizer.nextToken());
			}
		}

		for (int i = 0; i < m; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int startRow = Integer.parseInt(stringTokenizer.nextToken());
			int startCol = Integer.parseInt(stringTokenizer.nextToken());

			int endRow = Integer.parseInt(stringTokenizer.nextToken());
			int endCol = Integer.parseInt(stringTokenizer.nextToken());

			stringBuilder.append(sums[endRow][endCol] - sums[endRow][startCol - 1] - sums[startRow - 1][endCol]
					+ sums[startRow - 1][startCol - 1]).append("\n");
		}
		System.out.println(stringBuilder.toString());
	}

}

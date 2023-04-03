package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1263_D6_사람네트워크2_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static final int INF = 987654321;

	static int N;
	static boolean[][] isConnected;
	static int[][] shortestDist;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(bufferedReader.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			N = Integer.parseInt(stringTokenizer.nextToken());

			isConnected = new boolean[N][N];
			shortestDist = new int[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int to = Integer.parseInt(stringTokenizer.nextToken());
					if (to == 1) {
						isConnected[i][j] = true;
					}
					if (i != j) {
						if (to == 0) {
							shortestDist[i][j] = INF;
						} else {
							shortestDist[i][j] = 1;
						}
					}
				}
			}

			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						shortestDist[i][j] = Math.min(shortestDist[i][j], shortestDist[i][k] + shortestDist[k][j]);
					}
				}
			}

			int minVal = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int j = 0; j < N; j++) {
					sum += shortestDist[i][j];
				}
				minVal = Math.min(minVal, sum);
			}

			stringBuilder.append("#").append(testCase).append(" ").append(minVal).append("\n");
		}
		System.out.println(stringBuilder);

	}

}

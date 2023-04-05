package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_9205_G5_맥주마시면서걸어가기_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static int N;

	static final int INF = 987654321;

	public static void main(String[] args) throws IOException {

		int T = Integer.parseInt(bufferedReader.readLine());
		for (int testCase = 0; testCase < T; testCase++) {

			N = Integer.parseInt(bufferedReader.readLine());

			List<int[]> nodeList = new ArrayList<>();

			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int curRow = Integer.parseInt(stringTokenizer.nextToken());
			int curCol = Integer.parseInt(stringTokenizer.nextToken());
			nodeList.add(new int[] { curRow, curCol });

			for (int i = 1; i <= N; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				int r = Integer.parseInt(stringTokenizer.nextToken());
				int c = Integer.parseInt(stringTokenizer.nextToken());
				nodeList.add(new int[] { r, c });
			}

			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int targetRow = Integer.parseInt(stringTokenizer.nextToken());
			int targetCol = Integer.parseInt(stringTokenizer.nextToken());
			nodeList.add(new int[] { targetRow, targetCol });

			int[][] dp = new int[N + 2][N + 2];
			for (int i = 0; i < nodeList.size(); i++) {
				int[] fisrtNode = nodeList.get(i);
				for (int j = 0; j < nodeList.size(); j++) {
					if (i == j)
						continue;

					int[] secondNode = nodeList.get(j);
					int dist = Math.abs(fisrtNode[0] - secondNode[0]) + Math.abs(fisrtNode[1] - secondNode[1]);
					if (dist > 1000) {
						dp[i][j] = INF;
						dp[j][i] = INF;
					} else {
						dp[i][j] = dist;
						dp[j][i] = dist;
					}

				}
			}

			for (int k = 0; k < N + 2; k++) {
				for (int i = 0; i < N + 2; i++) {
					if (k == i)
						continue;
					for (int j = 0; j < N + 2; j++) {
						if (j == i || j == k)
							continue;
						dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
					}
				}
			}
			if (dp[0][N + 1] == INF) {
				stringBuilder.append("sad").append("\n");
			} else {
				stringBuilder.append("happy").append("\n");
			}
		}
		System.out.println(stringBuilder);
	}
}

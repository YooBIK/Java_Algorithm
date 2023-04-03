package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ18111 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static final int REMOVE_TIME = 2;
	static final int COVER_TIME = 1;

	static int N, M, B;
	static int[][] map;
	static int[] count = new int[257];

	public static void main(String[] args) throws IOException {

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());
		B = Integer.parseInt(stringTokenizer.nextToken());

		map = new int[N][M];

		int sum = 0;

		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				count[map[i][j]]++;

				sum += map[i][j];
			}
		}

		int minTargetHeight = sum / (N * M);
		int maxTargetHeight = (sum + B) / (N * M);

		int minTime = Integer.MAX_VALUE;
		int maxHeight = Integer.MIN_VALUE;

		for (int targetHeight = minTargetHeight; targetHeight <= maxTargetHeight; targetHeight++) {
			int time = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] > targetHeight) {
						time += (map[i][j] - targetHeight) * REMOVE_TIME;
					}

					if (map[i][j] < targetHeight) {
						time += (targetHeight - map[i][j]) * COVER_TIME;
					}
				}
			}
			if (time <= minTime) {
				minTime = time;
				maxHeight = targetHeight;
			}

		}
		System.out.println(minTime + " " + maxHeight);
	}

}

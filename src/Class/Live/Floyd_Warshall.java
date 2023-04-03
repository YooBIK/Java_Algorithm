package Class.Live;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Floyd_Warshall {

	static final int INF = 987654321;
	static int N;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bufferedReader.readLine());
		map = new int[N][N];
		StringTokenizer stringTokenizer;

		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				if (i != j && map[i][j] == 0) {
					map[i][j] = INF; // 연결되지 않은 경우
				}
			}
		}

		for (int k = 0; k < N; k++) { // 경유지
			for (int i = 0; i < N; i++) { // 출발지
//				if (i == k)
//					continue;
				for (int j = 0; j < N; j++) { // 도착지
//					if (i == j || j == k)
//						continue;
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);

				}
			}
		}
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
	}
}
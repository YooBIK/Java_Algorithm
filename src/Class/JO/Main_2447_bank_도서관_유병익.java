package Class.JO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2447_bank_도서관_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static int N;
	static int[][] infos;

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(bufferedReader.readLine());
		infos = new int[N][2];
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			infos[i][0] = Integer.parseInt(stringTokenizer.nextToken());
			infos[i][1] = Integer.parseInt(stringTokenizer.nextToken());
		}
		Arrays.sort(infos, (o1, o2) -> o1[0] == o2[0] ? Integer.compare(o1[1], o2[1]) : Integer.compare(o1[0], o2[0]));

		int maxUseTime = Integer.MIN_VALUE;
		int maxEmptyTime = Integer.MIN_VALUE;

		int curStart = 1;
		int curEnd = 1;
		for (int i = 0; i < N; i++) {
			if (infos[i][0] <= curEnd) {
				if (infos[i][1] > curEnd) {
					curEnd = infos[i][1];
					maxUseTime = Math.max(maxUseTime, curEnd - curStart);
				}
			} else {
				maxEmptyTime = Math.max(maxEmptyTime, infos[i][0] - curEnd);
				curStart = infos[i][0];
				curEnd = infos[i][1];
			}
		}
		System.out.println(maxUseTime + " " + maxEmptyTime);
	}

}

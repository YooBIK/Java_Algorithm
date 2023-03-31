package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_3307_D3_최장증가부분수열_유병익_BS {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	public static void main(String[] args) throws IOException {

		int T = Integer.parseInt(bufferedReader.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {

			int N = Integer.parseInt(bufferedReader.readLine());
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());

			int[] numbers = new int[N];
			int[] C = new int[N];
			int k = 0;
			for (int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
			}
			C[k++] = numbers[0];

			for (int i = 1; i < N; i++) {
				int result = Arrays.binarySearch(C, 0, k, numbers[i]);
				result = Math.abs(result) - 1;
				C[result] = numbers[i];
				if (k == result) {
					k++;
				}
			}
			stringBuilder.append("#").append(testCase).append(" ").append(k).append("\n");
		}
		System.out.println(stringBuilder);
	}

}

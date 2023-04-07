package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2042_G1_구간합구하기_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static int N, M, K;

	static long[] numberArray;
	static int[] indexArray;

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());

		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());
		K = Integer.parseInt(stringTokenizer.nextToken());

		numberArray = new long[N * 2 + 1];
		indexArray = new int[N * 2 + 1];

		for (int i = 1; i < N * 2 + 1; i++) {
			int bit = 1;
			int count = 1;
			while ((bit & i) == 0) {
				bit <<= 1;
				count = bit;
			}
			indexArray[i] = count;
		}

		for (int i = 1; i <= N; i++) {
			long curNumber = Long.parseLong(bufferedReader.readLine());

			if (indexArray[i] == 1) {
				numberArray[i] = curNumber;
			} else {
				update(i, curNumber);
			}
		}

		for (int i = 0; i < M + K; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int command = Integer.parseInt(stringTokenizer.nextToken());
			if (command == 1) {
				int A = Integer.parseInt(stringTokenizer.nextToken());
				long B = Long.parseLong(stringTokenizer.nextToken());
				update(A, B);
			} else {
				int A = Integer.parseInt(stringTokenizer.nextToken());
				int B = Integer.parseInt(stringTokenizer.nextToken());
				stringBuilder.append(getSum(B) - getSum(A - 1)).append("\n");
			}

		}
		System.out.println(stringBuilder);
	}

	private static long getSum(int index) {
		int i = index;
		long answer = numberArray[i];
		while (i > 0) {
			i -= indexArray[i];
			answer += numberArray[i];
		}
		return answer;
	}

	private static void update(int index, long number) {
		int i = index;
		long beforeNumber = getSum(i) - getSum(i - 1);

		while (i <= N) {
			numberArray[i] -= beforeNumber;
			numberArray[i] += number;
			i += indexArray[i];
		}
	}
}

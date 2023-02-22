package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_10451_순열사이클 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static int N;
	static int[] numbers;
	static boolean[] selected;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(bufferedReader.readLine());

		for (int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(bufferedReader.readLine());
			numbers = new int[N + 1];
			selected = new boolean[N + 1];
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int i = 1; i <= N; i++) {
				numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
			}
			stringBuilder.append(countCycle()).append("\n");
		}
		System.out.println(stringBuilder);
	}

	public static int countCycle() {
		int count = 0;

		for (int i = 1; i <= N; i++) {
			if (!selected[i]) {
				int nextIndex = i;
				while (!selected[nextIndex]) {
					selected[nextIndex] = true;
					nextIndex = numbers[nextIndex];
				}
				count++;
			}
		}
		return count;
	}

}

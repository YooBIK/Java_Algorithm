package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15650_S3_N과M2_유병익 {

	static int N;
	static int M;
	static int[] numbers;
	static StringBuilder stringBuilder = new StringBuilder();

	public static void combination(int depth, int start) {
		if (depth == M) {
			for (int i = 0; i < M; i++) {
				stringBuilder.append(numbers[i]).append(" ");
			}
			stringBuilder.append("\n");
			return;
		}

		for (int i = start; i <= N; i++) {
			numbers[depth] = i;
			combination(depth + 1, i + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numbers = new int[M];
		combination(0, 1);
		System.out.println(stringBuilder.toString());
	}
}
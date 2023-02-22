package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2961_S2_도영이가만든맛있는음식_유병익 {

	static int N;
	static long[] first;
	static long[] second;

	static long result = Integer.MAX_VALUE;

	public static void cook(int depth, int start, long curFirst, long curSecond) {
		if (depth == 0) {
			result = Math.min(result, Math.abs(curFirst - curSecond));
			return;
		}
		for (int i = start; i < N; i++) {
			cook(depth - 1, i + 1, curFirst * first[i], curSecond + second[i]);
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		first = new long[N];
		second = new long[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			first[i] = Long.parseLong(st.nextToken());
			second[i] = Long.parseLong(st.nextToken());
		}
		for (int j = 1; j <= N; j++) {
			cook(j, 0, 1, 0);
		}
		System.out.println(result);
	}
}

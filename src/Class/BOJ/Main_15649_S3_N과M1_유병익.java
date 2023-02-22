package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15649_S3_N과M1_유병익 {

	static int N;
	static int M;
	static boolean[] used;
	static int[] temp;
	static StringBuilder sb = new StringBuilder();

	public static void permu(int current) {
		if (current == M) {
			for (int i = 0; i < M; i++) {
				sb.append(temp[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		for (int i = 1; i <= N; i++) {
			if (!used[i]) {
				temp[current] = i;
				used[i] = true;
				permu(current + 1);
				used[i] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		used = new boolean[N + 1];
		temp = new int[M];
		permu(0);
		System.out.println(sb.toString());
	}
}

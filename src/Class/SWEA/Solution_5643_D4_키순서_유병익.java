package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5643_D4_키순서_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	public static void main(String[] args) throws IOException {

		int T = Integer.parseInt(bufferedReader.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			int N = Integer.parseInt(bufferedReader.readLine());
			int M = Integer.parseInt(bufferedReader.readLine());

			boolean[][] isSmall = new boolean[N][N];
			boolean[][] isTall = new boolean[N][N];

			for (int i = 0; i < M; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				int less = Integer.parseInt(stringTokenizer.nextToken()) - 1;
				int greater = Integer.parseInt(stringTokenizer.nextToken()) - 1;
				isSmall[less][greater] = true; // less < greater ;
				isTall[greater][less] = true; // greater > less;
			}

			int answer = 0;
			for (int i = 0; i < N; i++) {
				Queue<Integer> queue = new ArrayDeque<>();
				queue.offer(i);
				int count = 0;
				while (!queue.isEmpty()) {
					int curNode = queue.poll();

					for (int j = 0; j < N; j++) {
						if (isTall[j][curNode]) {
							queue.offer(j);
							count++;
						}
					}
				}

				queue.offer(i);
				while (!queue.isEmpty()) {
					int curNode = queue.poll();
					for (int j = 0; j < N; j++) {
						if (curNode == j)
							continue;
						if (isTall[j][curNode]) {
							queue.offer(j);
							count++;
						}
					}
				}

				if (count == N - 1) {
					answer++;
				}
			}

			stringBuilder.append("#").append(testCase).append(" ").append(answer).append("\n");
		}
		System.out.println(stringBuilder);
	}
}

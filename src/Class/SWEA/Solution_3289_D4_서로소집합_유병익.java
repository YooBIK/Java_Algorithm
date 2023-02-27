package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3289_D4_서로소집합_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static int N, M;
	static int[] parents;

	public static void init() {
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}

	public static int findRoot(int curNode) {
		if (curNode == parents[curNode]) {
			return curNode;
		}
		return parents[curNode] = findRoot(parents[curNode]);
	}

	public static void uniont(int first, int second) {

		int firstRoot = findRoot(first);
		int secondRoot = findRoot(second);
		if (firstRoot == secondRoot) {
			return;
		}

		parents[firstRoot] = secondRoot;
	}

	public static void main(String[] args) throws IOException {

		int T = Integer.parseInt(bufferedReader.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			stringBuilder.append("#").append(testCase).append(" ");
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			N = Integer.parseInt(stringTokenizer.nextToken());
			M = Integer.parseInt(stringTokenizer.nextToken());
			init();
			for (int i = 0; i < M; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				int command = Integer.parseInt(stringTokenizer.nextToken());
				int firstNode = Integer.parseInt(stringTokenizer.nextToken());
				int secondNode = Integer.parseInt(stringTokenizer.nextToken());

				if (command == 0) {
					uniont(firstNode, secondNode);
				} else {
					if (findRoot(firstNode) == findRoot(secondNode)) {
						stringBuilder.append(1);
					} else {
						stringBuilder.append(0);
					}
				}
			}
			stringBuilder.append("\n");
		}
		System.out.println(stringBuilder);

	}

}

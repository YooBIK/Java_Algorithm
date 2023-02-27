package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution_7465_D4_창용마을무리의개수_유병익2 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static int N, M;

	static int[] parents;
	static Set<Integer> parentsSet;

	private static void init() {
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}

	private static int findRoot(int curNode) {
		if (curNode == parents[curNode]) {
			return curNode;
		}

		return parents[curNode] = findRoot(parents[curNode]);
	}

	private static void union(int firstNode, int secondNode) {
		int firstRoot = findRoot(firstNode);
		int secondRoot = findRoot(secondNode);

		if (firstRoot != secondRoot) {
			parents[firstRoot] = secondRoot;
		}
	}

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(bufferedReader.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			N = Integer.parseInt(stringTokenizer.nextToken());
			M = Integer.parseInt(stringTokenizer.nextToken());
			init();

			for (int i = 0; i < M; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				int firstNode = Integer.parseInt(stringTokenizer.nextToken());
				int secondNode = Integer.parseInt(stringTokenizer.nextToken());

				union(firstNode, secondNode);
			}
			parentsSet = new HashSet<>();
			for (int i = 1; i <= N; i++) {
				parentsSet.add(findRoot(i));
			}
			stringBuilder.append("#").append(testCase).append(" ").append(parentsSet.size()).append("\n");
		}
		System.out.println(stringBuilder);
	}

	

}

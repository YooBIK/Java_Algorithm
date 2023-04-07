package Class.Live;

import java.util.Scanner;

public class Live0407_DJSet {

	static int N;
	static int[] parents;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		parents = new int[N];

	}

	private static void makeSet() {
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}

	private static int findRoot(int vertex) {
		if (parents[vertex] == vertex) {
			return vertex;
		}
		return findRoot(parents[vertex]);
	}

	private static void union(int first, int second) {
		int firstRoot = findRoot(first);
		int secondRoot = findRoot(second);

		if (firstRoot != secondRoot) {
			parents[firstRoot] = secondRoot;
		}
	}

}

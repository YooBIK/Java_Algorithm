package Class.Live;

import java.util.Arrays;
import java.util.Scanner;

public class Live0406_DJSet {

	static int[] parents;
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		parents = new int[N];

		makeSet();
		
		sc.close();
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

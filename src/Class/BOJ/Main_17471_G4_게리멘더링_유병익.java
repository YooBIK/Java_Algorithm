package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17471_G4_게리멘더링_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int N;
	static int[] population;
	static boolean[][] connection;
	static boolean[] visit;
	static int answer = Integer.MAX_VALUE;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bufferedReader.readLine());

		population = new int[N + 1];
		connection = new boolean[N + 1][N + 1];

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(stringTokenizer.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int num = Integer.parseInt(stringTokenizer.nextToken());
			for (int j = 0; j < num; j++) {
				int to = Integer.parseInt(stringTokenizer.nextToken());
				connection[i][to] = true;
				connection[to][i] = true;
			}
		}
		visit = new boolean[N + 1];
		List<Integer> areaPop = new ArrayList<>();
		for (int j = 1; j <= N; j++) {
			if (!visit[j]) {
				areaPop.add(bfs(j));
			}
		}

		if (areaPop.size() > 2) {
			System.out.println(-1);
		} else if (areaPop.size() == 2) {
			System.out.println(Math.abs(areaPop.get(0) - areaPop.get(1)));
		} else {
			for (int i = 1; i <= (N / 2); i++) {
				List<Integer> selected = new ArrayList<>();
				List<Integer> unselected = new ArrayList<>();
				visit = new boolean[N + 1];
				combination(0, i, 1, selected);
			}
			System.out.println(answer);
		}

	}

	private static void combination(int depth, int targetDepth, int startIndex, List<Integer> selected) {
		if (depth == targetDepth) {
			List<Integer> unselected = new ArrayList<>();
			for (int i = 1; i <= N; i++) {
				if (!selected.contains(i)) {
					unselected.add(i);
				}
			}
			for (int i = 0; i < selected.size() - 1; i++) {
				if (!union(selected.get(i), selected.get(i + 1)))
					return;
			}

			for (int i = 0; i < selected.size() - 1; i++) {

			}

		}

		for (int i = startIndex; i <= N; i++) {
			if (!visit[i]) {
				visit[i] = true;
				selected.add(i);
				combination(depth + 1, targetDepth, i + 1, selected);
				visit[i] = false;

			}
		}

	}

	private static boolean union(int first, int second) {
		int firstRoot = findRoot(first);
		int secondRoot = findRoot(second);
		if (firstRoot != secondRoot && connection[first][second]) {
			parents[firstRoot] = secondRoot;
			return true;
		}
		return false;
	}

	private static int findRoot(int first) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int bfs(int startNode) {
		Queue<Integer> queue = new ArrayDeque<>();
		int result = population[startNode];
		queue.offer(startNode);
		visit[startNode] = true;

		while (!queue.isEmpty()) {
			int curNode = queue.poll();

			for (int i = 1; i <= N; i++) {
				if (!visit[i] && connection[curNode][i]) {
					queue.offer(i);
					result += population[i];
					visit[i] = true;
				}
			}
		}

		return result;
	}

}

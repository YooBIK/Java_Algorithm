package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_15656_G5_치킨배달_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder stringBuilder = new StringBuilder();
	static StringTokenizer stringTokenizer;

	static int[] dirRow = { 0, 0, 1, -1 };
	static int[] dirCol = { 1, -1, 0, 0 };

	static int[][] map;
	static boolean[][] visit;
	static int N, M;
	static List<int[]> chickenList = new ArrayList<>();
	static List<int[]> houseList = new ArrayList<>();
	static List<int[]> selectedChickenList = new ArrayList<>();

	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < N; j++) {
				int cur = Integer.parseInt(stringTokenizer.nextToken());
				if (cur == 2) {
					chickenList.add(new int[] { i, j });
				}
				if (cur == 1) {
					houseList.add(new int[] { i, j });
				}
				map[i][j] = cur;
			}
		}
		combination(0, 0);
		System.out.println(result);
	}

	public static void combination(int count, int startIndex) {
		if (count == M) {
			int curChickenDist = 0;
			for (int[] house : houseList) {
				int minDist = Integer.MAX_VALUE;
				for (int[] chicken : selectedChickenList) {
					minDist = Math.min(minDist, Math.abs(house[0] - chicken[0]) + Math.abs(house[1] - chicken[1]));
				}
				curChickenDist += minDist;
			}
			result = Math.min(curChickenDist, result);
			return;
		}

		for (int i = startIndex; i < chickenList.size(); i++) {
			selectedChickenList.add(chickenList.get(i));
			combination(count + 1, i + 1);
			selectedChickenList.remove(chickenList.get(i));
		}

	}
}

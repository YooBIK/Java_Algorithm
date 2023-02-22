package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_5644_무선충전_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static List<Integer>[][] map = new ArrayList[10][10];
	static boolean[][] visit = new boolean[10][10];
	static HashMap<Integer, Integer> BCMap = new HashMap<>();

	static int[] dirRow = { 0, -1, 0, 1, 0 };
	static int[] dirCol = { 0, 0, 1, 0, -1 };

	static int M; // 이동 시간
	static int A; // BC 총 개수

	static int userAUsingBC = -1;
	static int userBUsingBC = -1;

	static List<Integer> userA = new ArrayList<>();
	static List<Integer> userB = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		int T = Integer.parseInt(bufferedReader.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {

			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			M = Integer.parseInt(stringTokenizer.nextToken());
			A = Integer.parseInt(stringTokenizer.nextToken());

			// A의 이동 정보
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int i = 0; i < M; i++) {
				userA.add(Integer.parseInt(stringTokenizer.nextToken()));
			}

			// B의 이동 정보
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int i = 0; i < M; i++) {
				userB.add(Integer.parseInt(stringTokenizer.nextToken()));
			}
			int BCNum = 0;
			for (int i = 0; i < A; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());

				int col = Integer.parseInt(stringTokenizer.nextToken()) - 1;
				int row = Integer.parseInt(stringTokenizer.nextToken()) - 1;
				int range = Integer.parseInt(stringTokenizer.nextToken());
				int power = Integer.parseInt(stringTokenizer.nextToken());
				BCMap.put(BCNum, power);
				for (boolean[] arr : visit) {
					Arrays.fill(arr, false);
				}
				visit[row][col] = true;
				dfs(row, col, row, col, BCNum, range);
				BCNum++;
			}

			for (List<Integer>[] listarr : map) {
				for (List<Integer> list : listarr) {
					System.out.print(list + "\t\t");
				}
				System.out.println();
			}

			int userARow = 0;
			int userACol = 0;

			int userBRow = 0;
			int userBCol = 0;

			int result = 0;

			int time = 0;
			while (time > M) {

				List<Integer> curUserA = map[userARow][userACol];
				List<Integer> curUserB = map[userBRow][userBCol];

				if (curUserA != null) {
					Collections.sort(curUserA, (o1, o2) -> Integer.compare(BCMap.get(o1), BCMap.get(o2)));
					userAUsingBC = curUserA.get(0);
				}

				if (curUserB != null) {

				}
			}

			for (int i = 0; i < M; i++) {
				if (map[userARow][userACol] != null) {

				}

				if (map[userBRow][userBCol] != null) {

				}
			}

		}

	}

	public static void dfs(int row, int col, int startRow, int startCol, int BCNum, int targetDepth) {

		if (map[row][col] == null) {
			map[row][col] = new ArrayList<>();
		}
		map[row][col].add(BCNum);

		if (Math.abs(row - startRow) + Math.abs(col - startCol) == targetDepth) {
			return;
		}

		for (int i = 1; i < 5; i++) {
			int newRow = row + dirRow[i];
			int newCol = col + dirCol[i];
			if (newRow >= 0 && newRow < 10 && newCol >= 0 && newCol < 10) {
				if (!visit[newRow][newCol]) {
					visit[newRow][newCol] = true;
					dfs(newRow, newCol, startRow, startCol, BCNum, targetDepth);
				}
			}

		}
	}

}

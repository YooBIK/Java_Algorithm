package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_요리사_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static int N;
	static int R;
	static int[][] relation;
	static boolean[] isSelected;

	static int result;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(bufferedReader.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(bufferedReader.readLine());
			relation = new int[N][N];
			isSelected = new boolean[N];
			for (int i = 0; i < N; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				for (int j = 0; j < N; j++) {
					relation[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				}
			}
			result = Integer.MAX_VALUE;
			combination(0, 0);
			stringBuilder.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.println(stringBuilder);
	}

	public static void combination(int depth, int startIndex) {
		if (depth == N / 2) {
			List<Integer> selected = new ArrayList<>();
			List<Integer> notSelselected = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				if (isSelected[i]) {
					selected.add(i);
				} else {
					notSelselected.add(i);
				}
			}
			int scoreA = 0;
			for (int i = 0; i < selected.size(); i++) {
				for (int j = 0; j < selected.size(); j++) {
					scoreA += relation[selected.get(i)][selected.get(j)];
				}
			}
			int scoreB = 0;
			for (int i = 0; i < N / 2; i++) {
				for (int j = 0; j < N / 2; j++) {
					scoreB += relation[notSelselected.get(i)][notSelselected.get(j)];
				}
			}
			result = Math.min(result, Math.abs(scoreA - scoreB));
		}

		for (int i = startIndex; i < N; i++) {
			isSelected[i] = true;
			combination(depth + 1, i + 1);
			isSelected[i] = false;
		}
	}

}

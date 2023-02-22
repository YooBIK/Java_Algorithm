package Study.Week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ21939 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	public static void main(String[] args) throws IOException {
		TreeSet<int[]> treeSet = new TreeSet<>(
				(o1, o2) -> o1[1] == o2[1] ? Integer.compare(o1[0], o2[0]) : Integer.compare(o1[1], o2[1]));

		HashMap<Integer, Integer> hashMap = new HashMap<>();

		int N = Integer.parseInt(bufferedReader.readLine());
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int probNumber = Integer.parseInt(stringTokenizer.nextToken());
			int difficulty = Integer.parseInt(stringTokenizer.nextToken());
			treeSet.add(new int[] { probNumber, difficulty });
			hashMap.put(probNumber, difficulty);
		}
		int M = Integer.parseInt(bufferedReader.readLine());
		for (int i = 0; i < M; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			String command = stringTokenizer.nextToken();
			if (command.equals("recommend")) {
				if (Integer.parseInt(stringTokenizer.nextToken()) == 1) {
					stringBuilder.append(treeSet.last()[0]).append("\n");
				} else {
					stringBuilder.append(treeSet.first()[0]).append("\n");
				}

			} else if (command.equals("add")) {
				int probNumber = Integer.parseInt(stringTokenizer.nextToken());
				int difficulty = Integer.parseInt(stringTokenizer.nextToken());
				treeSet.add(new int[] { probNumber, difficulty });
				hashMap.put(probNumber, difficulty);
			} else if (command.equals("solved")) {
				int probNumber = Integer.parseInt(stringTokenizer.nextToken());
				treeSet.remove(new int[] { probNumber, hashMap.get(probNumber) });
			}
		}
		System.out.println(stringBuilder);

	}
}

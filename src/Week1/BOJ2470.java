package Week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2470 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(bufferedReader.readLine());
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		List<Long> list = new ArrayList<>(N);

		for (int i = 0; i < N; i++) {
			list.add(Long.parseLong(stringTokenizer.nextToken()));
		}
		Collections.sort(list);

		if (list.get(list.size() - 1) <= 0) {
			stringBuilder.append(list.get(list.size() - 2)).append(" ").append(list.get(list.size() - 1));
			System.out.println(stringBuilder);
			return;
		}
		if (list.get(0) >= 0) {
			stringBuilder.append(list.get(0)).append(" ").append(list.get(1));
			System.out.println(stringBuilder);
			return;
		}

		int left = 0;
		int right = list.size() - 1;
		long result1 = list.get(left);
		long result2 = list.get(right);
		long minGap = Math.abs(result1 + result2);
		while (left < right) {

			if (Math.abs(list.get(left) + list.get(right)) < minGap) {
				minGap = Math.abs(list.get(left) + list.get(right));
				result1 = list.get(left);
				result2 = list.get(right);
			}

			if (list.get(left) + list.get(right) > 0) {
				right--;
			} else {
				left++;
			}
		}
		stringBuilder.append(result1).append(" ").append(result2);
		System.out.println(stringBuilder);

	}
}

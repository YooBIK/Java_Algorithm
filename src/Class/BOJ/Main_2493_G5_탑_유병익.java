package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_2493_G5_탑_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	public static void main(String[] args) throws IOException {

		int N = Integer.parseInt(bufferedReader.readLine());
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());

		LinkedList<Long> left = new LinkedList<>();
		LinkedList<Long> right = new LinkedList<>();

		HashMap<Long, Integer> hashMap = new HashMap<>();
		for (int i = 0; i < N; i++) {
			long curVal = Long.parseLong(stringTokenizer.nextToken());
			left.push(curVal);
			hashMap.put(curVal, i);
		}

		int[] resultArray = new int[N];

		while (true) {
			if (left.isEmpty()) {
				if (!right.isEmpty()) {
					for (int i = 0; i < right.size(); i++) {
						resultArray[hashMap.get(right.poll())] = 0;
					}
				}
				break;
			}

			if (right.isEmpty()) {
				if (!left.isEmpty())
					right.push(left.poll());
			}

			if (!left.isEmpty() && !right.isEmpty() && left.peek() < right.peek()) {
				right.push(left.poll());
			} else {
				while (!left.isEmpty() && !right.isEmpty() && left.peek() > right.peek()) {
					int leftIndex = hashMap.get(left.peek());
					resultArray[hashMap.get(right.poll())] = leftIndex + 1;
				}
			}
		}
		for (int i = 0; i < resultArray.length; i++) {
			stringBuilder.append(resultArray[i]).append(" ");
		}
		System.out.println(stringBuilder);

	}

}

package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class BOJ10975 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder stringBuilder = new StringBuilder();

	static int N;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bufferedReader.readLine());

		List<Integer> numbers = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			numbers.add(Integer.parseInt(bufferedReader.readLine()));
		}
		List<Integer> sortedList = new ArrayList<>(numbers);
		Collections.sort(sortedList);
		HashMap<Integer, Integer> hashMap = new HashMap<>();
		for (int i = 0; i < sortedList.size(); i++) {
			hashMap.put(sortedList.get(i), i);
		}
		List<int[]> resultList = new ArrayList<>();
		for (int i = 0; i < numbers.size(); i++) {
			int index = hashMap.get(numbers.get(i));
			if (resultList.isEmpty()) {
				resultList.add(new int[] { index, index });
			} else {
				boolean flag = false;
				for (int j = 0; j < resultList.size(); j++) {
					int[] cur = resultList.get(j);
					if (index + 1 == cur[0]) {
						cur[0] = index;
						flag = true;
						break;
					}
					if (index - 1 == cur[1]) {
						cur[1] = index;
						flag = true;
						break;
					}
				}
				if (!flag) {
					resultList.add(new int[] { index, index });
				}
			}
		}
		System.out.println(resultList.size());

	}

}

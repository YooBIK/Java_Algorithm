package Study.Week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ18870_좌표압축 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(bufferedReader.readLine());

		Set<Integer> set = new HashSet<>(N);
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		List<Integer> numberList = new ArrayList<>(N);
		for (int i = 0; i < N; i++) {
			int curNum = Integer.parseInt(stringTokenizer.nextToken());
			set.add(curNum);
			numberList.add(curNum);
		}
		List<Integer> temp = new ArrayList<>(set);
		Collections.sort(temp);
		HashMap<Integer, Integer> hashMap = new HashMap<>();
		for (int i = 0, size = temp.size(); i < size; i++) {
			hashMap.put(temp.get(i), i);
		}
		for (int i = 0; i < N; i++) {
			stringBuilder.append(hashMap.get(numberList.get(i))).append(" ");
		}
		System.out.println(stringBuilder);
	}
}

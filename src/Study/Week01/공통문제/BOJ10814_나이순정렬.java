package Study.Week01.공통문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class BOJ10814_나이순정렬 {

	public static void main(String[] args) throws IOException {
		HashMap<Integer, List<String>> hashMap = new HashMap<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int N = Integer.parseInt(input);
		for (int i = 0; i < N; i++) {
			input = br.readLine();
			StringTokenizer st = new StringTokenizer(input);
			int age = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			if (hashMap.containsKey(age)) {
				hashMap.get(age).add(name);
			} else {
				List<String> list = new ArrayList<>();
				list.add(name);
				hashMap.put(age, list);
			}
		}
		ArrayList<Entry<Integer, List<String>>> entryList = new ArrayList<>(hashMap.entrySet());
		Collections.sort(entryList, (o1, o2) -> Integer.compare(o1.getKey(), o2.getKey()));

		for (Entry<Integer, List<String>> e : entryList) {
			for (String s : e.getValue()) {
				System.out.println(e.getKey() + " " + s);
			}
		}
	}

}
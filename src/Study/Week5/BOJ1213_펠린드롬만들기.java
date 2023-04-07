package Study.Week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1213_펠린드롬만들기 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	public static void main(String[] args) throws IOException {
		String input = bufferedReader.readLine();
		HashMap<Character, Integer> hashMap = new HashMap<>();

		for (int i = 0; i < 26; i++) {
			hashMap.put((char) ('A' + i), 0);
		}

		for (int i = 0; i < input.length(); i++) {
			hashMap.put(input.charAt(i), hashMap.get(input.charAt(i)) + 1);
		}

		int oddCount = 0;
		for (char c : hashMap.keySet()) {
			if (hashMap.get(c) % 2 == 1) {
				oddCount++;
			}
		}
		if (oddCount > 1) {
			System.out.println("I'm Sorry Hansoo");
			return;
		}

		List<Character> charList = new ArrayList<>(hashMap.keySet());
		Collections.sort(charList);
		for (int i = 0; i < charList.size(); i++) {
			int count = hashMap.get(charList.get(i));
			for (int j = 0; j < count / 2; j++) {
				stringBuilder.append(charList.get(i));
			}
		}
		
		for (int i = 0; i < charList.size(); i++) {
			int count = hashMap.get(charList.get(i));
			if(count %2 == 1) {
				stringBuilder.append(charList.get(i));
			}
		}
		
		for (int i = charList.size()-1; i >=0; i--) {
			int count = hashMap.get(charList.get(i));
			for (int j = 0; j < count / 2; j++) {
				stringBuilder.append(charList.get(i));
			}
		}
		System.out.println(stringBuilder);
	}
}

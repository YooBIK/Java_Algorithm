package Class.Live.String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Rabin_Karp {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		char[] text = bufferedReader.readLine().toCharArray();
		char[] pattern = bufferedReader.readLine().toCharArray();

		long patternHash = 0, textHash = 0, power = 1;
		long base = 53;
		long mod = 1000000009;
		int textLength = text.length;
		int patternLength = pattern.length;
		List<Integer> resultList = new ArrayList<>();

		if (text.length < pattern.length) {
			System.out.println(0);
			return;
		}

		for (int i = 0; i < patternLength; i++) {
			patternHash = (patternHash * base) % mod;
			patternHash = (patternHash + pattern[i]) % mod;

			textHash = (textHash * base) % mod;
			textHash = (textHash + text[i]) % mod;

			if (i < patternLength - 1) {
				power = (power * base) % mod;
			}
		}

		if (patternHash == textHash) {
			resultList.add(1);
		}

		for (int i = 1; i <= textLength - patternLength; i++) {
			textHash = (((textHash - (text[i - 1] * power) % mod) % mod + mod) * base) % mod;
			textHash = (textHash + ((text[i + patternLength - 1]) % mod) % mod) % mod;

			if (patternHash == textHash) {
				resultList.add(i + 1);
			}
		}

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(resultList.size()).append("\n");
		for (int index : resultList) {
			stringBuilder.append(index).append(" ");
		}
		System.out.println(stringBuilder);

	}

}

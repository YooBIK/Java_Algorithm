package Class.Live.String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KMP {

	public static void main(String[] args) throws IOException {

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		char[] text = bufferedReader.readLine().toCharArray();
		char[] pattern = bufferedReader.readLine().toCharArray();

		int textLength = text.length;
		int patternLength = pattern.length;

		// Fail 함수 만들기 : KMP 알고리즘으로 부분 일치 문자열을 찾을 때, 접두사, 접미사 가 같은지 체크 & 돌아갈 위치 저장
		int[] fail = new int[patternLength];

		for (int i = 1, j = 0; i < patternLength; i++) { // i : 접미사 포인터( 1부터 시작) // j : 접두사 포인터
			while (j > 0 && pattern[i] != pattern[j]) {
				j = fail[j - 1];
			}

			if (pattern[i] == pattern[j]) {
				fail[i] = ++j;
			}
		}

		System.out.println(Arrays.toString(fail));

		List<Integer> resultList = new ArrayList<>();

		// i : text 포인터, j : 패턴 포인터
		for (int i = 0, j = 0; i < textLength; i++) {
			// 원문과 패턴이 맞지 않으면 -> j를 fail[j-1]로 이동시킨다.
			while (j > 0 && text[i] != pattern[j]) {
				j = fail[j - 1];
			}

			if (text[i] == pattern[j]) {
				if (j == patternLength - 1) {
					resultList.add(i - patternLength + 2);
					j = fail[j];
				} else {
					j++;
				}
			}
		}

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(resultList.size()).append("\n");
		for (int i : resultList) {
			stringBuilder.append(i).append(" ");
		}
		System.out.println(stringBuilder);

	}

}

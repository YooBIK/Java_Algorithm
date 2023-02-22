package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1233_D4_사칙연산유효성검사_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();
	static String ops = "+-*/";

	public static void main(String[] args) throws IOException {

		for (int testCase = 1; testCase <= 10; testCase++) {
			int resultFlag = 1;
			int N = Integer.parseInt(bufferedReader.readLine());
			if (N % 2 == 0) {
				resultFlag = 0;
			}

			for (int i = 0; i < N; i++) {
				String[] inputArray = bufferedReader.readLine().split(" ");
				if (inputArray.length == 2) {
					if (ops.contains(inputArray[1])) {
						resultFlag = 0;
					}
				} else if (inputArray.length == 3) {
					resultFlag = 0;
				} else if (inputArray.length == 4) {
					if (!ops.contains(inputArray[1])) {
						resultFlag = 0;
					}
				}
			}
			stringBuilder.append("#").append(testCase).append(" ").append(resultFlag).append("\n");
		}
		System.out.println(stringBuilder);
	}

}

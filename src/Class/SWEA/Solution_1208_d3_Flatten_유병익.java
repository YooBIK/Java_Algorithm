package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1208_d3_Flatten_유병익 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		StringBuilder stringBuilder = new StringBuilder();
		for (int testCase = 1; testCase <= 10; testCase++) {
			int dumpCount = Integer.parseInt(bufferedReader.readLine());
			int[] boxMap = new int[100];
			String input = bufferedReader.readLine();
			stringTokenizer = new StringTokenizer(input);
			for (int i = 0; i < 100; i++) {
				boxMap[i] = Integer.parseInt(stringTokenizer.nextToken());
			}
			while (true) {
				Arrays.sort(boxMap);
				if (dumpCount == 0)
					break;
				boxMap[0]++;
				boxMap[99]--;
				dumpCount--;
			}
			stringBuilder.append("#").append(testCase).append(" ").append(boxMap[99] - boxMap[0]).append("\n");
		}
		System.out.println(stringBuilder.toString());
	}

}

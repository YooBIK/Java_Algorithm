package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_1225_D3_암호생성기_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder builder = new StringBuilder();

	public static void main(String[] args) throws IOException {

		for (int testCase = 1; testCase <= 10; testCase++) {
			bufferedReader.readLine();
			LinkedList<Integer> queue = new LinkedList<>();
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int i = 0; i < 8; i++) {
				int curNum = Integer.parseInt(stringTokenizer.nextToken());
				queue.addLast(curNum);
			}
			boolean flag = true;
			while (flag) {
				for (int i = 1; i <= 5; i++) {
					int curValue = queue.pollFirst();
					if (curValue - i <= 0) {
						queue.addLast(0);
						flag = false;
						break;
					} else {
						queue.addLast(curValue - i);
					}
				}
			}
			builder.append("#").append(testCase).append(" ");
			for (int i = 0; i < 8; i++) {
				builder.append(queue.pollFirst()).append(" ");
			}
			builder.append("\n");
		}
		System.out.println(builder);

	}

}

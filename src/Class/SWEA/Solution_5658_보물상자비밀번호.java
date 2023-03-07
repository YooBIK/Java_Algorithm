package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution_5658_보물상자비밀번호 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static int N, K;
	static TreeSet<Long> treeSet;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(bufferedReader.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			N = Integer.parseInt(stringTokenizer.nextToken());
			K = Integer.parseInt(stringTokenizer.nextToken());
			treeSet = new TreeSet<>();
			ArrayDeque<Character> deque = new ArrayDeque<>();
			String input = bufferedReader.readLine();
			for (int i = 0; i < input.length(); i++) {
				deque.addLast(input.charAt(i));
			}
			for (int i = 0; i < N / 4; i++) {
				getResult(deque);
				deque.addFirst(deque.pollLast());
			}
			for (int i = 0; i < K - 1; i++) {
				treeSet.pollLast();
			}
			stringBuilder.append("#").append(testCase).append(" ").append(treeSet.last()).append("\n");
		}
		System.out.println(stringBuilder);

	}

	private static void getResult(ArrayDeque<Character> deque) {
		for (int i = 0; i < 4; i++) {
			long result = 0;
			for (int j = N / 4 - 1; j >= 0; j--) {
				int curVal = 0;
				long power = (long) Math.pow(16, j);
				char curChar = deque.pollFirst();
				deque.addLast(curChar);
				if (curChar >= 'A' && curChar <= 'F') {
					curVal = curChar - 'A' + 10;
				} else {
					curVal = curChar - '0';
				}
				result += curVal * power;
			}
			treeSet.add(result);
		}
	}

}

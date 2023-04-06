package Study.Week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 조건이 좀 불명확해서 고생했음
 * 
 *
 */
public class BOJ20055 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int N, K;

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken());
		K = Integer.parseInt(stringTokenizer.nextToken());

		int zeroCount = 0;
		ArrayDeque<Integer> firstQueue = new ArrayDeque<>();
		ArrayDeque<Integer> secondQueue = new ArrayDeque<>();
		HashMap<Integer, Integer> hashMap = new HashMap<>();

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 0; i < N; i++) {
			int curNum = Integer.parseInt(stringTokenizer.nextToken());
			hashMap.put(i, curNum);
			firstQueue.addLast(i);
		}

		for (int i = N; i < 2 * N; i++) {
			int curNum = Integer.parseInt(stringTokenizer.nextToken());
			hashMap.put(i, curNum);
			secondQueue.addLast(i);
		}

		Queue<Integer> robotQueue = new ArrayDeque<>();
		int answer = 0;
		while (zeroCount < K) {
			answer++;
			firstQueue.addFirst(secondQueue.pollLast()); // 1칸씩 회전
			secondQueue.addFirst(firstQueue.pollLast());

			int downPosition = firstQueue.peekLast(); // 내리는 위치

			int size = robotQueue.size();
			for (int i = 0; i < size; i++) { // 현재 올라가 있는 로봇들
				int robotPosition = robotQueue.poll();

				if (robotPosition == downPosition) {
					continue;
				}

				int nextPosition = (robotPosition + 1) % (2 * N);
				if (!robotQueue.contains(nextPosition) && hashMap.get(nextPosition) > 0) {
					hashMap.put(nextPosition, hashMap.get(nextPosition) - 1);

					if (hashMap.get(nextPosition) == 0) {
						zeroCount++;
					}

				} else {
					nextPosition = robotPosition;
				}

				if (nextPosition != downPosition) {
					robotQueue.offer(nextPosition);
				}
			}

			int upPosition = firstQueue.peekFirst(); // 올리는 위치

			if (!robotQueue.contains(upPosition) && hashMap.get(upPosition) > 0) {
				robotQueue.offer(upPosition);
				hashMap.put(upPosition, hashMap.get(upPosition) - 1);

				if (hashMap.get(upPosition) == 0) {
					zeroCount++;
				}
			}
		}
		System.out.println(answer);
	}

}

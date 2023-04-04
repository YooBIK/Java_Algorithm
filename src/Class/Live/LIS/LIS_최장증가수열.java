package Class.Live.LIS;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 시간복잡도 NLogN C[k] 배열: 길이 N인 LIS에 대해 길이 k를 만들 수 있는 수열의 가장 작은 값을 저장...? 동적 배열
 * C[k]를 사용해 가능한 LIS를 저장한 상태로 담아 놓는다. 진짜 LIS가 아닐 수도 있다.
 *
 */
public class LIS_최장증가수열 {
	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

	static int N;
	static int[] array;
	static int[] C;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bufferedReader.readLine());

		array = new int[N];
		C = new int[N];

		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(stringTokenizer.nextToken());
		}

		int[] dp = new int[N];
		for (int i = 0; i < N; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (array[j] < array[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}
		System.out.println(Arrays.toString(dp));

		int k = 0; // 현재 LIS 길이
		C[k++] = array[0]; // 초기 값 설정
		for (int i = 1; i < N; i++) {
			// array[i]가 들어갈 위치를 이진탐색으로 찾는다.
			// Arrays.binarySearch 메서드는 Key를 찾으면 해당 인덱스를, 찾지 못하면 (마지막까지 탐색한 위치 - 1)를 음수로
			// 전달한다.
			// 즉, 해당 값을 찾지 못하면, array[i]가 들어가야하는 인덱스의 음수를 반환한다고 생각하면 된다.
			int result = Arrays.binarySearch(C, 0, k, array[i]);

			result = Math.abs(result) - 1;
			C[result] = array[i];
			if (k == result) { // 맨 끝에 추가된 상황
				k++;
			}
		}
		System.out.println(Arrays.toString(C));
	}

}

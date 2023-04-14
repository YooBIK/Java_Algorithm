package Study.Week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

/**
 * 인구의 중간값인 마을을 찾는것이 포인트였다.
 * 처음에 long 을 썼는데, long 범위를 넘어가는 경우가 있기 때문에 BigInteger 자료형을 사용했다.
 */
public class BOJ2141_우체국 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int N;
	static HashMap<Long, Long> hashMap = new HashMap<>();

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bufferedReader.readLine());
		BigInteger totalPopulation = BigInteger.valueOf(0);
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			long position = Integer.parseInt(stringTokenizer.nextToken());
			long population = Integer.parseInt(stringTokenizer.nextToken());		// 마을 위치, 인구수 를 hashMap에 저장
			totalPopulation = totalPopulation.add(BigInteger.valueOf(population));	// 인구 총합을 저장

			hashMap.put(position, population);
		}
		List<Long> positionList = new ArrayList<>(hashMap.keySet());
		Collections.sort(positionList);												// 마을 위치 오름차순 정렬

		BigInteger curPop = BigInteger.valueOf(0);
		for (int i = 0; i < positionList.size(); i++) {
			curPop = curPop.add(BigInteger.valueOf(hashMap.get(positionList.get(i))));
			if (curPop.compareTo(totalPopulation.add(BigInteger.valueOf(1)).divide(BigInteger.valueOf(2))) !=-1) {	// 더한 값이 인구 중간보다 크거나 같아지는 순간이 최소 지점임!!

				System.out.println(positionList.get(i));
				return;
			}
		}
	}

}

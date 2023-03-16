package Study.Week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2141 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static int N;
	static HashMap<Long, Long> hashMap = new HashMap<>();

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bufferedReader.readLine());
		int totalPopulation = 0;
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			long position = Integer.parseInt(stringTokenizer.nextToken());
			long population = Integer.parseInt(stringTokenizer.nextToken());
			totalPopulation += population;
			hashMap.put(position, population);
		}
		List<Long> positionList = new ArrayList<>(hashMap.keySet());
		Collections.sort(positionList);

		long answer = -1;
		long curPop = 0;
		for (int i = 0; i < positionList.size(); i++) {
			curPop += hashMap.get(positionList.get(i));
			if (curPop >= (totalPopulation + 1) / 2) {
				System.out.println(positionList.get(i));
				return;
			}
		}
	}

}

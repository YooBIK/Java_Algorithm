package Study.Week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ20115_에너지드링크 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder stringBuilder = new StringBuilder();

	static int N;
	static List<Double> drinkList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bufferedReader.readLine());
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 0; i < N; i++) {
			drinkList.add(Double.parseDouble(stringTokenizer.nextToken()));
		}
		Collections.sort(drinkList);
		double result = 0;
		for (int i = 0; i < N - 1; i++) {
			result += drinkList.get(i) / 2;
		}
		result += drinkList.get(N - 1);
		System.out.println(result);
	}
}

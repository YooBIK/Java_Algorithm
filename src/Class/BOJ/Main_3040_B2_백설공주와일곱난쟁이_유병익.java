package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main_3040_B2_백설공주와일곱난쟁이_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder stringBuilder = new StringBuilder();

	static int N;
	static List<Integer> numbers = new ArrayList<>();
	static List<Integer> selected = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 9; i++) {
			numbers.add(Integer.parseInt(bufferedReader.readLine()));
		}
		combination(0, 0);
	}

	public static void combination(int depth, int startIndex) {
		if (depth == 7) {
			int sum = 0;
			for (int i = 0; i < selected.size(); i++) {
				sum += selected.get(i);
			}
			if (sum == 100) {
				for (int i = 0; i < selected.size(); i++) {
					System.out.println(selected.get(i));
				}
			}
			return;
		}

		for (int i = startIndex; i < 9; i++) {
			selected.add(numbers.get(i));
			combination(depth + 1, i + 1);
			selected.remove(selected.size() - 1);
		}
	}

}

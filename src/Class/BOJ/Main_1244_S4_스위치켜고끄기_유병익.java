package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1244_S4_스위치켜고끄기_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder stringBuilder = new StringBuilder();
	static StringTokenizer stringTokenizer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int lightNumber = Integer.parseInt(bufferedReader.readLine());
		boolean[] lights = new boolean[lightNumber + 1];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 1; i <= lightNumber; i++) {
			if (stringTokenizer.nextToken().equals("1")) {
				lights[i] = true;
			}
		}
		int studentNumber = Integer.parseInt(bufferedReader.readLine());
		for (int i = 0; i < studentNumber; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			if (stringTokenizer.nextToken().equals("1")) {
				int index = Integer.parseInt(stringTokenizer.nextToken());
				for (int j = index; j <= lightNumber; j += index) {
					lights[j] = !lights[j];
				}
			} else {
				int index = Integer.parseInt(stringTokenizer.nextToken());
				List<Integer> list = new ArrayList<>();
				list.add(index);
				int leftIndex = index - 1;
				int rightIndex = index + 1;
				while (leftIndex >= 1 && rightIndex <= lightNumber) {
					if (lights[leftIndex] != lights[rightIndex]) {
						break;
					} else {
						list.add(leftIndex--);
						list.add(rightIndex++);
					}
				}
				for (int j = 0; j < list.size(); j++) {
					int cur = list.get(j);
					lights[cur] = !lights[cur];
				}
			}
		}
		for (int i = 1; i <= lightNumber; i++) {
			if (lights[i] == true) {
				stringBuilder.append(1);
			} else {
				stringBuilder.append(0);
			}
			if (i % 20 == 0) {
				stringBuilder.append("\n");
			} else {
				stringBuilder.append(" ");
			}
		}
		System.out.println(stringBuilder.toString());

	}
}

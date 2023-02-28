package Class.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 시간 복잡도 O(V^3)
 * 
 *
 */
public class MST2_Prim1 {

	public static void main(String[] args) throws IOException {

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		int N = Integer.parseInt(bufferedReader.readLine());
		int[][] connection = new int[N][N];
		boolean[] visit = new boolean[N];

		// 1. 데이터 입력
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < N; j++) {
				connection[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}

		// 2. 임의의 정점 선택
		List<Integer> selected = new ArrayList<>();

		selected.add(0);
		visit[0] = true; // 방문 처리
		int result = 0; // 총 비용

		// 3.1 정점 개수만큼 반복
		for (int i = 0; i < N-1; i++) {
			int minValue = Integer.MAX_VALUE; // 갈 수 있는 Edge중 최소 값
			int index = 0;

			// 3.2 선택한 정점에서 갈 수 있는 Edge 중 최소 비용 찾기
			for (Integer v : selected) {
				for (int j = 0; j < N; j++) {
					if (!visit[j] && connection[v][j] < minValue) {
						minValue = connection[v][j];
						index = j;
					}
				}
			}

			// 4. 최소 비용 노드로 갈 수 있는 노드 방문 표시, 선택한 노드 처리 , 최소 값 갱신
			result += minValue;
			selected.add(index);
			visit[index] = true;
		}
		System.out.println(result);
		System.out.println(selected);
	}

}

package Class.graph;

import java.util.Arrays;

public class DisjoinSetTest {

	static int[] parents; 	// index에 해당하는 원소의 부모를 저장하는 배열
	static int[] rank; 		// 랭크 표시를 위한 배열

	public static void makeSet(int vertex) {
		parents[vertex] = vertex;
	}

	public static int findSet(int vertex) {
		if (vertex == parents[vertex]) { // Root인 경우
			return vertex;
		}
//		// Root가 아닌 경우, Path가 길어지면 Root를 찾는데 오래 걸릴 수 있음
//		return findSet(parents[vertex]);
//
//		// 모든 자식의 부모를 Root로 설정
//		return parents[vertex] = findSet(parents[vertex]);

		return findSet(parents[vertex]);
	}

	public static void union(int u, int v) {
		int uRoot = findSet(u);
		int vRoot = findSet(v);

		if (uRoot == vRoot)
			return;

		int uRank = rank[uRoot];
		int vRank = rank[vRoot];

		if (uRank > vRank) {
			parents[vRoot] = uRoot; // u의 Rank가 더 크므로 u밑으로 v를 붙힌다.
			rank[vRoot] = 0; 		// v의 Root는 더이상 루트가 아니므로 Rank를 0으로 바꿔준다.

		} else if (uRank < vRank) {
			parents[uRoot] = vRoot; // v의 Rank가 더 크므로 v밑으로 u를 붙힌다.
			rank[uRoot] = 0; 		// u의 Root는 더이상 루트가 아니므로 Rank를 0으로 바꿔준다.
		} else { 					// Rank가 서로 같다면, 어떻게 결합해도 결합 이후 Rank는 반드시 증가한다. 아무대나 붙힌다.
			rank[vRoot]++;
			parents[uRoot] = vRoot;
			rank[uRoot] = 0;
		}

		//parents[findSet(u)] = findSet(v); // u가 속한 집합을 v의 자식으로

	}

	public static void main(String[] args) {
		int N = 6;
		parents = new int[N + 1];
		rank = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			makeSet(i);
		}

		System.out.println(Arrays.toString(parents));
		System.out.println(Arrays.toString(rank));
		
		union(4, 3);
		
		System.out.println(Arrays.toString(parents));
		System.out.println(Arrays.toString(rank));
		union(6, 5);
		union(5, 4);

		System.out.println((char) (findSet(6) + 'a' - 1));
		System.out.println((char) (findSet(4) + 'a' - 1));
		System.out.println(Arrays.toString(parents));
		System.out.println(Arrays.toString(rank));

	}

}

package Study.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * #풀이 방법
 * - 각 노드들 사이의 관계를 2차원 배열에 저장
 * - 시작 노드부터 연결되어 있는 사람을 계속 타고가면서 목표 노드가 나올 때 까지 탐색
 * - BFS나 DFS 둘 다 구현해봤다. -> 큰 차이는 없는듯?
 */
public class BOJ2644_촌수계산 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;

    static int N, M;
    static int START, END;

    static int result = Integer.MAX_VALUE;

    static boolean[] visitDFS;
    static int[] visitBFS;
    static boolean[][] connection;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        START = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        END = Integer.parseInt(stringTokenizer.nextToken()) - 1;

        visitDFS = new boolean[N];
        visitBFS = new int[N];
        connection = new boolean[N][N];

        M = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int to = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            connection[from][to] = true;
            connection[to][from] = true;
        }

//        dfs(0, START);
//        if (result == Integer.MAX_VALUE) {
//            System.out.println(-1);
//        } else {
//            System.out.println(result);
//        }

        bfs();
        if (visitBFS[END] == 0) {
            System.out.println(-1);
        } else {
            System.out.println(visitBFS[END]);
        }

    }

    private static void dfs(int depth, int curNode) {

        if (curNode == END) {
            result = Math.min(result, depth);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (connection[curNode][i] && !visitDFS[i]) {
                visitDFS[i] = true;
                dfs(depth + 1, i);
                visitDFS[i] = false;
            }
        }
    }

    private static void bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(START);
        visitBFS[START] = 0;

        while (!queue.isEmpty()) {
            int curNode = queue.poll();
            for (int i = 0; i < N; i++) {
                if (connection[curNode][i] && visitBFS[i] == 0) {
                    queue.offer(i);
                    visitBFS[i] = visitBFS[curNode] + 1;
                }
            }
        }

    }

}

package Study.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * List에 {자식노드 번호, 가중치 }를 담아 해당 리스트를 배열에 저장한다.
 * 두 노드를 잡아서 늘린다는 것 -> 한 노드에서 시작해서 자식을 타고 리프까지 가는 경우들 중 2개를 고른다는 것!
 * 즉, 리프가 아닌 (자식이 있는) 노드에서 출발해서
 * 해당 서브트리의 리프까지 가는 길이를 모두 구하고 그 중 가장 큰 값 2개를 고른다.(자식이 하나라면 그 값이 최대값임)
 * 리프노드가 아닌 모든 노드에 해당 작업을 수행하고 그 때 마다 가장 큰 값을 갱신해준다.
 */
public class BOJ1967_트리의지름 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;

    static int N;

    static List<int[]>[] tree;

    static int result = 0;
    static int temp = Integer.MIN_VALUE;


    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        tree = new ArrayList[N + 1];

        for (int i = 0; i < N - 1; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int parentNode = Integer.parseInt(stringTokenizer.nextToken());
            int childNode = Integer.parseInt(stringTokenizer.nextToken());
            int weight = Integer.parseInt(stringTokenizer.nextToken());
            if (tree[parentNode] == null) {
                tree[parentNode] = new ArrayList<>();
            }
            tree[parentNode].add(new int[]{childNode, weight});
        }
        for (int i = 1; i <= N; i++) {  // 모든 노드를 순회
            if (tree[i] != null) {      // 리프 노드가 아니라면
                search(tree[i]);        // 탐색 진행
            }
        }
        System.out.println(result);
    }


    private static void search(List<int[]> start) {

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < start.size(); i++) {    // 모든 자식 노드를 확인
            int[] curChild = start.get(i);
            temp = Integer.MIN_VALUE;
            dfs(curChild, curChild[1]);             // i 번째 자식 노드에 대해 dfs 진행 (리프노드까지 가면서 가중치를 모두 더해준다.)
            list.add(temp);                         // i 번째 자식에서 출발해 리프까지 가는데 가장 긴 길이를 list에 저장
        }

        Collections.sort(list, (o1, o2) -> Integer.compare(o2, o1));    // 길이들을 내림차순으로 정렬
        if (list.size() == 1) {                                         // 길이가 1개면 그값 자체가 현재 만들 수 있는 가장 큰 지름
            result = Math.max(result, list.get(0));
        } else {
            result = Math.max(result, list.get(0) + list.get(1));       // 길이가 2개 이상이면 가장 큰 2개를 더한 값이 최대 지름
        }
    }

    private static void dfs(int[] curNode, int curValue) {

        List<int[]> childList = tree[curNode[0]];
        if (childList == null) {                // 리프 노드라면
            temp = Math.max(temp, curValue);    // 현재까지 가중치 합을 갱신
            return;                             // 종료
        }


        for (int i = 0; i < childList.size(); i++) {        //모든 자식을 순회하며 dfs 진행
            int[] nextNode = childList.get(i);
            dfs(nextNode, curValue + nextNode[1]);
        }


    }


}

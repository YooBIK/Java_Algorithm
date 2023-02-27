package Study.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class BOJ1967 {

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
        int maxLength = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            if (tree[i] != null) {
                search(tree[i]);
            }
        }

        System.out.println(result);
    }


    private static void search(List<int[]> start) {

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < start.size(); i++) {
            int[] curChild = start.get(i);
            temp = Integer.MIN_VALUE;
            dfs(curChild, curChild[1]);
            list.add(temp);
        }

        Collections.sort(list, (o1, o2) -> Integer.compare(o2, o1));
        if (list.size() == 1) {
            result = Math.max(result, list.get(0));
        } else {
            result = Math.max(result, list.get(0) + list.get(1));
        }
    }

    private static void dfs(int[] curNode, int curValue) {

        List<int[]> childList = tree[curNode[0]];
        if (childList == null) {
            temp = Math.max(temp, curValue);
            return;
        }


        for (int i = 0; i < childList.size(); i++) {
            int[] nextNode = childList.get(i);
            dfs(nextNode, curValue + nextNode[1]);
        }


    }


}

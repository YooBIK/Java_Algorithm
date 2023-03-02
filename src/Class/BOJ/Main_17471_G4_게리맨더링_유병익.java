package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_17471_G4_게리맨더링_유병익 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;

    static int N;
    static int[] population;
    static boolean[] isUsed;
    static boolean[][] connection;
    static int[] parents;
    static int answer = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        population = new int[N + 1];
        parents = new int[N + 1];
        isUsed = new boolean[N + 1];
        connection = new boolean[N + 1][N + 1];

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(stringTokenizer.nextToken());
        }


        for (int i = 1; i <= N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            for (int j = 0; j < n; j++) {
                int to = Integer.parseInt(stringTokenizer.nextToken());
                connection[i][to] = true;
            }
        }

        subset(1);

        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }

    }

    private static void makeSet() {
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
    }

    private static int findRoot(int vertex) {
        if (parents[vertex] == vertex) {
            return vertex;
        }
        return findRoot(parents[vertex]);
    }

    private static void subset(int depth) {
        if (depth == N + 1) {
            compare();
            return;
        }

        isUsed[depth] = true;
        subset(depth + 1);

        isUsed[depth] = false;
        subset(depth + 1);
    }

    private static void compare() {
        List<Integer> selected = new ArrayList<>();
        List<Integer> unselected = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            if (isUsed[i]) {
                selected.add(i);
            } else {
                unselected.add(i);
            }
        }
        if (selected.size() == 0 || unselected.size() == 0) {
            return;
        }

        makeSet();
        if (canConnect(selected) && canConnect(unselected)) {
            int result = Math.abs(getSum(selected) - getSum(unselected));
            answer = Math.min(answer, result);
        }
    }

    private static int getSum(List<Integer> list) {
        int sum = 0;
        for (Integer i : list) {
            sum += population[i];
        }
        return sum;
    }

    private static boolean canConnect(List<Integer> selected) {

        for (int i = 0; i < selected.size(); i++) {
            for (int j = 0; j < selected.size(); j++) {
                union(selected.get(i), selected.get(j));
            }
        }

        int root = findRoot(selected.get(0));
        for (int i = 0; i < selected.size(); i++) {
            if (findRoot(selected.get(i)) != root) {
                return false;
            }
        }
        return true;
    }

    private static void union(int first, int second) {
        int firstRoot = findRoot(first);
        int secondRoot = findRoot(second);
        if (firstRoot != secondRoot) {
            if (connection[first][second]) {
                parents[secondRoot] = firstRoot;
            }
        }
    }

}

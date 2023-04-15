package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1647 {

    static class Edge {
        int start;
        int end;
        int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;

    static int N, M;
    static PriorityQueue<Edge> priorityQueue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.weight, o2.weight));
    static PriorityQueue<Edge> selectedEdge = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.weight, o1.weight));

    static int[] parents;

    public static void main(String[] args) throws IOException {

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int start = Integer.parseInt(stringTokenizer.nextToken());
            int end = Integer.parseInt(stringTokenizer.nextToken());
            int weight = Integer.parseInt(stringTokenizer.nextToken());
            priorityQueue.offer(new Edge(start, end, weight));
        }
        System.out.println(getResult());

    }

    private static long getResult() {
        makeSet();

        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            if (findRoot(edge.start) != findRoot(edge.end)) {
                union(edge.start, edge.end);
                selectedEdge.offer(edge);
            }
        }
        long result = 0;
        selectedEdge.poll();
        while (!selectedEdge.isEmpty()) {
            result += selectedEdge.poll().weight;
        }
        return result;
    }

    private static int findRoot(int vertex) {
        if (parents[vertex] == vertex) {
            return vertex;
        }
        return parents[vertex] = findRoot(parents[vertex]);
    }

    private static void union(int first, int second) {
        int firstRoot = findRoot(first);
        int secondRoot = findRoot(second);

        if (firstRoot != secondRoot) {
            parents[firstRoot] = secondRoot;
        }
    }

    private static void makeSet() {
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
    }
}

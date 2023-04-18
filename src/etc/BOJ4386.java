package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ4386 {

    static class Edge {
        int start;
        int end;
        double weight;

        public Edge(int start, int end, double weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;

    static int N;
    static PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o1.weight, o2.weight));


    static int[] parents;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());

        List<double[]> starList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            double posX = Double.parseDouble(stringTokenizer.nextToken());
            double posY = Double.parseDouble(stringTokenizer.nextToken());
            starList.add(new double[]{posX, posY});
        }

        makeSet();
        initPriorityQueue(starList);
        System.out.printf("%.2f", getResult());

    }

    private static double getResult() {

        double result = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (findRoot(edge.start) != findRoot(edge.end)) {
                union(edge.start, edge.end);
                result += edge.weight;
            }
        }
        return result;
    }

    private static void initPriorityQueue(List<double[]> starList) {

        for (int i = 0; i < N; i++) {
            double[] current = starList.get(i);
            for (int j = i + 1; j < N; j++) {
                double[] next = starList.get(j);
                double distance = Math.sqrt(Math.pow(Math.abs(current[0] - next[0]), 2) + Math.pow(Math.abs(current[1] - next[1]), 2));
                pq.add(new Edge(i, j, distance));
            }
        }
    }

    private static void makeSet() {
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
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

}

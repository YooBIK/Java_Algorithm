package etc.CodeTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CodeTreeBread {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;

    static int N, M;
    static int[][] map;
    static boolean[][][] visit;


    static int[] dirRow = {-1, 0, 0, 1};
    static int[] dirCol = {0, -1, 1, 0};

    static List<int[]> baseCampList = new ArrayList<>();
    static HashMap<Integer, int[]> storeMap = new HashMap<>();


    public static void main(String[] args) throws IOException {
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        map = new int[N][N];
        visit = new boolean[M][N][N];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if (map[i][j] == 1) {
                    baseCampList.add(new int[]{i, j});
                }
            }
        }

        for (int i = 1; i <= M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int row = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int col = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            storeMap.put(i, new int[]{row, col});
        }
        start();

    }

    private static void start() {

        int count = 0;
        int time = 0;

        List<Queue<int[]>> queueList = new ArrayList<>();

        while (count < M) {
            time++;

            for (int i = 0; i < queueList.size(); i++) {
                Queue<int[]> curQueue = queueList.get(i);
                boolean[][] curVisit = visit[i];
                int[] curTarget = storeMap.get(i + 1);

                if (map[curTarget[0]][curTarget[1]] == -1) {
                    continue;
                }

                int size = curQueue.size();
                for (int j = 0; j < size; j++) {
                    int[] curPos = curQueue.poll();

                    for (int k = 0; k < 4; k++) {
                        int newRow = curPos[0] + dirRow[k];
                        int newCol = curPos[1] + dirCol[k];
                        if (newRow >= 0 && newRow < N && newCol >= 0 && newCol < N && map[newRow][newCol] != -1 && !curVisit[newRow][newCol]) {
                            if (curTarget[0] == newRow && curTarget[1] == newCol) {
                                map[newRow][newCol] = -1;
                                count++;
                                break;
                            } else {
                                curVisit[newRow][newCol] = true;
                                curQueue.offer(new int[]{newRow, newCol});
                            }
                        }
                    }
                }
            }

            if (time <= M) {
                int[] storePosition = storeMap.get(time);   // 현재 시간의 출발하는 사람의 목표 편의점 위치
                Collections.sort(baseCampList, (o1, o2) -> {
                    int o1Dist = Math.abs(o1[0] - storePosition[0]) + Math.abs(o1[1] - storePosition[1]);
                    int o2Dist = Math.abs(o2[0] - storePosition[0]) + Math.abs(o2[1] - storePosition[1]);
                    return o1Dist == o2Dist ?
                            (o1[0] == o2[0] ? Integer.compare(o1[1], o2[1]) : Integer.compare(o1[0], o2[0])) : Integer.compare(o1Dist, o2Dist);
                });
                int[] curBaseCamp = baseCampList.remove(0);    // 현재 시간의 사람이 출발할 베이스 캠프
                map[curBaseCamp[0]][curBaseCamp[1]] = -1;   // 이제 못감

                Queue<int[]> queue = new ArrayDeque<>();
                queue.offer(new int[]{curBaseCamp[0], curBaseCamp[1]});
                queueList.add(queue);
            }
        }
        System.out.println(time);

    }
}

package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17142_G3_연구소3_유병익 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;

    static int N, M;
    static int NUMBER_OF_VIRUS = 0;
    static int[][] map;
    static boolean[][] visit;

    static int[] dirRow = {0, 0, -1, 1};
    static int[] dirCol = {1, -1, 0, 0};

    static List<int[]> virusPositions = new ArrayList<>();
    static List<int[]> selectedViruses;

    static int EMPTY_AREA = 0;
    static int EMPTY_AREA_TEMP = 0;
    static int CUR_TIME;

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if (map[i][j] == 2) {
                    NUMBER_OF_VIRUS++;
                    virusPositions.add(new int[]{i, j});
                }
                if (map[i][j] == 0) {
                    EMPTY_AREA++;
                }
            }
        }
        if(EMPTY_AREA == 0){
            System.out.println(0);
            return;
        }


        selectedViruses = new ArrayList<>(virusPositions);
        combination(0, 0);
        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }

    }

    private static void combination(int depth, int startPosition) {
        if (depth == NUMBER_OF_VIRUS - M) {
            int[][] temp = copyMap();

            visit = new boolean[N][N];
            bfs(temp);

            if (CUR_TIME != -1) {
                answer = Math.min(answer, CUR_TIME);
            }
            return;
        }

        for (int i = startPosition; i < NUMBER_OF_VIRUS; i++) {
            int[] curPosition = virusPositions.get(i);
            map[curPosition[0]][curPosition[1]] = -1;
            selectedViruses.remove(curPosition);
            combination(depth + 1, i + 1);
            map[curPosition[0]][curPosition[1]] = 2;
            selectedViruses.add(curPosition);
        }

    }


    private static void bfs(int[][] temp) {
        EMPTY_AREA_TEMP = EMPTY_AREA;
        CUR_TIME = -1;

        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < selectedViruses.size(); i++) {
            int[] cur = selectedViruses.get(i);
            int curRow = cur[0];
            int curCol = cur[1];
            int time = 0;
            queue.offer(new int[]{curRow, curCol, time});
            visit[cur[0]][cur[1]] = true;
        }

        while (!queue.isEmpty()) {
            int[] curPos = queue.poll();
            int curRow = curPos[0];
            int curCol = curPos[1];
            int curTime = curPos[2];
            for (int i = 0; i < 4; i++) {
                int newRow = curRow + dirRow[i];
                int newCol = curCol + dirCol[i];
                if (newRow >= 0 && newRow < N && newCol >= 0 && newCol < N) {
                    if (!visit[newRow][newCol] && temp[newRow][newCol] != 1) {
                        visit[newRow][newCol] = true;
                        queue.offer(new int[]{newRow, newCol, curTime + 1});
                        if (temp[newRow][newCol] == 0) {
                            EMPTY_AREA_TEMP--;
                        }

                        if (EMPTY_AREA_TEMP == 0) {
                            CUR_TIME = curTime + 1;
                            return;
                        }
                    }
                }
            }
        }

    }

    private static int[][] copyMap() {
        int[][] temp = new int[N][N];
        for (int i = 0; i < N; i++) {
            temp[i] = map[i].clone();
        }
        return temp;
    }

}

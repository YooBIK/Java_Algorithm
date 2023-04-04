package etc.CodeTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TheGameOfTag {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;

    static final int RUNNER = 2;
    static final int TAGGER = 1;
    static final int CLOCK = 1;
    static final int REVERSE = 0;

    static int N, M, H, K;

    static int[][] map;
    static boolean[][] visit;
    static boolean[][] tree;


    // 우 하 좌 상
    static int[] dirRow = {0, 1, 0, -1};  // up down -> row col , left right -> col row
    static int[] dirCol = {1, 0, -1, 0};


    static int[] clockRow = {-1, 0, 1, 0};
    static int[] clockCol = {0, 1, 0, -1};

    static int[] reverseClockRow = {1, 0, -1, 0};
    static int[] reverseClockCol = {0, 1, 0, -1};

    // row, col, dir, 현재 해당 방향 이동 횟수, 해당 방향 최대 이동 횟수, 방향 전환 횟수
    static int[] curTaggerInfo = new int[6];

    static int answer = 0;


    static Queue<int[]> runnerQueue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        H = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[N][N];
        map[N / 2][N / 2] = TAGGER;
        tree = new boolean[N][N];
        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int row = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int col = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int dir = Integer.parseInt(stringTokenizer.nextToken()) - 1;    // 0 이면 좌우, 1이면 상하

            map[row][col] = RUNNER;
            runnerQueue.offer(new int[]{row, col, dir});
        }

        for (int i = 0; i < H; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int treeRow = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int treeCol = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            tree[treeRow][treeCol] = true;
        }

        playGame();
        System.out.println(answer);

    }

    private static void playGame() {
        int curTime = 0;

        visit = new boolean[N][N];
        curTaggerInfo[0] = N / 2;
        curTaggerInfo[1] = N / 2;
        curTaggerInfo[2] = 0;
        curTaggerInfo[3] = CLOCK;

        while (curTime < K) {

            System.out.println(curTime);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();


            moveRunner();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
            if (curTaggerInfo[3] == CLOCK) {
                answer += moveTagger(curTime);
            } else {
                answer += moveTaggerReverse(curTime);
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();

            curTime++;
            answer += catchRunner(curTime);
        }
    }


    private static int catchRunner(int curTime) {

        int score = 0;

        int curRow = curTaggerInfo[0];
        int curCol = curTaggerInfo[1];
        int curDir = curTaggerInfo[2];
        int curState = curTaggerInfo[3];

        for (int i = 0; i < 2; i++) {
            if (curState == CLOCK) {
                curRow += clockRow[curDir];
                curCol += clockCol[curDir];
            } else {
                curRow += reverseClockRow[curDir];
                curCol += reverseClockCol[curDir];
            }
            if (curRow >= 0 && curRow < N && curCol >= 0 && curCol < N && map[curRow][curCol] == RUNNER && !tree[curRow][curCol]) {
                map[curRow][curCol] = 0;
                score += curTime;
            }
        }
        return score;

    }

    private static int moveTagger(int curTime) {

        int score = 0;

        int curRow = curTaggerInfo[0];
        int curCol = curTaggerInfo[1];
        int curDir = curTaggerInfo[2];

        int newRow = curRow + clockRow[curDir];
        int newCol = curCol + clockCol[curDir];

        if (map[newRow][newCol] == RUNNER && !tree[newRow][newCol]) {
            score += curTime;
        }

        map[curRow][curCol] = 0;
        map[newRow][newCol] = TAGGER;

        curTaggerInfo[0] = newRow;
        curTaggerInfo[1] = newCol;

        visit[newRow][newCol] = true;

        int nextDir = (curDir + 1) % 4;
        newRow += clockRow[nextDir];
        newCol += clockCol[nextDir];

        if (!visit[newRow][newCol]) {
            curTaggerInfo[2] = nextDir;
        }

        if (curTaggerInfo[0] == 0 && curTaggerInfo[1] == 0) {
            visit = new boolean[N][N];
            visit[0][0] = true;
            curTaggerInfo[3] = REVERSE;
            curTaggerInfo[2] = 0;
        }
        return score;
    }

    private static int moveTaggerReverse(int curTime) {

        int score = 0;

        int curRow = curTaggerInfo[0];
        int curCol = curTaggerInfo[1];
        int curDir = curTaggerInfo[2];

        int newRow = curRow + reverseClockRow[curDir];
        int newCol = curCol + reverseClockCol[curDir];

        if (map[newRow][newCol] == RUNNER && !tree[newRow][newCol]) {
            score += curTime;
        }

        map[curRow][curCol] = 0;
        map[newRow][newCol] = TAGGER;

        curTaggerInfo[0] = newRow;
        curTaggerInfo[1] = newCol;

        newRow += reverseClockRow[curDir];
        newCol += reverseClockCol[curDir];
        if (newRow < 0 || newRow >= N || newCol < 0 || newCol >= N || visit[newRow][newCol]) {
            curTaggerInfo[2] = (curDir + 1) % 4;
        }

        if (curTaggerInfo[0] == N / 2 && curTaggerInfo[1] == N / 2) {
            visit = new boolean[N][N];
            visit[N / 2][N / 2] = true;
            curTaggerInfo[3] = CLOCK;
            curTaggerInfo[2] = 0;
        }
        return score;
    }

    private static void moveRunner() {

        int size = runnerQueue.size();

        int[][] temp = new int[N][N];
        temp[curTaggerInfo[0]][curTaggerInfo[1]] = TAGGER;

        for (int i = 0; i < size; i++) {
            int[] curRunnerInfo = runnerQueue.poll();
            if (map[curRunnerInfo[0]][curRunnerInfo[1]] != RUNNER) {    // 직전 TIME 에 TAGGER 한테 잡힌 경우
                continue;
            }

            int newRow = curRunnerInfo[0] + dirRow[curRunnerInfo[2]];
            int newCol = curRunnerInfo[1] + dirCol[curRunnerInfo[2]];
            if (newRow < 0 || newRow >= N || newCol < 0 || newCol >= N) {
                int newDir = (curRunnerInfo[2] + 2) % 4;
                newRow = curRunnerInfo[0] + dirRow[newDir];
                newCol = curRunnerInfo[1] + dirCol[newDir];
                curRunnerInfo[2] = newDir;
            }

            if (map[newRow][newCol] != TAGGER) {
                curRunnerInfo[0] = newRow;
                curRunnerInfo[1] = newCol;
            }
            temp[curRunnerInfo[0]][curRunnerInfo[1]] = RUNNER;
            runnerQueue.offer(curRunnerInfo);
        }
        map = temp;
    }


}

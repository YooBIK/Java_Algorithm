package etc.CodeTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Battleground {

    static class Player {
        int playerNum;
        int curRow;
        int curCol;
        int curDir;
        int power;
        int gun;

        public Player(int playerNum, int curRow, int curCol, int curDir, int power, int gun) {
            super();
            this.playerNum = playerNum;
            this.curRow = curRow;
            this.curCol = curCol;
            this.curDir = curDir;
            this.power = power;
            this.gun = gun;
        }

        public int getMaxPower() {
            return power + gun;
        }

        @Override
        public String toString() {
            return "playerNum=" + playerNum + ", curRow=" + curRow + ", curCol=" + curCol + ", curDir=" + curDir
                    + ", power=" + power + ", gun=" + gun;
        }

    }

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;

    static int N, M, K;

    static int[] dirRow = {-1, 0, 1, 0};
    static int[] dirCol = {0, 1, 0, -1};

    static PriorityQueue<Integer>[][] gunMap;
    static PriorityQueue<Player>[][] playerMap;
    static Queue<Player> playerQueue = new ArrayDeque<>();

    static int[] score;

    public static void main(String[] args) throws IOException {
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        playerMap = new PriorityQueue[N][N];
        gunMap = new PriorityQueue[N][N];
        score = new int[M];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                playerMap[i][j] = new PriorityQueue<>((o1, o2)
                        -> o1.getMaxPower() == o2.getMaxPower() ? Integer.compare(o1.power, o2.power) : Integer.compare(o1.getMaxPower(), o2.getMaxPower()));
                gunMap[i][j] = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
                int curGun = Integer.parseInt(stringTokenizer.nextToken());
                if (curGun > 0) {
                    gunMap[i][j].offer(curGun);
                }
            }
        }

        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int row = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int col = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int dir = Integer.parseInt(stringTokenizer.nextToken());
            int power = Integer.parseInt(stringTokenizer.nextToken());
            Player curPlayer = new Player(i, row, col, dir, power, 0);
            playerMap[row][col].offer(curPlayer);
            playerQueue.offer(curPlayer);
        }

        playGame();
        for (int i = 0; i < score.length; i++) {
            System.out.print(score[i] + " ");
        }
    }

    private static void playGame() {
        for (int round = 1; round <= K; round++) {
            movePlayers();
        }
    }

    private static void movePlayers() {

        int size = playerQueue.size();

        for (int i = 0; i < size; i++) {
            Player curPlayer = playerQueue.poll();

            int curRow = curPlayer.curRow;
            int curCol = curPlayer.curCol;
            int curDir = curPlayer.curDir;
            playerMap[curRow][curCol].remove(curPlayer);
            int newRow = curRow + dirRow[curDir];
            int newCol = curCol + dirCol[curDir];
            int newDir = curDir;

            if (!boundaryCheck(newRow, newCol)) {
                newDir = (curDir + 2) % 4;
                newRow = curRow + dirRow[newDir];
                newCol = curCol + dirCol[newDir];
            }

            curPlayer.curRow = newRow;
            curPlayer.curCol = newCol;
            curPlayer.curDir = newDir;
            playerMap[newRow][newCol].add(curPlayer);

            if (playerMap[newRow][newCol].size() == 2) {
                Player loser = playerMap[newRow][newCol].poll();
                Player winner = playerMap[newRow][newCol].peek();

                score[winner.playerNum] += winner.getMaxPower() - loser.getMaxPower();
                moveLoser(loser);
                if (!gunMap[newRow][newCol].isEmpty() && gunMap[newRow][newCol].peek() > winner.gun) {
                    if (winner.gun > 0) {
                        gunMap[newRow][newCol].offer(winner.gun);
                    }
                    winner.gun = gunMap[newRow][newCol].poll();
                }
            } else {
                if (!gunMap[newRow][newCol].isEmpty() && gunMap[newRow][newCol].peek() > curPlayer.gun) {
                    if (curPlayer.gun > 0) {
                        gunMap[newRow][newCol].offer(curPlayer.gun);
                    }
                    curPlayer.gun = gunMap[newRow][newCol].poll();
                }
            }
            playerQueue.offer(curPlayer);
        }

    }

    private static void moveLoser(Player loser) {
        int curRow = loser.curRow;
        int curCol = loser.curCol;
        int curDir = loser.curDir;

        if (loser.gun > 0) {
            gunMap[curRow][curCol].add(loser.gun);
            loser.gun = 0;
        }

        int newRow = curRow + dirRow[curDir];
        int newCol = curCol + dirCol[curDir];

        while (!boundaryCheck(newRow, newCol) || !playerMap[newRow][newCol].isEmpty()) {
            curDir = (curDir + 1) % 4;
            newRow = curRow + dirRow[curDir];
            newCol = curCol + dirCol[curDir];
        }
        playerMap[curRow][curCol].remove(loser);
        loser.curRow = newRow;
        loser.curCol = newCol;
        loser.curDir = curDir;
        playerMap[newRow][newCol].offer(loser);

        if (!gunMap[loser.curRow][loser.curCol].isEmpty() && gunMap[loser.curRow][loser.curCol].peek() > loser.gun) {
            loser.gun = gunMap[loser.curRow][loser.curCol].poll();
        }
    }

    private static boolean boundaryCheck(int newRow, int newCol) {
        return newRow >= 0 && newRow < N && newCol >= 0 && newCol < N;
    }

}

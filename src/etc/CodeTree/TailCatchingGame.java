package etc.CodeTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TailCatchingGame {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;

    static int answer = 0;
    static int N, M, K;
    static int[][] map;

    static int[] dirRow = {0, 1, 0, -1};    // dir++ -> 시계방향 , dir+2 -> 반시계 방향
    static int[] dirCol = {1, 0, -1, 0};

    static class Group {
        Node head;
        Node tail;
        List<Node> nodeList;

        public Group(Node head, Node tail, List<Node> nodeList) {
            this.head = head;
            this.tail = tail;
            this.nodeList = nodeList;
            setHeadDirection();
            setDirection();
        }

        public void setHeadDirection() {
            for (int i = 0; i < 4; i++) {
                int newRow = head.row + dirRow[i];
                int newCol = head.col + dirCol[i];
                if (boundaryCheck(newRow, newCol) && (map[newRow][newCol] == 4 || map[newRow][newCol] == 3)) {
                    head.dirRow = dirRow[i];
                    head.dirCol = dirCol[i];
                    return;
                }
            }
        }

        public void setDirection() {
            Node temp = tail;
            while (temp.parent != null) {
                temp.dirRow = temp.parent.row - temp.row;
                temp.dirCol = temp.parent.col - temp.col;
                temp = temp.parent;
            }
        }

        public void moveNodes() {
            setHeadDirection();
            setDirection();
            Node temp = head;
            while (temp.child != null) {
                temp.move();
                temp = temp.child;
            }
            tail.move();

            map[head.row][head.col] = 1;
            map[tail.row][tail.col] = 3;
        }

        public void swapHeadTail() {
            Node temp = head;
            head = tail;
            head.child = head.parent;
            head.parent = null;

            tail = temp;
            tail.parent = tail.child;
            tail.child = null;

            temp = head.child;
            while (temp.child != null) {
                Node tempNode = temp.child;
                temp.child = temp.parent;
                temp.parent = tempNode;
                temp = temp.child;
            }

            map[head.row][head.col] = 1;
            map[tail.row][tail.col] = 3;

            setIndex();
        }

        public void setIndex() {
            Node temp = head;
            int i = 1;

            while (temp.child != null) {
                temp.index = i++;
                temp = temp.child;
            }
            tail.index = i;
        }

    }

    static class Node {

        Node parent = null;
        Node child = null;

        int index;

        int row;
        int col;

        int dirRow;
        int dirCol;

        public Node(int index, int row, int col) {
            this.index = index;
            this.row = row;
            this.col = col;
        }

        public void move() {
            map[row][col] = 4;
            row = row + dirRow;
            col = col + dirCol;
            map[row][col] = 2;
        }
    }

    public static void main(String[] args) throws IOException {

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[N][N];
        List<int[]> headList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                int curVal = Integer.parseInt(stringTokenizer.nextToken());
                if (curVal == 1) {
                    headList.add(new int[]{i, j});
                }
                map[i][j] = curVal;
            }
        }

        List<Group> groupList = makeGroup(headList);

        int curRound = 0;
        while (curRound < K) {
            int curStage = (curRound / N) % 4;
            int curTarget = curRound % N;
            moveGroup(groupList);
            findTarget(groupList, curStage, curTarget);
            curRound++;
        }
        System.out.println(answer);

    }


    private static void findTarget(List<Group> groupList, int curStage, int curTarget) {
        int[] stageStartRow = {curTarget, N - 1, N - curTarget - 1, 0};
        int[] stageStartCol = {0, curTarget, N - 1, N - curTarget - 1};

        int[] searchDirRow = {0, -1, 0, 1};
        int[] searchDirCol = {1, 0, -1, 0};

        for (int i = stageStartRow[curStage], j = stageStartCol[curStage]; boundaryCheck(i, j); i += searchDirRow[curStage], j += searchDirCol[curStage]) {
            if (map[i][j] > 0 && map[i][j] < 4) {
                for (int k = 0; k < groupList.size(); k++) {
                    boolean flag = false;
                    List<Node> nodeList = groupList.get(k).nodeList;
                    for (Node curNode : nodeList) {
                        if (curNode.row == i && curNode.col == j) {
                            flag = true;
                            answer += curNode.index * curNode.index;
                            break;
                        }
                    }
                    if (flag) {
                        groupList.get(k).swapHeadTail();
                        return;
                    }
                }
            }
        }
    }

    private static void moveGroup(List<Group> groupList) {
        for (Group group : groupList) {
            group.moveNodes();
        }
    }

    private static List<Group> makeGroup(List<int[]> headList) {

        List<Group> groupList = new ArrayList<>();
        for (int[] curPos : headList) {
            Node head = new Node(1, curPos[0], curPos[1]);
            Node tail = findTail(head);
            groupList.add(new Group(head, tail, getNodeList(head)));
        }
        return groupList;
    }

    private static List<Node> getNodeList(Node head) {
        List<Node> nodeList = new ArrayList<>();

        Node curNode = head;
        while (curNode.child != null) {
            nodeList.add(curNode);
            curNode = curNode.child;
        }
        nodeList.add(curNode);
        return nodeList;
    }

    private static Node findTail(Node head) {

        boolean[][] visit = new boolean[N][N];
        Queue<Node> queue = new ArrayDeque<>();

        queue.offer(head);
        visit[head.row][head.col] = true;

        Node tail = null;
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            for (int i = 0; i < 4; i++) {
                int newRow = curNode.row + dirRow[i];
                int newCol = curNode.col + dirCol[i];

                if (boundaryCheck(newRow, newCol) && !visit[newRow][newCol] && map[newRow][newCol] == 2) {
                    Node curChild = new Node(curNode.index + 1, newRow, newCol);
                    curNode.child = curChild;
                    curChild.parent = curNode;
                    tail = curChild;
                    queue.offer(curChild);
                    visit[newRow][newCol] = true;
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            int newRow = tail.row + dirRow[i];
            int newCol = tail.col + dirCol[i];
            if (boundaryCheck(newRow, newCol) && map[newRow][newCol] == 3) {
                Node realTail = new Node(tail.index + 1, newRow, newCol);
                tail.child = realTail;
                realTail.parent = tail;

                tail = realTail;
            }
        }
        return tail;
    }

    private static boolean boundaryCheck(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < N;
    }
}

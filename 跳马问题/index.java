import java.util.*;

public class HorseJump {
    private int[][] board;
    private int[][] moves;
    private int startX, startY;
    private int endX, endY;
    private int pathCount; /
    private int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};
    private int[] dy = {2, 1, -1, -2, -2, -1, 1, 2};

    public HorseJump(int n, int m, int startX, int startY, int endX, int endY) {
        board = new int[n][m];
        moves = new int[n][m];
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        pathCount = 0;
    }

    private void calculateMoves() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int count = 0;
                for (int k = 0; k < 8; k++) {
                    int nextX = i + dx[k];
                    int nextY = j + dy[k];
                    if (nextX >= 0 && nextX < board.length && nextY >= 0 && nextY < board[0].length) {
                        count++;
                    }
                }
                moves[i][j] = count;
            }
        }
    }

    private void searchPath(int x, int y, int step) {
        if (x == endX && y == endY && step == board.length * board[0].length - 1) {
            pathCount++;
            printBoard();
            System.out.println("成功路径数：" + pathCount);
            return;
        }
        if (step >= board.length * board[0].length - 1) {
            return;
        }
        board[x][y] = step;
        for (int i = 0; i < 8; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if (nextX >= 0 && nextX < board.length && nextY >= 0 && nextY < board[0].length && board[nextX][nextY] == -1) {
                int nextStep = step + 1;
                board[nextX][nextY] = nextStep;
                searchPath(nextX, nextY, nextStep);
                board[nextX][nextY] = -1;
            }
        }
        board[x][y] = -1;
    }

    private void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.printf("%3d", board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void solve() {
        calculateMoves();
        board[startX][startY] = 0;
        searchPath(startX, startY, 0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入棋盘的行数：");
        int n = scanner.nextInt();
        System.out.print("请输入棋盘的列数：");
        int m = scanner.nextInt();
        System.out.print("请输入起始位置的横坐标：");
        int startX = scanner.nextInt();
        System.out.print("请输入起始位置的纵坐标：");
        int startY = scanner.nextInt();
        System.out.print("请输入目标位置的横坐标：");
        int endX = scanner.nextInt();
        System.out.print("请输入目标位置的纵坐标：");
        int endY = scanner.nextInt();

        HorseJump horseJump = new HorseJump(n, m, startX, startY, endX, endY);
        horseJump.solve();
    }
}

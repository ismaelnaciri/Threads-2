package ex6;

import java.util.concurrent.Semaphore;

public class Ex6 {
    private static final int bounds = 6;
    private static final int[][] moves = {{2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};
    private static final int NUM_THREADS = bounds * bounds;
    private static int threadCounter = 0;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < bounds; i++) {
            for (int j = 0; j < bounds; j++) {
                Thread t = new Thread(new KnightTourSolver(i, j));
                t.start();
            }
        }

        // Wait until all threads finish
        synchronized (Ex6.class) {
            while (threadCounter < NUM_THREADS) {
                try {
                    Ex6.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        long endTime = System.currentTimeMillis();
        long execTime = endTime - startTime;
        System.out.println("Execution Time: " + execTime + " milliseconds");
    }

    private static boolean solveKnightTour(int[][] board, int x, int y, int moveCount) {
        if (moveCount > bounds * bounds) {
            return true;
        }

        for (int[] move : moves) {
            int newX = x + move[0];
            int newY = y + move[1];
            if (isValidMove(board, newX, newY)) {
                board[newX][newY] = moveCount;
                if (solveKnightTour(board, newX, newY, moveCount + 1)) {
                    return true;
                }
                board[newX][newY] = 0;
            }
        }
        return false;
    }

    private static boolean isValidMove(int[][] board, int x, int y) {
        return x >= 0 && x < bounds && y >= 0 && y < bounds && board[x][y] == 0;
    }

    private static void printBoard(int[][] board, int startX, int startY) {
        System.out.println("Initial horse position: ( " + startX + " , " + startY + " )");
        for (int i = 0; i < bounds; i++) {
            for (int j = 0; j < bounds; j++) {
                System.out.printf("| %2d ", board[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("-----------------------------");
    }

    static class KnightTourSolver implements Runnable {
        private int startX;
        private int startY;

        public KnightTourSolver(int startX, int startY) {
            this.startX = startX;
            this.startY = startY;
        }

        @Override
        public void run() {
            int[][] board = new int[bounds][bounds];
            board[startX][startY] = 1;
            if (solveKnightTour(board, startX, startY, 2)) {
                synchronized (Ex6.class) {
                    printBoard(board, startX, startY);
                    threadCounter++;
                    Ex6.class.notifyAll();
                }
            } else {
                synchronized (Ex6.class) {
                    System.out.println("Initial horse position: ( " + startX + " , " + startY + " )");
                    System.out.println("No solution found.");
                    System.out.println("-----------------------------");
                    threadCounter++;
                    Ex6.class.notifyAll();
                }
            }
        }
    }
}

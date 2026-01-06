package com.grab.test;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class TicTacToeTimeoutReentrantLock {

    private static final char[][] board = new char[3][3];
    private static final ReentrantLock boardLock = new ReentrantLock();
    private static volatile char currentTurn = 'X';
    private static volatile boolean gameOver = false;

    private static Thread playerXThread, playerOThread;

    public static void main(String[] args) {
        initializeBoard();

        playerXThread = new Thread(() -> play('X'));
        playerOThread = new Thread(() -> play('O'));

        playerXThread.start();
        playerOThread.start();

        while (!gameOver) {
            Thread currentPlayerThread = currentTurn == 'X' ? playerXThread : playerOThread;

            try {
                Thread.sleep(5000); // Wait 5 seconds for the current player
                if (!gameOver && currentPlayerThread.isAlive()) {
                    System.out.println("Player " + currentTurn + " took too long. Interrupting...");
                    currentPlayerThread.interrupt();
                }
            } catch (InterruptedException ignored) {}
        }
    }

    private static void play(char symbol) {
        Scanner scanner = new Scanner(System.in);

        while (!gameOver) {
            if (currentTurn != symbol) {
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                continue;
            }

            try {
                boardLock.lock();
                System.out.println("Player " + symbol + "'s turn. Enter row and column (0-2): ");

                int row, col;
                try {
                    row = scanner.nextInt();
                    col = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Invalid input. Skipping turn.");
                    skipTurn();
                    continue;
                }

                if (!isValidMove(row, col)) {
                    System.out.println("Invalid move. Try again.");
                    continue;
                }

                board[row][col] = symbol;
                printBoard();

                if (checkWin(symbol)) {
                    System.out.println("Player " + symbol + " wins!");
                    gameOver = true;
                } else if (isDraw()) {
                    System.out.println("Game is a draw.");
                    gameOver = true;
                } else {
                    switchTurn();
                }

            } catch (Exception e) {
                System.out.println("Player " + symbol + " interrupted. Skipping turn.");
                switchTurn(); // skip turn on timeout
            } finally {
                if (boardLock.isHeldByCurrentThread()) boardLock.unlock();
            }
        }
    }

    private static void skipTurn() {
        if (boardLock.isHeldByCurrentThread()) boardLock.unlock();
        switchTurn();
    }

    private static synchronized void switchTurn() {
        currentTurn = currentTurn == 'X' ? 'O' : 'X';
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    private static boolean checkWin(char sym) {
        for (int i = 0; i < 3; i++)
            if ((board[i][0] == sym && board[i][1] == sym && board[i][2] == sym) ||
                (board[0][i] == sym && board[1][i] == sym && board[2][i] == sym))
                return true;

        return (board[0][0] == sym && board[1][1] == sym && board[2][2] == sym) ||
               (board[0][2] == sym && board[1][1] == sym && board[2][0] == sym);
    }

    private static boolean isDraw() {
        for (char[] row : board)
            for (char cell : row)
                if (cell == ' ') return false;
        return true;
    }

    private static void printBoard() {
        System.out.println("Board:");
        for (char[] row : board) {
            for (char cell : row) System.out.print("| " + cell + " ");
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static void initializeBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = ' ';
    }
}

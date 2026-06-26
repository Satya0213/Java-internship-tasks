import java.util.Scanner;

/**
 * Tic Tac Toe Console Game
 * InternPe Java Internship - Task 3
 * Author: Satyajeet | ID: IPI#80141
 */
public class TicTacToe {

    static char[] board = new char[9];
    static char currentPlayer = 'X';

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("╔══════════════════════════╗");
        System.out.println("║    TIC TAC TOE  |  Java  ║");
        System.out.println("║          InternPe        ║");
        System.out.println("╚══════════════════════════╝");

        boolean playAgain = true;

        while (playAgain) {
            initBoard();
            boolean gameOver = false;

            while (!gameOver) {
                printBoard();
                System.out.print("Player " + currentPlayer + ", enter position (1-9): ");

                int pos;
                try {
                    pos = Integer.parseInt(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("  ⚠  Invalid input. Enter a number 1–9.");
                    continue;
                }

                if (pos < 1 || pos > 9) {
                    System.out.println("  ⚠  Out of range. Choose between 1 and 9.");
                    continue;
                }

                if (board[pos - 1] != ' ') {
                    System.out.println("  ⚠  Cell already taken. Pick another.");
                    continue;
                }

                board[pos - 1] = currentPlayer;

                if (checkWin()) {
                    printBoard();
                    System.out.println("🎉  Player " + currentPlayer + " wins! Congrats!\n");
                    gameOver = true;
                } else if (isBoardFull()) {
                    printBoard();
                    System.out.println("🤝  It's a draw! Well played by both!\n");
                    gameOver = true;
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }

            System.out.print("Play again? (y/n): ");
            String again = sc.nextLine().trim().toLowerCase();
            playAgain = again.equals("y");
            currentPlayer = 'X';
        }

        System.out.println("\nThanks for playing! – InternPe Java Internship");
        sc.close();
    }

    static void initBoard() {
        for (int i = 0; i < 9; i++) board[i] = ' ';
    }

    static void printBoard() {
        System.out.println();
        System.out.println("  Position guide:     Current board:");
        System.out.println("   1 | 2 | 3           " + board[0] + " | " + board[1] + " | " + board[2]);
        System.out.println("  ---+---+---          ---+---+---");
        System.out.println("   4 | 5 | 6           " + board[3] + " | " + board[4] + " | " + board[5]);
        System.out.println("  ---+---+---          ---+---+---");
        System.out.println("   7 | 8 | 9           " + board[6] + " | " + board[7] + " | " + board[8]);
        System.out.println();
    }

    static boolean checkWin() {
        int[][] wins = {
            {0,1,2}, {3,4,5}, {6,7,8},  // rows
            {0,3,6}, {1,4,7}, {2,5,8},  // cols
            {0,4,8}, {2,4,6}            // diagonals
        };
        for (int[] w : wins) {
            if (board[w[0]] == currentPlayer &&
                board[w[1]] == currentPlayer &&
                board[w[2]] == currentPlayer) return true;
        }
        return false;
    }

    static boolean isBoardFull() {
        for (char c : board) if (c == ' ') return false;
        return true;
    }
}
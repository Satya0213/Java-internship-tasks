import java.util.Random;
import java.util.Scanner;

/**
 * Rock Paper Scissors Game
 * Author: Satyajeet | InternPe Java Internship - Task 2
 * A console-based game where the user plays against the computer.
 */
public class RockPaperScissors {

    // ──────────────────────────────────────────────
    //  CONSTANTS
    // ──────────────────────────────────────────────
    static final int ROCK     = 1;
    static final int PAPER    = 2;
    static final int SCISSORS = 3;

    static final String[] CHOICE_NAMES = {"", "Rock", "Paper", "Scissors"};

    // ASCII art for each choice
    static final String[] ROCK_ART = {
        "    _______",
        "---'   ____)___",
        "      (_____)  )",
        "      (_____)  )",
        "   ---'(_____) /",
        "        '-----'"
    };

    static final String[] PAPER_ART = {
        "    _______",
        "---'   ____)____",
        "          ______)",
        "          _______)",
        "         _______)",
        "---.__________)"
    };

    static final String[] SCISSORS_ART = {
        "    _______",
        "---'   ____)____",
        "          ______)",
        "       __________)",
        "      (____)",
        "---.__(___)"
    };

    // ──────────────────────────────────────────────
    //  MAIN
    // ──────────────────────────────────────────────
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random  random  = new Random();

        int playerScore   = 0;
        int computerScore = 0;
        int ties          = 0;
        int totalRounds   = 0;

        printBanner();

        boolean playing = true;

        while (playing) {
            System.out.println("\n========================================");
            System.out.println("  SCORES  |  You: " + playerScore
                    + "  |  Computer: " + computerScore
                    + "  |  Ties: " + ties);
            System.out.println("========================================");
            System.out.println("\nChoose your weapon:");
            System.out.println("  1. Rock");
            System.out.println("  2. Paper");
            System.out.println("  3. Scissors");
            System.out.println("  0. Quit");
            System.out.print("\nEnter choice (0-3): ");

            // ── Input validation ──────────────────
            int playerChoice = -1;
            if (scanner.hasNextInt()) {
                playerChoice = scanner.nextInt();
            } else {
                scanner.next(); // discard invalid token
            }

            if (playerChoice == 0) {
                playing = false;
                break;
            }

            if (playerChoice < 1 || playerChoice > 3) {
                System.out.println("⚠  Invalid input! Please enter 1, 2, 3 or 0 to quit.");
                continue;
            }

            // ── Computer picks ────────────────────
            int computerChoice = random.nextInt(3) + 1;   // 1, 2 or 3

            // ── Display choices ───────────────────
            System.out.println("\n  YOU chose:       " + CHOICE_NAMES[playerChoice]);
            printArt(playerChoice);
            System.out.println("\n  COMPUTER chose:  " + CHOICE_NAMES[computerChoice]);
            printArt(computerChoice);

            // ── Determine result ──────────────────
            int result = determineWinner(playerChoice, computerChoice);
            totalRounds++;

            System.out.println();
            if (result == 0) {
                System.out.println("  🤝  It's a TIE!");
                ties++;
            } else if (result == 1) {
                System.out.println("  🎉  YOU WIN this round!");
                playerScore++;
            } else {
                System.out.println("  💻  COMPUTER WINS this round!");
                computerScore++;
            }
        }

        // ── Final summary ─────────────────────────
        printSummary(playerScore, computerScore, ties, totalRounds);
        scanner.close();
    }

    // ──────────────────────────────────────────────
    //  GAME LOGIC
    // ──────────────────────────────────────────────

    /**
     * Returns: 1 = player wins, -1 = computer wins, 0 = tie
     */
    static int determineWinner(int player, int computer) {
        if (player == computer) return 0;

        // Rock beats Scissors, Paper beats Rock, Scissors beats Paper
        if ((player == ROCK     && computer == SCISSORS) ||
            (player == PAPER    && computer == ROCK)     ||
            (player == SCISSORS && computer == PAPER)) {
            return 1;  // player wins
        }
        return -1;     // computer wins
    }

    // ──────────────────────────────────────────────
    //  UI HELPERS
    // ──────────────────────────────────────────────

    static void printBanner() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║     ROCK  •  PAPER  •  SCISSORS          ║");
        System.out.println("║     Java Console Game                    ║");
        System.out.println("╚══════════════════════════════════════════╝");
    }

    static void printArt(int choice) {
        String[] art;
        switch (choice) {
            case ROCK:     art = ROCK_ART;     break;
            case PAPER:    art = PAPER_ART;    break;
            case SCISSORS: art = SCISSORS_ART; break;
            default:       return;
        }
        for (String line : art) {
            System.out.println("  " + line);
        }
    }

    static void printSummary(int p, int c, int t, int total) {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║             GAME OVER — SUMMARY          ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.printf( "║  Total Rounds Played : %-18d║%n", total);
        System.out.printf( "║  Your Wins           : %-18d║%n", p);
        System.out.printf( "║  Computer Wins       : %-18d║%n", c);
        System.out.printf( "║  Ties                : %-18d║%n", t);
        System.out.println("╠══════════════════════════════════════════╣");

        String verdict;
        if (total == 0)  verdict = "No rounds played.";
        else if (p > c)  verdict = "🏆  Congratulations — YOU WIN!";
        else if (c > p)  verdict = "💻  Computer wins overall. Try again!";
        else             verdict = "🤝  Overall TIE — well matched!";

        System.out.printf( "║  %-41s║%n", verdict);
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println("\nThanks for playing! — Built with ❤ by Satyajeet");
    }
}
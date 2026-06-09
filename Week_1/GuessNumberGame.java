import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessNumberGame {

    private static final Scanner sc = new Scanner(System.in);
    private static final Random random = new Random();

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("      GUESS THE NUMBER GAME");
        System.out.println("=================================");

        boolean playAgain;

        do {
            startGame();

            System.out.print("\nDo you want to play again? (Y/N): ");
            playAgain = sc.next().equalsIgnoreCase("Y");

        } while (playAgain);

        System.out.println("\nThanks for playing!");
        sc.close();
    }

    private static void startGame() {

        int maxNumber;
        int maxAttempts;

        System.out.println("\nSelect Difficulty:");
        System.out.println("1. Easy   (1-50, 10 Attempts)");
        System.out.println("2. Medium (1-100, 7 Attempts)");
        System.out.println("3. Hard   (1-200, 5 Attempts)");

        int choice = getIntegerInput("Enter choice: ");

        switch (choice) {
            case 1:
                maxNumber = 50;
                maxAttempts = 10;
                break;
            case 2:
                maxNumber = 100;
                maxAttempts = 7;
                break;
            case 3:
                maxNumber = 200;
                maxAttempts = 5;
                break;
            default:
                System.out.println("Invalid choice. Medium mode selected.");
                maxNumber = 100;
                maxAttempts = 7;
        }

        int secretNumber = random.nextInt(maxNumber) + 1;
        int attemptsUsed = 0;
        boolean guessed = false;

        System.out.println("\nI have selected a number between 1 and " + maxNumber);

        while (attemptsUsed < maxAttempts) {

            System.out.println("\nAttempts Left: " + (maxAttempts - attemptsUsed));

            int guess = getIntegerInput("Enter your guess: ");
            attemptsUsed++;

            if (guess == secretNumber) {
                guessed = true;

                int score = (maxAttempts - attemptsUsed + 1) * 10;

                System.out.println("\n🎉 Congratulations!");
                System.out.println("You guessed the number in " + attemptsUsed + " attempts.");
                System.out.println("Your Score: " + score);

                break;
            }

            int difference = Math.abs(secretNumber - guess);

            if (guess < secretNumber) {
                System.out.println("Too Low!");
            } else {
                System.out.println("Too High!");
            }

            if (difference <= 5) {
                System.out.println("🔥 Very Close!");
            } else if (difference <= 15) {
                System.out.println("🌟 Close!");
            } else {
                System.out.println("❄ Far Away!");
            }
        }

        if (!guessed) {
            System.out.println("\nGame Over!");
            System.out.println("The correct number was: " + secretNumber);
        }
    }

    private static int getIntegerInput(String message) {

        while (true) {
            try {
                System.out.print(message);
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                sc.nextLine();
            }
        }
    }
}
import java.util.Random;
import java.util.Scanner;

/**
 * Java Number Guessing Game
 *
 * A beginner-friendly console application where the player attempts to guess
 * a randomly generated number between 1 and 100.
 */
public class Main {

    // Define the range bounds for the guessing game
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        printWelcomeBanner();

        boolean playAgain = true;
        int totalGamesPlayed = 0;
        int bestScore = Integer.MAX_VALUE; // Track lowest number of attempts

        // Main game loop: controls repeated playthroughs
        while (playAgain) {
            totalGamesPlayed++;
            System.out.println("\n--- Round " + totalGamesPlayed + " ---");

            int attemptsThisRound = playRound(scanner, random);

            // Update best score if the player completed the round faster
            if (attemptsThisRound < bestScore) {
                bestScore = attemptsThisRound;
                System.out.println("★ New best score: " + bestScore + " attempt(s)!");
            }

            // Ask if the player wants another round
            playAgain = askToPlayAgain(scanner);
        }

        printGameSummary(totalGamesPlayed, bestScore);

        // Close scanner resource to avoid memory/resource leaks
        scanner.close();
    }

    /**
     * Executes a single round of the number guessing game.
     *
     * @param scanner Scanner instance for capturing user input
     * @param random  Random instance for generating the target number
     * @return The number of attempts taken to guess the secret number
     */
    private static int playRound(Scanner scanner, Random random) {
        // Generate random number in the range [MIN_NUMBER, MAX_NUMBER]
        int targetNumber = random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
        int attempts = 0;
        boolean hasGuessedCorrectly = false;

        System.out.println("I have chosen a number between " + MIN_NUMBER + " and " + MAX_NUMBER + ".");
        System.out.println("Can you guess what it is?");

        // Loop until the correct number is guessed
        while (!hasGuessedCorrectly) {
            int guess = getValidGuess(scanner);
            attempts++;

            if (guess < targetNumber) {
                System.out.println("Too Low! Try a higher number.");
            } else if (guess > targetNumber) {
                System.out.println("Too High! Try a lower number.");
            } else {
                hasGuessedCorrectly = true;
                System.out.println("\n🎉 Congratulations! You guessed the correct number: " + targetNumber);
                System.out.println("It took you " + attempts + " attempt(s).");
            }
        }

        return attempts;
    }

    /**
     * Safely prompts and validates player input.
     * Handles non-integer input and numbers outside the valid range.
     *
     * @param scanner Scanner instance
     * @return A valid integer guess within [MIN_NUMBER, MAX_NUMBER]
     */
    private static int getValidGuess(Scanner scanner) {
        while (true) {
            System.out.print("\nEnter your guess (" + MIN_NUMBER + " - " + MAX_NUMBER + "): ");

            // Check for end of input stream (e.g. piped input or EOF)
            if (!scanner.hasNext()) {
                System.out.println("\nInput stream closed. Exiting game.");
                System.exit(0);
            }

            // Validate that input is an integer to avoid InputMismatchException crashes
            if (!scanner.hasNextInt()) {
                String invalidInput = scanner.next();
                System.out.println("Invalid input: '" + invalidInput + "'. Please enter a valid whole number.");
                continue;
            }

            int guess = scanner.nextInt();

            // Validate that the integer falls within the allowable range
            if (guess < MIN_NUMBER || guess > MAX_NUMBER) {
                System.out.println("Out of bounds! Please enter a number between " + MIN_NUMBER + " and " + MAX_NUMBER + ".");
                continue;
            }

            return guess;
        }
    }

    /**
     * Prompts the player to decide whether to play another round.
     * Accepts 'yes', 'y', 'no', 'n' (case-insensitive).
     *
     * @param scanner Scanner instance
     * @return true if user wants to play again, false otherwise
     */
    private static boolean askToPlayAgain(Scanner scanner) {
        while (true) {
            System.out.print("\nWould you like to play again? (y/n): ");

            if (!scanner.hasNext()) {
                return false;
            }

            String response = scanner.next().trim().toLowerCase();

            if (response.equals("y") || response.equals("yes")) {
                return true;
            } else if (response.equals("n") || response.equals("no")) {
                return false;
            } else {
                System.out.println("Invalid choice. Please enter 'y' for yes or 'n' for no.");
            }
        }
    }

    /**
     * Prints an introduction banner to the console.
     */
    private static void printWelcomeBanner() {
        System.out.println("==================================================");
        System.out.println("          JAVA NUMBER GUESSING GAME               ");
        System.out.println("==================================================");
        System.out.println("Welcome! Test your intuition and logic by finding");
        System.out.println("the mystery number in as few attempts as possible.");
        System.out.println("==================================================");
    }

    /**
     * Displays overall game session statistics upon exit.
     *
     * @param totalGames Total rounds completed
     * @param bestScore  Fewest attempts recorded across all rounds
     */
    private static void printGameSummary(int totalGames, int bestScore) {
        System.out.println("\n==================================================");
        System.out.println("                  GAME OVER                       ");
        System.out.println("==================================================");
        System.out.println("Total round(s) played: " + totalGames);
        if (bestScore != Integer.MAX_VALUE) {
            System.out.println("Best round score     : " + bestScore + " attempt(s)");
        }
        System.out.println("Thank you for playing! Have a great day.");
        System.out.println("==================================================");
    }
}

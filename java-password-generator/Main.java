import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Java Password Generator
 * A beginner-friendly, console-based application to generate secure, customizable passwords.
 */
public class Main {

    // Character pools for password generation
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+[]{}|;:,.<>?/";

    // SecureRandom provides cryptographically strong random numbers
    private static final SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        displayWelcomeBanner();

        boolean continueGenerating = true;

        while (continueGenerating) {
            System.out.println("\n--------------------------------------------------");
            System.out.println("            CONFIGURE YOUR PASSWORD               ");
            System.out.println("--------------------------------------------------");

            int length = promptPasswordLength(scanner);

            // Character type selections
            boolean useUpper = false;
            boolean useLower = false;
            boolean useNumbers = false;
            boolean useSpecial = false;

            boolean validSelection = false;
            while (!validSelection) {
                System.out.println("\nSelect character types to include (at least one):");
                useUpper = promptYesNo(scanner, "Include uppercase letters (A-Z)? (y/n): ");
                useLower = promptYesNo(scanner, "Include lowercase letters (a-z)? (y/n): ");
                useNumbers = promptYesNo(scanner, "Include numbers (0-9)? (y/n): ");
                useSpecial = promptYesNo(scanner, "Include special characters (!@#$%...)? (y/n): ");

                if (useUpper || useLower || useNumbers || useSpecial) {
                    validSelection = true;
                } else {
                    System.out.println("\n[!] Error: You must select at least one character type. Please try again.");
                }
            }

            // Generate password
            String generatedPassword = generatePassword(length, useUpper, useLower, useNumbers, useSpecial);
            String strengthRating = evaluateStrength(length, useUpper, useLower, useNumbers, useSpecial);

            // Display results
            displayPasswordResult(generatedPassword, strengthRating);

            // Ask to continue
            continueGenerating = promptYesNo(scanner, "\nWould you like to generate another password? (y/n): ");
        }

        System.out.println("\n==================================================");
        System.out.println("  Thank you for using Java Password Generator!    ");
        System.out.println("==================================================");

        scanner.close();
    }

    /**
     * Prints the welcome banner for the application.
     */
    private static void displayWelcomeBanner() {
        System.out.println("==================================================");
        System.out.println("        SECURE JAVA PASSWORD GENERATOR            ");
        System.out.println("==================================================");
        System.out.println("Welcome! Create strong and secure passwords easily.");
    }

    /**
     * Prompts the user to enter a valid password length with boundary checks.
     *
     * @param scanner Scanner for user input.
     * @return Validated password length.
     */
    private static int promptPasswordLength(Scanner scanner) {
        int minLength = 4;
        int maxLength = 128;

        while (true) {
            System.out.print("Enter password length (" + minLength + " - " + maxLength + "): ");
            String input = scanner.nextLine().trim();

            try {
                int length = Integer.parseInt(input);
                if (length >= minLength && length <= maxLength) {
                    return length;
                } else {
                    System.out.println("[!] Length must be between " + minLength + " and " + maxLength + ". Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("[!] Invalid input. Please enter a valid whole number.");
            }
        }
    }

    /**
     * Prompts the user with a yes/no question and validates the response.
     *
     * @param scanner Scanner for user input.
     * @param prompt  Question prompt to display.
     * @return True if user chooses yes ('y'/'yes'), false for no ('n'/'no').
     */
    private static boolean promptYesNo(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            } else {
                System.out.println("[!] Invalid choice. Please enter 'y' for yes or 'n' for no.");
            }
        }
    }

    /**
     * Generates a random password based on the user's selected parameters.
     * Ensures at least one character from each selected category is included,
     * then fills remaining characters from the combined pool, and shuffles.
     *
     * @param length     Desired password length.
     * @param useUpper   Include uppercase letters.
     * @param useLower   Include lowercase letters.
     * @param useNumbers Include numeric digits.
     * @param useSpecial Include special characters.
     * @return Generated password string.
     */
    private static String generatePassword(int length, boolean useUpper, boolean useLower, boolean useNumbers, boolean useSpecial) {
        StringBuilder characterPool = new StringBuilder();
        List<Character> passwordChars = new ArrayList<>();

        // Ensure at least one character from each chosen type is present
        if (useUpper) {
            characterPool.append(UPPERCASE);
            passwordChars.add(getRandomChar(UPPERCASE));
        }
        if (useLower) {
            characterPool.append(LOWERCASE);
            passwordChars.add(getRandomChar(LOWERCASE));
        }
        if (useNumbers) {
            characterPool.append(NUMBERS);
            passwordChars.add(getRandomChar(NUMBERS));
        }
        if (useSpecial) {
            characterPool.append(SPECIAL_CHARACTERS);
            passwordChars.add(getRandomChar(SPECIAL_CHARACTERS));
        }

        String pool = characterPool.toString();

        // Fill remaining length with random characters from the combined pool
        while (passwordChars.size() < length) {
            passwordChars.add(getRandomChar(pool));
        }

        // Shuffle the characters to prevent predictable category placement
        Collections.shuffle(passwordChars, random);

        // Build the final password string
        StringBuilder finalPassword = new StringBuilder();
        for (char ch : passwordChars) {
            finalPassword.append(ch);
        }

        return finalPassword.toString();
    }

    /**
     * Retrieves a random character from a given character set.
     *
     * @param source String pool of characters.
     * @return A randomly picked character.
     */
    private static char getRandomChar(String source) {
        int index = random.nextInt(source.length());
        return source.charAt(index);
    }

    /**
     * Evaluates simple password strength based on length and character variance.
     *
     * @param length     Password length.
     * @param useUpper   Uppercase included.
     * @param useLower   Lowercase included.
     * @param useNumbers Numbers included.
     * @param useSpecial Special characters included.
     * @return Strength description (e.g., Weak, Moderate, Strong, Very Strong).
     */
    private static String evaluateStrength(int length, boolean useUpper, boolean useLower, boolean useNumbers, boolean useSpecial) {
        int categoriesCount = 0;
        if (useUpper) categoriesCount++;
        if (useLower) categoriesCount++;
        if (useNumbers) categoriesCount++;
        if (useSpecial) categoriesCount++;

        if (length >= 16 && categoriesCount >= 3) {
            return "Very Strong [****]";
        } else if (length >= 12 && categoriesCount >= 3) {
            return "Strong [*** ]";
        } else if (length >= 8 && categoriesCount >= 2) {
            return "Moderate [**  ]";
        } else {
            return "Weak [*   ]";
        }
    }

    /**
     * Prints the final generated password with formatting.
     *
     * @param password       The generated password.
     * @param strengthRating Evaluated strength string.
     */
    private static void displayPasswordResult(String password, String strengthRating) {
        System.out.println("\n==================================================");
        System.out.println("              GENERATED PASSWORD                  ");
        System.out.println("==================================================");
        System.out.println(" Password : " + password);
        System.out.println(" Length   : " + password.length());
        System.out.println(" Strength : " + strengthRating);
        System.out.println("==================================================");
    }
}

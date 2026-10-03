import java.util.Scanner;

/**
 * Student Grade Calculator
 *
 * A beginner-friendly console application that collects student details,
 * validates subject marks, computes total score and percentage, assigns
 * academic letter grades, and generates a formatted report card.
 */
public class Main {

    // Grading and score boundaries
    private static final double MIN_MARK = 0.0;
    private static final double MAX_MARK = 100.0;
    private static final double PASS_PERCENTAGE_THRESHOLD = 40.0;

    // Predefined curriculum subjects
    private static final String[] SUBJECTS = {
        "Mathematics",
        "Science",
        "English",
        "Social Studies",
        "Computer Science"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printWelcomeHeader();

        boolean continueCalculating = true;

        while (continueCalculating) {
            // 1. Capture student details
            String studentName = getStudentName(scanner);

            // 2. Capture marks for each subject with validation
            double[] marks = new double[SUBJECTS.length];
            System.out.println("\nEnter marks obtained out of 100 for each subject:");
            for (int i = 0; i < SUBJECTS.length; i++) {
                marks[i] = getValidMark(scanner, SUBJECTS[i]);
            }

            // 3. Perform calculations
            double totalMarks = calculateTotalMarks(marks);
            double maxPossibleMarks = SUBJECTS.length * MAX_MARK;
            double percentage = calculatePercentage(totalMarks, maxPossibleMarks);
            String grade = determineGrade(percentage);
            boolean isPassed = determinePassStatus(percentage);

            // 4. Display formatted report card
            printReportCard(studentName, marks, totalMarks, maxPossibleMarks, percentage, grade, isPassed);

            // 5. Ask to process another student
            continueCalculating = askToContinue(scanner);
        }

        System.out.println("\nThank you for using the Student Grade Calculator. Goodbye!");
        scanner.close();
    }

    /**
     * Prompts the user for the student's name, ensuring non-empty input.
     *
     * @param scanner Scanner instance for console input
     * @return Validated student name
     */
    private static String getStudentName(Scanner scanner) {
        while (true) {
            System.out.print("\nEnter student name: ");
            if (!scanner.hasNextLine()) {
                System.out.println("\nInput stream ended. Exiting program.");
                System.exit(0);
            }

            String name = scanner.nextLine().trim();
            if (!name.isEmpty()) {
                return name;
            }
            System.out.println("Student name cannot be empty. Please try again.");
        }
    }

    /**
     * Prompts for and validates the mark entered for a specific subject.
     * Rejects non-numeric values and numbers outside the [0, 100] range.
     *
     * @param scanner     Scanner instance
     * @param subjectName Name of the subject being evaluated
     * @return Validated mark as a double
     */
    private static double getValidMark(Scanner scanner, String subjectName) {
        while (true) {
            System.out.printf("  %-18s: ", subjectName);

            if (!scanner.hasNextLine()) {
                System.out.println("\nInput stream ended. Exiting program.");
                System.exit(0);
            }

            String line = scanner.nextLine().trim();

            try {
                double mark = Double.parseDouble(line);

                if (mark < MIN_MARK || mark > MAX_MARK) {
                    System.out.println("    [!] Invalid range: Marks must be between " 
                            + (int) MIN_MARK + " and " + (int) MAX_MARK + ".");
                    continue;
                }

                return mark;
            } catch (NumberFormatException e) {
                System.out.println("    [!] Invalid input: Please enter a valid numeric value.");
            }
        }
    }

    /**
     * Sums all subject marks.
     *
     * @param marks Array containing marks for each subject
     * @return Sum of marks
     */
    private static double calculateTotalMarks(double[] marks) {
        double sum = 0.0;
        for (double mark : marks) {
            sum += mark;
        }
        return sum;
    }

    /**
     * Computes the overall percentage score.
     *
     * @param totalMarks       Total marks scored by the student
     * @param maxPossibleMarks Maximum achievable marks across all subjects
     * @return Calculated percentage
     */
    private static double calculatePercentage(double totalMarks, double maxPossibleMarks) {
        return (totalMarks / maxPossibleMarks) * 100.0;
    }

    /**
     * Maps the percentage score to an academic letter grade.
     *
     * Scale:
     * - 90% and above : A+ (Outstanding)
     * - 80% to 89.9%  : A  (Excellent)
     * - 70% to 79.9%  : B  (Very Good)
     * - 60% to 69.9%  : C  (Good)
     * - 50% to 59.9%  : D  (Satisfactory)
     * - 40% to 49.9%  : E  (Pass)
     * - Below 40%     : F  (Fail)
     *
     * @param percentage Overall student percentage
     * @return Letter grade
     */
    private static String determineGrade(double percentage) {
        if (percentage >= 90.0) {
            return "A+";
        } else if (percentage >= 80.0) {
            return "A";
        } else if (percentage >= 70.0) {
            return "B";
        } else if (percentage >= 60.0) {
            return "C";
        } else if (percentage >= 50.0) {
            return "D";
        } else if (percentage >= 40.0) {
            return "E";
        } else {
            return "F";
        }
    }

    /**
     * Determines whether the student passed or failed.
     *
     * @param percentage Overall student percentage
     * @return true if percentage meets the passing threshold, false otherwise
     */
    private static boolean determinePassStatus(double percentage) {
        return percentage >= PASS_PERCENTAGE_THRESHOLD;
    }

    /**
     * Prints a formatted report card displaying subject breakdown and final grade.
     */
    private static void printReportCard(String studentName, double[] marks, double totalMarks,
                                       double maxPossibleMarks, double percentage, String grade,
                                       boolean isPassed) {
        System.out.println("\n========================================================");
        System.out.println("                 STUDENT REPORT CARD                    ");
        System.out.println("========================================================");
        System.out.printf("Student Name   : %s%n", studentName);
        System.out.println("--------------------------------------------------------");
        System.out.printf("%-4s %-22s %-12s %-10s%n", "No.", "Subject", "Max Marks", "Obtained");
        System.out.println("--------------------------------------------------------");

        for (int i = 0; i < SUBJECTS.length; i++) {
            System.out.printf("%-4d %-22s %-12.0f %-10.2f%n", (i + 1), SUBJECTS[i], MAX_MARK, marks[i]);
        }

        System.out.println("--------------------------------------------------------");
        System.out.printf("Total Marks    : %.2f / %.0f%n", totalMarks, maxPossibleMarks);
        System.out.printf("Percentage     : %.2f%%%n", percentage);
        System.out.printf("Assigned Grade : %s%n", grade);
        System.out.printf("Final Result   : %s%n", isPassed ? "PASSED" : "FAILED");
        System.out.println("========================================================");
    }

    /**
     * Asks the user if they wish to evaluate another student.
     *
     * @param scanner Scanner instance
     * @return true to calculate again, false to exit
     */
    private static boolean askToContinue(Scanner scanner) {
        while (true) {
            System.out.print("\nDo you want to calculate grades for another student? (y/n): ");
            if (!scanner.hasNextLine()) {
                return false;
            }

            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            } else {
                System.out.println("Invalid response. Please enter 'y' for yes or 'n' for no.");
            }
        }
    }

    /**
     * Prints the initial welcome banner for the program.
     */
    private static void printWelcomeHeader() {
        System.out.println("========================================================");
        System.out.println("               STUDENT GRADE CALCULATOR                 ");
        System.out.println("========================================================");
        System.out.println("Welcome! Enter student information and subject marks to ");
        System.out.println("generate an official academic grade report card.");
        System.out.println("========================================================");
    }
}

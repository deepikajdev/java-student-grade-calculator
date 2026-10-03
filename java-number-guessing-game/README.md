# Java Number Guessing Game

An interactive, beginner-friendly console application written in pure Core Java. The computer randomly selects a secret number between 1 and 100, and the player attempts to guess it with helpful directional feedback ("Too High" or "Too Low") while tracking their attempts.

---

## 📌 Project Description

The **Java Number Guessing Game** is designed to demonstrate foundational Java programming concepts, clean code principles, and defensive programming. Players test their deductive reasoning against a pseudo-random number generator, receiving instant feedback on each attempt until the correct number is found. The project includes replay capability, session statistics, and robust input validation to prevent crashes from invalid user inputs.

---

## ✨ Features

- **Pseudo-Random Number Generation**: Generates an unpredictable integer between 1 and 100 on each round using `java.util.Random`.
- **Directional Hints**: Provides immediate visual hints (`Too High!` or `Too Low!`) to guide the player toward the target number.
- **Attempt Tracking**: Accurately counts how many guesses were needed to find the number during the current round.
- **Session High-Score**: Keeps track of the player's personal best (fewest attempts) across multiple rounds.
- **Graceful Error Handling**: 
  - Catches non-numeric inputs (e.g., words, symbols) without throwing uncaught exceptions.
  - Warns players when their input falls outside the permissible range (1–100).
- **Replay Loop**: Prompts the user to start a new round or exit cleanly with a session summary.
- **Clean Architecture**: Separates game initialization, round logic, input validation, and replay prompts into distinct, well-documented methods.

---

## 🛠️ Technologies Used

- **Language**: Java (Core Java / Standard Edition)
- **Built-in Libraries**:
  - `java.util.Scanner` — for reading and parsing command-line input.
  - `java.util.Random` — for generating pseudo-random secret numbers.

---

## 📋 Prerequisites

Before running the project, make sure you have the Java Development Kit (JDK) installed on your system.

- **Java JDK**: Version 8 or higher (JDK 11, 17, or 21 LTS recommended).
- To verify your installation, open a terminal or command prompt and run:
  ```bash
  java -version
  javac -version
  ```

---

## 🚀 How to Compile and Run

1. **Clone or Navigate to the Project Folder**:
   ```bash
   cd java-number-guessing-game
   ```

2. **Compile the Source File**:
   ```bash
   javac Main.java
   ```
   *This compiles `Main.java` and produces `Main.class` in the same directory.*

3. **Run the Application**:
   ```bash
   java Main
   ```

---

## 💻 Example Output

```text
==================================================
          JAVA NUMBER GUESSING GAME               
==================================================
Welcome! Test your intuition and logic by finding
the mystery number in as few attempts as possible.
==================================================

--- Round 1 ---
I have chosen a number between 1 and 100.
Can you guess what it is?

Enter your guess (1 - 100): 50
Too Low! Try a higher number.

Enter your guess (1 - 100): 75
Too High! Try a lower number.

Enter your guess (1 - 100): 62
Too Low! Try a higher number.

Enter your guess (1 - 100): abc
Invalid input: 'abc'. Please enter a valid whole number.

Enter your guess (1 - 100): 120
Out of bounds! Please enter a number between 1 and 100.

Enter your guess (1 - 100): 68
Too High! Try a lower number.

Enter your guess (1 - 100): 65

🎉 Congratulations! You guessed the correct number: 65
It took you 5 attempt(s).
★ New best score: 5 attempt(s)!

Would you like to play again? (y/n): n

==================================================
                  GAME OVER                       
==================================================
Total round(s) played: 1
Best round score     : 5 attempt(s)
Thank you for playing! Have a great day.
==================================================
```

---

## 💡 What I Learned

Building this project reinforced fundamental concepts in software development and Core Java:

1. **Working with User Input (`Scanner`)**:
   - Understanding standard input streams (`System.in`).
   - Using `scanner.hasNextInt()` vs. `scanner.nextInt()` to validate data types before reading them.
   - Properly closing resources to avoid memory leaks.

2. **Control Flow and State Management**:
   - Implementing nested loops: an outer `while` loop for round replay and an inner `while` loop for guess attempts.
   - Managing flags and state variables (`hasGuessedCorrectly`, `playAgain`, `attempts`).

3. **Defensive Programming & Input Validation**:
   - Anticipating edge cases such as empty input, special characters, alphabetic characters, and out-of-range integers.
   - Ensuring the program never crashes due to unexpected user interaction.

4. **Modular Code Design**:
   - Decomposing logic into focused helper methods (`playRound`, `getValidGuess`, `askToPlayAgain`) with meaningful names.
   - Using constants (`private static final`) instead of hardcoded magic numbers for configuration bounds.

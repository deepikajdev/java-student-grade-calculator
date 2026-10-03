# Student Grade Calculator

A complete, beginner-friendly console application written in pure Core Java. The program collects a student's name and marks across 5 academic subjects, validates scores within the standard 0–100 range, calculates total marks and percentage, assigns corresponding letter grades, and outputs an aligned, professional academic report card.

---

## 📌 Project Description

The **Student Grade Calculator** is a practical utility designed to demonstrate core Java fundamentals: working with arrays, looping constructs, conditional logic, user input processing, and defensive programming. It ensures error-free operation through real-time input sanitization, preventing crashes caused by non-numeric inputs or out-of-range marks, while offering a clean, repeatable evaluation workflow for multiple students.

---

## ✨ Features

- **Multi-Subject Mark Entry**: Pre-configured with 5 curriculum subjects (`Mathematics`, `Science`, `English`, `Social Studies`, and `Computer Science`).
- **Input Validation**:
  - Rejects non-numeric characters (strings, special symbols) gracefully without throwing uncaught exceptions.
  - Enforces score bounds between `0.0` and `100.0`.
  - Disallows blank or whitespace-only student names.
- **Accurate Metric Calculations**:
  - Sums total marks obtained across all 5 subjects (out of 500 max).
  - Computes the percentage accurately using double-precision floating-point arithmetic.
- **Standardized Grading System**:
  - Evaluates percentage scores against a tiered academic grading scale.
- **Pass / Fail Status**:
  - Flags final academic status based on a customizable minimum benchmark (40.0%).
- **Formatted Report Card**:
  - Displays a tabular, easy-to-read student grade sheet using structured column formatting.
- **Session Continuation**:
  - Allows evaluating multiple students in a single execution session without restarting the program.

---

## 📊 Grading Scale

| Percentage Range | Grade | Performance Level | Status |
| :--- | :---: | :--- | :---: |
| **90.0% – 100.0%** | **A+** | Outstanding | PASS |
| **80.0% – 89.9%**  | **A**  | Excellent | PASS |
| **70.0% – 79.9%**  | **B**  | Very Good | PASS |
| **60.0% – 69.9%**  | **C**  | Good | PASS |
| **50.0% – 59.9%**  | **D**  | Satisfactory | PASS |
| **40.0% – 49.9%**  | **E**  | Pass | PASS |
| **Below 40.0%**    | **F**  | Fail | FAIL |

---

## 🛠️ Technologies Used

- **Language**: Java (Core Java / Java SE)
- **Built-in Packages**:
  - `java.util.Scanner` — for console input reading and line parsing.

*No external libraries, build tools, or frameworks are required.*

---

## 📋 Prerequisites

- **Java Development Kit (JDK)**: Version 8 or higher installed on your computer.
- Verify your Java environment:
  ```bash
  java -version
  javac -version
  ```

---

## 🚀 Execution Instructions

1. **Navigate to the Project Folder**:
   ```bash
   cd student-grade-calculator
   ```

2. **Compile the Java Source File**:
   ```bash
   javac Main.java
   ```
   *This generates `Main.class` in the same directory.*

3. **Run the Program**:
   ```bash
   java Main
   ```

---

## 💻 Sample Output

```text
========================================================
               STUDENT GRADE CALCULATOR                 
========================================================
Welcome! Enter student information and subject marks to 
generate an official academic grade report card.
========================================================

Enter student name: Alex Turner

Enter marks obtained out of 100 for each subject:
  Mathematics       : 92
  Science           : 88.5
  English           : 79
  Social Studies    : abc
    [!] Invalid input: Please enter a valid numeric value.
  Social Studies    : 105
    [!] Invalid range: Marks must be between 0 and 100.
  Social Studies    : 84
  Computer Science  : 96

========================================================
                 STUDENT REPORT CARD                    
========================================================
Student Name   : Alex Turner
--------------------------------------------------------
No.  Subject                Max Marks    Obtained  
--------------------------------------------------------
1    Mathematics            100          92.00     
2    Science                100          88.50     
3    English                100          79.00     
4    Social Studies         100          84.00     
5    Computer Science       100          96.00     
--------------------------------------------------------
Total Marks    : 439.50 / 500
Percentage     : 87.90%
Assigned Grade : A
Final Result   : PASSED
========================================================

Do you want to calculate grades for another student? (y/n): n

Thank you for using the Student Grade Calculator. Goodbye!
```

---

## 💡 Learning Outcomes

Developing this project helped reinforce essential software engineering concepts in Java:

1. **Arrays and Array Traversal**:
   - Storing fixed-length collections of subject names (`String[]`) and corresponding scores (`double[]`).
   - Using `for` loops to iterate through array elements synchronously for input capture and report rendering.

2. **Console Input Parsing & Defensive Design**:
   - Managing input streams with `scanner.nextLine()`.
   - Parsing numbers safely using `Double.parseDouble()` within `try-catch` blocks to capture `NumberFormatException`s.
   - Guarding against out-of-range numerical values before accepting data into application state.

3. **Structured String Formatting (`System.out.printf`)**:
   - Aligning tabular data in console outputs with left-aligned format specifiers (`%-18s`, `%-12.0f`, `%.2f`).

4. **Modular Programming**:
   - Designing small, reusable, single-responsibility methods (`getValidMark`, `calculateTotalMarks`, `calculatePercentage`, `determineGrade`, `printReportCard`).
   - Encapsulating thresholds and rules into clear static constants (`MIN_MARK`, `MAX_MARK`, `PASS_PERCENTAGE_THRESHOLD`).

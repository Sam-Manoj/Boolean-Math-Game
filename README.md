# Boolean Logic Quest

![Game Banner](https://github.com/Sam-Manoj/Boolean-Math-Game/raw/main/JAVA.png)


## Introduction

Boolean Logic Quest is an interactive game designed to introduce and test users on the basics of Boolean logic in a fun, timed setting. The game is structured across multiple levels of increasing complexity, where players must answer Boolean questions correctly within a given time limit.

Each level presents questions involving Boolean operations like **AND, OR, and NOT**, along with randomly generated values for variables such as **x and y**. As players advance, the number of variables and the complexity of expressions grow, challenging their understanding and speed.

---

## Problem Definition

### Input Specifications

1. **Player Actions:**

   - **Answer Input:** Players provide answers to each Boolean question in text boxes for verification. Answers are expected as **true/false** (or **1/0**, if specified).
   - **Start Game/Restart Game:** The game begins when the player chooses **Start**, and a restart option is available if the timer runs out or the player answers incorrectly.

2. **Game Variables:**

   - **Random Boolean Values:** Each question involves Boolean values assigned to variables like **x and y**.
   - **Question Generation:** Each level has randomly generated Boolean expressions that the player must solve, with complexity increasing in each level.

### Output Specifications

1. **Game Interface:**

   - **Boolean Questions:** Each level displays a series of Boolean questions with variables and operators (**AND, OR, NOT**).
   - **Timer Display:** A visible timer shows the remaining time for each level.
   - **Score:** After each level, the player’s score is calculated and displayed based on accuracy and time taken.
   - **Leaderboard:** Displays the top scores of all players, with names and scores.
   - **Binary Conversion:** Players must convert given numbers to binary, perform the Boolean operation, and then convert the final binary value to decimal.

2. **Feedback:**

   - **Correct/Incorrect Answers:** The game provides immediate feedback after each answer submission.
   - **Level Completion:** If all answers are correct, the player progresses to the next level. If answers are incorrect or time runs out, the player is prompted to retry the level.

---

## Special Processing Requirements

1. **Random Question Generation:**

   - Boolean values for variables (**x, y, z, etc.**) should be generated randomly.
   - Boolean expressions for each question should be dynamically created to ensure variety in each game session.

2. **Timer and Scoring:**

   - The game timer must start when the player enters the level, counting down until the player completes the level or time runs out.
   - **Scoring Formula:** Points are calculated based on correct answers and the time remaining. Faster, accurate responses yield higher scores.
   - **Bonus Points:** Awarded for early completion of a level.

3. **Database Requirements:**

   - **Score Tracking:** Store each player’s score in a database, along with the timestamp and username.
   - **Leaderboard:** Retrieve and display the highest scores, ranking players by their total score or speed.

4. **Level Progression Logic:**

   - Players can only progress if they’ve answered all questions correctly within the given time.
   - Increase the complexity of Boolean expressions with each level by adding more variables and operations.

---

## Game Levels and Complexity

### **Level 1**

- **Variables:** 2 (e.g., x and y)
- **Operators:** Simple **AND, OR, NOT**
- **Questions:** Basic expressions, like **(x && y), (x || y), (!x)**
- **Time Limit:** 300 seconds

### **Level 2**

- **Variables:** 3 (e.g., x, y, z)
- **Operators:** Mix of **AND, OR, and NOT** with multiple variables
- **Questions:** Medium-complexity expressions, like **(x && y || z), (x || !y && z)**
- **Time Limit:** 420 seconds

### **Level 3**

- **Variables:** 4 (e.g., x, y, z, w)
- **Operators:** Complex Boolean expressions combining **AND, OR, and NOT**
- **Questions:** Medium-complexity expressions, like **(x && y || z), (x || !y && z)**
- **Time Limit:** 300 seconds

---

## How to Play

![Game Banner](https://github.com/Sam-Manoj/Boolean-Math-Game/tree/main/JavaFX/FXML/images%20and%20video/setbg.png)

1. **Start the Game** – Click on the **Start** button.
2. **Answer Boolean Questions** – Solve Boolean logic expressions by entering the correct answers.
3. **Keep an Eye on the Timer** – Complete all answers before the timer runs out.
4. **Check Your Score** – After each level, view your score based on speed and accuracy.
5. **Progress Through Levels** – Answer all questions correctly to unlock the next level.
6. **Compete on the Leaderboard** – Try to achieve a high score and make it to the top rankings.

---

## Technologies Used

- **Programming Language:** Python/JavaScript
- **Game Engine:** Godot/Unity (if applicable)
- **Database:** SQLite/MySQL for score tracking
- **Frontend:** HTML, CSS, JavaScript (for UI elements)

---

## Future Improvements

- More levels with **higher complexity**.
- Additional Boolean operators like **XOR, NOR, NAND**.
- Multiplayer mode with **real-time competition**.
- Adaptive difficulty based on player performance.

---

## Contributing

If you’d like to contribute to the development of **Boolean Logic Quest**, feel free to:

- **Fork this repository**
- **Submit pull requests** with bug fixes or new features
- **Report issues** in the Issues section

---


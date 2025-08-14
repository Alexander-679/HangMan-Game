Hangman Game in Java (Swing GUI)
Project Overview
This project is a graphical implementation of the classic Hangman word-guessing game developed using Java and the Swing library for its user interface. The game randomly selects a word from a predefined list, and the player tries to guess it one letter at a time. For each incorrect guess, a part of a hangman stick figure is drawn. The player wins if they guess the word before the figure is fully drawn.

The GUI makes the game interactive and user-friendly by providing alphabet buttons to click, a clear display of guessed letters and blanks, and visual feedback through hangman drawings. It also includes a "New Game" button to restart easily.

Features
Graphical User Interface (GUI): Built with Java Swing (JFrame, JPanel, JLabel, JButton) for intuitive gameplay.

Alphabet Buttons: Clickable buttons for letters A-Z, automatically disabled after use to prevent repeated guesses.

Word Display: Shows underscores for unknown letters and reveals correct ones with spaces for easy reading (e.g., _ _ A _ _ N).

Random Word Selection: Words are randomly picked from a predefined list each game.

Hangman Drawing: Progressive drawing of the hangman figure based on the number of wrong guesses (head, body, arms, legs).

New Game Functionality: Quickly reset the game without restarting the program.

Win/Loss Notifications: Clear messages when the player wins or loses, showing the correct word.

Simple and Clear Layout: The GUI is divided between the hangman drawing, alphabet buttons, and message/word display areas.

How to Run
Prerequisites
Java Development Kit (JDK) installed (version 8 or higher)

An IDE like Visual Studio Code, IntelliJ IDEA, Eclipse, or simply command line access

Running the Game
Download or clone this repository.

Save the main program file as HangmanGUI.java.

Open your terminal or IDE terminal and navigate to the directory containing HangmanGUI.java.

Compile the code:

text
javac HangmanGUI.java
Run the game:

text
java HangmanGUI
The graphical window will appear; click letters to guess and try to reveal the word before reaching 6 wrong guesses.

Code Structure Highlights
HangmanGUI Class: Main class managing GUI setup, game logic, and event handling.

HangmanPanel Inner Class: Handles custom drawing of the hangman figure using Java AWT Graphics.

Letter Guess Logic: Detects correct and wrong guesses, updates display and hangman drawing, disables buttons after use.

Game State Management: Tracks current word, guessed letters, wrong guess count, and win/lose conditions.

Technologies Used
Java SE (Standard Edition)

Swing library for GUI

AWT Graphics for custom drawing

Event-driven programming (ActionListener for buttons)

Random class for word selection

Sample Gameplay
text
Word: _ _ A _ _ N
Wrong guesses: 2 / 6
As wrong guesses increase, parts of the hangman are drawn progressively. The player wins by guessing all letters before the hangman is completed.

Potential Extensions
Add sound effects for correct/wrong guesses and game end.

Include hints or category selection for words.

Replace stick-figure drawings with images or animations.

Expand word list with file input or online dictionary integration.

Add keyboard input support alongside button clicks.

Contact
Feel free to contribute, open issues, or suggest improvements!

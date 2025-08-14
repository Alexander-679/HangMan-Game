import javax.swing.*;
import java.awt.*;
import java.util.*;

public class HangmanGUI extends JFrame {
    private String[] words = {"JAVA", "PROGRAMMING", "HANGMAN", "COMPUTER", "SWING", "KEYBOARD"};
    private String currentWord;
    private char[] guessedWord;
    private int wrongGuesses;
    private final int MAX_WRONG = 6;

    private JLabel wordLabel;
    private JLabel messageLabel;
    private HangmanPanel hangmanPanel;
    private JPanel lettersPanel;
    private JButton newGameButton;

    public HangmanGUI() {
        setTitle("Hangman Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel for messages
        JPanel topPanel = new JPanel(new BorderLayout());
        wordLabel = new JLabel("", SwingConstants.CENTER);
        wordLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
        messageLabel = new JLabel("Welcome to Hangman!", SwingConstants.CENTER);
        messageLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        topPanel.add(wordLabel, BorderLayout.CENTER);
        topPanel.add(messageLabel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        // Left panel for hangman drawing
        hangmanPanel = new HangmanPanel();
        add(hangmanPanel, BorderLayout.CENTER);

        // Right panel for alphabet buttons
        lettersPanel = new JPanel(new GridLayout(7, 4, 5, 5));
        add(lettersPanel, BorderLayout.EAST);

        // Bottom panel for new game button
        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(e -> startNewGame());
        add(newGameButton, BorderLayout.SOUTH);

        createLetterButtons();
        startNewGame();

        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createLetterButtons() {
        lettersPanel.removeAll();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (char c : alphabet.toCharArray()) {
            JButton button = new JButton(String.valueOf(c));
            button.addActionListener(e -> guessLetter(button));
            lettersPanel.add(button);
        }
    }

    private void startNewGame() {
        Random rand = new Random();
        currentWord = words[rand.nextInt(words.length)];
        guessedWord = new char[currentWord.length()];
        Arrays.fill(guessedWord, '_');
        wrongGuesses = 0;
        updateWordLabel();
        messageLabel.setText("New game started! Guess a letter.");
        hangmanPanel.repaint();

        // Enable all buttons again
        for (Component comp : lettersPanel.getComponents()) {
            if (comp instanceof JButton) comp.setEnabled(true);
        }
    }

    private void guessLetter(JButton button) {
        String letter = button.getText();
        button.setEnabled(false); // disable button after use
        boolean correct = false;

        for (int i = 0; i < currentWord.length(); i++) {
            if (currentWord.charAt(i) == letter.charAt(0)) {
                guessedWord[i] = letter.charAt(0);
                correct = true;
            }
        }

        if (!correct) {
            wrongGuesses++;
            messageLabel.setText("Wrong guess! (" + wrongGuesses + "/" + MAX_WRONG + ")");
        } else {
            messageLabel.setText("Good guess!");
        }

        updateWordLabel();
        hangmanPanel.repaint();
        checkGameStatus();
    }

    private void updateWordLabel() {
        StringBuilder spacedWord = new StringBuilder();
        for (char c : guessedWord) {
            spacedWord.append(c).append(' '); // letter or underscore + space
        }
        wordLabel.setText(spacedWord.toString().trim()); // remove last space
    }

    private void checkGameStatus() {
        if (String.valueOf(guessedWord).equals(currentWord)) {
            messageLabel.setText("You win! The word was " + currentWord);
            disableLetterButtons();
        } else if (wrongGuesses >= MAX_WRONG) {
            messageLabel.setText("Game Over! The word was " + currentWord);
            disableLetterButtons();
        }
    }

    private void disableLetterButtons() {
        for (Component comp : lettersPanel.getComponents()) {
            if (comp instanceof JButton) comp.setEnabled(false);
        }
    }

    // Inner class for drawing hangman
    class HangmanPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);

            // Base
            g.drawLine(50, 250, 150, 250);
            // Pole
            g.drawLine(100, 250, 100, 50);
            // Top beam
            g.drawLine(100, 50, 200, 50);
            // Rope
            g.drawLine(200, 50, 200, 70);

            // Draw hangman parts based on wrong guesses
            if (wrongGuesses > 0) g.drawOval(175, 70, 50, 50); // Head
            if (wrongGuesses > 1) g.drawLine(200, 120, 200, 180); // Body
            if (wrongGuesses > 2) g.drawLine(200, 130, 170, 160); // Left arm
            if (wrongGuesses > 3) g.drawLine(200, 130, 230, 160); // Right arm
            if (wrongGuesses > 4) g.drawLine(200, 180, 170, 210); // Left leg
            if (wrongGuesses > 5) g.drawLine(200, 180, 230, 210); // Right leg
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HangmanGUI::new);
    }
}

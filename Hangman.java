import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

class Hangman {
    private Dictionary dictionary;
    private Scanner scanner;
    private String word;
    private HashSet<Character> guessed;
    private Boolean isOn;
    private int remainingGuesses;

    Hangman(Dictionary dictionary) {
        this.dictionary = dictionary;
        scanner = new Scanner(System.in);
    }

    // Starts the game
    void start() throws InterruptedException {
        initialize();
        isOn = true;

        // Game Loop
        while (isOn) {
            String revealedWord;
            char guess;
            boolean success;

            // Prompt a guess and add it to the set
            guess = getUniqueGuess();
            guessed.add(guess);

            // Tell user if the guess was good or bad
            success = checkGuess(guess);
            Thread.sleep(1500);

            // If the guess was bad, tick closer to impending doom
            if (!success) {
                remainingGuesses--;
            }

            // Display the revealed letters so far
            revealedWord = revealWord();
            System.out.println("Word so far: " + revealedWord);

            // Check the revealed word for the win condition
            checkWinCondition(revealedWord);
        }
    }

    // Reset game variables and preview the word length
    private void initialize() {
        displayWelcomeMessage();

        word = dictionary.random();
        guessed = new HashSet<>();
        remainingGuesses = 10;
        System.out.println("Here's your word! " + revealWord());
    }

    // Displays a welcome message
    private void displayWelcomeMessage() {
        System.out.println("*********************************************");
        System.out.println("*  Welcome to My Simple Game of Hangman!!!  *");
        System.out.println("*                by Ben Wong                *");
        System.out.println("*********************************************");
    }

    // Display the word, replacing letters with dashes, unless guessed.
    private String revealWord() {
        StringBuilder sb = new StringBuilder();

        System.out.println();

        // Iterate through the word, checking whether letters are found in the guessed set
        for (int i = 0; i < word.length(); i++) {
            if (guessed.contains(word.charAt(i))) {
                sb.append(word.charAt(i));
            } else {
                sb.append('-');
            }
        }

        return sb.toString();
    }

    // Prompts a guess and ensures it has not been already guessed
    private char getUniqueGuess() {
        char letter;
        ArrayList<Character> alphabetizedGuesses;


        // Show remaining guesses and guessed letters
        System.out.println(remainingGuesses + ((remainingGuesses == 1) ? " guess remaining!" : " guesses remaining"));
        System.out.print("Letters guessed: ");

        // Show guessed letters in alphabetical order
        alphabetizedGuesses = new ArrayList<>(guessed);
        Collections.sort(alphabetizedGuesses);

        for (char guess : alphabetizedGuesses) {
            System.out.print(guess + " ");
        }
        System.out.println();


        // Continue prompting while letter has already been guessed
        do {
            letter = getALetter();
            if (guessed.contains(letter)) {
                System.out.println("You've already guessed that!");
            }
        } while (guessed.contains(letter));

        return letter;
    }

    // Gets a letter from the user
    private char getALetter() {
        String rawGuess = "";
        // Ensure the input is a letter
        while (rawGuess.length() != 1 || !Character.isLetter(rawGuess.charAt(0))) {
            System.out.print("Guess one letter at a time: ");
            rawGuess = scanner.nextLine();
        }

        return rawGuess.toLowerCase().charAt(0);
    }

    // Verify whether the guess is successful
    private boolean checkGuess(char guess) {
        if (word.contains(Character.toString(guess))) {
            System.out.println("Nice Work!");
            return true;
        } else {
            System.out.println("*sadly*: womp womp...");
            return false;
        }
    }

    // Checks for a win/loss
    private void checkWinCondition(String revealedWord) {
        // if the revealed word is the same as the word, we win
        if (word.equals(revealedWord)) {
            System.out.println("You win!");

            // if the revealed word is incomplete, check remaining guesses for loss
        } else if (remainingGuesses <= 0) {
            System.out.println("Game Over...");
            System.out.println("The word was " + word);
        } else {

            // If neither, let the game continue
            return;
        }

        // Prompts after a win or loss condition
        System.out.println("Would you like to play again?");
        char playAgain = getYesOrNo();
        if (playAgain == 'y') {
            initialize();
        } else {
            isOn = false;
        }
    }

    // Ensures the letter is a 'y' or 'n'
    private char getYesOrNo() {
        String rawGuess = "";

        while (rawGuess.length() != 1 || !isValidYN(rawGuess.charAt(0))) {
            System.out.print("Enter y/n: ");
            rawGuess = scanner.nextLine();
        }
        return rawGuess.toLowerCase().charAt(0);
    }

    // Verifies the letter is [YyNn]
    private boolean isValidYN(char letter) {
        switch (letter) {
            case ('y'):
            case ('n'):
            case ('Y'):
            case ('N'):
                return true;
            default:
                return false;
        }
    }
}

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

    private void initialize() {
        displayWelcomeMessage();

        word = dictionary.random();
        guessed = new HashSet<>();
        remainingGuesses = 7;
        System.out.println("Here's your word! " + revealWord());
    }

    private void displayWelcomeMessage() {
        System.out.println("*********************************************");
        System.out.println("*  Welcome to My Simple Game of Hangman!!!  *");
        System.out.println("*                by Ben Wong                *");
        System.out.println("*********************************************");
    }

    private String revealWord() {
        // Display the word, replacing letters with dashes, unless guessed.
        System.out.println();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (guessed.contains(word.charAt(i))) {
                sb.append(word.charAt(i));
            } else {
                sb.append('-');
            }
        }
        return sb.toString();
    }

    private char getUniqueGuess() {
        System.out.println(remainingGuesses + ((remainingGuesses == 1) ? " guess " : " guesses ") + "remaining");
        char letter;
        do {
            letter = getALetter();
            if (guessed.contains(letter)) {
                System.out.println("You've already guessed that!");
            }
        } while (guessed.contains(letter));
        return letter;
    }

    private char getALetter() {
        String rawGuess = "";
        // make sure they enter just a letter
        while (rawGuess.length() != 1 || !Character.isLetter(rawGuess.charAt(0))) {
            System.out.print("Guess one letter at a time: ");
            rawGuess = scanner.nextLine();
        }

        return rawGuess.toLowerCase().charAt(0);
    }

    private boolean checkGuess(char guess) {
        if (word.contains(Character.toString(guess))) {
            System.out.println("Nice Work!");
            return true;
        } else {
            System.out.println("*sadly*: womp womp...");
            return false;
        }
    }

    private void checkWinCondition(String revealedWord) {
        if (word.equals(revealedWord)) {
            System.out.println("You win!");
        } else if (remainingGuesses <= 0) {
            System.out.println("Game Over...");
            System.out.println("The word was " + word);
        } else {
            return;
        }

        System.out.println("Would you like to play again?");
        char playAgain = getYesOrNo();
        if (playAgain == 'y') {
            initialize();
        } else {
            isOn = false;
        }
    }

    private char getYesOrNo() {
        String rawGuess = "";

        while (rawGuess.length() != 1 || !isValidYN(rawGuess.charAt(0))) {
            System.out.print("Enter y/n: ");
            rawGuess = scanner.nextLine();
        }
        return rawGuess.toLowerCase().charAt(0);
    }

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

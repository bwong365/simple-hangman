import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class TextInterface implements UserInterface {
    private Scanner scanner;

    public TextInterface() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void displayWelcome() {
        System.out.println("*********************************************");
        System.out.println("*  Welcome to My Simple Game of Hangman!!!  *");
        System.out.println("*                by Ben Wong                *");
        System.out.println("*********************************************");
        System.out.println();
    }

    @Override
    public void revealWord(String revealedWord) {
        System.out.print("The word so far: ");
        System.out.println(revealedWord);
    }

    @Override
    public char promptGuess(int remainingGuesses, HashSet<Character> guessedLetters) {
        showRemainingGuesses(remainingGuesses);
        showAlphabetizedGuesses(guessedLetters);
        return getUniqueGuess(guessedLetters);
    }

    @Override
    public void showSuccess(boolean wasSuccessful) {
        System.out.println((wasSuccessful) ? "Nice work!" : "*sadly*: womp womp...");

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            System.out.println("Something went wrong...");
        }
    }

    @Override
    public boolean playAgain(boolean wasVictorious, String word) {
        displayGameOver(wasVictorious, word);

        char playAgain = getYesOrNo();
        if (playAgain == 'y') {
            return true;
        } else {
            return false;
        }
    }

    private void showRemainingGuesses(int remainingGuesses) {
        // Show remaining guesses
        System.out.println(remainingGuesses + ((remainingGuesses == 1) ? " guess remaining!" : " guesses remaining"));
    }

    private void showAlphabetizedGuesses(HashSet<Character> guessedLetters) {
        System.out.print("Letters guessed: ");
        var alphabetizedGuesses = new ArrayList<>(guessedLetters);

        Collections.sort(alphabetizedGuesses);
        for (char guess : alphabetizedGuesses) {
            System.out.print(guess + " ");
        }
        System.out.println();
    }

    private char getUniqueGuess(HashSet<Character> guessedLetters) {
        char letter;

        // Continue prompting while letter has already been guessedLetters
        do {
            letter = getALetter();
            if (guessedLetters.contains(letter)) {
                System.out.println("You've already guessed that!");
            }
        } while (guessedLetters.contains(letter));

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

    private void displayGameOver(boolean wasVictorious, String word) {
        System.out.println(wasVictorious ? "You Win!" : "Game Over...");
        System.out.println("The word was " + word);
        System.out.println();
        System.out.print("Would you like to play again? ");
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

import java.util.HashSet;

public class Hangman {
    private Dictionary dictionary;
    private UserInterface ui;
    
    private String word;
    private HashSet<Character> guessedLetters;
    private int remainingGuesses;
    private Boolean isOn;
    

    public Hangman(Dictionary dictionary, UserInterface ui) {
        this.dictionary = dictionary;
        this.ui = ui;
    }

    // Starts the game
    public void start() {
        initialize();
        isOn = true;

        // Game Loop
        while (isOn) {
            String revealedWord;
            char guess;
            boolean wasSuccessful;

            // Prompt a guess and add it to the set
            guess = ui.promptGuess(remainingGuesses, guessedLetters);
            guessedLetters.add(guess);

            // check and inform user if guess was good or bad
            wasSuccessful = checkGuess(guess);

            // If the guess was bad, tick closer to impending doom
            if (!wasSuccessful) {
                remainingGuesses--;
            }

            // Display the revealed letters so far
            revealedWord = generateRevealedWord();

            // Check the revealed word for the win condition
            if (!checkWinCondition(revealedWord)) {
                ui.revealWord(revealedWord);
            }
        }
    }

    // Reset game variables and preview the word length
    private void initialize() {
        word = dictionary.random();
        guessedLetters = new HashSet<>();
        remainingGuesses = 10;

        ui.initialize();
        ui.revealWord(generateRevealedWord());
    }

    // Generates the revealed word, replacing letters with dashes, unless the letters have been guessed.
    private String generateRevealedWord() {
        var sb = new StringBuilder();

        System.out.println();

        // Iterate through the word, checking whether letters are found in the guessedLetters set
        for (int i = 0; i < word.length(); i++) {
            if (guessedLetters.contains(word.charAt(i))) {
                sb.append(word.charAt(i));
            } else {
                sb.append('-');
            }
        }

        return sb.toString();
    }

    // Verify whether the guess is successful
    private boolean checkGuess(char guess) {
        boolean wasSuccessful = word.contains(Character.toString(guess));
        ui.showSuccess(wasSuccessful);
        return wasSuccessful;
    }

    // Checks for a win/loss
    private boolean checkWinCondition(String revealedWord) {
        boolean wasVictorious;
        boolean playAgain;

        // if the revealed word is the same as the word, we win
        if (word.equals(revealedWord)) {
            wasVictorious = true;

        // if the revealed word is incomplete, check remaining guesses for loss
        } else if (remainingGuesses <= 0) {
            wasVictorious = false;

        // If neither, let the game continue
        } else {
            return false;
        }

        playAgain = ui.playAgain(wasVictorious, word);
        if (playAgain) {
            initialize();
        } else {
            isOn = false;
        }

        return true;
    }
}

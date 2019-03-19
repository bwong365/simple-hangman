import java.util.HashSet;

public interface UserInterface {
    public void displayWelcome();
    public void revealWord(String revealedWord);
    public char promptGuess(int remainingGuesses, HashSet<Character> guessedLetters);
    public void showSuccess(boolean wasSuccessful);
    public boolean playAgain(boolean wasVictorious, String word);
}

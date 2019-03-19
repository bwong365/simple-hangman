import java.util.HashSet;

public interface UserInterface {
    public void displayWelcome();

    public char getUniqueGuess(HashSet<Character> guessed);

    public void revealWord(String revealedWord);

    public void displayAlphabetizedGuesses();


}

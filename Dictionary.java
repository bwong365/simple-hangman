import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

class Dictionary {
    private List<String> words;
    private Random random;

    // Load a list of words from a file
    Dictionary() throws IOException {
        words = Files.readAllLines(Paths.get("dictionary.txt"));
        random = new Random();
    }

    // Pick a random word
    String random() {
        int line = this.random.nextInt(words.size());
        return words.get(line);
    }
}

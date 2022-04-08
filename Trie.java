package boggle;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Class that compose the dictionary into a tree structure.
 */
public class Trie {
    private boolean wordEnd;
    private Map<Character, Trie> child = new HashMap<>();

    /**
     * Method to insert the word in the Tree.
     * @param word is the word in dictionary.
     */
    public void insert(String word) {
        if (word.length() == 0) {
            wordEnd = true;
        } else {
            char first = word.charAt(0);
            String rest = word.substring(1);

            if (child.containsKey(first)) {
                child.get(first).insert(rest);
            } else {
                child.put(first, new Trie());
                child.get(first).insert(rest);
            }
        }
    }

    /**
     * Method that traverses the Trie. I
     * @param path is the word to be checked if it exists in the Trie.
     * @return none if the length  of the word is zero, meaning no word.
     */
    private Trie traverse(String path) {
        if (path.length() == 0) {
            return this;
        }
        char first = path.charAt(0);
        String rest = path.substring(1);
        if (!child.containsKey(first)) {
            return null;
        }
        return child.get(first).traverse(rest);
    }

    /**
     * Method to check if the word has a path in the Trie.
     * @param word is the word.
     * @return a boolean
     */
    public boolean hasPath(String word) {
        Trie node = this.traverse(word);
        return node != null;
    }

    /**
     * Method that checks if the word exists in the Trie.
     * @param word is the word.
     * @return boolean.
     */
    public boolean contains(String word) {
        Trie node = this.traverse(word);
        if (node == null) {
            return false;
        }
        return node.wordEnd;
    }

    public static void main(String[] args) throws Exception {
        Trie root = new Trie();
        Scanner scan = new Scanner(new FileInputStream("dictionary.txt"));
        while (scan.hasNext()) {
            root.insert(scan.next());
        }
    }
}
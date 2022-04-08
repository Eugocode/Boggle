/**
 * Project Title: Boggle Solver
 * @author Dleamnor Euraze M. Cawaling
 * This program finds all the valid words in a Boggle Game.
 * A word is valid if:
 *      1. Letters in the word are connected in adjacent way.
 *      2. The words are in the dictionary text file.
 *      3. The word length is 3 or more.
 * A trie data structure is used to compose the words inside the dictionary into a Trie Tree.
 */
package boggle;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Class which finds for all the valid words in the board wherein the letters are defined by the user.
 */
public class BoggleSolver {
    //variables
    private static int size = 4;
    private static String[][] board = new String[size][size];
    private static ArrayList<String> dictionary = new ArrayList<String>();
    private Set<String> words = new TreeSet<>();
    private Trie trie;

    /**
     * Constructor that accepts the board returned by the createBoard method.
     * @param grid is a string array of board
     * Here makeTrie method is called as well as the findWords.
     */
    public BoggleSolver(String[][] grid) {
        if (grid == null)
            System.out.println("Board should not be empty.");

        makeTrie("dictionary");

        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                findWords("", r, c, new HashSet<>(), words);
            }
        }
    }
    /**
     * Method to create the board which is a string of array.
     * Each element of the array corresponds to one letter in the boggle board.
     * Each letter is defined by the user.
     * @return String[][]
     */
    public static String[][] createBoard() {
        Scanner input = new Scanner(System.in);
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                board[r][c] = input.next();
                System.out.print(board[r][c]);
            }
            System.out.println();
        }
        System.out.println();
        return board;
    }

    /**
     * Method of making a set of valid words.
     * @return the Set of valid words.
      */
    public Set<String> getValidWords() {
        return this.words;
    }

    /**
     *Method that transform the dictionary into a Trie Tree.
     * @param filename is a dictionary text file wherein we search to know if a word is valid.
     */
    private void makeTrie(String filename) {
        trie = new Trie();
        Scanner scan;
        try {
            scan = new Scanner(new FileInputStream(filename));
            while (scan.hasNext()) {
                trie.insert(scan.next().toUpperCase());
            }
            scan.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method that make the row and column into a string
     * @param row position row in the board
     * @param col position col in the board
     * @return String of position of visited letter.
     */
    private static String coord(int row, int col) {
        return row + "-" + col;
    }

    /**
     *Method to find valid words in the board.
     * @param prefix is the first letters in a word
     * @param row is the position in row
     * @param col is the position in column
     * @param visited is the letters that are visited
     * @param words is generated word in the Tree Set
     */
    private void findWords(String prefix, int row, int col, Set<String> visited, Set<String> words) {
        String word = prefix + getLetter(row, col);
        //if the word is not on the path in trie return nothing
        if (!trie.hasPath(word)) {
            return;
        }

        if (trie.contains(word) && word.length() > 2) {                 //accepts only 3 letter words and above
            words.add(word);
        }
        visited.add(coord(row, col));                                   //add the coordinates of letter visited
        // Mark all characters as not visited

        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = col - 1; c <= col + 1; c++) {
                //for out of bounds
                if (r < 0 || r > 3 || c < 0 || c > 3 || r == row && c == col) {
                    continue;
                }
                Set<String> copy = new HashSet<>(visited);
                findWords(word, r, c, copy, words);
            }
        }
    }

    /**
     * Method to get the letters in the board.
     * @param row row
     * @param col column
     * @return letter in the board
     */
    public String getLetter(int row, int col) {
        if (row < 0 || row >= 4 || col < 0 || col >= 4) {
            throw new IndexOutOfBoundsException(String.format("Row %d Col %d out of bounds", row, col));
        }
        return board[row][col];
    }

    public static void main (String args[]){
        System.out.println("This boggle game has a size of 4x4");
        System.out.println("Enter the 16 letters in boggle from left to right by level starting from the top.\n");
        System.out.println("Separate it by space.");
        String[][] grid = createBoard();
        BoggleSolver board = new BoggleSolver(grid);

        //print number of words
        System.out.println(board.getValidWords().size());
        //print valid words found
        for (String word : board.getValidWords()) {
            System.out.println(word);
        }
    }
}
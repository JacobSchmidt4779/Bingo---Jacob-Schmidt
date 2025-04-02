package Bingo;

import java.util.regex.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

/* 
 * Class to manage data related to a Bingo Card and Bingo Card Spaces
 * @author @JacobSchmidt4779
 */
public class BingoCard {
    private static final String Spacer = "\n--|--+--+--+--+--|\n"; // String to be used to separate bingo card rows when
                                                                   // printing

    private static int idIndex = 1;
    public static boolean HAS_FREE_SPACE = false; // If true, changes new card construction so the center space (at row
                                                  // 2, col 2) is a 'Free Space'

    private int id;
    private BingoCardSpace spaces[][];

    /*
     * Default constructor
     */
    public BingoCard() {
        this.id = idIndex;
        idIndex++;
        this.spaces = BingoCard.defaultSpaces();
        if (HAS_FREE_SPACE) {
            this.spaces[2][2] = new BingoCardSpace(-1);
            this.spaces[2][2].mark();
        }
    }

    /*
     * Convenience constructor that adds the numbers in the specified String to the
     * bingo card
     * 
     * @param String nums - a list of numbers between 1-75 separated by commas
     */
    public BingoCard(String nums) {
        this.id = idIndex;
        idIndex++;
        this.spaces = BingoCard.defaultSpaces();

        if (HAS_FREE_SPACE) {
            this.spaces[2][2] = new BingoCardSpace(-1);
            this.spaces[2][2].mark();
        }

        Pattern pattern = Pattern.compile("(?<=\\d)\\s*,\\s*(?=\\d)");
        String[] splitNums = pattern.split(nums.trim());

        for (String target : splitNums) {
            this.addNumber(Integer.parseInt(target));
        }
    }

    /*
     * Convenience method to generate a 5 by 5 2D array of default bingo spaces
     * 
     * @return 2D BingoCardSpace array
     */
    private static BingoCardSpace[][] defaultSpaces() {
        BingoCardSpace[][] spaces = new BingoCardSpace[5][5];

        for (int row = 0; row < spaces.length; row++) {
            for (int col = 0; col < spaces[0].length; col++) {
                spaces[row][col] = new BingoCardSpace();
            }
        }

        return spaces;
    }

    /*
     * Convenience method that constructs an Array List of bingo cards from a
     * formatted text file
     * 
     * @param String fileName - the name of the file to construct bingo cards from
     * 
     * @return ArrayList<BingoCard> containing the bingo cards constructed
     */
    public static ArrayList<BingoCard> createCardsFromFile(String fileName) {
        ArrayList<BingoCard> cards = new ArrayList<BingoCard>();
        try {
            File file = new File(fileName);
            Scanner scan = new Scanner(file);
            Pattern numPattern = Pattern.compile("[\\s\\d,]*");
            String currentCardNums = "";
            while (scan.hasNextLine()) {
                String currentLine = scan.nextLine().trim();
                if (!currentLine.isEmpty()) {
                    if (currentLine.startsWith("Card") && !currentCardNums.isEmpty()) {
                        cards.add(new BingoCard(currentCardNums.substring(0, currentCardNums.length() - 1)));
                        currentCardNums = "";
                    } else {
                        Matcher numMatcher = numPattern.matcher(currentLine);
                        if (numMatcher.matches()) {
                            currentCardNums += currentLine + ",";
                        }
                    }
                }
            }
            scan.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return cards;
    }

    /*
     * Adds the specified number to the proper Bingo card column in the next empty
     * space. If there isn't an additional empty space, the number isn't added
     * 
     * Assumes that num is between 1-75 inclusive and hasn't already been added to
     * the card
     * 
     * @param int num - the number to add
     */
    public void addNumber(int num) {
        int col = (int) ((num - 1) / 15);

        for (int i = 0; i < spaces.length; i++) {
            if (spaces[i][col].matchNumber(0)) {
                spaces[i][col] = new BingoCardSpace(num);
                return;
            }
        }
    }

    public void addNumber(int... args) {
        for (int num : args) {
            this.addNumber(num);
        }
    }

    /*
     * Marks a space on a bingo card
     * 
     * @param int row - row that contains the space to mark
     * 
     * @param int col - column that contains the space to mark
     */
    public void markSpace(int row, int col) {
        this.spaces[row][col].mark();
    }

    /*
     * Marks a space on a bingo card
     * 
     * @param String rowCol - the first char and second char corresponds to the row
     * and column of the space to be marked respectively.
     */
    public void markSpace(String rowCol) {
        rowCol = rowCol.trim().toUpperCase();

        if (Pattern.matches("^\s*[BINGO][BINGO]\s*$", rowCol)) {
            int row = charToCol(rowCol.charAt(0));
            int col = charToCol(rowCol.charAt(1));
            markSpace(row, col);
        }
    }

    public void markSpace(String... args) {
        for (String target : args) {
            markSpace(target);
        }
    }

    /*
     * Checks if the space at the specified row and col is marked
     * 
     * @param int row - the row of the space
     * 
     * @param int col - the column of the space
     * 
     * @return true if the space is marked, otherwise false
     */
    public boolean isSpaceMarkedAt(int row, int col) {
        return spaces[row][col].isMarked();
    }

    /*
     * Convenience method to convert a char representing a bingo column to its
     * respective column
     * 
     * @param char bingoCol - character that represents a column on a bingo (a char
     * contained in 'bingo'), ignoring case
     * 
     * @return int representation of the column character
     */
    public static int charToCol(char bingoCol) {
        int res = ((int) bingoCol) - 60 - ((bingoCol > 'O') ? 1 : 0) * 32;
        res = (int) (0.0282 * Math.pow(res, 3) - 1.0485 * Math.pow(res, 2) + 12.09 * res - 40);
        return res;
    }

    /*
     * Convenience method to get the char of the column that the specified num would
     * fall under on a bingo card
     * 
     * @param int num - the number between 1-75 inclusive to get the column for
     * 
     * @return char representation of the column
     */
    public static char getCharForNum(int num) {
        num = (int) (num / 15.1);
        return ("BINGO").charAt(num);
    }

    /*
     * Convenience method to get the char for the specified col
     * of the col starting from 0
     * 
     * @param int col - the integer representation of a column on a bingo card from
     * 0 to 4
     * 
     * @return char representation of the column
     */
    public static char getCharForCol(int col) {
        return ("BINGO").charAt(col);
    }

    /*
     * Gets the number of the space at the specified row and column
     * 
     * @param String rowCol - the first char and second char corresponds to the row
     * and column of the space.
     * 
     * @return int at the space
     */
    public int getNumberAt(String rowCol) {
        rowCol = rowCol.trim().toUpperCase();

        if (!Pattern.matches("^\s*[BINGO][BINGO]\s*$", rowCol)) {
            return -1;
        }

        int row = charToCol(rowCol.charAt(0));
        int col = charToCol(rowCol.charAt(1));
        return spaces[row][col].getNumber();
    }

    /*
     * Checks if the number at the specified row and column equals the number to
     * match
     * 
     * @param int row - the row of the space
     * 
     * @param int col - the column of the space
     * 
     * @param int match - the number to match
     * 
     * @return true if match equals the number at the space, otherwise false
     */
    public boolean numberMatchAt(int row, int col, int match) {
        return spaces[row][col].matchNumber(match);
    }

    public BingoCardSpace[][] getSpaces() {
        return spaces;
    }

    /*
     * Convenience method to convert an integer to a formatted String to improve
     * appearance when printed
     * 
     * @param int i - row that contains the integer
     * 
     * @param int j - column that contains the integer
     * 
     * @return String with length 2 of the specified num or FS (Free Space) if
     * specified num equals -1
     */
    private String numFormat(int row, int col) {
        if (spaces[row][col].matchNumber(-1))
            return "\u001B[31mFS\u001B[0m";
        if (spaces[row][col].isMarked())
            return "\u001B[31mXX\u001B[0m";
        return String.format("%-2d", spaces[row][col].getNumber());
    }

    public static String numToString(int num) {
        return getCharForNum(num) + "" + num;
    }

    /*
     * Convenience method to convert a row of integers to a formatted String to
     * improve appearance when printed
     * 
     * @param int i - index of row in cardNumbers to convert
     * 
     * @return String with a standardized length
     */
    public String rowToString(int i) {
        String res = getCharForCol(i) + " |";
        for (int j = 0; j < spaces[i].length; j++) {
            res += numFormat(i, j) + "|";
        }
        return res;
    }

    /*
     * Converts this bingo card to a formatted String to improve appearance when
     * printed
     * 
     * @return String to display
     */
    @Override
    public String toString() {
        String res = "Bingo Card #" + id + "\n";
        res += "  |B |I |N |G |O |" + Spacer;
        for (int i = 0; i < spaces.length - 1; i++) {
            res += rowToString(i) + Spacer;
        }
        res += rowToString(spaces.length - 1);
        return res;
    }
}

package Bingo;

import java.util.regex.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class BingoCard {
    private static int idIndex = 1;
    private int id;
    private BingoCardSpace spaces[][];

    public static boolean HAS_FREE_SPACE = false;   // If true, changes new card construction so the center square (at row 2, col 2) is a 'Free Space'

    public static final String Spacer = "\n--|--+--+--+--+--|\n";   // String to be used to separate bingo card rows when printing
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
     * Convenience constructor that adds the numbers in the specified String to the bingo card
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
     * Convenience method that constructs an Array List of bingo cards from a formatted text file
     * @param String fileName - the name of the file to construct bingo cards from
     * @return ArrayList<BingoCard> containing the bingo cards constructed
     */
    public static ArrayList<BingoCard> createCardsFromFile(String fileName) {
        ArrayList<BingoCard> cards = new ArrayList<BingoCard>();
        try {
            File file = new File(fileName);
            Scanner scan = new Scanner(file);
            Pattern numPattern = Pattern.compile("[\\s\\d,]*");
            String currentCardNums = "";
            while(scan.hasNextLine()) {
                String currentLine = scan.nextLine().trim();
                if (!currentLine.isEmpty()){
                    if (currentLine.startsWith("Card") && !currentCardNums.isEmpty()) {
                        cards.add(new BingoCard(currentCardNums.substring(0, currentCardNums.length() - 1)));
                        currentCardNums = "";
                    } 
                    else {
                        Matcher numMatcher = numPattern.matcher(currentLine);
                        if (numMatcher.matches()) {
                            currentCardNums += currentLine + ",";
                        }                        
                    }
                }
            }
            scan.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return cards;
    }

    public BingoCard addNumber(int num) {
        BingoCard card = new BingoCard();
        int col = (int) ((num - 1) / 15);
        
        for (int i = 0; i < spaces.length; i++) {
            if (spaces[i][col].matchNumber(0)) {
                   spaces[i][col] = new BingoCardSpace(num);
            }
        }

        return card;
    }

    public BingoCard addNumber(int... args) {
        BingoCard card = new BingoCard();

        for (int num : args){
            card = card.addNumber(num);
        }

        return card;
    }

    /* 
     * Method to mark a spot on a bingo card
     * @param int row - row that contains the spot to mark
     * @param int col - column that contains the spot to mark
     */
    public void markSpace(int row, int col) {
        this.spaces[row][col].mark();
    }

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

    public boolean isSpaceMarkedAt(int row, int col) {
        return spaces[row][col].isMarked();
    }

    /* 
     * Convenience method to convert a char representing a bingo column to its respective column
     * @param char bingoCol - character that represents a column on a bingo (a char contained in 'bingo'), ignoring case
     * @return int representation of the column character
     */
    public static int charToCol(char bingoCol) {
        int res = ((int) bingoCol) - 60 - ((bingoCol > 'O') ? 1 : 0) * 32;
        res = (int) (0.0282 * Math.pow(res, 3) - 1.0485 * Math.pow(res, 2) + 12.09 * res - 40);
        return res;
    }

    public static char getCharForNum(int num) {
        num = (int) (num / 15.1);
        return ("BINGO").charAt(num);
    }

    public static char getCharForCol(int col){
        return ("BINGO").charAt(col);
    }

    public int getNumberAt(String rowCol) {
        rowCol = rowCol.trim().toUpperCase();
        if (!Pattern.matches("^\s*[BINGO][BINGO]\s*$", rowCol)) {
            return -1;
        }
        int row = charToCol(rowCol.charAt(0));
        int col = charToCol(rowCol.charAt(1));
        return spaces[row][col].getNumber();
    }

    public boolean numberMatchAt (int row, int col, int match) {
        return spaces[row][col].matchNumber(match);
    }

    public BingoCardSpace[][] getSpaces() {
        return spaces;
    }

    /* 
     * Convenience method to convert an integer to a formatted String to improve appearance when printed
     * @param int i - row that contains the integer
     * @param int j - column that contains the integer
     * @return String with length 2 of the specified num or FS (Free Space) if specified num equals -1
     */
    private String numFormat(int row, int col) {
        if (spaces[row][col].matchNumber(-1)) return "\u001B[31mFS\u001B[0m";
        if (spaces[row][col].isMarked()) return "\u001B[31mXX\u001B[0m";
        return String.format("%-2d", spaces[row][col].getNumber());
    }

    public static String numToString(int num) {
        return getCharForNum(num) + "" + num;
    }

    /* 
     * Convenience method to convert a row of integers to a formatted String to improve appearance when printed
     * @param int i - index of row in cardNumbers to convert
     * @return String with a standardized length
     */
    public String rowToString(int i){
        String res = getCharForCol(i) + " |";
        for (int j = 0; j < spaces[i].length; j++) {
            res += numFormat(i, j) + "|";
        }
        return res;
    }

    /* 
     * Converts this bingo card to a formatted String to improve appearance when printed
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

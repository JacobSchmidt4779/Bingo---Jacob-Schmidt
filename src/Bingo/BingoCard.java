package Bingo;

import java.util.regex.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class BingoCard {
    private static int idIndex = 1;
    private int id;
    private int[][] cardNumbers;        // Contains the numbers on the bingo card
    private boolean[][] markedSpots;    // Designates if a spot on the card has been marked

    public static boolean HAS_FREE_SPACE = false;   // If true, changes new card construction so the center square (at row 2, col 2) is a 'Free Space'

    public static final String Spacer = "--+--+--+--+--";   // String to be used to separate bingo card rows when printing
    /*
     * Default constructor
     */
    public BingoCard() {
        this.id = idIndex;
        idIndex++;
        this.cardNumbers = new int[5][5];
        this.markedSpots = new boolean[5][5];
        // Arrays.stream(markedSpots).forEach(target -> Arrays.fill(target, false));
        if (HAS_FREE_SPACE) {
            this.cardNumbers[2][2] = -1; 
            this.markedSpots[2][2] = true;
        }

    }

    /* 
     * Convenience constructor that adds the numbers in the specified String to the bingo card
     * @param String nums - a list of numbers between 1-75 separated by commas
     */
    public BingoCard(String nums) {
        this.id = idIndex;
        idIndex++;
        this.cardNumbers = new int[5][5];
        this.markedSpots = new boolean[5][5];
        if (HAS_FREE_SPACE) {
            this.cardNumbers[2][2] = -1;
            this.markedSpots[2][2] = true;
        }
        Pattern pattern = Pattern.compile("(?<=\\d)\\s*,\\s*(?=\\d)");
        String[] splitNums = pattern.split(nums.trim());
        for (String target : splitNums) {
            this.addNumber(Integer.parseInt(target));
        }
    }

    /* 
     * Convenience method that constructs an Array List of bingo cards from a formatted text file
     * @param String fileName - the name of the file to construct bingo cards from
     * @return ArrayList<BingoCard> containing the bingo cards constructed
     */
    public static ArrayList<BingoCard> createCardsFromFile(String fileName) {
        ArrayList<BingoCard> cards = new ArrayList<>();
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

    /* 
     * Method to add individual integers to the bingo card
     * @param int num - an integer between 1-75 inclusive to add
     * @return true if number was added, otherwise false
     */
    public boolean addNumber(int num) {
        if (num <= 0 || num > 75) return false;
        int col = (int) ((num - 1) / 15);
        for (int i = 0; i < cardNumbers.length; i++){
            if (cardNumbers[i][col] == 0) {
                   cardNumbers[i][col] = num;
                return true;
            }
        }
        return false;
    }

    /* 
     * Convenience method to add integers to the bingo card
     * @params int args - integers between 1-75 inclusive to add
     * @return true if all args were added, otherwise false
     */
    public boolean addNumber(int... args) {
        boolean res = true;
        for (int num : args){
            res = res & addNumber(num);
        }
        return res;
    }

    /* 
     * Method to mark a spot on a bingo card
     * @param int i - row that contains the spot to mark
     * @param int j - column that contains the spot to mark
     */
    public void markSpot(int i, int j) {
        this.markedSpots[i][j] = true;
    }

    /* 
     * Convenience method to convert an integer to a formatted String to improve appearance when printed
     * @param int i - row that contains the integer
     * @param int j - column that contains the integer
     * @return String with length 2 of the specified num or FS (Free Space) if specified num equals -1
     */
    private String numFormat(int i, int j) {
        if (cardNumbers[i][j] == -1) return "\u001B[31mFS\u001B[0m";
        if (markedSpots[i][j] == true) return "\u001B[31m" + String.format("%-2d", cardNumbers[i][j]) + "\u001B[0m";
        return String.format("%-2d", cardNumbers[i][j]);
    }

    /* 
     * Convenience method to convert a row of integers to a formatted String to improve appearance when printed
     * @param int i - index of row in cardNumbers to convert
     * @return String with a standardized length
     */
    public String rowToString(int i){
        String res = "";
        for (int j = 0; j < cardNumbers[i].length - 1; j++) {
            res += numFormat(i, j) + "|";
        }
        res += numFormat(i, cardNumbers[i].length - 1);
        return res;
    }

    /* 
     * Converts this bingo card to a formatted String to improve appearance when printed
     * @return String to display
     */
    @Override
    public String toString() {
        String res = "B |I |N |G |O " + "\n" + Spacer + "\n";
        for (int i = 0; i < cardNumbers.length - 1; i++) {
            res += rowToString(i) + "\n" + Spacer + "\n";
        }
        res += rowToString(cardNumbers.length - 1);
        return res;
    }
}

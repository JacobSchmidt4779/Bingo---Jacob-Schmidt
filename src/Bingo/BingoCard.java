package Bingo;

import java.util.regex.*;

public class BingoCard {
    private String name;
    private int[][] cardNumbers;

    public static boolean HAS_FREE_SPACE = false;

    /*
     * Default constructor
     */
    public BingoCard() {
        this.name = new String();
        this.cardNumbers = new int[5][5];
        if (HAS_FREE_SPACE) this.cardNumbers[2][2] = -1;
    }

    /* 
     * Convenience constructor that adds the numbers in the specified String to the bingo card
     * @param String nums - a list of numbers between 1-75 separated by commas
     */
    public BingoCard(String nums) {
        this.name = new String();
        this.cardNumbers = new int[5][5];
        if (HAS_FREE_SPACE) this.cardNumbers[2][2] = -1;
        Pattern pattern = Pattern.compile("\\s*,\\s*");
        String[] splitNums = pattern.split(nums.trim());
        for (String target : splitNums) {
            this.addNumber(Integer.parseInt(target));
        }
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
     * Convenience method to convert an integer to a formatted String to improve appearance when printed
     * @param int num - integer to format
     * @return String with length 2 of the specified num or FS (Free Space) if specified num equals -1
     */
    private static String numFormat(int num) {
        if (num == -1) return "FS";
        return String.format("%-2d", num);
    }

    /* 
     * Convenience method to convert a row of integers to a formatted String to improve appearance when printed
     * @param int i - index of row in cardNumbers to convert
     * @return String with a standardized length
     */
    private String rowToString(int i){
        String res = "";
        for (int j = 0; j < cardNumbers[i].length - 1; j++) {
            res += numFormat(cardNumbers[i][j]) + "|";
        }
        res += numFormat(cardNumbers[i][cardNumbers[i].length - 1]);
        return res;
    }

    /* 
     * Converts this bingo card to a formatted String to improve appearance when printed
     * @return String to display
     */
    @Override
    public String toString() {
        String spacer = "\n--+--+--+--+--\n";
        String res = this.name + "\n" + "B |I |N |G |O " + spacer;
        for (int i = 0; i < cardNumbers.length - 1; i++) {
            res += rowToString(i) + spacer;
        }
        res += rowToString(cardNumbers.length - 1);
        return res;
    }
}

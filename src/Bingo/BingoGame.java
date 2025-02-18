package Bingo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BingoGame {
    private ArrayList<Integer> calledNums;  // ArrayList to hold all previously called numbers
    private ArrayList<BingoCard> cards;     // ArrayList to hold 1 to 4

    /* 
     * Default Constructor
     */
    public BingoGame() {
        this.calledNums = new ArrayList<Integer>();
    }

    public void StartGame() {
        System.out.println("What game mode would you like to play: random or manual?");
        Scanner scan = new Scanner(System.in);
        String ans = "";

        while (ans != "random" || ans != "manual") {
            ans = scan.nextLine().trim();
        }

        scan.close();

        if (ans.equals("random")) 
            playRandomGame();
        else 
            playManualGame();
    }

    public void playRandomGame() {
        System.out.println("How many bingo cards would you like to play with?\nEnter a number from 1 to a max of 4 bingo cards");
        String ans = "";
        Scanner scan = new Scanner(System.in);
        Pattern numPattern = Pattern.compile("^([1-4])$");
        Matcher match;
        do {
            ans = scan.nextLine().trim();
            match = numPattern.matcher(ans);
        } while (match.matches());
    }

    public void playManualGame() {
        //TODO
    }
    
    /* 
     * Method to search calledNums for a specified integer
     * @param int t - integer to search for
     * @param int low - lower bound of array to search
     * @param int high - upper bound of array to search
     * @return int index where the integer should be inserted into calledNums maintain natural order
     *         otherwise return -1 if the integer is less than 1 or greater than 75 or 
     *         the integer already exists in calledNums
     */
    private int getIndexOfOrderedInsert(int t, int low, int high) {
		int half = (Integer) ((high-low)/2) + low;
		if (high == low) {
            if (t > calledNums.get(high)) {
                return high + 1;
            }
            if (t == calledNums.get(high)) {
                return -1;
            }
			return low;
		}
		if (t < calledNums.get(half)) {
			return getIndexOfOrderedInsert(t, low, half);
		}
		if (t > calledNums.get(half)) {
			return getIndexOfOrderedInsert(t, half + 1, high);
		}
		return -1;
	}

    /* 
     * Adds the specified num to calledNums while maintaining natural order
     * @param int num - integer to insert
     * @return true if num was inserted, otherwise return false 
     */
    public boolean addToCalledNums(int num) {
        if (num < 1 || num > 75) return false;
        if (calledNums.size() == 0) {
            calledNums.add(num);
            return true;
        }
        int insertIndex = getIndexOfOrderedInsert(num, 0, calledNums.size() - 1);
        if (insertIndex == -1) return false;
        if (insertIndex >= calledNums.size()) {
            calledNums.addLast(num);
            return true;
        }
        calledNums.add(insertIndex, num);
        return true;
    }

    /* 
     * Generates a random number between 1 and 75 inclusive that has not been called during this game
     * @return the random number that was generated
     */
    public int generateNumber() {
        if (calledNums.size() == 75) return -1;
        int res = (int) (Math.random() * (75 - calledNums.size())) + 1;
        boolean cont = true;
        while (cont) {
            cont = !addToCalledNums(res);
            res = ((res + 1) % 75) + 1;
        }
        return res;
    }

    public void generateBingoCards(int amount) {
        ArrayList<BingoCard> temp = BingoCard.createCardsFromFile("BingoCards.txt");
        for (int i = 0; i < amount; i++) {
            int randIndex = (int) (Math.random() * temp.size());
            cards.add(temp.remove(randIndex));
        }
    }

    /* 
     * Generates a readable list of all numbers previously called
     * @return String containing the list
     */
    @Override
    public String toString() {
        String res = "";
        Iterator<Integer> itr = calledNums.iterator();
        for(int i = 0; i < calledNums.size(); i++) {
            res += itr.next() + " ";
        }
        return res;
    }
}
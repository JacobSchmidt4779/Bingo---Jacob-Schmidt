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
        this.cards = new ArrayList<BingoCard>();
    }

    public void StartGame() {
        System.out.println("What game mode would you like to play: random or manual?");
        Scanner scan = new Scanner(System.in);
        String ans = "";

        while (!(ans.equals("random") || ans.equals("manual"))) {
            ans = scan.nextLine().trim();
        }
        System.out.println("Selected: " + ans + "\n");


        if (ans.equals("random")) 
            playRandomGame();
        else 
            playManualGame();
    }

    public void playRandomGame() {
        System.out.println("How many bingo cards would you like to play with?\nEnter an amount from 1 to a max of 4 bingo cards");
        Scanner scan = new Scanner(System.in);
        Pattern numPattern = Pattern.compile("^([1-4])$");
        String ans = scan.nextLine().trim();
        Matcher match = numPattern.matcher(ans);
        
        while (!match.matches()) {
            ans = scan.nextLine().trim();
            match = numPattern.matcher(ans);
        }

        System.out.println("Selected " + ans + " card(s)\n");

        generateBingoCards(Integer.parseInt(ans));
        
        while (promptToMarkMultCards())
            System.out.println();

        if (cards.size() == 0) {
            System.out.println("You lost!");
        }
        else {
            System.out.println("You win!");
        }
        scan.close();
    }

    public boolean promptToMarkMultCards() {
        Scanner scan = new Scanner(System.in);
        int number =generateNumber();

        if (cards.size() == 0) 
            return false;

        for (int i = 0; i < cards.size(); i++) {
            System.out.println("Number rolled: " + BingoCard.numToString(number));
            System.out.println(cards.get(i));
            System.out.println("Enter the Row and Column (eg BB) of a number to mark, 'next' to continue to the next card, or 'bingo' if you have a bingo");
            String ans = scan.nextLine().trim();

            while (!ans.equals("next") && !ans.equals("bingo")){

                if (cards.get(i).getNumberAt(ans) == -1) {
                    System.out.println("Not a valid coordinate!");
                } 
                else if (cards.get(i).getNumberAt(ans) != number){
                    System.out.println("Selected number (" + cards.get(i).getNumberAt(ans) + ") does not match number rolled!");
                }
                else {
                    cards.get(i).markSpot(ans);
                    System.out.println(cards.get(i));
                    System.out.println("Spot " + ans + " marked");
                }

                ans = scan.nextLine().trim();
            }
            if (ans.equals("bingo")) {
                if (!cards.get(i).checkBingo()) {
                    System.out.println("Card does not have bingo! Card discarded...Continuing with next card");
                    cards.remove(i);
                    i--;
                }
                else {
                    return false;
                }
            }

        }
        return true;
    }

    public void playManualGame() {
        ArrayList<BingoCard> pool = BingoCard.createCardsFromFile("BingoCards.txt");
        for (BingoCard target : pool) {
            System.out.println(target + "\n");
        }
        System.out.println("Please select the bingo card you wish to play with by entering its number");
        Scanner scan = new Scanner(System.in);
        Pattern numPattern = Pattern.compile("^([1-" + pool.size() + "])$");
        String ans = scan.nextLine().trim();
        Matcher match = numPattern.matcher(ans);
        
        while (!match.matches()) {
            ans = scan.nextLine().trim();
            match = numPattern.matcher(ans);
        }

        System.out.println("Selected Card #" + ans + "\n");

        cards.add(pool.get(Integer.parseInt(ans) - 1));

        promptToMarkSingleCard();
    }

    public void promptToMarkSingleCard() {
        Scanner scan = new Scanner(System.in);

        BingoCard card = cards.getFirst();

        System.out.println(card);
        System.out.println("Enter the Row and Column (eg BB) of a number to mark or 'bingo' if you have a bingo");
        String ans = scan.nextLine().trim().toUpperCase();

        while (!ans.equals("QUIT")){
            if (ans.equals("BINGO")) {
                if (!card.checkBingo()) {
                    System.out.println("Card does not have bingo!");
                }
                else {
                    System.out.println("You win!");
                    return;
                }
            }
            else if (card.getNumberAt(ans) == -1) {
                System.out.println("Not a valid coordinate!");
            } 
            else {
                card.markSpot(ans);
                System.out.println(card);
                System.out.println("Spot " + ans + " marked");
            }

            ans = scan.nextLine().trim().toUpperCase();
        }
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
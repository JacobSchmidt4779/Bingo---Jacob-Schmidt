package Bingo;

import java.util.ArrayList;
import java.util.Iterator;

public class BingoGame {
    private ArrayList<Integer> calledNums;

    /* 
     * Default Constructor
     */
    public BingoGame() {
        this.calledNums = new ArrayList<Integer>();
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
		if(high == low) {
            if (t > calledNums.get(high)) {
                return high + 1;
            }
            if (t == calledNums.get(high)) {
                return -1;
            }
			return low;
		}
		if(t < calledNums.get(half)) {
			return getIndexOfOrderedInsert(t, low, half);
		}
		if(t > calledNums.get(half)) {
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
        if(num < 1 || num > 75) return false;
        if(calledNums.size() == 0) {
            calledNums.add(num);
            return true;
        }
        int insertIndex = getIndexOfOrderedInsert(num, 0, calledNums.size() - 1);
        if(insertIndex == -1) return false;
        if(insertIndex >= calledNums.size()) {
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
        if(calledNums.size() == 75) return -1;
        int res = (int) (Math.random() * (75 - calledNums.size())) + 1;
        boolean cont = true;
        while (cont) {
            cont = !addToCalledNums(res);
            res = ((res + 1) % 75) + 1;
        }
        return res;
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
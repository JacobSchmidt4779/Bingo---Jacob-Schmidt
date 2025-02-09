package Bingo;

import java.util.ArrayList;
import java.util.Iterator;

public class BingoGame {
    private ArrayList<Integer> calledNums;

    public BingoGame() {
        this.calledNums = new ArrayList<Integer>();
    }
    
    private int getIndexOfOrderedInsert(int t, int low, int high) {
		int half = (Integer) ((high-low)/2) + low;
		if(high == low) {
            if (t > calledNums.get(high)) {
                return high + 1;
            }
			return low;
		}
		if(t < calledNums.get(half)) {
			return getIndexOfOrderedInsert(t, low, half - 1);
		}
		if(t > calledNums.get(half)) {
			return getIndexOfOrderedInsert(t, half + 1, high);
		}
		return -1;
	}

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
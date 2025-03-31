package BingoPatterns;

import Bingo.BingoCard;
import Bingo.BingoCardSpace;

public class BingoColumnPattern extends BingoPattern {

    @Override
    public boolean checkBingo(BingoCard card) {
        BingoCardSpace[][] spaces = card.getSpaces();

        for (int col = 0; col < spaces.length; col++) {
            if (checkCol(col, spaces)) {
                return true;
            }
        }

        return false;
    }

    private static boolean checkCol(int col, BingoCardSpace[][] spaces) {

        for (int row = 0; row < spaces.length; row++) {
            if (!spaces[row][col].isMarked()) {
                return false;
            }
        }

        return true;
    }
}
package BingoPatterns;

import Bingo.BingoCard;
import Bingo.BingoCardSpace;

public class BingoRowPattern extends BingoPattern {

    @Override
    public boolean checkBingo(BingoCard card) {
        BingoCardSpace[][] spaces = card.getSpaces();

        for (int row = 0; row < spaces.length; row++) {
            if (checkRow(spaces[row])) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int numberOfBingos(BingoCard card) {
        BingoCardSpace[][] spaces = card.getSpaces();
        int bingoCount = 0;

        for (int row = 0; row < spaces.length; row++) {
            if (checkRow(spaces[row])) {
                bingoCount++;
            }
        }

        return bingoCount;
    }

    private static boolean checkRow(BingoCardSpace[] row) {

        for (int col = 0; col < row.length; col++) {
            if (!row[col].isMarked()) {
                return false;
            }
        }

        return true;
    }
}

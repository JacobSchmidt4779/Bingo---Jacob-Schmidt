package BingoPatterns;

import Bingo.BingoCard;

public abstract class BingoPattern {
    public boolean checkBingo(BingoCard card) {
        return false;
    }

    public int numberOfBingos(BingoCard card) {
        return 0;
    }
}

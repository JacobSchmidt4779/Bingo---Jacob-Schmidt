package BingoPatterns;

import java.util.ArrayList;

import Bingo.BingoCard;

public class BingoManager {
    ArrayList<BingoPattern> patterns;

    public BingoManager() {
        patterns = new ArrayList<BingoPattern>();
    }

    public void addPattern(BingoPattern pattern) {
        patterns.add(pattern);
    }

    public int numberOfBingos(BingoCard card) {
        int bingoCount = 0;

        for (int i = 0; i < patterns.size(); i++) {
            bingoCount += patterns.get(i).numberOfBingos(card);
        }

        return bingoCount;
    }
}

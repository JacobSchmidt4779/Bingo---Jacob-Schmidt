package BingoPatterns;

import Bingo.BingoCard;
import Bingo.BingoCardSpace;

public class BingoDiagonalPattern extends BingoPattern {

    @Override
    public boolean checkBingo(BingoCard card) {
        BingoCardSpace[][] spaces = card.getSpaces();

        if (spaces[0][0].isMarked() || spaces[4][0].isMarked()) {
            boolean downward = true;
            boolean upward = true;

            for(int i = 1; i < spaces.length; i++){
                downward = downward && spaces[i][i].isMarked();
                upward = upward && spaces[i][4 - i].isMarked();
            }

            return downward || upward;
        }
        
        return false;
    }

    @Override
    public int numberOfBingos(BingoCard card) {
        BingoCardSpace[][] spaces = card.getSpaces();

        boolean downward = true;
        boolean upward = true;

        for(int i = 1; i < spaces.length; i++){
            downward = downward && spaces[i][i].isMarked();
            upward = upward && spaces[i][4 - i].isMarked();
        }
        
        return (downward ? 1 : 0) + (upward ? 1 : 0);
    }
}

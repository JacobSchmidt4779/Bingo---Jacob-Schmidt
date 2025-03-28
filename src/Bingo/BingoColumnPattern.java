package Bingo;

public class BingoColumnPattern implements BingoPattern {

    public boolean checkBingo(BingoCard card) {
        BingoCardSpace[][] spaces = card.getSpaces();

        for (int col = 0; col < spaces.length; col++) {
            boolean res = true;

            for (int row = 0; row < spaces[0].length; row++) {
                res = res && spaces[row][col].isMarked();
            }

            if (res)
                return res;
        }
        return false;
    }
}
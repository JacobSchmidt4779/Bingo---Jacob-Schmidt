package Bingo;

public class BingoRowPattern implements BingoPattern {

    public boolean checkBingo(BingoCard card) {
        BingoCardSpace[][] spaces = card.getSpaces();
        for (int row = 0; row < spaces.length; row++) {
                boolean res = true;
                for (int col = 0; col < spaces[0].length; col++) {
                    res = res && spaces[row][col].isMarked();
                }
                if (res) return res;
        }
        return false;
    }
}

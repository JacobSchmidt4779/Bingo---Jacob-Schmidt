package Bingo;

public class BingoRowPattern implements BingoPattern {
    @Override
    public static boolean checkBingo(BingoCard card) {
        BingoCardSpace[][] spaces = card.getSpaces();

        for (int row = 0; row < spaces.length; row++) {
            if (checkRow(spaces[row])) {
                return true;
            }
        }

        return false;
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

package Bingo;

public class BingoDiagonalPattern implements BingoPattern{
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
}

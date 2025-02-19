import Bingo.BingoCard;
import Bingo.BingoGame;

public class Main {
    public static void main(String[] args) {
        BingoCard.HAS_FREE_SPACE = false;
        BingoGame game = new BingoGame();
        game.StartGame();
    }
}

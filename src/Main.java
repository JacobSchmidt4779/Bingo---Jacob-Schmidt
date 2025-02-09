import Bingo.BingoCard;
import Bingo.BingoGame;

public class Main {
    public static void main(String[] args) {
        // BingoCard.HAS_FREE_SPACE = true;
        // ArrayList<BingoCard> cards = BingoCard.createCardsFromFile("BingoCards.txt");
        // cards.get(0).markSpot(4,1);;
        // for (BingoCard target : cards) {
        //     System.out.println(target + "\n");
        // }
        BingoGame game = new BingoGame();
        for (int i = 0; i < 75; i ++) {
            System.out.println("Rand num: " + game.generateNumber() + " called: " + game);
        }
    }
}

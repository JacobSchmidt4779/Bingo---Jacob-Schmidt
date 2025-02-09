import java.util.ArrayList;

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
        System.out.println("1,2,3,4,5,8");
        System.out.println(game.getIndexOfOrderedInsert(0, 0, 5));
        System.out.println(game.getIndexOfOrderedInsert(6, 0, 5));
        System.out.println(game.getIndexOfOrderedInsert(9, 0, 5));
        System.out.println(game.getIndexOfOrderedInsert(3, 0, 5));
    }
}

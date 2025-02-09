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
        game.addToCalledNums(2);
        game.addToCalledNums(1);
        game.addToCalledNums(5);
        game.addToCalledNums(3);
        game.addToCalledNums(4);
        game.addToCalledNums(3);
        game.addToCalledNums(5);
        System.out.println(game);
    }
}

import java.util.ArrayList;

import Bingo.BingoCard;
import Bingo.BingoGame;

public class Main {
    public static void main(String[] args) {
        BingoCard.HAS_FREE_SPACE = false;
        BingoCard card = BingoCard.createCardsFromFile("BingoCards.txt").get(0);
        
        for (int col = 0; col < 5; col++) {
            card.markSpot(col, 4 - col);
            card.markSpot(4, col);
        }
        System.out.println(card);
        System.out.println(card.checkBingo());

        System.out.println(BingoCard.numToString(card.getNumberAt("BI")));
        // for (BingoCard target : cards) {
        //     System.out.println(target + "\n");
        // }
        // BingoGame game = new BingoGame();
        // for (int i = 0; i < 75; i ++) {
        //     System.out.println("Rand num: " + game.generateNumber() + " called: " + game);
        // }
        // System.out.println(BingoCard.charToCol('B'));
        // System.out.println(BingoCard.charToCol('I'));
        // System.out.println(BingoCard.charToCol('N'));
        // System.out.println(BingoCard.charToCol('G'));
        // System.out.println(BingoCard.charToCol('O'));
        // System.out.println(BingoCard.charToCol('b'));
        // System.out.println(BingoCard.charToCol('i'));
        // System.out.println(BingoCard.charToCol('n'));
        // System.out.println(BingoCard.charToCol('g'));
        // System.out.println(BingoCard.charToCol('o'));
    }
}

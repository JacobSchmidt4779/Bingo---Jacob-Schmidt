import java.util.ArrayList;

import Bingo.BingoCard;

public class Main {
    public static void main(String[] args) {
        BingoCard.HAS_FREE_SPACE = true;
        ArrayList<BingoCard> cards = BingoCard.createCardsFromFile("BingoCards.txt");
        cards.get(0).markSpot(4,1);;
        for (BingoCard target : cards) {
            System.out.println(target + "\n");
        }
    }
}

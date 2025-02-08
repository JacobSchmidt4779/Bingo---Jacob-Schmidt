import java.util.ArrayList;

import Bingo.BingoCard;

public class Main {
    public static void main(String[] args) {
        ArrayList<BingoCard> cards = BingoCard.createCardsFromFile("BingoCards.txt");
        for (BingoCard target : cards) {
            System.out.println(target + "\n");
        }
    }
}

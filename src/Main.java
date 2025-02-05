import Bingo.BingoCard;

public class Main {
    public static void main(String[] args) {
        BingoCard.HAS_FREE_SPACE = true;
        BingoCard card = new BingoCard();
        card.addNumber(1, 15, 16, 20, 66, 57, 44, 35, 29, 33, 0, 99, 75);
        System.out.println(card.toString());

        card = new BingoCard(" 1, 15, 16, 20, 66, 57, 44, 35, 29, 33, 0, 99, 75");
        System.out.println(card.toString());

        BingoCard.HAS_FREE_SPACE = false;
        card = new BingoCard();
        card.addNumber(1, 15, 16, 20, 66, 57, 44, 35, 29, 33, 0, 99, 75);
        System.out.println(card.toString());
    }
}

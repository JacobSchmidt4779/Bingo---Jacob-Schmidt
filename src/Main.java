import Bingo.BingoCard;

public class Main {
    public static void main(String[] args) {
        BingoCard card = new BingoCard();
        card.addNumber(1, 15, 16, 20, 66, 57, 44, 35, 29, 33, 0, 99, 75);
        System.out.print(card.toString());
    }
}

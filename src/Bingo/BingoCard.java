package Bingo;

public class BingoCard {
    private String name;
    private int[][] cardNumbers;

    public static boolean HAS_FREE_SPACE = false;

    public BingoCard() {
        this.name = new String();
        this.cardNumbers = new int[5][5];
        if (HAS_FREE_SPACE) this.cardNumbers[2][2] = -1;
    }

    public boolean addNumber(int num) {
        if (num <= 0 || num > 75) return false;
        int col = (int) ((num - 1) / 15);
        for (int i = 0; i < cardNumbers.length; i++){
            if (cardNumbers[i][col] == 0) {
                   cardNumbers[i][col] = num;
                return true;
            }
        }
        return false;
    }

    public boolean addNumber(int... args) {
        boolean res = true;
        for (int num : args){
            res = res & addNumber(num);
        }
        return res;
    }

    private String numFormat(int i, int j) {
        if (cardNumbers[i][j] == -1) return "FS";
        return String.format("%-2d", cardNumbers[i][j]);
    }

    private String rowToString(int i){
        String res = "";
        for (int j = 0; j < cardNumbers[i].length - 1; j++) {
            res += numFormat(i, j) + "|";
        }
        res += numFormat(i, cardNumbers[i].length - 1);
        return res;
    }

    @Override
    public String toString() {
        String spacer = "\n--+--+--+--+--\n";
        String res = this.name + "\n" + "B |I |N |G |O " + spacer;
        for (int i = 0; i < cardNumbers.length - 1; i++) {
            res += rowToString(i) + spacer;
        }
        res += rowToString(cardNumbers.length - 1);
        return res;
    }
}

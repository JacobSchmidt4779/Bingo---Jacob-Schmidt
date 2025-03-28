package Bingo;

public class BingoCardSpace {
    private int number;
    private boolean checked;

    public BingoCardSpace(int setNumber) {
        number = setNumber;
        checked = false;
    }

    public boolean matchNumber(int numberToMatch) {
        return number == numberToMatch;
    }

    public void check() {
        checked = true;
    }

    public boolean isChecked() {
        return checked;
    }
}

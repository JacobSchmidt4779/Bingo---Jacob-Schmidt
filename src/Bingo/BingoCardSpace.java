package Bingo;

public class BingoCardSpace {
    private int number;
    private boolean checked;

    public BingoCardSpace() {
        number = 0;
        checked = false;
    }

    public BingoCardSpace(int setNumber) {
        number = setNumber;
        checked = false;
    }

    public int getNumber() {
        return number;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void mark() {
        checked = true;
    }

    public boolean matchNumber(int numberToMatch) {
        return number == numberToMatch;
    }
}

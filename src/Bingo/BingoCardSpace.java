package Bingo;

public class BingoCardSpace {
    private int number;
    private boolean marked;

    public BingoCardSpace() {
        number = 0;
        marked = false;
    }

    public BingoCardSpace(int setNumber) {
        number = setNumber;
        marked = false;
    }

    public int getNumber() {
        return number;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void mark() {
        marked = true;
    }

    public boolean matchNumber(int numberToMatch) {
        return number == numberToMatch;
    }
}

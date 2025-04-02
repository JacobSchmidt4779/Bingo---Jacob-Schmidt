package BingoPatterns;

import java.util.ArrayList;
import java.util.regex.Pattern;

import Bingo.BingoCard;

public class BingoCustomPattern extends BingoPattern {
    public enum DefaultPattern {
        T_PATTERN(
            new String[] { 
                "bb", "bi", "bn", "bg", "bo", "in", "nn", "gn", "on"
            }
        ),

        SQUARE_PATTERN(
            new String[] {
                "bb", "bi", "bn", "bg", "bo", "ib", "nb", "gb", "ob", "oi", "on", "og", "oo", "go", "no", "io"
            }
        );

        public final String[] value;

        private DefaultPattern(String[] value) {
            this.value = value;
        }
    }

    private class Coordinate {
        private int row;
        private int col;

        public Coordinate(int setRow, int setCol) {
            row = setRow;
            col = setCol;
        }

        public Coordinate(String rowCol) {
            rowCol = rowCol.trim().toUpperCase();

            if (Pattern.matches("^\s*[BINGO][BINGO]\s*$", rowCol)) {
                this.row = BingoCard.charToCol(rowCol.charAt(0));
                this.col = BingoCard.charToCol(rowCol.charAt(1));
            }
        }
    }

    private ArrayList<Coordinate> patternCoordinates;

    public BingoCustomPattern() {
        patternCoordinates = new ArrayList<Coordinate>();
    }

    public BingoCustomPattern(String[] coordinates) {
        patternCoordinates = new ArrayList<Coordinate>();
        this.addCoords(coordinates);
    }
    
    @Override
    public boolean checkBingo(BingoCard card) {
        for (Coordinate target : patternCoordinates) {
            if (!card.isSpaceMarkedAt(target.row, target.col)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int numberOfBingos(BingoCard card) {
        for (Coordinate target : patternCoordinates) {
            if (!card.isSpaceMarkedAt(target.row, target.col)) {
                return 0;
            }
        }
        return 1;
    }

    public boolean addCoord(int row, int col) {
        if (row < 0 || row > 4 || col < 0 || col > 4) {
            return false;
        }
        
        patternCoordinates.add(new Coordinate(row, col));
        return true;
    }

    public void addCoord(String rowCol) {
        patternCoordinates.add(new Coordinate(rowCol));
    }

    public void addCoords(String... rowCol) {
        for (String target : rowCol) {
            addCoord(target);
        }
    }
}

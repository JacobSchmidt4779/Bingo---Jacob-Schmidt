package BingoPatterns;

import java.util.ArrayList;
import java.util.regex.Pattern;

import Bingo.BingoCard;

public class BingoCustomPattern {

    private class Coordinate {
        private int row;
        private int col;

        public Coordinate(int setRow, int setCol) {
            row = setRow;
            col = setCol;
        }
    }

    private ArrayList<Coordinate> patternCoordinates;

    public BingoCustomPattern() {
        patternCoordinates = new ArrayList<Coordinate>();
    }

    public boolean addCoord(int row, int col) {
        if (row < 0 || row > 4 || col < 0 || col > 4) {
            return false;
        }
        
        patternCoordinates.add(new Coordinate(row, col));
        return true;
    }

    public void addCoord(String rowCol) {
        rowCol = rowCol.trim().toUpperCase();

        if (Pattern.matches("^\s*[BINGO][BINGO]\s*$", rowCol)) {
            int row = BingoCard.charToCol(rowCol.charAt(0));
            int col = BingoCard.charToCol(rowCol.charAt(1));
            patternCoordinates.add(new Coordinate(row, col));
        }
    }

    public void addCoords(String... rowCol) {
        for (String target : rowCol) {
            addCoord(target);
        }
    }

    public boolean checkBingo(BingoCard card) {
        for (Coordinate target : patternCoordinates) {
            if (!card.isSpaceMarkedAt(target.row, target.col)) {
                return false;
            }
        }
        return true;
    }
}

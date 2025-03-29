package BingoPatterns;

import java.util.ArrayList;

import Bingo.BingoCard;

public class BingoCustomPattern {

    public class Coordinate {
        private int row;
        private int col;

        public Coordinate() {
            row = -1;
            col = -1;
        }

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

    public boolean addCoord(Coordinate coord) {
        if (coord.row < 0 || coord.row > 4 || coord.col < 0 || coord.col > 4) {
            return false;
        }

        patternCoordinates.add(coord);
        return true;
    }

    public void addCoordinates(Coordinate... args) {
        for (Coordinate target : args) {
            patternCoordinates.add(target);
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

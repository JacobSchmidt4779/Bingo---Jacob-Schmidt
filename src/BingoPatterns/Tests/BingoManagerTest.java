package BingoPatterns.Tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Bingo.BingoCard;
import BingoPatterns.BingoColumnPattern;
import BingoPatterns.BingoCustomPattern;
import BingoPatterns.BingoDiagonalPattern;
import BingoPatterns.BingoManager;
import BingoPatterns.BingoRowPattern;

public class BingoManagerTest {
    BingoCard card;
    BingoManager manager;
    
    @BeforeEach
    public void setup() {
        card = new BingoCard();
        card.addNumber(3, 22, 32, 51, 66, 
            6, 19, 44, 60, 61,
            7, 27, 45, 58, 72,
            15, 16, 41, 46, 75,
            1, 30, 31, 52, 63);
        
        manager = new BingoManager();
    }

    @Test
    void testNumberOfBingosBM1() {
        manager.addPattern(new BingoRowPattern());
        card.markSpace("bb", "bi" , "bn", "bg", "bo");

        assertEquals(manager.numberOfBingos(card), 1);
    }

    @Test
    void testNumberOfBingosBM2() {
        manager.addPattern(new BingoRowPattern());
        card.markSpace("bb", "bi" , "bn", "bg", "bo", "nb", "ni", "nn", "ng", "no", "ob", "oi", "on", "og", "oo");

        assertEquals(manager.numberOfBingos(card), 3);
    }

    @Test
    void testNumberOfBingosBM3() {
        manager.addPattern(new BingoRowPattern());
        card.markSpace("bb", "bi" , "bn", "bg", "bo", "nb", "ni", "nn", "ng", "no", "ob", "oi", "on", "og", "oo", "io", "go");

        assertEquals(manager.numberOfBingos(card), 3);
    }

    @Test
    void testNumberOfBingosBM4() {
        manager.addPattern(new BingoRowPattern());
        manager.addPattern(new BingoColumnPattern());
        card.markSpace("bb", "bi" , "bn", "bg", "bo", "nb", "ni", "nn", "ng", "no", "ob", "oi", "on", "og", "oo", "io", "go");

        assertEquals(manager.numberOfBingos(card), 4);
    }

    @Test
    void testNumberOfBingosBM5() {
        manager.addPattern(new BingoRowPattern());
        manager.addPattern(new BingoColumnPattern());
        manager.addPattern(new BingoDiagonalPattern());

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                card.markSpace(row, col);
            }
        }

        assertEquals(manager.numberOfBingos(card), 12);
    }

    @Test
    void testNumberOfBingosBM6() {
        manager.addPattern(new BingoRowPattern());
        manager.addPattern(new BingoColumnPattern());
        manager.addPattern(new BingoDiagonalPattern());
        manager.addPattern(new BingoCustomPattern(BingoCustomPattern.DefaultPattern.T_PATTERN.value));
        manager.addPattern(new BingoCustomPattern(BingoCustomPattern.DefaultPattern.SQUARE_PATTERN.value));

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                card.markSpace(row, col);
            }
        }

        assertEquals(manager.numberOfBingos(card), 14);
    }

    @Test
    void testNumberOfBingosBM7() {
        manager.addPattern(new BingoRowPattern());
        manager.addPattern(new BingoColumnPattern());
        manager.addPattern(new BingoDiagonalPattern());
        manager.addPattern(new BingoCustomPattern(BingoCustomPattern.DefaultPattern.T_PATTERN.value));
        manager.addPattern(new BingoCustomPattern(BingoCustomPattern.DefaultPattern.SQUARE_PATTERN.value));

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 4; col++) {
                card.markSpace(row, col);
            }
        }

        card.markSpace("io", "no", "go", "oo");

        assertEquals(manager.numberOfBingos(card), 9);
    }

    @Test
    void testNumberOfBingosBM8() {
        manager.addPattern(new BingoRowPattern());
        manager.addPattern(new BingoColumnPattern());
        manager.addPattern(new BingoDiagonalPattern());
        manager.addPattern(new BingoCustomPattern(BingoCustomPattern.DefaultPattern.T_PATTERN.value));
        manager.addPattern(new BingoCustomPattern(BingoCustomPattern.DefaultPattern.SQUARE_PATTERN.value));

        for (int row = 0; row < 5; row++) {
            if (row == 1) row ++;
            for (int col = 0; col < 5; col++) {
                card.markSpace(row, col);
            }
        }

        card.markSpace("ib", "in", "ig", "io");

        assertEquals(manager.numberOfBingos(card), 11);
    }

    @Test
    void testNumberOfBingosBM9() {
        manager.addPattern(new BingoRowPattern());
        manager.addPattern(new BingoColumnPattern());
        manager.addPattern(new BingoDiagonalPattern());
        manager.addPattern(new BingoCustomPattern(BingoCustomPattern.DefaultPattern.T_PATTERN.value));
        manager.addPattern(new BingoCustomPattern(BingoCustomPattern.DefaultPattern.SQUARE_PATTERN.value));

        for (int row = 0; row < 5; row++) {
            if (row == 2) row ++;
            for (int col = 0; col < 5; col++) {
                card.markSpace(row, col);
            }
        }

        card.markSpace("nb", "nn", "ng", "no");

        assertEquals(manager.numberOfBingos(card), 12);
    }
}

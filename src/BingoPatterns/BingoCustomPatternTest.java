package BingoPatterns;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Bingo.BingoCard;

public class BingoCustomPatternTest {
    BingoCard card;
    BingoCustomPattern pattern;

    @BeforeEach
    public void setup() {
        card = new BingoCard();
        card.addNumber(3, 22, 32, 51, 66, 
            6, 19, 44, 60, 61,
            7, 27, 45, 58, 72,
            15, 16, 41, 46, 75,
            1, 30, 31, 52, 63);
        
        pattern = new BingoCustomPattern();
    }

    @Test
    void testCheckBingoP13() {
        pattern.addCoords("bb", "bi", "bn", "bg", "bo", "in", "nn", "gn", "on");
        card.markSpace("bb", "bi", "bn", "bg", "bo", "in", "nn", "gn", "on");

        assertTrue(pattern.checkBingo(card));
    }

    @Test
    void testCheckBingoP14() {
        pattern.addCoords("bb", "bi", "bn", "bg", "bo", "in", "nn", "gn", "on");

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                card.markSpace(row, col);
            }
        }

        assertTrue(pattern.checkBingo(card));
    }

    @Test
    void testCheckBingoP15() {
        pattern.addCoords("bb", "bi", "bn", "bg", "bo", "in", "nn", "gn", "on");
        card.markSpace("bb", "bi", "bn", "bg", "bo", "in", "gn", "on");

        assertFalse(pattern.checkBingo(card));
    }

    @Test
    void testCheckBingoP16() {
        pattern.addCoords("bb", "bi", "bn", "bg", "bo", "ib", "nb", "gb", "ob", "oi", "on", "og", "oo", "go", "no", "io");
        card.markSpace("bb", "bi", "bn", "bg", "bo", "ib", "nb", "gb", "ob", "oi", "on", "og", "oo", "go", "no", "io");

        assertTrue(pattern.checkBingo(card));
    }

    @Test
    void testCheckBingoP17() {
        pattern.addCoords("bb", "bi", "bn", "bg", "bo", "ib", "nb", "gb", "ob", "oi", "on", "og", "oo", "go", "no", "io");
        
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 4; col++) {
                card.markSpace(row, col);
            }
        }
        card.markSpace("bo", "io", "no", "go");

        assertFalse(pattern.checkBingo(card));
    }

    @Test
    void testCheckBingoP18() {
        pattern.addCoords("bb", "bi", "bn", "bg", "bo", "ib", "nb", "gb", "ob", "oi", "on", "og", "oo", "go", "no", "io");
        
        for (int row = 1; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                card.markSpace(row, col);
            }
        }
        card.markSpace("ib", "nb", "gb", "ob");

        assertFalse(pattern.checkBingo(card));
    }
}

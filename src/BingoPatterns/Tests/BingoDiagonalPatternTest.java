package BingoPatterns.Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Bingo.BingoCard;
import BingoPatterns.BingoDiagonalPattern;

public class BingoDiagonalPatternTest {
    BingoCard card;
    BingoDiagonalPattern pattern;

    @BeforeEach
    public void setup() {
        card = new BingoCard();
        card.addNumber(3, 22, 32, 51, 66, 
            6, 19, 44, 60, 61,
            7, 27, 45, 58, 72,
            15, 16, 41, 46, 75,
            1, 30, 31, 52, 63);
        pattern = new BingoDiagonalPattern();
    }

    @Test
    void testCheckBingoP10() {
        card.markSpace("bb", "ii" , "nn", "gg", "oo");
        
        assertTrue(pattern.checkBingo(card));
    }

    @Test
    void testCheckBingoP11() {
        card.markSpace("bo", "ig" , "nn", "gi", "ob");
        
        assertTrue(pattern.checkBingo(card));
    }

    @Test
    void testCheckBingoP12() {
        card.markSpace("bi", "ii" , "ni", "gi", "oi");
        
        assertFalse(pattern.checkBingo(card));
    }
}

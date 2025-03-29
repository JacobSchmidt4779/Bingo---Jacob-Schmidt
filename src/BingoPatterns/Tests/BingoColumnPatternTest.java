package BingoPatterns.Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Bingo.BingoCard;
import BingoPatterns.BingoColumnPattern;

public class BingoColumnPatternTest {
    BingoCard card;

    @BeforeEach
    public void setup() {
        card = new BingoCard();
        card.addNumber(3, 22, 32, 51, 66, 
            6, 19, 44, 60, 61,
            7, 27, 45, 58, 72,
            15, 16, 41, 46, 75,
            1, 30, 31, 52, 63);
    }

    @Test
    void testCheckBingoP5() {
        card.markSpace("bb", "ib" , "nb", "gb", "ob");
        
        assertTrue(BingoColumnPattern.checkBingo(card));
    }

    @Test
    void testCheckBingoP6() {
        card.markSpace("bg", "ig" , "ng", "gg", "og");
        
        assertTrue(BingoColumnPattern.checkBingo(card));
    }

    @Test
    void testCheckBingoP7() {
        card.markSpace("bo", "io" , "no", "go", "oo");
        
        assertTrue(BingoColumnPattern.checkBingo(card));
    }

    @Test
    void testCheckBingoP8() {
        card.markSpace("bb", "bi" , "bn", "bg", "bo");
        
        assertFalse(BingoColumnPattern.checkBingo(card));
    }

    @Test
    void testCheckBingoP9() {
        card.markSpace("bi", "ni", "gi", "oi");
        
        assertFalse(BingoColumnPattern.checkBingo(card));
    }
}

package BingoPatterns.Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Bingo.BingoCard;
import BingoPatterns.BingoRowPattern;

public class BingoRowPatternTest {
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

    // bingo
    @Test
    void testCheckBingoP1() {
        card.markSpace("bb", "bi" , "bn", "bg", "bo");
        
        assertTrue(BingoRowPattern.checkBingo(card));
    }

    @Test
    void testCheckBingoP2() {
        card.markSpace("nb", "ni", "nn", "ng", "no");

        assertTrue(BingoRowPattern.checkBingo(card));
    }

    @Test 
    void testCheckBingoP3() {
        card.markSpace( "ob", "oi", "on", "og", "oo");

        assertTrue(BingoRowPattern.checkBingo(card));
    }

    @Test 
    void testCheckBingoP4() {
        card.markSpace("bb", "bi" , "bn", "bg");

        assertFalse(BingoRowPattern.checkBingo(card));
    }
}

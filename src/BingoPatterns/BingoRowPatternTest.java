package BingoPatterns;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Bingo.BingoCard;

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
    void testCheckBingoFirstRowMarked() {
        card.markSpace("bb", "bi" , "bn", "bg", "bo");
        
        assertTrue(BingoRowPattern.checkBingo(card));
    }

    @Test
    void testCheckBingoSecondRowMarked() {
        card.markSpace("nb", "ni", "nn", "ng", "no");

        assertTrue(BingoRowPattern.checkBingo(card));
    }

    @Test 
    void testCheckBingoThirdRowMarked() {
        card.markSpace( "ob", "oi", "on", "og", "oo");

        assertTrue(BingoRowPattern.checkBingo(card));
    }
}

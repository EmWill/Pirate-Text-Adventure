package test;

import model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {
    private Player testPirate;

    @BeforeEach
    public void initialize() {
        testPirate = new Player(0, "blank", 0, 0);
    }

    @Test
    public void newNameJimmy(){
        testPirate.nameSet("Jimmy");
        assertEquals("Jimmy", testPirate.getPirateName());
    }

    @Test
    public void drinkTwice()
    {
        testPirate.drink(5);
        assertEquals(testPirate.drunkenness, 5);
        testPirate.drink(4);
        assertEquals(testPirate.drunkenness, 9);
    }

    @Test
    public void drinkNegativeZeroPositive(){
        testPirate.drink(-40);
        assertEquals(testPirate.drunkenness, -40);
        testPirate.drink(0);
        assertEquals(testPirate.drunkenness, -40);
        testPirate.drink(7);
        assertEquals(testPirate.drunkenness, -33);
    }

    @Test
    public void drinkNegativeTwice(){
        testPirate.drink(-1);
        assertEquals(testPirate.drunkenness, -1);
        testPirate.drink(-7);
        assertEquals(testPirate.drunkenness, -8);
    }
}

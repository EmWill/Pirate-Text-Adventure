package test;

import model.Gun;
import model.Player;
import model.Sword;
import model.Weapon;
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

    @Test
    public void goNorthTest(){
        testPirate.goNorth();
        assertEquals(1, testPirate.pirateY);
    }

    @Test
    public void goWestTest(){
        testPirate.goWest();
        assertEquals(-1, testPirate.pirateX);
    }

    @Test
    public void goSouthTest(){
        testPirate.goSouth();
        assertEquals(-1, testPirate.pirateY);
    }


    @Test
    public void goEastTest(){
        testPirate.goEast();
        assertEquals(1, testPirate.pirateX);
    }

    @Test
    public void equipTestMiddleItem(){
        testPirate.inventory.add(new Sword("Chinese Chicken", 12, "test", "test2"));
        testPirate.inventory.add(new Sword("Za Warudo", 24, "slaps", "wicked"));
        testPirate.inventory.add(new Sword("Dark Necklace", 100, "dark evil energy",
                "twisted"));
        assertTrue(testPirate.equip("Za Warudo"));
        assertEquals(testPirate.currentWeapon.getName(), "Za Warudo");
        assertEquals(testPirate.currentWeapon.examine(), "wicked" );
        assertTrue(testPirate.equip("Za Warudo"));
    }

    @Test
    public void equipTestFirstItem(){
        testPirate.inventory.add(new Sword("Chinese Chicken", 12, "test", "test2"));
        testPirate.inventory.add(new Sword("Za Warudo", 24, "slaps", "wicked"));
        testPirate.inventory.add(new Sword("Dark Necklace", 100, "dark evil energy",
                "twisted"));
        assertTrue(testPirate.equip("Chinese Chicken"));
        assertEquals(testPirate.currentWeapon.getName(), "Chinese Chicken");
        assertEquals(testPirate.currentWeapon.examine(), "test2" );
        assertTrue(testPirate.equip("Chinese Chicken"));
    }

    @Test
    public void checkAmmoSword(){
       Sword chicken = new Sword("Chinese Chicken", 12, "test", "test2");
        testPirate.inventory.add(chicken);
        Gun gun = new Gun("the gun", 24, "slaps", "test", 100);
        testPirate.inventory.add(gun);
        assertEquals("ye don't have any ammo. LUCKILY, this weapon doesn't USE ammo", testPirate.checkAmmo(chicken));
        assertEquals("it has 100 thingies of ammo", testPirate.checkAmmo(gun));
    }
}

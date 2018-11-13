package test;
import exceptions.EmptyRoomException;
import model.Melee;
import ui.Adventure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class AdventureTest {
    private Adventure testAdventure;
    Scanner scanner = new Scanner(System.in);

    @BeforeEach
    public void initialize() throws IOException {
        testAdventure = new Adventure("John", 100, 100);
    }

    @Test
    public void updateRoomTestQuarterpass(){
        testAdventure.captain.pirateX = 0;
        testAdventure.captain.pirateY = 0;
        testAdventure.dungeon.updateRoom(testAdventure.currentPlayer);
        assertEquals(testAdventure.captain.currentRoom, testAdventure.dungeon.quarters);
    }

    @Test
    public void updateRoomTestQuarterfailY(){
        testAdventure.captain.pirateX = 0;
        testAdventure.captain.pirateY = 1;
        testAdventure.dungeon.updateRoom(testAdventure.currentPlayer);
        assertFalse(testAdventure.captain.currentRoom == testAdventure.dungeon.quarters);
    }

    @Test
    public void updateRoomTestQuarterfailX(){
        testAdventure.captain.pirateX = 1;
        testAdventure.captain.pirateY = 0;
        testAdventure.dungeon.updateRoom(testAdventure.currentPlayer);
        assertFalse(testAdventure.captain.currentRoom == testAdventure.dungeon.quarters);
    }

  @Test
    public void emptyRoomTestEmpty(){
      try {
          testAdventure.emptyRoom(testAdventure.captain.currentRoom.stuff);
          fail("Expected EmptyRoomException");
      } catch (EmptyRoomException e) {
      }
  }

    @Test
    public void emptyRoomTestNotEmpty(){
        testAdventure.captain.currentRoom.stuff.put("Melee", new Melee(" ", 0,  " ", " "));
        try {
            testAdventure.emptyRoom(testAdventure.captain.currentRoom.stuff);
        } catch (EmptyRoomException e) {
            fail("Did not expect EmptyRoomException");
        }
    }


}

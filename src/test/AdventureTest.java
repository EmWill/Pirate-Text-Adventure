package test;
import exceptions.EmptyRoomException;
import model.Adventure;
import model.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Scanner;

public class AdventureTest {
    private Adventure testAdventure;
    Scanner scanner = new Scanner(System.in);

    @BeforeEach
    public void initialize() throws IOException {
        testAdventure = new Adventure("John", 100, 100);
    }

    @Test
    public void updateRoomTestQuarterpass(){
        testAdventure.player.pirateX = 0;
        testAdventure.player.pirateY = 0;
        testAdventure.updateRoom();
        assertEquals(testAdventure.current, testAdventure.quarters);
    }

    @Test
    public void updateRoomTestQuarterfailY(){
        testAdventure.player.pirateX = 0;
        testAdventure.player.pirateY = 1;
        testAdventure.updateRoom();
        assertFalse(testAdventure.current == testAdventure.quarters);
    }

    @Test
    public void updateRoomTestQuarterfailX(){
        testAdventure.player.pirateX = 1;
        testAdventure.player.pirateY = 0;
        testAdventure.updateRoom();
        assertFalse(testAdventure.current == testAdventure.quarters);
    }

  @Test
    public void emptyRoomTestEmpty(){
        int i = 1;
      try {
          testAdventure.emptyRoom(testAdventure.current.stuff);
      } catch (EmptyRoomException e) {
          i = 2;
      }
      assertEquals(i, 2);
  }

    @Test
    public void emptyRoomTestNotEmpty(){
        int i = 1;
        testAdventure.current.stuff.add(new Sword(" ", 0,  " ", " "));
        try {
            testAdventure.emptyRoom(testAdventure.current.stuff);
        } catch (EmptyRoomException e) {
            i = 2;
        }
        assertEquals(i, 1);
    }



}

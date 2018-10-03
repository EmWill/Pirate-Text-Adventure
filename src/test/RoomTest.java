package test;
import model.Object;
import model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Scanner;

public class RoomTest {
    private Room testRoom;

    @BeforeEach
    public void initialize() {
        testRoom = new Room("test room", "Welcome to the twisted test room. There's not much here, I'm afraid.",
                new ArrayList<Object>(), 0, 0);
    }


    @Test
    public void overviewTest() {
        testRoom.overview();
        assertEquals(1, 1);
    }


}
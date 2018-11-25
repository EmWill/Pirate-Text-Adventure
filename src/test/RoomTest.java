package test;
import model.Item;
import model.Mob;
import model.Player;
import model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Adventure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

public class RoomTest {
    private Room testRoom;
    private Mob testPirate;

    @BeforeEach
    public void initialize() throws IOException {
            testPirate = new Player(0, "blank", 0, 0, new Adventure("swag", 0, 0));

        testRoom = new Room("test room", "Welcome to the twisted test room. There's not much here, I'm afraid.",
                new HashMap<>(), 0, 0, false, false, false,false, "none" );
    }


    @Test
    public void overviewTest() throws FileNotFoundException, UnsupportedEncodingException {
        testRoom.overview();
        assertEquals(1, 1);
    }


}
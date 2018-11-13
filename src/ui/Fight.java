package ui;

import model.NPC;
import model.Player;
import model.Room;

public class Fight {
    private Player player;
    private NPC monster;
    private  Room room;

    public Fight(Player player, NPC monster, Room room){
        this.player = player;
        this.monster = monster;
        this.room = room;
    }


}

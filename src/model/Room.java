package model;

import java.util.List;

public class Room {
    public String name;
    public String description;
    public List<Object> stuff;
    public int roomX;
    public int roomY;
    public boolean n;
    public boolean w;
    public boolean s;
    public boolean e;
    public Player player;

    public Room(String title, String rundown, List<Object> things, int x, int y, Boolean north, Boolean west,
                Boolean south, Boolean east, Player pirate){
        name = title;
        description = rundown;
        stuff = things;
        roomX = x;
        roomY = y;
        n = north;
        w = west;
        s = south;
        e = east;
        player = pirate;
    }

    public void overview(){
        System.out.println(this.name);
        System.out.println(this.description);
        if(!stuff.isEmpty()) {
            System.out.println(".");
            System.out.println("let's see what's lying about...");
            System.out.println("there's...");
            for (Object o : stuff
                    ) {
                System.out.println(o.examine());

            }
        }
    }


}

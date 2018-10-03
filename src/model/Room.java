package model;

import java.util.List;

public class Room {
    public String name;
    public String description;
    public List<Object> stuff;
    public int roomX;
    public int roomY;

    public Room(String title, String rundown, List<Object> things, int x, int y){
        name = title;
        description = rundown;
        stuff = things;
        roomX = x;
        roomY = y;
    }

    public void overview(){
        System.out.println(this.name);
        System.out.println(this.description);
    }


}

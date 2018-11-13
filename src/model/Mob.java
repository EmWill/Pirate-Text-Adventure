package model;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Mob {
    public String pirateName;
    public Room currentRoom;
    public int pirateX;
    public int pirateY;
    public int pirateHP;
    public Weapon currentWeapon;
    public List<Item> inventory;
    public List<Room> recentlyVisited;

    public Mob(int y, int x, String name) {
        pirateY = y;
        currentRoom = new Room ("error room", "uh oh! you shouldn't be here. tell miles and he'll fix it lol",
                 new HashMap<>(), 69, 420, false, false, false, false,  "none");
        currentWeapon = new Melee("yer own fists", 1, "ya give em a good punch",
                "ye better find a weapon fast.");
        pirateX = x;
        inventory = new ArrayList<Item>();
        pirateHP = 100;
        pirateName = name;
        recentlyVisited = new ArrayList<Room>();
    }

    // REQUIRES: nonempty string
    // MODIFIES: this
    // EFFECTS: changes name of pirate
    public void nameSet(String name)
    {pirateName = name;}

    // EFFECTS: returns pirate's name
    public String getMobName(){
        return pirateName;
    }

    // EFFECTS: returns a list of descriptions of items in captain's inventory or informs them that they have nothing.
    public void getInventory() throws FileNotFoundException, UnsupportedEncodingException {
        if (inventory.isEmpty()){
            System.out.println("ye haven't any belongings");
        }
        else {System.out.println("ye have...");
            for (Item i :inventory)
            { System.out.println(i.getName() + ": " + i.examine()); }
        }
    }

    //EFFECTS: returns remaining ammo of weapon
    public String returnAmmo(Gun theGun){
        return ("it has " + (Integer.toString(theGun.getAmmo()) + " thingies of ammo"));
    }

    public int getPirateX(){
        return pirateX;
    }

    public int getPirateY(){
        return pirateY;
    }

    public void goNorth(){
        this.pirateY++;
    }

    public void goWest(){
        this.pirateX--;
    }

    public void goSouth(){
        this.pirateY--;
    }

    public void goEast(){
        this.pirateX++;
    }


    public void removeObject(Item o){
        if (inventory.contains(o))
            currentRoom.addObject(o);
        inventory.remove(o);
    }





}

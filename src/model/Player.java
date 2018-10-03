package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public int drunkenness;
    public String pirateName;
    public int pirateX;
    public int pirateY;
    public List<Object> inventory;


    public Player(int drunk, String name, int x, int y) {
        drunkenness = drunk;
        pirateName = name;
        pirateX = x;
        pirateY = y;
        inventory = new ArrayList<>();
    }

    // REQUIRES: nonempty string
    // MODIFIES: this
    // EFFECTS: changes name of pirate
    public void nameSet(String name)
    {pirateName = name;}

    // MODIFIES: this
    // EFFECTS: adds or subtracts from pirate's drunkenness and reports change
    public void drink(int amount)
    {if(amount == 0){
        System.out.println("aye.. not feeling up to it?");

    }
    else if (amount < 0)
    {System.out.println("yer spitting it up!?");
    }
    drunkenness += amount;
    System.out.println(pirateName + " ye are " + drunkenness + " percent drunk");
    }
    // stub


    // EFFECTS: returns pirate's name
    public String getPirateName(){
        return pirateName;
    }

    // EFFECTS: returns pirate's level of drunkenness
    public int getDrunkenness(){
        return drunkenness;
    }

    public int getPirateX(){
        return pirateX;
    }

    public int getPirateY(){
        return pirateY;
    }

}
package model;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Player extends Mob {
    public int drunkenness;


    public Player(int drunk, String name, int x, int y) {
        super(y, x, name);
        drunkenness = drunk;

    }

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


    // EFFECTS: returns pirate's level of drunkenness
    public int getDrunkenness(){
        return drunkenness;
    }


    public Boolean equip(String pick){
        for (Item o: inventory
             ) {
            if (pick.equals(o.getName())){
                if (o.canEquip()){
                currentWeapon = (Weapon) o;
                return true;}
                else System.out.println("Ye can't equip that!");
            }

        }
        return false;
    }


    //EFFECTS: checks if a weapon is a gun. if it is, returns its remaining ammo.
    public String checkAmmo(Weapon ammoWeapon){
        if (ammoWeapon.isSword()){
            return "ye don't have any ammo. LUCKILY, this weapon doesn't USE ammo";
        }
        else return (returnAmmo((Gun) ammoWeapon));
    }

    //EFFECTS: tells captain what weapon they're currently wielding
    public void getEquipment() throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("ye be wielding " + currentWeapon.getName());
        System.out.println(currentWeapon.examine());
    }


    public void setRoom(Room r){
        if (!currentRoom.equals(r)){
        currentRoom = r;
        r.addPirate(this);
        }
    }

}
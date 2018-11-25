package model;

import ui.Adventure;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Player extends Mob {
    public int drunkenness;
    private Adventure adventure;


    public Player(int drunk, String name, int x, int y, Adventure adventure) {
        super(y, x, name);
        drunkenness = drunk;
        this.adventure = adventure;

    }

    // MODIFIES: this
    // EFFECTS: adds or subtracts from pirate's drunkenness and reports change
    public void drink(int amount)
    {if(amount == 0){
        adventure.gamePanel.textShift("aye.. not feeling up to it?");

    }
    else if (amount < 0)
    {adventure.gamePanel.textShift("yer spitting it up!?");
    }
    drunkenness += amount;
    adventure.gamePanel.textShift(pirateName + " ye are " + drunkenness + " percent drunk");
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
                else adventure.gamePanel.textShift("Ye can't equip that!");
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
        adventure.gamePanel.textShift("ye be wielding " + currentWeapon.getName());
        adventure.gamePanel.textShift(currentWeapon.examine());
    }

    // EFFECTS: returns a list of descriptions of items in captain's inventory or informs them that they have nothing.
    public void getInventory() throws FileNotFoundException, UnsupportedEncodingException {
        if (inventory.isEmpty()){
            adventure.gamePanel.textShift("ye haven't any belongings");
        }

        else {adventure.gamePanel.textShift("_________");
            adventure.gamePanel.textShift("ye have...");
            for (Item i :inventory)
            { adventure.gamePanel.textShift(i.getName() + ": " + i.examine()); }
        }
    }

    public void setRoom(Room r){
        if (!currentRoom.equals(r)){
        currentRoom = r;
        r.addPirate(this);
        }
    }

}
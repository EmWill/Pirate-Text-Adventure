package model;

import com.sun.xml.internal.bind.v2.runtime.SwaRefAdapter;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Player {
    public int drunkenness;
    public String pirateName;
    public int pirateX;
    public int pirateY;
    public Weapon currentWeapon;
    public List<Object> inventory;


    public Player(int drunk, String name, int x, int y) {
        drunkenness = drunk;
        pirateName = name;
        pirateX = x;
        pirateY = y;
        inventory = new ArrayList<>();
        currentWeapon = new Sword("yer own fists", 1, "ya give em a good punch",
                "ye better find a weapon fast.");
        Weapon darkness = new Sword("testSword", 12, "gives you a big smack",
                "a dark and evil sword");
        Weapon testGun = new Gun("testGun", 1000, "bang", "a rusty revolver",
                6);
        inventory.add(testGun);
        inventory.add(darkness);

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

    // EFFECTS: returns a list of descriptions of items in player's inventory or informs them that they have nothing.
    public void getInventory() throws FileNotFoundException, UnsupportedEncodingException {
        if (inventory.isEmpty()){
            System.out.println("ye haven't any belongings");
        }
        else {System.out.println("ye have...");
            for (Object i :inventory)
            { System.out.println(i.getName() + ": " + i.examine()); }
        }
    }


    public Boolean equip(String pick){
        for (Object o: inventory
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

    //EFFECTS: returns remaining ammo of weapon
    public String returnAmmo(Gun theGun){
        return ("it has " + (Integer.toString(theGun.getAmmo()) + " thingies of ammo"));
    }

    //EFFECTS: tells player what weapon they're currently wielding
    public void getEquipment() throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("ye be wielding " + currentWeapon.getName());
        System.out.println(currentWeapon.examine());
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

}
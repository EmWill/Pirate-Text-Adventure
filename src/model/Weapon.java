package model;

public abstract class Weapon implements Fighter, Object {
    protected String title;
    protected Boolean canObtain;
    protected int slapDamage;
    protected String slapDescription;
    protected String itemDescription;

    public Weapon (String name, int damage, String slapscription, String description){
        title = name;
        canObtain = true;
        slapDamage = damage;
        slapDescription = slapscription;
        itemDescription = description;
    }

    // EFFECTS: prints description of attack and returns damage
    @Override
    public int slap() {
        System.out.println(slapDescription);
        return slapDamage;
    }

    //EFFECTS: returns item description
    @Override
    public String examine() {
        return itemDescription;
    }

    //EFFECTS: returns true if item can be added to player's inventory
    @Override
    public boolean obtainable() {
        return canObtain;
    }

    //EFFECTS: returns weapon's damage stat
    public int getDamage(){
        return slapDamage;
    }

    //EFFECTS: returns weapon's attack description.
    public String getSlapDescription(){
        return slapDescription;
    }

    //EFFECTS: returns weapon's name
    public String getName(){
        return title;
    }

    //EFFECTS: returns true, indicating that the weapon can be equipped
    public boolean canEquip(){
        return true;
    }

    //EFFECTS: returns true if weapon is a sword. returns false if it is a gun.
    public abstract boolean isSword();
}



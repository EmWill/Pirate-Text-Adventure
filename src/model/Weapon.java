package model;

public class Weapon implements Fighter, Object {
    private Boolean canObtain;
    private int slapDamage;
    private String slapDescription;
    private String itemDescription;

    public Weapon (int damage, String slapscription, String description){
        canObtain = true;
        slapDamage = damage;
        slapDescription = slapscription;
        itemDescription = description;
    }

    @Override
    public int slap() {
        System.out.println(slapDescription);
        return slapDamage;
    }

    @Override
    public String examine() {
        return itemDescription;
    }

    @Override
    public boolean obtainable() {
        return canObtain;
    }

    public int getDamage(){
        return slapDamage;
    }

    public boolean getcanObtain(){
        return canObtain;
    }

    public String getSlapDescription(){
        return slapDescription;
    }

    public String getItemDescription(){
        return itemDescription;
    }
}

package model;

public interface Object {
    public String examine(); // returns a description of the object

    public boolean obtainable(); // returns true if object can be added to the player's inventory

    public String getName();

    public boolean canEquip(); // returns true if an object can be equipped as a weapon
}

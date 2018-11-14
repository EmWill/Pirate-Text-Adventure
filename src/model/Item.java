package model;

public interface Item {
    public String examine(); // returns a description of the object

    public boolean obtainable(); // returns true if object can be added to the captain's inventory

    public String getName();

    public void use();

    public boolean canEquip(); // returns true if an object can be equipped as a weapon
}

package model;

public class Sword extends Weapon {

    public Sword(String name, int damage, String slapscription, String description) {
        super(name, damage, slapscription, description);
    }

    // returns true, indicating weapon is a sword.
    @Override
    public boolean isSword() {
        return true;
    }
}

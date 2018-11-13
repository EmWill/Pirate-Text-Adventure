package model;

public class Melee extends Weapon {

    public Melee(String name, int damage, String slapscription, String description) {
        super(name, damage, slapscription, description);
    }

    // returns true, indicating weapon is a sword.
    @Override
    public boolean isSword() {
        return true;
    }
}

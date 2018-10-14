package model;

public class Gun extends Weapon {
    private int ammo;
    public Gun(String name, int damage, String slapscription, String description, int startingAmmo) {
        super(name, damage, slapscription, description);
        ammo = startingAmmo;
    }

    // EFFECTS: returns false, indicating weapon is gun.
    @Override
    public boolean isSword() {
        return false;
    }

    // EFFECTS: returns count of remaining ammo
    public int getAmmo(){
        return ammo;
    }
}

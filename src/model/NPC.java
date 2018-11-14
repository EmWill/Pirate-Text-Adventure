package model;

import java.util.Objects;

public class NPC extends Mob implements Fighter {
    public String trace;
    public String description;
    public Boolean hostile;
    public NPC(int y, int x, String name, String trace, String description, Boolean hostile, Weapon currentWeapon) {
        super(y, x, name);
        this.trace = trace;
        this.description = description;
        this.hostile = hostile;
    }

    @Override
    public int slap() {
        return 0;
    }

    public void addPlacesBeen(Room r){
        if (!recentlyVisited.contains(r)){
            recentlyVisited.add(r);
            r.addMobTrace(this);
        }
    }

    public void removePlacesBeen(Room r){
        if (recentlyVisited.contains(r)){
            recentlyVisited.remove(r);
            r.removeMobTrace(this);
        }
    }


    public void setRoom(Room r){if (!currentRoom.equals(r)){
        currentRoom = r;
        r.addMob(this);
        addPlacesBeen(r);
        if (recentlyVisited.size() >= 3){removePlacesBeen(recentlyVisited.get(0));}
    }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NPC npc = (NPC) o;
        return Objects.equals(trace, npc.trace) &&
                Objects.equals(description, npc.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(trace, description);
    }


}

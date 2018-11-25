package model;

import ui.Adventure;
import ui.BetterGamePanel;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class Room {
    public String name;
    public String description;
    public Map<String, Item> stuff;
    private List<Player> pirates;
    private List<NPC> goons;
    private List<NPC> goonTrace;
    public int roomX;
    public int roomY;
    public boolean n;
    public boolean w;
    public boolean s;
    public boolean e;
    public String trigger;
    private BetterGamePanel gamePanel;
    private Adventure adventure;

    public Room(String title, String rundown, Map<String, Item> things, int x, int y, Boolean north, Boolean west,
                Boolean south, Boolean east, String trigger){
        name = title;
        description = rundown;
        stuff = things;
        roomX = x;
        roomY = y;
        n = north;
        w = west;
        s = south;
        e = east;
        this.trigger = trigger;
        pirates = new ArrayList<Player>();
        this.goonTrace = new ArrayList<NPC>();
        this.goons = new ArrayList<NPC>();
    }

    public void setGamePanel(BetterGamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void overview() throws FileNotFoundException, UnsupportedEncodingException {
        if (trigger == "none") {
            gamePanel.textShift("^^^^^^^^^^^^^^^^");
            gamePanel.textShift(this.name);
            gamePanel.textShift(this.description);
            if (!stuff.isEmpty()) {
                gamePanel.textShift(".");
                gamePanel.textShift("let's see what's lying about...");
                gamePanel.textShift("there's...");
                for (String s : stuff.keySet()
                        ) {
                    String description = stuff.get(s).examine();
                    gamePanel.textShift(s + ": " + description);

                }
            }

            gamePanel.textShift("___");

            if (pirates.contains(adventure.captain) && adventure.currentPlayer.equals(adventure.firstMate)){
                gamePanel.textShift("Hey! Your captain, " + adventure.captain.pirateName + " is here!");
            }
            if (pirates.contains(adventure.firstMate) && adventure.currentPlayer.equals(adventure.captain)){
                gamePanel.textShift("Your loyal first mate, " + adventure.firstMate.pirateName + " is here!");

            }

            for (NPC goon: goonTrace
                 ) {gamePanel.textShift(goon.trace);

            }

            for (Mob mob: goons
                    ) if (mob instanceof NPC){
            {gamePanel.textShift(((NPC) mob).description);}

            }
        }
        else if (trigger == "firstMate"){
            trigger = "none";
adventure.firstMateIntro();
        }

        adventure.ratCheck();
    }

    public void addObject(Item o){
            stuff.put(o.getName(), o);
    }


    public void setAdventure(Adventure a){
        adventure = a;
    }

    public void addPirate(Player p){
        if (!pirates.contains(p)){
            pirates.add(p);
            p.setRoom(this);
            p.pirateX = this.roomX;
            p.pirateY = this.roomY;
        }
    }

    public void removePirate(Player p){
        if (pirates.contains(p)){
            pirates.remove(p);
        }
    }

    public void removeMob (Mob mob){
        if (goons.contains(mob)){
            goons.remove(mob);
        }
    }

    public void addMob(NPC n){
        if (!goons.contains(n)){
            goons.add(n);
            n.setRoom(this);
            n.pirateX = this.roomX;
            n.pirateY = this.roomY;
        }
    }

    public void addMobTrace(NPC m){
        if (!goonTrace.contains(m)){
            goonTrace.add(m);
            m.addPlacesBeen(this);
        }
    }

    public void removeMobTrace(NPC m) {
        if (goonTrace.contains(m)){
            goonTrace.remove(m);
            m.removePlacesBeen(this);
        }
    }

    public boolean containsByName(String name){
        boolean contains = false;
        for (NPC npc: goons
             ) {if (npc.getMobName() == name){
                 contains = true;
        }

        }
        return contains;
    }

    public boolean itemMatch(String s){
        for (String key: stuff.keySet()
                ) {if (s.equals(key)){ return true;}

        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(name, room.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}

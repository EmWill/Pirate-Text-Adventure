package model;

import api.ReadWebPageEx;
import api.WeatherParser;
import org.json.JSONException;
import ui.Adventure;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class Dungeon {
    public Room quarters;
    public Room longhall;
    public Room hallEnd;
    public ArrayList<Room> map;
    public List<NPC> goons;
    private Random rand = new Random();
    private ReadWebPageEx reader = new ReadWebPageEx();
    private WeatherParser weatherParser = new WeatherParser();
    private Adventure adventure;
    private Boolean raining;

    public Dungeon(Adventure adventure) throws IOException {
        this.adventure = adventure;
        //Establish whether or not it's raining:
        rainSearch();
        //List of goons incoming:
        Weapon claws = new Melee("Rat's Claws", 0, "A brutal slash!",
                "A sharp pair of claws");
        NPC rat = new NPC(1, 0, "Ratta 2E.", "There are some nuts and bolts on the floor...",
                "A perfect replica of a rat stands before you... stay on guard.", false, claws);

        // List of objects for longhall
        Map<String, Item> longhlallList = new HashMap<>();
        longhlallList.put("rusty sword", new Melee("rusty sword", 0, "i'm surprised you're even " +
                "holding this thing", "a useless rusty sword..." +
                " god i hate that useless rusty sword"));

        quarters = new Room("Captain's Quarters", "You stand in your luxurious quarters. To your north is" +
                " a long stretch of hallway. You begin to wish you hadn't installed that labyrinth between you and" +
                " the upper deck.", new HashMap<>(), 0, 0,
                true,
                false,
                false,
                false,
                "none");

        longhall = new Room("loooong hallway", "something about this place gives you an itch" +
                " in your back that you can't quite reach. It will take some time to reach the other end of the hall." +
                " Make sure you're ready.",
                longhlallList, 0, 1,
                true,
                false,
                true,
                false,
                "none");
        if (!raining){
        hallEnd = new Room("end of long hallway", "You stand before a large chasm. On the northern side"
                + " stands a door could lead you one step closer to freedom. You have no way to reach it. Climbing "
                + "down the chasm, you see a locked door to the west. If only you had a key..."
                , new HashMap<>(), 0, 2,
                false,
                false ,
                true ,
                false,
                "firstMate");}
                else {hallEnd = new Room("end of long hallway", "Rain floods the chasm in front of you. "
        + "The path is now clear.",
                new HashMap<>(), 0, 2,
                false,
                false, false,
                false,
                "firstMate"); }
        map = new ArrayList<Room>();
        goons = new ArrayList<NPC>();
        addRoom(quarters);
        addRoom(longhall);
        addRoom(hallEnd);
        goons.add(rat);
    }


    // EFFECTS: updates current room to the room matching the captain's coordinates
    // MODIFIES: currentPlayer
    public void updateRoom(Player player){
        for (Room r: map
                ) {
            if (r.roomX == player.pirateX){
                if (r.roomY == player.pirateY){
                    player.currentRoom.removePirate(player);
                    player.setRoom(r);
                }
            }
        }
    }

    // EFFECTS: Changes NPC's current room
    // MODIFIES: NPC
    public void updateRoomMob(NPC n){
        for (Room r: map
                ) {
            if (r.roomX == n.pirateX){
                if (r.roomY == n.pirateY){
                    n.currentRoom.removeMob(n);
                    n.setRoom(r);
                }
            }

        }
    }

    // for all goX's
    // EFFECTS: Moves captain in the desired direction, then provides an overview of the new current room.
    // otherwise, informs captain if path is blocked.
    // MODIFIES: captain
    public void goNorth(Player player) throws FileNotFoundException, UnsupportedEncodingException {
        if(player.currentRoom.n){
            player.goNorth();
            updateRoom(player);
            player.currentRoom.overview();}
        else{System.out.println("Aye! that path be blocked!");}
        advanceTime();
    }

    public void goSouth(Player player) throws FileNotFoundException, UnsupportedEncodingException {
        if(player.currentRoom.s){
            player.goSouth();
            updateRoom(player);
            player.currentRoom.overview();}
        else{System.out.println("Aye! That path be blocked!");}
        advanceTime();
    }

    public void goWest(Player player) throws FileNotFoundException, UnsupportedEncodingException {
        if(player.currentRoom.w){
            player.goWest();
            updateRoom(player);
            player.currentRoom.overview();}
        else{System.out.println("Aye! That path be blocked!");}
        advanceTime();
    }

    public void goEast(Player player) throws FileNotFoundException, UnsupportedEncodingException {
        if(player.currentRoom.e){
            player.goEast();
            updateRoom(player);
            player.currentRoom.overview();}
        else{System.out.println("Aye! That path be blocked!");}
        advanceTime();
    }



    // NOTE: this is the only method in this class that requires the adventure field.
    // Its only purpose is to give each room access to adventure, in the case that the player's UI has unique
    // interactions with particular rooms
    // Should this be changed? Everything else in this class is A-okay with coupling and cohesion

    // EFFECTS: adds room to game map
    // does serve the purpose of giving every room access to the adventure class
    private void addRoom(Room room){
        if(!map.contains(room)){
            map.add(room);
            room.setAdventure(adventure);
        }
    }


    // EFFECTS: Updates all NPC behavior.
    private void advanceTime(){
        for (NPC goon: goons
                ) {findPath(goon);

        }

    }
    // TODO: Fixed coupling issues. Could this be simplified further?
    // EFFECTS: Generates and executes path for an NPC
    // MODIFIES: NPC, Room
    private void findPath(NPC npc){
        int i = oneDFour();
        if (i == 1){
            npcRolledNorth(npc);
        }
        if (i == 2){
            npcRolledWest(npc);
        }
        if (i == 3){
            npcRolledSouth(npc);
        }
        if (i == 4) {
            npcRolledEast(npc);
        }
    }

    private void npcRolledNorth(NPC npc){
        if (npc.currentRoom.n){
            npc.goNorth();
            updateRoomMob(npc);
        }
        else if (npc.currentRoom.s){
            npc.goSouth();
            updateRoomMob(npc);
        }
    }

    private void npcRolledWest(NPC npc){
        if (npc.currentRoom.w){
            npc.goWest();
            updateRoomMob(npc);
        }
        else if (npc.currentRoom.e){
            npc.goEast();
            updateRoomMob(npc);
        }
    }

    private void npcRolledSouth(NPC npc){
        if (npc.currentRoom.s){
            npc.goSouth();
            updateRoomMob(npc);
        }
        else if (npc.currentRoom.n){
            npc.goNorth();
            updateRoomMob(npc);
        }
    }

    private void npcRolledEast(NPC npc){
        if (npc.currentRoom.e){
            npc.goEast();
            updateRoomMob(npc);
        }
        else if (npc.currentRoom.w){
            npc.goWest();
            updateRoomMob(npc);
        }
    }

    public int oneDFour(){
        return (rand.nextInt(4 - 1 + 1) + 1);
    }

    //EFFECTS: returns current weather in vancouver
    public String returnWeather(){
        try {
            return weatherParser.parseArray(reader.returnData());
        } catch (JSONException e) {
            System.out.println("Uh oh! Something went wrong! Don't worry! You can keep playing, but the weather " +
                    "functionality is not working.");
            return "Ain't no sunshine";
        } catch (IOException e) {
            System.out.println("Uh oh! Something went wrong! Don't worry! You can keep playing, but the weather " +
                    "functionality is not working.");
            return "Ain't no sunshine";
        }
    }

    //MODIFIES: this
    //EFFECTS: if it's raining in vancouver, makes it rain in the dungeon
    public void rainSearch(){
        String weather = returnWeather();
        String search = "rain";

        if (weather.toLowerCase().indexOf(search.toLowerCase()) != -1 ){
            raining = true;
            System.out.println("Aye, it's a rainy day...");
        }
        else {
            raining = false;
            System.out.println("Clear skies...");
        }

    }





}
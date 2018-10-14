package model;
import exceptions.EmptyRoomException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Adventure {
    Scanner scanner = new Scanner(System.in);
    public Player player;
    public Room current;
    public Room quarters;
    public Room longhall;
    public ArrayList<Room> map;
    List<String> file1 = Files.readAllLines(Paths.get("save1.txt"));
    List<String> file2 = Files.readAllLines(Paths.get("save2.txt"));
    List<String> file3 = Files.readAllLines(Paths.get("save3.txt"));






    public Adventure(String startingName, int startingX, int startingY) throws IOException {
        player = new Player(0, startingName,
                startingX,
                startingY);

        // List of objects for longhall
        ArrayList<Object> longhlallList = new ArrayList<Object>();
        longhlallList.add(new Sword("rusty useless thing", 0, "i'm surprised you're even " +
                "holding this thing", "a useless rusty sword..." +
                " god i hate that useless rusty sword"));


        current = new Room ("error room", "uh oh! you shouldn't be here. tell miles and he'll fix it lol",
                new ArrayList<Object>(), 69, 420, false, false, false, false, player);

        quarters = new Room("Captain's Quarters", "You stand in your luxurious quarters. To your north is" +
                " a long stretch of hallway. You begin to wish you hadn't installed that labyrinth between you and" +
                " the upper deck.", new ArrayList<Object>(), 0, 0,
                true,
                false,
                false,
                false,
                player);

        longhall = new Room("loooong hallway", "something about this place gives you an itch" +
                " in your back that you can't quite reach. there's something ahead, but miles hasn't added it yet.",
                longhlallList, 0, 1,
                false,
                false,
                true,
                false,
                player);
        map = new ArrayList<Room>();
        map.add(quarters);
        map.add(longhall);
    }


    public void start() throws FileNotFoundException, UnsupportedEncodingException {
        if (player.pirateX == -100){
            System.out.print("Hello " +player.pirateName);
            System.out.print("! You are " +player.drunkenness);
            System.out.println(" percent drunk... Wait! That's a terrible name! What's your real name?");
            levelOne();
        }
        else {updateRoom();
            current.overview();
        while(!choose()){}
    }}

    public void levelOne() throws FileNotFoundException, UnsupportedEncodingException {
        while(!namepicker()){}
    }

 private boolean namepicker() throws FileNotFoundException, UnsupportedEncodingException {
        String choice = "";
        choice = scanner.nextLine();
        if (choice.equals("fuck")){
            System.out.println("That's vulgar! Pick something else!");
            return false;
        }
        else
        {player.pirateName = choice;
     System.out.println(player.pirateName +" is a much better name! Unfortunately," +
             " you are a pirate whose ship is sinking, and I don't think you're quite drunk enough for the job!" +
             " How much do you want to drink?");
        while(!firstdrink()){}}
        return true;
 }

    private boolean firstdrink() throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("AYE.. ye best be selecting a reasonable integarrrrrr");
        int quantity = 0;
        if (notInt()){
        quantity = scanner.nextInt();
        player.drink(quantity);
        if (player.getDrunkenness() <= 0)
        {System.out.println("Are you KIDDING me?");
        System.out.println("GAME OVER");
        }
        else if (player.getDrunkenness() >= 1000)
        {System.out.println(player.getPirateName() +"... please... slow down");
        System.out.println("GAME OVER");
        return true;}
        else if (player.getDrunkenness() >= 100)
        {System.out.println("Whoa! That's a lot.");
        System.out.println("SECRET END");
        return true;}
        else if (player.getDrunkenness() < 10)
        {System.out.println("More than that!");
        return false;}
        else {System.out.println(player.getPirateName() + "....");
        System.out.println("YOUR SINK IS SHIPPING!");
        System.out.println("...and you're just durnk enough to pull this off...");
        player.pirateX = 0;
        player.pirateY = 0;
        updateRoom();
    current.overview();
    scanner.nextLine();
        while (!choose());}
        return true;}
    else System.out.println("HEY! that's not a number!");
        return false;}

    // EFFECTS: returns false if next scanner input isn't an integer
    private boolean notInt(){
        if (!scanner.hasNextInt())
        { scanner.nextLine();
        return false;}
    return true;}

    // EFFECTS: updates current room to the room matching the player's coordinates
    // MODIFIES: this
    public void updateRoom(){
        for (Room r: map
             ) {
            if (r.roomX == player.pirateX){
                if (r.roomY == player.pirateY){
                    this.current = r;
                }
            }
        }
    }

    // EFFECTS: saves player's progress on whichever file they choose.
    // MODIFIES: save1.txt, save2.txt, save3.txt
    private void save() throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("Please select a file:");
        System.out.println("File 1: " + file1.get(0));
        System.out.println("File 2: " + file2.get(0));
        System.out.println("File 3: " + file3.get(0));
        String choice = scanner.nextLine();
        if (choice.equals("1") || choice.equals(file1.get(0)) || choice.equals("File 1") || choice.equals("file 1")) {
            PrintWriter writer = new PrintWriter("save1.txt", "UTF-8");
            writer.println(player.getPirateName());
            writer.println(Integer.toString(player.pirateX));
            writer.println(Integer.toString(player.pirateY));
            writer.close();
            System.out.println("Yer game be saved!");
        }
        else if (choice.equals("2") || choice.equals(file2.get(0)) || choice.equals("File 2") || choice.equals("file 2")) {
            PrintWriter writer = new PrintWriter("save2.txt", "UTF-8");
            writer.println(player.getPirateName());
            writer.println(Integer.toString(player.pirateX));
            writer.println(Integer.toString(player.pirateY));
            writer.close();
            System.out.println("Yer game be saved!");
        }
        else if (choice.equals("3") || choice.equals(file3.get(0)) || choice.equals("File 3") || choice.equals("file 3")) {
            PrintWriter writer = new PrintWriter("save3.txt", "UTF-8");
            writer.println(player.getPirateName());
            writer.println(Integer.toString(player.pirateX));
            writer.println(Integer.toString(player.pirateY));
            writer.close();
            System.out.println("Yer game be saved!");
        }
        else {System.out.println("Changed yer mind? Or did ye spell something wrong?"); }
        while (!choose());

    }

    // EFFECTS: allows player to choose an action from the list.
    private boolean choose() throws FileNotFoundException, UnsupportedEncodingException {
        String choice;
        System.out.println("Aye.. what will ye do next?");
        System.out.println("(type ? for help)");
        choice = scanner.nextLine();
        if (choice.equals("?")){help();}
        else if (choice.equals("n")){goNorth();}
        else if (choice.equals("s")){goSouth();}
        else if (choice.equals("w")){goWest();}
        else if (choice.equals("e")){goEast();}
        else if (choice.equals("save")){save();}
        else if (choice.equals("look")){current.overview(); while(!choose());}
        else if (choice.equals("inventory")){player.getInventory(); while (!choose());}
        else if (choice.equals("weapon")){player.getEquipment(); while (!choose());}
        else if (choice.equals("equip")){equipSelect();}
        else if (choice.equals("ammo")){System.out.println(player.checkAmmo(player.currentWeapon)); while (!choose());}
        else if (choice.equals("get")){
            try {
                get();
            } catch (EmptyRoomException e) {
                System.out.println("There's nary a thing to be gotten!");

            }
        finally {while (!choose());}}
        else {System.out.println("I don't know that guy... Did you spell something wrong?");
            return false;}
        return true;

    }

    // EFFECTS: displays a list of controls for the player
    private void help() throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("?: help");
        System.out.println("n: go north");
        System.out.println("s: go south");
        System.out.println("w: go west");
        System.out.println("e: go east");
        System.out.println("save: save");
        System.out.println("look: read description again");
        System.out.println("inventory: displays your inventory");
        System.out.println("weapon: check yer current weapon");
        System.out.println("equip: equip a new weapon");
        System.out.println("ammo: check yer equipped weapon's ammo");
        while (!choose());
    }

    // EFFECTS: change's player's equipped weapon based on player choice
    // MODIFIES: player
    private void equipSelect() throws FileNotFoundException, UnsupportedEncodingException {
        String pick;
        System.out.println("What from yer inventory do ye want to equip?");
        pick = scanner.nextLine();
        if (player.equip(pick)){
            player.equip(pick);
            System.out.println("Ye've equipped " + pick);
            while(!choose());
        }
        else if (!player.equip(pick)) {System.out.println("Uh, did ye spell it right?");
        while(!choose());}
    }

    //EFFECTS: lets players choose from a list of available items to obtain
    //MODIFIES: player, current room.
    private void get() throws FileNotFoundException, UnsupportedEncodingException, EmptyRoomException {
        emptyRoom(current.stuff);
            int i = 0;
            System.out.println("What do ye want to get? Select a number (if ye want to cancel, just type any letter!).");
            for (Object o:current.stuff
                 ) {
                i++;
                System.out.println(Integer.toString(i) + ": " + o.getName());
            }
            if (notInt()){
            int choice = scanner.nextInt();
            if (choice > 0 && choice <= i){
                Object thing = current.stuff.get(choice - 1);
                if (thing.obtainable()){
                    System.out.println("Ye get " + (thing.getName()));
                player.inventory.add(current.stuff.get(choice - 1));
                current.stuff.remove(choice - 1); }
                else System.out.println("Ye can't fit that in yer pockets!");
                scanner.nextLine();

            }
            else {System.out.println("This room doesn't have that many items!");
            }}
            else System.out.println("Changed yer mind?");

        }

    // for all goX's
    // EFFECTS: Moves player in the desired direction, then provides an overview of the new current room.
    // otherwise, informs player if path is blocked.
    // MODIFIES: player
    private void goNorth() throws FileNotFoundException, UnsupportedEncodingException {
        if(this.current.n){
        player.goNorth();
        updateRoom();
        current.overview();}
        else{System.out.println("Aye! that path be blocked!");}
        while (!choose());
    }

    private void goSouth() throws FileNotFoundException, UnsupportedEncodingException {
       if(this.current.s){
           player.goSouth();
        updateRoom();
        current.overview();}
        else{System.out.println("Aye! That path be blocked!");}
        while (!choose());
    }

    private void goWest() throws FileNotFoundException, UnsupportedEncodingException {
        if(this.current.w){
            player.goWest();
            updateRoom();
            current.overview();}
        else{System.out.println("Aye! That path be blocked!");}
        while (!choose());
    }

    private void goEast() throws FileNotFoundException, UnsupportedEncodingException {
        if(this.current.e){
            player.goEast();
            updateRoom();
            current.overview();}
        else{System.out.println("Aye! That path be blocked!");}
        while (!choose());
    }

    //EFFECTS: throws EmptyRoomException if List<Object> is empty.
    public void emptyRoom(List<Object> room) throws EmptyRoomException{
        if (!(room.size() > 0)){
            throw new EmptyRoomException();
        }

    }


    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList(Arrays.asList(splits));
    }



}

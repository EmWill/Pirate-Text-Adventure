package ui;
import exceptions.EmptyRoomException;
import model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Adventure implements ActionListener {
    Scanner scanner = new Scanner(System.in);
    public Player captain;
    public Player firstMate;
    public Player currentPlayer;
    public Dungeon dungeon;
    public BetterGamePanel gamePanel;
    List<String> file1 = Files.readAllLines(Paths.get("save1.txt"));
    List<String> file2 = Files.readAllLines(Paths.get("save2.txt"));
    List<String> file3 = Files.readAllLines(Paths.get("save3.txt"));



    public Adventure(String startingName, int startingX, int startingY, BetterGamePanel gamePanel) throws IOException {
        captain = new Player(0, startingName,
                startingX,
                startingY);
        firstMate = new Player(0, "Blank", 0, 2);

        Weapon darkness = new Melee("testSword", 1, "gives you a big smack",
                "a dark and evil sword");
        Weapon testGun = new Gun("testGun", 2, "bang", "a rusty revolver",
                6);
        captain.inventory.add(testGun);
        captain.inventory.add(darkness);
        currentPlayer = captain;
        this.dungeon = new Dungeon(this);
        this.gamePanel = gamePanel;
        gamePanel.textField.addActionListener(this);
    }


    public void start() throws FileNotFoundException, UnsupportedEncodingException {
        for (NPC goon: dungeon.goons
             ) {dungeon.updateRoomMob(goon);

        }
        if (captain.pirateX == -100){
            System.out.print("Hello " + captain.pirateName);
            System.out.print("! You are " + captain.drunkenness);
            System.out.println(" percent drunk... Wait! That's a terrible name! What's your real name?");
            levelOne();
        }
        else {dungeon.updateRoom(currentPlayer);
            currentPlayer.currentRoom.overview();
        while(!choose()){}
    }}

    public void levelOne() throws FileNotFoundException, UnsupportedEncodingException {
        while(!namepicker()){}
        while (!firstdrink()) {}
        }


 private boolean namepicker() throws FileNotFoundException, UnsupportedEncodingException {
        String choice = "";
        choice = scanner.nextLine();
        if (choice.equals("fuck")){
            System.out.println("That's vulgar! Pick something else!");
            return false;
        }
        else
            if (currentPlayer.equals(captain))
        {
            currentPlayer.pirateName = choice;
     System.out.println(currentPlayer.pirateName +" is a much better name! Unfortunately," +
             " you are a pirate captain whose ship is sinking, and I don't think you're quite drunk enough for the job!" +
             " How much do you want to drink?");
       }
        return true;
 }

    private boolean firstdrink() throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("AYE.. ye best be selecting a reasonable integarrrrrr");
        int quantity = 0;
        if (notInt()){
        quantity = scanner.nextInt();
        currentPlayer.drink(quantity);
        if (currentPlayer.getDrunkenness() <= 0)
        {System.out.println("Are you KIDDING me?");
        System.out.println("GAME OVER");
        }
        else if (currentPlayer.getDrunkenness() >= 1000)
        {System.out.println(currentPlayer.getMobName() +"... please... slow down");
        System.out.println("GAME OVER");
        return true;}
        else if (currentPlayer.getDrunkenness() >= 100)
        {System.out.println("Whoa! That's a lot.");
        System.out.println("SECRET END");
        return true;}
        else if (currentPlayer.getDrunkenness() < 10)
        {System.out.println("More than that!");
        return false;}
        else {System.out.println(currentPlayer.getMobName() + "....");
        System.out.println("YOUR SINK IS SHIPPING!");
        System.out.println("...and you're just durnk enough to pull this off...");
        currentPlayer.pirateX = 0;
        currentPlayer.pirateY = 0;
        dungeon.updateRoom(currentPlayer);
    currentPlayer.currentRoom.overview();
    scanner.nextLine();
        while (!choose());}
        return true;}
    else System.out.println("HEY! that's not a number!");
        return false;}

        public void firstMateIntro() throws FileNotFoundException, UnsupportedEncodingException {
        currentPlayer = firstMate;
        System.out.println("WOW! This is a long hall. (Hit enter to continue)");
        scanner.nextLine();
        System.out.println(".");
            scanner.nextLine();
            System.out.println(".");
            scanner.nextLine();
            System.out.println(".");
            scanner.nextLine();
            System.out.println("Anyways... remember your first mate?");
            scanner.nextLine();
            System.out.println("They were a cool pirate, dare I say.");
            scanner.nextLine();
            System.out.println("With the ability to climb objects, and fit into " +
                    "small spaces, I wonder if they'll be useful on your quest?");
            scanner.nextLine();
            System.out.println("Just remember, they're terrified of... everything");
            scanner.nextLine();
            System.out.println("Let's pan over to them now! What was their name again?");
            while(!namepicker()){}
            dungeon.updateRoom(currentPlayer);
            currentPlayer.currentRoom.overview();
        }

    // EFFECTS: returns false if next scanner input isn't an integer
    private boolean notInt(){
        if (!scanner.hasNextInt())
        { scanner.nextLine();
        return false;}
    return true;}


    // EFFECTS: saves captain's progress on whichever file they choose.
    // MODIFIES: save1.txt, save2.txt, save3.txt
    private void save() throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("Please select a file:");
        System.out.println("File 1: " + file1.get(0));
        System.out.println("File 2: " + file2.get(0));
        System.out.println("File 3: " + file3.get(0));
        String choice = scanner.nextLine();
        if (choice.equals("1") || choice.equals(file1.get(0)) || choice.equals("File 1") || choice.equals("file 1")) {
            PrintWriter writer = new PrintWriter("save1.txt", "UTF-8");
            writer.println(captain.getMobName());
            writer.println(Integer.toString(captain.pirateX));
            writer.println(Integer.toString(captain.pirateY));
            writer.close();
            System.out.println("Yer game be saved!");
        }
        else if (choice.equals("2") || choice.equals(file2.get(0)) || choice.equals("File 2") || choice.equals("file 2")) {
            PrintWriter writer = new PrintWriter("save2.txt", "UTF-8");
            writer.println(captain.getMobName());
            writer.println(Integer.toString(captain.pirateX));
            writer.println(Integer.toString(captain.pirateY));
            writer.close();
            System.out.println("Yer game be saved!");
        }
        else if (choice.equals("3") || choice.equals(file3.get(0)) || choice.equals("File 3") || choice.equals("file 3")) {
            PrintWriter writer = new PrintWriter("save3.txt", "UTF-8");
            writer.println(captain.getMobName());
            writer.println(Integer.toString(captain.pirateX));
            writer.println(Integer.toString(captain.pirateY));
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
        else if (choice.equals("n")){dungeon.goNorth(currentPlayer); while (!choose()){}}
        else if (choice.equals("s")){dungeon.goSouth(currentPlayer); while (!choose()){}}
        else if (choice.equals("w")){dungeon.goWest(currentPlayer); while (!choose()){}}
        else if (choice.equals("e")){dungeon.goEast(currentPlayer); while (!choose()){}}
        else if (choice.equals("save")){save();}
        else if (choice.equals("look")){
            currentPlayer.currentRoom.overview(); while(!choose());}
        else if (choice.equals("inventory")){
            currentPlayer.getInventory(); while (!choose());}
        else if (choice.equals("weapon")){
            currentPlayer.getEquipment(); while (!choose());}
        else if (choice.equals("equip")){equipSelect();}
        else if (choice.equals("ammo")){System.out.println(captain.checkAmmo(captain.currentWeapon)); while (!choose());}
        else if (choice.equals("use")){
            try {
                use();
            } catch (EmptyRoomException e) {
                System.out.println("There's nary a thing to use!");
            }
            finally {
                while (!choose());}}
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

    // EFFECTS: displays a list of controls for the captain
    private void help() throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("?: Help");
        System.out.println("n: Go north");
        System.out.println("s: Go south");
        System.out.println("w: Go west");
        System.out.println("e: Go east");
        System.out.println("save: Save");
        System.out.println("look: Read description again");
        System.out.println("inventory: Displays your inventory");
        System.out.println("weapon: Check yer current weapon");
        System.out.println("equip: Equip a new weapon");
        System.out.println("ammo: Check yer equipped weapon's ammo");
        System.out.println("get: Pick an item up");
        while (!choose());
    }

    // EFFECTS: change's captain's equipped weapon based on captain choice
    // MODIFIES: captain
    private void equipSelect() throws FileNotFoundException, UnsupportedEncodingException {
        String pick;
        System.out.println("What from yer inventory do ye want to equip?");
        pick = scanner.nextLine();
        if (currentPlayer.equip(pick)){
            currentPlayer.equip(pick);
            System.out.println("Ye've equipped " + pick);
            while(!choose());
        }
        else if (!currentPlayer.equip(pick)) {System.out.println("Uh, did ye spell it right?");
        while(!choose());}
    }

    //EFFECTS: lets players choose from a list of available items to obtain
    //MODIFIES: captain, current room.
    private void get() throws FileNotFoundException, UnsupportedEncodingException, EmptyRoomException {
        emptyRoom(currentPlayer.currentRoom.stuff); // checks to see if EmptyRoomException needs to be thrown
            System.out.println("What do ye want to get? (if ye want to cancel, just type any letter!).");
            String choice = scanner.nextLine();
            if (currentPlayer.currentRoom.itemMatch(choice)){
                Item thing = currentPlayer.currentRoom.stuff.get(choice);
                if (thing.obtainable()){
                    System.out.println("Ye get " + (thing.getName()));
                currentPlayer.inventory.add(currentPlayer.currentRoom.stuff.get(choice));
                currentPlayer.currentRoom.stuff.remove(choice); }
                else {System.out.println("Ye can't fit that in yer pockets!");
                scanner.nextLine();}

            }
            else System.out.println("Changed yer mind?");

        }

        private void use() throws EmptyRoomException {
            emptyRoom(currentPlayer.currentRoom.stuff);
        System.out.println("What do ye want to use?");
        String choice = scanner.nextLine();
        if (currentPlayer.currentRoom.itemMatch(choice)){
            Item thing = currentPlayer.currentRoom.stuff.get(choice);
            thing.use();
        }

        }


    //EFFECTS: throws EmptyRoomException if List<Item> is empty.
    public void emptyRoom(Map<String, Item> roomStuff) throws EmptyRoomException{
        if (!(roomStuff.size() > 0)){
            throw new EmptyRoomException();
        }

    }


    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList(Arrays.asList(splits));
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

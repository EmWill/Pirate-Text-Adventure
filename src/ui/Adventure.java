package ui;
import exceptions.EmptyRoomException;
import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Adventure extends JFrame implements ActionListener {
    Scanner scanner = new Scanner(System.in);
    private String operation;
    public Player captain;
    public Player firstMate;
    public Player currentPlayer;
    public Dungeon dungeon;
    private BetterGamePanel gamePanel;
    List<String> file1 = Files.readAllLines(Paths.get("save1.txt"));
    List<String> file2 = Files.readAllLines(Paths.get("save2.txt"));
    List<String> file3 = Files.readAllLines(Paths.get("save3.txt"));



    public Adventure(String startingName, int startingX, int startingY) throws IOException {

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
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.gamePanel = new BetterGamePanel();
//        add(gamePanel);
//        setVisible(true);
//        pack();
//        gamePanel.textField.addActionListener(this);
        operation = "choose";

    }

    public void setPanel(BetterGamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void testMethod(){
        gamePanel.textShift("swag");
    }






    public void start() throws FileNotFoundException, UnsupportedEncodingException {
        for (NPC goon: dungeon.goons
             ) {dungeon.updateRoomMob(goon);

        }
        if (captain.pirateX == -100){
            System.out.print("Hello " + captain.pirateName);
            System.out.print("! You are " + captain.drunkenness);
            gamePanel.textShift(" percent drunk... Wait! That's a terrible name! What's your real name?");
//            levelOne();
        }
        else {dungeon.updateRoom(currentPlayer);
            currentPlayer.currentRoom.overview();
//            while(!choose()){}
            // choose();
            gamePanel.textShift("Aye.. what will ye do next?");
            gamePanel.textShift("(type ? for help)");
    }}

//    public void levelOne() throws FileNotFoundException, UnsupportedEncodingException {
//        while(!namepicker()){}
//        while (!firstdrink()) {}
//        }


 private boolean namepicker() throws FileNotFoundException, UnsupportedEncodingException {
        String choice = "";
        choice = scanner.nextLine();
        if (choice.equals("fuck")){
            gamePanel.textShift("That's vulgar! Pick something else!");
            return false;
        }
        else
            if (currentPlayer.equals(captain))
        {
            currentPlayer.pirateName = choice;
     gamePanel.textShift(currentPlayer.pirateName +" is a much better name! Unfortunately," +
             " you are a pirate captain whose ship is sinking, and I don't think you're quite drunk enough for the job!" +
             " How much do you want to drink?");
       }
        return true;
 }

//    private boolean firstdrink() throws FileNotFoundException, UnsupportedEncodingException {
//        gamePanel.textShift("AYE.. ye best be selecting a reasonable integarrrrrr");
//        int quantity = 0;
//        if (notInt()){
//        quantity = scanner.nextInt();
//        currentPlayer.drink(quantity);
//        if (currentPlayer.getDrunkenness() <= 0)
//        {gamePanel.textShift("Are you KIDDING me?");
//        gamePanel.textShift("GAME OVER");
//        }
//        else if (currentPlayer.getDrunkenness() >= 1000)
//        {gamePanel.textShift(currentPlayer.getMobName() +"... please... slow down");
//        gamePanel.textShift("GAME OVER");
//        return true;}
//        else if (currentPlayer.getDrunkenness() >= 100)
//        {gamePanel.textShift("Whoa! That's a lot.");
//        gamePanel.textShift("SECRET END");
//        return true;}
//        else if (currentPlayer.getDrunkenness() < 10)
//        {gamePanel.textShift("More than that!");
//        return false;}
//        else {gamePanel.textShift(currentPlayer.getMobName() + "....");
//        gamePanel.textShift("YOUR SINK IS SHIPPING!");
//        gamePanel.textShift("...and you're just durnk enough to pull this off...");
//        currentPlayer.pirateX = 0;
//        currentPlayer.pirateY = 0;
//        dungeon.updateRoom(currentPlayer);
//    currentPlayer.currentRoom.overview();
//    scanner.nextLine();
//        while (!choose());}
//        return true;}
//    else gamePanel.textShift("HEY! that's not a number!");
//        return false;}

        public void firstMateIntro() throws FileNotFoundException, UnsupportedEncodingException {
        currentPlayer = firstMate;
        gamePanel.textShift("WOW! This is a long hall. (Hit enter to continue)");
        scanner.nextLine();
        gamePanel.textShift(".");
            scanner.nextLine();
            gamePanel.textShift(".");
            scanner.nextLine();
            gamePanel.textShift(".");
            scanner.nextLine();
            gamePanel.textShift("Anyways... remember your first mate?");
            scanner.nextLine();
            gamePanel.textShift("They were a cool pirate, dare I say.");
            scanner.nextLine();
            gamePanel.textShift("With the ability to climb objects, and fit into " +
                    "small spaces, I wonder if they'll be useful on your quest?");
            scanner.nextLine();
            gamePanel.textShift("Just remember, they're terrified of... everything");
            scanner.nextLine();
            gamePanel.textShift("Let's pan over to them now! What was their name again?");
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
        gamePanel.textShift("Please select a file:");
        gamePanel.textShift("File 1: " + file1.get(0));
        gamePanel.textShift("File 2: " + file2.get(0));
        gamePanel.textShift("File 3: " + file3.get(0));
        String choice = scanner.nextLine();
        if (choice.equals("1") || choice.equals(file1.get(0)) || choice.equals("File 1") || choice.equals("file 1")) {
            PrintWriter writer = new PrintWriter("save1.txt", "UTF-8");
            writer.println(captain.getMobName());
            writer.println(Integer.toString(captain.pirateX));
            writer.println(Integer.toString(captain.pirateY));
            writer.close();
            gamePanel.textShift("Yer game be saved!");
        }
        else if (choice.equals("2") || choice.equals(file2.get(0)) || choice.equals("File 2") || choice.equals("file 2")) {
            PrintWriter writer = new PrintWriter("save2.txt", "UTF-8");
            writer.println(captain.getMobName());
            writer.println(Integer.toString(captain.pirateX));
            writer.println(Integer.toString(captain.pirateY));
            writer.close();
            gamePanel.textShift("Yer game be saved!");
        }
        else if (choice.equals("3") || choice.equals(file3.get(0)) || choice.equals("File 3") || choice.equals("file 3")) {
            PrintWriter writer = new PrintWriter("save3.txt", "UTF-8");
            writer.println(captain.getMobName());
            writer.println(Integer.toString(captain.pirateX));
            writer.println(Integer.toString(captain.pirateY));
            writer.close();
            gamePanel.textShift("Yer game be saved!");
        }
        else {gamePanel.textShift("Changed yer mind? Or did ye spell something wrong?"); }
//        while (!choose());

    }

//    // EFFECTS: allows player to choose an action from the list.
//    private boolean choose() throws FileNotFoundException, UnsupportedEncodingException {
//        String choice;
//        gamePanel.textShift("Aye.. what will ye do next?");
//        gamePanel.textShift("(type ? for help)");
//        choice = scanner.nextLine();
//        if (choice.equals("?")){help();}
//        else if (choice.equals("n")){dungeon.goNorth(currentPlayer); while (!choose()){}}
//        else if (choice.equals("s")){dungeon.goSouth(currentPlayer); while (!choose()){}}
//        else if (choice.equals("w")){dungeon.goWest(currentPlayer); while (!choose()){}}
//        else if (choice.equals("e")){dungeon.goEast(currentPlayer); while (!choose()){}}
//        else if (choice.equals("save")){save();}
//        else if (choice.equals("look")){
//            currentPlayer.currentRoom.overview(); while(!choose());}
//        else if (choice.equals("inventory")){
//            currentPlayer.getInventory(); while (!choose());}
//        else if (choice.equals("weapon")){
//            currentPlayer.getEquipment(); while (!choose());}
//        else if (choice.equals("equip")){equipSelect();}
//        else if (choice.equals("ammo")){gamePanel.textShift(captain.checkAmmo(captain.currentWeapon)); while (!choose());}
//        else if (choice.equals("use")){
//            try {
//                use();
//            } catch (EmptyRoomException e) {
//                gamePanel.textShift("There's nary a thing to use!");
//            }
//            finally {
//                while (!choose());}}
//        else if (choice.equals("get")){
//            try {
//                get();
//            } catch (EmptyRoomException e) {
//                gamePanel.textShift("There's nary a thing to be gotten!");
//
//            }
//        finally {while (!choose());}}
//        else {gamePanel.textShift("I don't know that guy... Did you spell something wrong?");
//            return false;}
//        return true;
//
//    }

    // EFFECTS: allows player to choose an action from the list.
    private void choose() throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("we're bad, boys");
        String choice;
        gamePanel.textShift("Aye.. what will ye do next?");
        gamePanel.textShift("(type ? for help)");
        System.out.println("we're good, boys");
        choice = gamePanel.textField.getText();
        System.out.println("we got goodie goodie boys");
        if (choice.equals("?")){help();}
        else if (choice.equals("n")){dungeon.goNorth(currentPlayer); }
        else if (choice.equals("s")){dungeon.goSouth(currentPlayer); }
        else if (choice.equals("w")){dungeon.goWest(currentPlayer); }
        else if (choice.equals("e")){dungeon.goEast(currentPlayer); }
        else if (choice.equals("save")){save();}
        else if (choice.equals("look")){
            System.out.println("chose look");
            currentPlayer.currentRoom.overview(); }
        else if (choice.equals("inventory")){
            currentPlayer.getInventory(); }
        else if (choice.equals("weapon")){
            currentPlayer.getEquipment(); }
        else if (choice.equals("equip")){equipSelect();}
        else if (choice.equals("ammo")){gamePanel.textShift(captain.checkAmmo(captain.currentWeapon)); }
        else if (choice.equals("use")){
            try {
                use();
            } catch (EmptyRoomException e) {
                gamePanel.textShift("There's nary a thing to use!");
            }
            }
        else if (choice.equals("get")){
            try {
                get();
            } catch (EmptyRoomException e) {
                gamePanel.textShift("There's nary a thing to be gotten!");

            }
            }
        else {gamePanel.textShift("I don't know that guy... Did you spell something wrong?");
            }


    }



    // EFFECTS: displays a list of controls for the captain
    private void help() throws FileNotFoundException, UnsupportedEncodingException {
        gamePanel.textShift("?: Help");
        gamePanel.textShift("n: Go north");
        gamePanel.textShift("s: Go south");
        gamePanel.textShift("w: Go west");
        gamePanel.textShift("e: Go east");
        gamePanel.textShift("save: Save");
        gamePanel.textShift("look: Read description again");
        gamePanel.textShift("inventory: Displays your inventory");
        gamePanel.textShift("weapon: Check yer current weapon");
        gamePanel.textShift("equip: Equip a new weapon");
        gamePanel.textShift("ammo: Check yer equipped weapon's ammo");
        gamePanel.textShift("get: Pick an item up");
//        while (!choose());
    }

    // EFFECTS: change's captain's equipped weapon based on captain choice
    // MODIFIES: captain
    private void equipSelect() throws FileNotFoundException, UnsupportedEncodingException {
        String pick;
        gamePanel.textShift("What from yer inventory do ye want to equip?");
        pick = scanner.nextLine();
        if (currentPlayer.equip(pick)){
            currentPlayer.equip(pick);
            gamePanel.textShift("Ye've equipped " + pick);

        }
        else if (!currentPlayer.equip(pick)) {gamePanel.textShift("Uh, did ye spell it right?");
        }
    }

    //EFFECTS: lets players choose from a list of available items to obtain
    //MODIFIES: captain, current room.
    private void get() throws FileNotFoundException, UnsupportedEncodingException, EmptyRoomException {
        emptyRoom(currentPlayer.currentRoom.stuff); // checks to see if EmptyRoomException needs to be thrown
        gamePanel.textShift("What do ye want to get? (if ye want to cancel, just type any letter!).");
        operation = "get";
    }

    private void getTwo(String choice){
        System.out.println("get success");
            if (currentPlayer.currentRoom.itemMatch(choice)){
                Item thing = currentPlayer.currentRoom.stuff.get(choice);
                if (thing.obtainable()){
                    gamePanel.textShift("Ye get " + (thing.getName()));
                currentPlayer.inventory.add(currentPlayer.currentRoom.stuff.get(choice));
                currentPlayer.currentRoom.stuff.remove(choice); }
                else {gamePanel.textShift("Ye can't fit that in yer pockets!");
                scanner.nextLine();}

            }
            else gamePanel.textShift("Changed yer mind?");

            operation = "choose";

        }

        private void use() throws EmptyRoomException {
            emptyRoom(currentPlayer.currentRoom.stuff);
        gamePanel.textShift("What do ye want to use?");
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
        if (operation == "choose") {
            gamePanel.textShift(gamePanel.textField.getText());
            try {
                choose();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            gamePanel.textShift(gamePanel.textField.getText());
        }
        else if (operation == "get"){
            getTwo(gamePanel.textField.getText());
        }

        gamePanel.textField.setText("");
    }


}

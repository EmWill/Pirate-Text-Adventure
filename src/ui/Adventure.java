package ui;
import exceptions.EmptyRoomException;
import model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class Adventure extends JFrame implements ActionListener {
    Scanner scanner = new Scanner(System.in);
    private String operation;
    public Player captain;
    public Player firstMate;
    public Player currentPlayer;
    public Dungeon dungeon;
    public BetterGamePanel gamePanel;
    private JDialog rattaDialog;
    public LabelChanger treasurePanel;
    List<String> file1 = Files.readAllLines(Paths.get("save1.txt"));
    List<String> file2 = Files.readAllLines(Paths.get("save2.txt"));
    List<String> file3 = Files.readAllLines(Paths.get("save3.txt"));



    public Adventure(String startingName, int startingX, int startingY) throws IOException {

        captain = new Player(50, startingName,
                startingX,
                startingY,
                this);
        firstMate = new Player(0, "Blank", 0, 2,  this);

        Weapon darkness = new Melee("testSword", 1, "gives you a big smack",
                "a dark and evil sword");
        Weapon testGun = new Gun("testGun", 2, "bang", "a rusty revolver",
                6);
        captain.inventory.add(testGun);
        captain.inventory.add(darkness);
        currentPlayer = captain;
        this.dungeon = new Dungeon(this);
        BufferedImage ratta = ImageIO.read(new File("ratta 2 e.png"));
        ImageIcon icon = new ImageIcon(ratta);
        JLabel rattaImage = new JLabel(icon);
        rattaDialog = new JDialog();
        rattaDialog.add(rattaImage);
        rattaDialog.setSize(40, 100);
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        rattaDialog.setLocation(((scrn.width - getWidth()) / 2) + 411, (scrn.height - getHeight()) / 2);
        rattaDialog.setVisible(false);
        JButton b1 = new JButton();
        JButton b2 = new JButton();
        JButton b3 = new JButton();
        JButton b4 = new JButton();
        JButton b5 = new JButton();
        setSize(200,140);
        setLayout(new FlowLayout());
        b1.setText("West");
        b2.setText("North");
        b3.setText("South");
        b4.setText("East");
        b5.setText("HELP");
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        b1.setSize(200,200);
        positionButtons();
        b1.setVisible(true);
      setVisible(true);
      b1.addActionListener(getButtonWest());
        b2.addActionListener(getButtonNorth());
        b3.addActionListener(getButtonSouth());
        b4.addActionListener(getButtonEast());
        b5.addActionListener(getButtonHelp());
       treasurePanel = new LabelChanger();
       treasurePanel.setLocation(800, 700);
       treasurePanel.setVisible(false);

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
        for (Room room: dungeon.map
                ) {room.setGamePanel(gamePanel);

        }
    }



    private void positionButtons() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(((scrn.width - getWidth())- 1026) / 2, (scrn.height - getHeight()) / 2);
    }




    public void start() throws FileNotFoundException, UnsupportedEncodingException {
        gamePanel.textShift("__________________________");
        if (dungeon.raining){gamePanel.textShift("Aye, it's a rainy day...");}
        else {gamePanel.textShift("Clear skies...");}
        gamePanel.textShift("__________________________");
        for (NPC goon: dungeon.goons
             ) {dungeon.updateRoomMob(goon);

        }
        if (captain.pirateX == -100){
            gamePanel.textShift("Hello " + captain.pirateName + "! You are " + captain.drunkenness
                    + " percent drunk...");
            gamePanel.textShift("Wait! That's a terrible name! What's your real name?");
            operation = "levelOne";
        }
        else {dungeon.updateRoom(currentPlayer);
            currentPlayer.currentRoom.overview();
//            while(!choose()){}
            // choose();
            gamePanel.textShift("Aye.. what will ye do next?");
            gamePanel.textShift("(type ? for help)");
    }}

    public void levelOne(String choice) throws FileNotFoundException, UnsupportedEncodingException {
        namepicker(choice);
            gamePanel.textShift(currentPlayer.getMobName() + "....");
            gamePanel.textShift("YOUR SINK IS SHIPPING!");
            gamePanel.textShift("...and you're just durnk enough to pull this off...");
            currentPlayer.pirateX = 0;
            currentPlayer.pirateY = 0;
            dungeon.updateRoom(currentPlayer);
            currentPlayer.currentRoom.overview();

    }


 private void namepicker(String choice) throws FileNotFoundException, UnsupportedEncodingException {
        if (choice.equals("fuck") || choice.equals("darn")){
            gamePanel.textShift("That's vulgar! We're gonna keep the default name!");

        }
        else

            currentPlayer.pirateName = choice;


        operation = "choose";
 }
//
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

    // Scripted event, then sets operation to "firstMate", prompting player to pick a name
        public void firstMateIntro() throws FileNotFoundException, UnsupportedEncodingException {
            currentPlayer = firstMate;
            gamePanel.textShift("WOW! This is a long hall.");
            gamePanel.textShift(".");
            gamePanel.textShift(".");
            gamePanel.textShift(".");
            gamePanel.textShift("Anyways... remember your first mate?");
            gamePanel.textShift("They were a cool pirate, dare I say.");
            gamePanel.textShift("With the ability to climb objects, and fit into " +
                    "small spaces, I wonder if they'll be useful on your quest?");
            gamePanel.textShift("Just remember, they're terrified of... everything");
            gamePanel.textShift("Let's pan over to them now! What was their name again?");
            operation = "firstMate";
        }

        // Continuation of firstMateIntro, after player has selected a name.
        private void firstMateIntroTwo(){
            try {
                namepicker(gamePanel.textField.getText());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            dungeon.updateRoom(currentPlayer);
            try {
                currentPlayer.currentRoom.overview();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            operation = "choose";
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
        operation = "save";
    }
    private void saveTwo() throws FileNotFoundException, UnsupportedEncodingException{
        String choice = gamePanel.textField.getText();
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
        operation = "choose";

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
        String choice;
//        gamePanel.textShift("Aye.. what will ye do next?");
//        gamePanel.textShift("(type ? for help)");
        choice = gamePanel.textField.getText();
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

    public void ratCheck(){
        if (currentPlayer.currentRoom.containsByName("Ratta 2E.")){
            rattaDialog.setVisible(true);
        }
        else rattaDialog.setVisible(false);
        gamePanel.textField.requestFocus();

    }



    // EFFECTS: displays a list of controls for the captain
    private void help() throws FileNotFoundException, UnsupportedEncodingException {
        gamePanel.textShift("____________");
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
        gamePanel.textShift("____________");
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
        operation = "use";}

        private void useTwo(String choice){
        if (currentPlayer.currentRoom.itemMatch(choice)) {
            Item thing = currentPlayer.currentRoom.stuff.get(choice);
            thing.use();
        }
        else {gamePanel.textShift("Changed yer mind?");}
            operation = "choose";
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
        gamePanel.textShift(gamePanel.textField.getText());
        if (operation == "choose") {
            try {
                choose();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
//            gamePanel.textShift(gamePanel.textField.getText());
        }
        else if (operation == "get"){
            getTwo(gamePanel.textField.getText());
        }

        else if (operation == "use")
        {useTwo(gamePanel.textField.getText());}

        else if (operation == "firstMate") {
            firstMateIntroTwo();
        }

        else if (operation == "save"){
            try {
                saveTwo();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
        }

        else if (operation == "levelOne"){
            if (!(gamePanel.textField.getText() == "")){
                try {
                    levelOne(gamePanel.textField.getText());
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
            }
        }

        gamePanel.textField.setText("");


        }

    private ActionListener getButtonNorth() {
        ActionListener north = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (operation == "choose") {
                    try {
                        dungeon.goNorth(currentPlayer);
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };
        return north;
    }

    private ActionListener getButtonWest() {
        ActionListener west = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (operation == "choose"){
                    try {
                        dungeon.goWest(currentPlayer);
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };
        return west;
    }

    private ActionListener getButtonSouth() {
        ActionListener south = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (operation == "choose") {
                    try {
                        dungeon.goSouth(currentPlayer);
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };
        return south;
    }

    private ActionListener getButtonEast() {
        ActionListener east = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (operation == "choose") {
                    try {
                        dungeon.goEast(currentPlayer);
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };
        return east;
    }

    private ActionListener getButtonHelp() {
        ActionListener help = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (operation == "choose") {
                    try {
                        help();
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };

        return help;
    }

    }




package model;
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
    public ArrayList<Room> map;
    List<String> lines = Files.readAllLines(Paths.get("inputfile.txt"));




    public Adventure() throws IOException {
        player = new Player(0, "Blank",
                (Integer.parseInt((String)lines.get(0))),
                (Integer.parseInt((String)lines.get(1))));
        current = new Room ("error room", "uh oh! you shouldn't be here. tell miles and he'll fix it lol",
                new ArrayList<Object>(), 69, 420);
        quarters = new Room("Captain's Quarters", "You stand in your luxurious quarters. To your north is" +
                " a long stretch of hallway. You begin to wish you hadn't installed that labyrinth between you and" +
                " the upper deck.", new ArrayList<Object>(), 0, 0);
        map = new ArrayList<Room>();
        map.add(quarters);
    }

    public void start() throws FileNotFoundException, UnsupportedEncodingException {
        if (player.pirateX == -100){
            System.out.print("Hello " +player.pirateName);
            System.out.print("! You are " +player.drunkenness);
            System.out.println(" percent drunk... Wait! That's a terrible name! What's your real name?");
            levelOne();
        }
        else {updateRoom();
            current.overview();}
    }

    public void levelOne() throws FileNotFoundException, UnsupportedEncodingException {
        while(!namepicker()){}
    }

 public boolean namepicker() throws FileNotFoundException, UnsupportedEncodingException {
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

    public boolean firstdrink() throws FileNotFoundException, UnsupportedEncodingException {
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
        save();
        updateRoom();
    current.overview();}
        return true;}
    else return false;}

    // EFFECTS: returns false if next scanner input isn't an integer
    private boolean notInt(){
        if (!scanner.hasNextInt())
        {System.out.println("hey! that's not a number!");
        scanner.nextLine();
        return false;}
    return true;}

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

    public void save() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("inputfile.txt", "UTF-8");
        writer.println(Integer.toString(player.pirateX));
        writer.println(Integer.toString(player.pirateY));
        writer.close();

    }

    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList(Arrays.asList(splits));
    }



}

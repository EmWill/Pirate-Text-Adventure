package ui;

import java.util.Scanner;

public class Player {
    Scanner scanner = new Scanner(System.in);
    private int drunkenness;
    private String pirateName;


    public Player(int drunk, String name){
        drunkenness = drunk;
                pirateName = name;

    }

    public void start(){
        System.out.print("Hello " +this.pirateName);
        System.out.print("! You are " +this.drunkenness);
        System.out.println(" percent drunk... Wait! That's a terrible name! What's your real name?");
        namepicker();

    }

 public void namepicker(){
        String choice = "";
        choice = scanner.nextLine();
        if (choice.equals("fuck")){
            System.out.println("That's vulgar! Pick something else!");
            namepicker();
        }
        else
        {this.pirateName = choice;
     System.out.println(this.pirateName +" is a much better name! Unfortunately," +
             " you are a pirate whose ship is sinking, and I don't think you're quite drunk enough for the job!" +
             " How much do you want to drink?");
        firstdrink();}
 }

    public void firstdrink() {
        System.out.println("AYE.. ye best be selecting a reasonable number");
        int quantity = 0;
        quantity = scanner.nextInt();
        this.drunkenness = (this.drunkenness + quantity);
        if (this.drunkenness <= 0)
        {System.out.println(pirateName + ", you are " + drunkenness + " percent drunk. Are you KIDDING me?");
        System.out.println("GAME OVER");
        }
        else if (this.drunkenness >= 1000)
        {System.out.println(pirateName +"... please... slow down");
        System.out.println("GAME OVER");}
        else if (this.drunkenness >= 100)
        {System.out.println("Whoa! That's a lot.");
        System.out.println("SECRET END");}
        else if (this.drunkenness < 10)
        {System.out.println("More than that!");
        firstdrink();}
        else {System.out.println(pirateName + "....");
        System.out.println("YOUR SINK IS SHIPPING!");
        System.out.println("...and you're just durnk enough to pull this off...");}








    }
    public static void main(String[] args) {
        Player player = new Player(0, "blank");
        player.start();
    }
}

package ui;

import model.Adventure;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    Scanner scanner = new Scanner(System.in);
    List<String> save1;
    List<String> save2;
    List<String> save3;


    public void setSaves() throws IOException {
        {
            try {
                save1 = Files.readAllLines(Paths.get("save1.txt"));
            } catch (IOException e) {
                PrintWriter writer = new PrintWriter("save1.txt", "UTF-8");
                writer.println("New Game");
                writer.println(Integer.toString(-100));
                writer.println(Integer.toString(-100));
                writer.close();
                save1 = Files.readAllLines(Paths.get("save1.txt"));
            }
        }
        {
            try {
                save2 = Files.readAllLines(Paths.get("save2.txt"));
            } catch (IOException e) {
                PrintWriter writer = new PrintWriter("save2.txt", "UTF-8");
                writer.println("New Game");
                writer.println(Integer.toString(-100));
                writer.println(Integer.toString(-100));
                writer.close();
                save2 = Files.readAllLines(Paths.get("save2.txt"));
            }
        }
        {
            try {
                save3 = Files.readAllLines(Paths.get("save3.txt"));
            } catch (IOException e) {
                PrintWriter writer = new PrintWriter("save3.txt", "UTF-8");
                writer.println("New Game");
                writer.println(Integer.toString(-100));
                writer.println(Integer.toString(-100));
                writer.close();
                save3 = Files.readAllLines(Paths.get("save3.txt"));
            }
        }
        while (!begin()){}
    }


    public boolean begin() throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("Welcome to pirate adventure!");
        System.out.println("Please select a file:");
        System.out.println("File 1: " + save1.get(0));
        System.out.println("File 2: " + save2.get(0));
        System.out.println("File 3: " + save3.get(0));
        String choice = scanner.nextLine();
        if (choice.equals("1") || choice.equals(save1.get(0))){
            Adventure adventure = null;
            try {
                adventure = new Adventure(save1.get(0), (Integer.parseInt(save1.get(1))), (Integer.parseInt(save1.get(2))));
            } catch (IOException | IndexOutOfBoundsException | NullPointerException | NumberFormatException e) {
                System.out.println("That file is corrupted!");
            }
            adventure.start();
            return true;
        }
        else if (choice.equals("2") || choice.equals(save2.get(0))){
            Adventure adventure = null;
            try {
                adventure = new Adventure(save2.get(0), (Integer.parseInt(save2.get(1))), (Integer.parseInt(save2.get(2))));
            } catch (IOException | IndexOutOfBoundsException | NullPointerException | NumberFormatException e) {
                System.out.println("That file is corrupted!");
            }
            adventure.start();
            return true;
        }
        else if (choice.equals("3") || choice.equals(save3.get(0))){
            Adventure adventure = null;
            try {
                adventure = new Adventure(save3.get(0), (Integer.parseInt(save3.get(1))), (Integer.parseInt(save3.get(2))));
            } catch (IOException | IndexOutOfBoundsException | NullPointerException | NumberFormatException e) {
                System.out.println("That file is corrupted!");
            }
            adventure.start();
            return true;
        }
        else {System.out.println("Please enter the number or name of a file!");
        return false;}
    }

}

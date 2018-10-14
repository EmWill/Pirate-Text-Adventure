package ui;

import model.Adventure;
import java.io.PrintWriter;

import java.io.IOException;
import java.util.Scanner;

public class Start {


    public static void main(String[] args) throws IOException {
        MainMenu menu = new MainMenu();
        System.out.println("PIRATE ADVENTURE! by miles williams who was born in 1999 in case it comes up in a puzzle");
        System.out.println("GRITHAWK GAMING ©");
        System.out.println(".");
        menu.setSaves();
    }



}
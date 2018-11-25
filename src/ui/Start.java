package ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Start extends JFrame implements ActionListener {
    private BetterGamePanel gamePanel;
    private MainMenu menu;
    Scanner scanner = new Scanner(System.in);
    java.util.List<String> save1;
    java.util.List<String> save2;
    List<String> save3;
    Adventure adventure;


    private Start() throws IOException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gamePanel = new BetterGamePanel();
//        this.menu = new MainMenu();
        System.out.println("PIRATE ADVENTURE! by miles williams who was born in 1999 in case it comes up in a puzzle");
        System.out.println("GRITHAWK GAMING ©");
        System.out.println(".");
        add(gamePanel);
        setVisible(true);
      pack();
      centreOnScreen();
        gamePanel.printText("PIRATE ADVENTURE! by miles williams who was born in 1999 in case it comes up in a puzzle");
        gamePanel.printText("GRITHAWK GAMING ©");
        gamePanel.printText(".");
//        menu.setPanel(gamePanel);
        gamePanel.textField.addActionListener(this);
        try {
//            menu.setSaves();
            setSaves();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }


    public void setPanel(BetterGamePanel gamePanel){
        this.gamePanel = gamePanel;
    }


    public void setSaves() throws IOException {
        {
            try {
                save1 = Files.readAllLines(Paths.get("save1.txt"));
                System.out.println(save1.get(0));
                System.out.println(save1.get(1));
                System.out.println(save1.get(2));
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
                System.out.println(save2.get(0));
                System.out.println(save2.get(1));
                System.out.println(save2.get(2));
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
                System.out.println(save3.get(0));
                System.out.println(save3.get(1));
                System.out.println(save3.get(2));
            } catch (IOException e) {
                PrintWriter writer = new PrintWriter("save3.txt", "UTF-8");
                writer.println("New Game");
                writer.println(Integer.toString(-100));
                writer.println(Integer.toString(-100));
                writer.close();
                save3 = Files.readAllLines(Paths.get("save3.txt"));
            }
        }
//        while (!begin()){}
        falseBegin();
    }

    public void falseBegin(){
        gamePanel.textArea.setText("");
        gamePanel.printText("PIRATE ADVENTURE! by miles williams who was born in 1999 in case it comes up in a puzzle");
        gamePanel.printText("GRITHAWK GAMING ©");
        gamePanel.printText(".");
        gamePanel.printText("Welcome to PIRATE ADVENTURE!");
        gamePanel.printText("Please select a file:");
        gamePanel.printText("File 1: " + save1.get(0));
        gamePanel.printText("File 2: " + save2.get(0));
        gamePanel.printText("File 3: " + save3.get(0));
    }


    public void begin() throws FileNotFoundException, UnsupportedEncodingException {
        gamePanel.textArea.setText("");
        gamePanel.printText("PIRATE ADVENTURE! by miles williams who was born in 1999 in case it comes up in a puzzle");
        gamePanel.printText("GRITHAWK GAMING ©");
        gamePanel.printText(".");
        gamePanel.printText("Welcome to PIRATE ADVENTURE!");
        gamePanel.printText("Please select a file:");
        gamePanel.printText("File 1: " + save1.get(0));
        gamePanel.printText("File 2: " + save2.get(0));
        gamePanel.printText("File 3: " + save3.get(0));
        String choice = gamePanel.textField.getText();
        if (choice.equals("1") || choice.equals(save1.get(0))){
//            Adventure adventure = null;
            try {
                this.adventure = new Adventure(save1.get(0), (Integer.parseInt(save1.get(1))), (Integer.parseInt(save1.get(2)))
                        );
            } catch (IOException | IndexOutOfBoundsException | NullPointerException | NumberFormatException e) {
                gamePanel.printText("That file is corrupted!");
            }

            adventure.start();

//            return true;
        }
        else if (choice.equals("2") || choice.equals(save2.get(0))){
//            Adventure adventure = null;

            try {
               this.adventure = new Adventure(save2.get(0), (Integer.parseInt(save2.get(1))), (Integer.parseInt(save2.get(2)))
                        );
            } catch (IOException | IndexOutOfBoundsException | NullPointerException | NumberFormatException e) {
                gamePanel.printText("That file is corrupted!");
            }

            adventure.start();
//            return true;
        }
        else if (choice.equals("3") || choice.equals(save3.get(0))){
//            Adventure adventure = null;
            try {
                this.adventure = new Adventure(save3.get(0), (Integer.parseInt(save3.get(1))), (Integer.parseInt(save3.get(2)))
                        );
            } catch (IOException | IndexOutOfBoundsException | NullPointerException | NumberFormatException e) {
                e.printStackTrace();
                gamePanel.printText("That file is corrupted!");
            }
adventure.setPanel(gamePanel);
            gamePanel.textField.removeActionListener(this);
            gamePanel.textField.addActionListener(adventure);
            adventure.start();
//            return true;
        }
        else {
            gamePanel.printText("Please enter the number or name of a file!");
//            return false;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            begin();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        gamePanel.textField.setText("");
    }



    public static void main(String[] args) throws IOException {
       new Start();
    }



}
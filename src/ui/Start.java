package ui;


import java.io.IOException;

public class Start {
    private BetterGamePanel gamePanel;


    public static void main(String[] args) throws IOException {
        MainMenu menu = new MainMenu();
        System.out.println("PIRATE ADVENTURE! by miles williams who was born in 1999 in case it comes up in a puzzle");
        System.out.println("GRITHAWK GAMING ©");
        System.out.println(".");
        BetterGamePanel gamePanel = new BetterGamePanel();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gamePanel.createAndShowGUI();
            }
        });
        menu.setSaves();
    }



}
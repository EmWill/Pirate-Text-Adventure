package ui;

/* TextDemo.java requires no other files. */

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class BetterGamePanel extends JPanel implements ActionListener {
    protected JTextField textField;
    protected JTextArea textArea;
    private List<String> textHistory;
    private final static String newline = "\n";
    private MainMenu mainMenu;

    public BetterGamePanel() {
        super(new GridBagLayout());
//this.mainMenu = new MainMenu();
//mainMenu.setPanel(this);
        textHistory = new ArrayList<>();
        for (int i = 0; i <=24 ; i++){
            textHistory.add("");
        }



        textField = new JTextField(75);
        textField.addActionListener(this);

        textArea = new JTextArea(27, 76);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;



        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        add(textField, c);


    }

    public void actionPerformed(ActionEvent evt) {
//        String text = textField.getText();
//        textArea.setText("");
//        textShift(text);
//        textPrint();
//        textField.selectAll();
//
//
//        //Make sure the new text is visible, even if there
//        //was a selection in the text area.
//        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    public void printText(String text){
        textArea.append(text + newline);
        textField.selectAll();
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    public void clearText(){
        textArea.setText("");
    }

    public void textShift(String text){
        textArea.setText("");
        String nextText = text;
        String lastText = "";
        Boolean next = true;
        int i = 0;
        for (String line: textHistory
             ) {if (next){
                 lastText = textHistory.get(i);
                 textHistory.set(i, nextText);
                 i++;
                 next = false;
        }
else {
                 nextText = textHistory.get(i);
                 textHistory.set(i, lastText);
                 i++;
                 next = true;
        }
        }
        textPrint();

    }

    public void textPrint(){
        for (int i = textHistory.size(); i > 0; i--){
            textArea.append(textHistory.get(i-1) + newline);
        }
    }


    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    public void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TextDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add contents to the window.
        frame.add(new BetterGamePanel());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public void close(){
        this.setVisible(false);

    }


}
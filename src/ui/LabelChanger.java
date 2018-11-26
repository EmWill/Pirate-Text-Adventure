package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LabelChanger extends JFrame implements ActionListener
{
    private JLabel label;
    private JButton btn;
    public LabelChanger()
    {
        super("The title");
        setPreferredSize(new Dimension(400, 90));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
        setLayout(new FlowLayout());
        btn = new JButton("Push this button to unlock the treasure");
        btn.setActionCommand("myButton");
        btn.addActionListener(this); //sets "this" class as an action listener for btn.
        //that means that when the btn is clicked,
        //this.actionPerformed(ActionEvent e) will be called.
        //You could also set a different class, if you wanted
        //to capture the response behaviour elsewhere
        label = new JLabel("");
        add(btn);
        add(label);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    //this is the method that runs when Swing registers an action on an element
    //for which this class is an ActionListener
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("myButton"))
        {
          btn.setText("YOU'RE GOING TO JAIL!!!");
          label.setText("p.s. good luck on finals");
        }
    }
    public static void main(String[] args)
    {
        new LabelChanger();
    }
}
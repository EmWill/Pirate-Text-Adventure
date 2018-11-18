package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LabelChanger extends JFrame implements ActionListener
{
    private JTextField output;
    private JTextField field;
    public LabelChanger()
    {
        super("The title");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(this, 1));
        setPreferredSize(new Dimension(900, 700));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
        JButton btn = new JButton("Change");
        btn.setActionCommand("myButton");
        btn.addActionListener(this); //sets "this" class as an action listener for btn.
        //that means that when the btn is clicked,
        //this.actionPerformed(ActionEvent e) will be called.
        //You could also set a different class, if you wanted
        //to capture the response behaviour elsewhere
        output = new JTextField(5);
        field = new JTextField(5);

        add(field);
        add(btn);
        add(output);
        pack();
        setLocationRelativeTo(field);
        setVisible(true);
        setResizable(false);
    }

    //this is the method that runs when Swing registers an action on an element
    //for which this class is an ActionListener
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("myButton"))
        {
            output.setText(field.getText());
        }
    }

}
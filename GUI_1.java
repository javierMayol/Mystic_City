import java.util.*;
import java.awt.*;
import javax.swing.*;

public class GUI_1 implements UserInterface
{
  JFrame f;
  JTextArea area;
  JPanel p;
  JButton b;

  public static void main(String[] args)
  {
    GUI_1 gu = new GUI_1();
    return;
  }

  public GUI_1()
  {
    f = new JFrame();
    area = new JTextArea();
    p = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
    f.setLayout(new GridLayout(5, 2, 10, 10));

    p.add(new JButton("GO"));
    p.add(new JButton("GET"));
    p.add(new JButton("USE"));
    p.add(new JButton("LOOK"));
    p.add(new JButton("TALK"));
    p.add(new JButton("ASK"));
    p.add(new JButton("TRADE"));
  

    f.add(new JScrollPane(area), BorderLayout.CENTER);
    b = new JButton("click here.");
    f.add(p);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setSize(400, 640);
    f.setVisible(true);
    f.setTitle("GUI 1"); 
  }

  public void display(String s)
  {
    area.setFont(new Font("Serif", Font.BOLD, 18));
    area.setText(s);
    f.add(new JScrollPane(area), BorderLayout.CENTER);
  }

  public void displayPrompt(String s)
  {
    area.setFont(new Font("Serif", Font.BOLD, 18));
    area.setText(s +" >");
    f.add(new JScrollPane(area), BorderLayout.CENTER);
  }    

  public String getLine()
  {
    return null;
  } 
}

//public class Gui_component extends JComponent

  

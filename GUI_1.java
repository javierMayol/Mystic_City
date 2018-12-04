import java.util.*;
import java.awt.*;
import javax.swing.*;

public class GUI_1 implements UserInterface
{
  JFrame f;
  JTextArea area;
  JPanel p;
  JButton b;

  public GUI_1()
  {
    f = new JFrame();
    p = new JPanel();//new FlowLayout(FlowLayout.CENTER, 5, 5));
    f.setLayout(new GridLayout(5, 2, 10, 10));
    f.add(new Label("GUIIII"));

    p.add(new JButton("GO"));
    p.add(new JButton("GET"));

    area = new JTextArea();
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
    area.setText(s);
    f.add(new JScrollPane(area), BorderLayout.CENTER);
  }

  public String getLine()
  {
    return null;
  } 
}

//public class Gui_component extends JComponent

  

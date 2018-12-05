import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI_1 implements UserInterface
{
  private JFrame f;
  private JTextArea area;
  private JPanel p;
  private JButton b;
  private keyboardScanner keyboard;
  private String cmd;

  public GUI_1()
  {
    keyboard = keyboard.getInstance();
    cmd = new String();
    f = new JFrame();
    area = new JTextArea(25, 25);
    p = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
    f.setLayout(new GridLayout(2, 2, 5, 5));
    area.setFont(new Font("SanSerif", Font.PLAIN, 13));
    //area.setEditable(false);

    b = new JButton("GO");
    b.addActionListener(new GUIListener());
    b.addMouseListener(new GUIListener());
    p.add(b);

    b = new JButton("GET");
    b.addActionListener(new GUIListener());
    b.addMouseListener(new GUIListener());
    p.add(b);

    b = new JButton("USE");
    b.addActionListener(new GUIListener());
    b.addMouseListener(new GUIListener());
    p.add(b);

    b = new JButton("LOOK");
    b.addActionListener(new GUIListener());
    b.addMouseListener(new GUIListener());
    p.add(b);

    b = new JButton("TALK");
    b.addActionListener(new GUIListener());
    b.addMouseListener(new GUIListener());
    p.add(b);

    b = new JButton("ASK");
    b.addActionListener(new GUIListener());
    b.addMouseListener(new GUIListener());
    p.add(b);

    b = new JButton("TRADE");
    b.addActionListener(new GUIListener());
    b.addMouseListener(new GUIListener());
    p.add(b);
  
    b = new JButton("EXIT");
    b.addActionListener(new GUIListener());
    b.addMouseListener(new GUIListener());
    p.add(b); 
    b = new JButton("LOOK");

    f.add(new JScrollPane(area), BorderLayout.CENTER);
    f.add(p);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setSize(400, 640);
    f.setVisible(true);
  }

  public void display(String s)
  {
    area.setText(s);
  }

  public void displayPrompt(String s)
  {
    f.setTitle(s); 
  }    

  public String buttonPress(String e)
  {
    return e;
  }

  public String getLine()
  {
    cmd = keyboard.getInput();
    //Say something like if(button is press, get the source and return text)
    return cmd;
  } 

  class GUIListener extends JComponent implements MouseListener, ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      addMouseListener(this);
      Object o = e.getSource();
      JButton b = null;
      if(o instanceof JButton)
      {
        b = (JButton) o;
        display(b.getText()); 
      }
    }
    public void mouseMoved(MouseEvent e){}//Needed for MouseMotionListener interface.
    public void mouseDragged(MouseEvent e){}// "    "      "	"	".
    public void mouseExited(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseClicked(MouseEvent e)
    {
      Object o = e.getSource();
      JButton b = null;
      b = (JButton) o;
      if(e.getClickCount() == 1)
        display("You pressed "+b.getText());
    }
  }  
}

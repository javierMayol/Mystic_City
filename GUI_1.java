import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class GUI_1 implements UserInterface
{
  private static JFrame f;
  private JTextArea area;
  private JPanel p;
  private JButton b;
  private keyboardScanner keyboard;
  private String cmd;
  private MouseEvent event;

  public GUI_1()
  {
    keyboard = keyboard.getInstance();
    cmd = new String();
    f = new JFrame();
    area = new JTextArea(5, 5);
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

    b = new JButton("INVE");
    b.addActionListener(new GUIListener());
    b.addMouseListener(new GUIListener());
    p.add(b); 
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

  public String getLine()
  {
    String out = cmd;
    if(out.equalsIgnoreCase("GET"))
      return out +" leather bag";
    else if(out.equalsIgnoreCase("GO"))
    {
      out = "GO N";
      return out;
    }
    else if(out.equalsIgnoreCase("TALK"))
      return out + " N";
    else if(out.equalsIgnoreCase("USE"))
      return out + " N";
    return out; 
  } 

  class GUIListener extends JComponent implements MouseListener, ActionListener
  {
    public void actionPerformed(ActionEvent e){}
    public void mouseMoved(MouseEvent e){}//Needed for MouseMotionListener interface.
    public void mouseDragged(MouseEvent e){}// "    "      "	"	".
    public void mouseExited(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseReleased(MouseEvent e)
    {
      cmd = "";
    }
    public void mousePressed(MouseEvent e)
    {
      Object o = e.getSource();
      JButton button = (JButton) o;
      cmd = button.getText();
    }
    public void mouseClicked(MouseEvent e){}
  }  
}

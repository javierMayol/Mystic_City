/*
  Javier Mayol
  cmayol
  GUI_1 implementation 
  follows a Bridge design pattern.
  Interfaces:
	UserInterface
    In GUIListener class :
	ActionListener
	MouseListerner
  GUIListener extends JComponents.

  The initialization takes care of the creation
  of panels, text fields, buttons, and frames.
  Once loadded this GUI displays the following:
  * A read-only text area.
  * A text field that allows to input command as well.
	Once the correct command is displayed in the text field,
  	whether using the button or directly typing the command,
	the command gets executed by pressing the ENTER key in
	the keyboard or by clicking ENTER in the button panel.
  * A button for each command.
  * A button for each artifact in the inventory of the player.
*/
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class GUI_1 implements UserInterface
{
  private static JFrame f;
  private JTextArea area;
  private JTextField text;
  private JPanel p;
  private JButton b;
  private keyboardScanner keyboard;
  private String cmd;
  private MouseEvent event;
  private static String[] PA;

  public GUI_1()
  {
    keyboard = keyboard.getInstance();
    cmd = new String();
    f = new JFrame();
    text = new JTextField();
    text.addActionListener(new GUIListener());
    area = new JTextArea(5, 5);
    area.setFont(new Font("SanSerif", Font.PLAIN, 13));
    area.setEditable(false);
    p = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
    f.setLayout(new GridLayout(3, 1, 5, 15));

    f.add(new JScrollPane(area), BorderLayout.CENTER);
    f.add(new JScrollPane(text), BorderLayout.CENTER);
    //Buttons names.
    String [] button_label = new String[] {"GO", "GET","USE","LOOK","TALK","ASK","TRADE","INVE","EXIT","ENTER","CLEAR"};

    for(int i = 0; i < button_label.length ; i++)
    {
        b = new JButton(button_label[i]);
        b.addMouseListener(new GUIListener());
        p.add(b);
    }
    f.add(p);
    //Coordinate names.
    String [] coord = new String[]{"N","S","E","W","U","D","NE","NW","SE","SW","NNE","NNW","ENE","WNW","ESE","WSW","SSE","SSW"};

    for(int i = 0; i < coord.length ; i++)
    {
        b = new JButton(coord[i]);
        b.setPreferredSize(new Dimension(40, 20));
        b.addMouseListener(new GUIListener());
        p.add(b);
    }
    f.add(p);
    try{
      for(int i = 0; i < PA.length ; i++)
      {
         b = new JButton(PA[i]);
         b.setPreferredSize(new Dimension(150, 30));
         b.addMouseListener(new GUIListener());
         p.add(b);
      }
      if(PA.length > 0)
        f.add(p);
    
    }catch(NullPointerException e){}
 
    f.add(p);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setSize(920, 620);
    f.setResizable(false);
    f.setVisible(true);
  }

  //Display text to the read only text area.
  public void display(String s)
  {
    area.setText(s);
  }

  public void displayPrompt(String s)
  {
    f.setTitle(s); 
  }    
  
  //Executes commands.
  public String getLine()
  {
    String out = cmd;
    return out; 
  } 

  //Populate Player's artifacts array for purpose of creating 
  //buttons for them. The argument get pass by artNames() method
  //in the Player class.
  public static void gettingArtifacts(String[] list)
  {
    PA = list;
  }
  
  //Class to implement mouse and action events.
  class GUIListener extends JComponent implements MouseListener, ActionListener
  {
    private JButton button;
    String pre;
    String post;
    public GUIListener()
    {
      button = new JButton();
      pre = new String();
      post = new String();
    }

    public void actionPerformed(ActionEvent e)
    {
      String textField = text.getText();
      cmd = textField;
      pre = "";
      post = "";
    }
    public void mouseMoved(MouseEvent e){}//Needed for MouseMotionListener interface.
    public void mouseDragged(MouseEvent e){}// "    "      "	"	".
    public void mouseExited(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseReleased(MouseEvent e)
    {
      Object o = e.getSource();
      button = (JButton) o;
      //Command LOOK and INVE should allow the player to stay in the same round.
      if(button.getText().equals("LOOK") || button.getText().equals("INVE"))
      {
        pre = "";
        post = "";
        cmd = button.getText();
      }
      if(button.getText().equals("CLEAR"))
      {
        pre ="";
        post = "";
        text.setText("");
      }
      text.setText(pre+" ");
      cmd = "";
    }
    public void mousePressed(MouseEvent e)
    {
      Object o = e.getSource();
      button = (JButton) o;
      if(button.getText().equals("EXIT"))
      {
        cmd = "Q";
        System.exit(0); 
      }
      if(button.getText().equals("ENTER"))
      {
        post = text.getText()+"\n";
        cmd = post;
        f.dispose();//Close frame after the command is sent for execution.
      }
      pre = text.getText();
      pre += " "+button.getText();
    }
    public void mouseClicked(MouseEvent e){}
  }  
}

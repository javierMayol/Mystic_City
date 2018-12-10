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
  private JFrame f;
  private JTextArea area;
  private JTextField text;
  private JPanel p;
  private JButton b;
  private keyboardScanner keyboard;
  private String cmd;
  private String buffer;
  private boolean bufferReady;
  private MouseEvent event;
  private JOptionPane pop;
  private static String[] PA;
  public static boolean pop_window = false;
  public GUI_1()
  {
    bufferReady =  false;
    buffer = new String();
    cmd = new String();
    keyboard = keyboard.getInstance();
    f = new JFrame();
    text = new JTextField();
    text.addActionListener(new GUIListener());
    area = new JTextArea(5, 5);
    area.setFont(new Font("SanSerif", Font.PLAIN, 13));
    area.setEditable(false);
    f.setLayout(new GridLayout(5, 1, 5, 5));
    //f.setLayout(new GridBagLayout());
    f.add(new JScrollPane(area), BorderLayout.CENTER);
    f.add(new JScrollPane(text), BorderLayout.CENTER);
    //Panel Grid Bag
    p =  new JPanel(new GridBagLayout());  //new FlowLayout(FlowLayout.CENTER, 5, 5));    //Coordinate names.
    
    GridBagConstraints c = new GridBagConstraints();
    //Buttons names.
    String [] button_label = new String[] {"GO", "GET","USE","LOOK","DROP","TALK","ASK","TRADE","INVE","EXIT","ENTER","CLEAR"};
    int l = 0;
    for(int i = 0; i < 4 ; i++)
    {
      for(int j = 0; j < 3; j++)
      {
        b = new JButton(button_label[l]);
        b.addMouseListener(new GUIListener());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = j;
        c.gridy = i;
        p.add(b, c);
        l++;
      }
    }
    f.add(p);  
 
    //Coordinate names.
    String [] coord = new String[]{"N","S","E","W","U","D","NE","NW","SE","SW","NNE","NNW","ENE","WNW","ESE","WSW","SSE","SSW"};

    p =  new JPanel(new GridBagLayout());
    l = 0;
    for(int i = 0; i < 3 ; i++)
    {
      for(int j = 0; j < 6; j++)
      {
        b = new JButton(coord[l]);
        b.setPreferredSize(new Dimension(40, 20));
        b.addMouseListener(new GUIListener());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 0.5;
        c.gridx = j;
        c.gridy = i + 4;
        p.add(b, c);
  	l++;
      }
    }
    f.add(p);
    p =  new JPanel(new GridLayout(3, 10, 5, 5));  //new FlowLayout(FlowLayout.CENTER, 5, 5)); 
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
    f.setSize(465, 700);
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

  public static void setWindow(boolean b)
  {
    pop_window = b;
  }

  public String popUpWindow()
  {
     String ret = null;
     try{
      Object[] OPA = Arrays.copyOf(PA, PA.length, Object[].class);
      Object select = null;
      f.setVisible(false);
      String riddle = area.getText();
      String label = f.getTitle();
      if(label.equals("Oh no!! The OGRE IS HERE!!"))
      {
        select = JOptionPane.showInputDialog(null,riddle,label, JOptionPane.QUESTION_MESSAGE, null, OPA, OPA[0]);
        ret = (String) select;
      }
      else
        ret = JOptionPane.showInputDialog(null,riddle,label, JOptionPane.QUESTION_MESSAGE);
      pop_window = false;
      f.dispose();
      //PA = null;
      }
      catch(NullPointerException e){}
      return ret;
   }
  //Executes commands.
  public String getLine()
  {
    if(pop_window) 
    {
      String [] tmp = Arrays.copyOf(PA, PA.length, String[].class);
      cmd = popUpWindow();
      PA = tmp;
    }
/*
    else 
    {
      try{
        while(!bufferReady)
          Thread.sleep(500);
        synchronized(buffer)
        {
          cmd = buffer;
          bufferReady = false;
        }
      }
      catch(InterruptedException e){}
    }
*/
    return cmd; 
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

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
  private JPanel p;
  private JButton b;
  private JTextArea area;
  private JTextField text;
  private String cmd;
  private String buffer;
  private boolean bufferReady;
  private static Object OptionPaneObj;
  private static String[] PA;
  private static boolean pop_window = false;
  private static ImageIcon icon;

  public GUI_1()
  {
    bufferReady =  false;
    buffer = new String();
    OptionPaneObj = new Object();
    cmd = new String();
    f = new JFrame();
    text = new JTextField();
    text.addActionListener(new GUIListener());
    area = new JTextArea(5, 5);
    area.setFont(new Font("SanSerif", Font.PLAIN, 13));
    area.setEditable(false);
    f.setLayout(new GridLayout(5, 1, 5, 5));
    f.add(new JScrollPane(area), BorderLayout.CENTER);
    f.add(new JScrollPane(text), BorderLayout.CENTER);

    p =  new JPanel(new GridLayout(4, 3, 5, 5)); 
    //Buttons names.
    String [] button_label = new String[] {"GO", "GET","USE","LOOK","DROP","TALK","TRADE","INVE","CLEAR","EXIT","ENTER","GUI 0"};
    int l = 0;
    for(int i = 0; i < 4 ; i++)
    {
      for(int j = 0; j < 3; j++)
      {
        b = new JButton(button_label[l]);
        b.addMouseListener(new GUIListener());
        p.add(b);
        l++;
      }
    }
    f.add(p);  
 
    //Coordinate names.
    String [] coord = new String[]{"NW","NNW","N","NNE","NE","WNW","ENE","W","E","WSW","ESE","SW","SSW","S","SSE","SE","U","D"};

    p =  new JPanel(new GridLayout(5,5,5,5));
    l = 0;
    for(int i = 0; i < 5 ; i++)
    {
      for(int j = 0; j < 5; j++)
      {
        if((i==1)&&(j==2))
        {
          b = new JButton("U");
          b.setPreferredSize(new Dimension(40, 20));
          b.addMouseListener(new GUIListener());
          p.add(b);
        }
        else if((i==3)&&(j==2))
        {
          b = new JButton("D");
          b.setPreferredSize(new Dimension(40, 20));
          b.addMouseListener(new GUIListener());
          p.add(b);
        }
        else if((j%4!=0)&&((i>0)&&(i<4)))
        {
          b = new JButton("");
          b.setVisible(false); 
          p.add(b);
        }
        else
        {
          b = new JButton(coord[l]);
          b.setPreferredSize(new Dimension(40, 20));
          b.addMouseListener(new GUIListener());
          p.add(b);
  	  l++;
       }
      }
    }
    f.add(p);
    
    //Populate panel with artifact names of the player.
    p =  new JPanel(new GridLayout(3, 10, 5, 5));
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
    f.setSize(460, 700);
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

  //gettingArtifacts() method populates PA[] with player's artifact. 
  public static void gettingArtifacts(String[] list)
  {
    PA = list;
  }

  // Sets OptionPaneObj var with the returned value of JOptionPane dialog window. 
  // Pops a JOptionPane window when the getLine() method gets called.
  public static void setOptionPane(String disp, String label, ImageIcon icon, Object[] OPA)
  {
    try{
      pop_window = true;
      f.setVisible(false);
      if(OPA == null)
        OptionPaneObj = JOptionPane.showInputDialog(null,disp,label, JOptionPane.PLAIN_MESSAGE, icon, null, null);
      else 
        OptionPaneObj = JOptionPane.showInputDialog(null,disp,label, JOptionPane.PLAIN_MESSAGE, icon, OPA, OPA[0]);
    }catch(NullPointerException e){}
    catch(ArrayIndexOutOfBoundsException e){}
  }

  //Executes commands.
  public String getLine()
  {
    try{
      if(pop_window){ 
        cmd = (String) OptionPaneObj;
        pop_window = false;
      }
    }catch( ClassCastException e){}
    return cmd; 
  } 

  //*************** Nested class to implement mouse and action events.********************//
  class GUIListener extends JComponent implements MouseListener, ActionListener
  {
    private JButton button;
    private String pre;
    private String post;
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

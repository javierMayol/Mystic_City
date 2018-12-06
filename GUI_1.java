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
    p = new JPanel(); //new FlowLayout(FlowLayout.CENTER, 5, 5));
    f.setLayout(new GridLayout(3, 1, 5, 15));

    f.add(new JScrollPane(area), BorderLayout.CENTER);
    f.add(new JScrollPane(text), BorderLayout.CENTER);
    //Buttons names.
    String [] button_label = new String[] {"GO", "GET","USE","LOOK","TALK","ASK","TRADE","INVE","EXIT","ENTER"};

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
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setSize(850, 620);
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
    return out; 
  } 

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
      }
      pre = text.getText();
      pre += " "+button.getText();
      if(pre.equals("LOOK") || pre.equals("INVE"))
        cmd = pre;
    }
    public void mouseClicked(MouseEvent e){}
  }  
}

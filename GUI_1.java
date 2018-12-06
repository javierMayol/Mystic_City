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
    text = new JTextField("ENTER INPUT");
    text.addActionListener(new GUIListener());
    area = new JTextArea(5, 5);
    area.setFont(new Font("SanSerif", Font.PLAIN, 13));
    area.setEditable(false);
    p = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
    f.setLayout(new GridLayout(4, 1, 5, 5));

    //button dimensions.
    String [] button_label = new String[] {"GO", "GET","USE","LOOK","TALK","ASK","TRADE","INVE","EXIT","ENTER"};

    p.add(text);

    for(int i = 0; i < button_label.length ; i++)
    {
        b = new JButton(button_label[i]);
        b.addMouseListener(new GUIListener());
        p.add(b);
    }
/*
    b = new JButton("GET");
    b.addMouseListener(new GUIListener());
    //p.add(b);

    b = new JButton("USE");
    b.addMouseListener(new GUIListener());
    p.add(b);

    b = new JButton("LOOK");
    b.addMouseListener(new GUIListener());
    p.add(b);

    b = new JButton("TALK");
    b.addMouseListener(new GUIListener());
    p.add(b);

    b = new JButton("ASK");
    b.addMouseListener(new GUIListener());
    p.add(b);

    b = new JButton("TRADE");
    b.addMouseListener(new GUIListener());
    p.add(b);
  
    b = new JButton("EXIT");
    b.addMouseListener(new GUIListener());
    p.add(b); 

    b = new JButton("ENTER");
    b.addMouseListener(new GUIListener());
    p.add(b); 

    b = new JButton("INVE");
    //b.addActionListener(new GUIListener());
    b.addMouseListener(new GUIListener());
    p.add(b); 
*/
    f.add(new JScrollPane(area), BorderLayout.CENTER);
    f.add(new JScrollPane(text), BorderLayout.CENTER);
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
    return out; 
  } 

  class GUIListener extends JComponent implements MouseListener, ActionListener
  {
    private JButton button;
    private boolean pressed;
    String pre;
    String post;
    public GUIListener()
    {
      button = new JButton();
      pressed = false;
      pre = new String();
      post = new String();
    }

    public void actionPerformed(ActionEvent e)
    {
      String textField = text.getText();
      cmd = textField;
      pre = "";
      post = "";
      display(textField);
    }
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
      pressed = true;
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
      pre = button.getText();
      text.setText(pre+" ");
      if(pre.equals("LOOK") || pre.equals("INVE"))
        cmd = pre;
    }
    public void mouseClicked(MouseEvent e){}
  }  
}

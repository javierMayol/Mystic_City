 /*
 *  Neel Patel 	NetID : npate315	UIN : 674004711
 * Keval Patel
 * Javier Mayol NetID : cmayol2		UIN : 671352495
 */
import java.util.*;
import javax.swing.*;

public class Riddle 
{
  private static Character Ogre;
  private Character victim;
  private keyboardScanner keyboard;
  private static LinkedList<String>riddles = new LinkedList<String>();//Try includding riddles in the GDF document.
  private static LinkedList<String>answers = new LinkedList<String>();
  private static IO io;
  private static String right_answer;

  public Riddle()
  {
    keyboard = keyboard.getInstance();
    io = new IO(); //victim.get_io();
  }

  public Riddle(Character A)
  {
    victim = A;
    keyboard = keyboard.getInstance();
    this.io = new IO();
    io.selectInterface(io.TEXT); //victim.get_io();
  }

  public static Character getOgre()
  {
     if(Ogre == null)
       Ogre = Character.getCharacterByID(2);  
     return Ogre;
  }

  public void execute()
  {
    if(Ogre ==  null ) getOgre();
    Random rand = new Random();
    int index = rand.nextInt(riddles.size());
    String display = "\n\nHA HA HA! Let's see if you really know this stuff!\n";
    display += riddles.get(index);
    //display += riddle;
    right_answer = answers.get(index);
    io.selectInterface(victim.getPlayerInterface());
    ImageIcon icon = new ImageIcon("GreenOgre.png");
    Object[] options = {"A","B","C","D","E"};
    if(victim.getPlayerInterface() == io.GUI_1)
      GUI_1.setOptionPane(display, "Oh no!! THE OGRE IS HERE!!", icon, options);
    else
      io.display(display);
    String answer = io.getLine();
    Answer_method(answer);
  }

  public void Answer_method(String answer)
  {
    if(!(answer.equalsIgnoreCase(right_answer)))
    {
      loot(victim.emptyInventory());
      io.display("\n\n\n\n\n\nHA HAHA HA!!! YOU LOSE !!!!!!\n");
      victim.message("\n>You have a new message:\n\n  "+Ogre.name()+" has stolen everything from you.");
    }
    else
    {
      getPrize();
    }
  }

  private String pickRiddle()
  {
     Random rand = new Random();
     int index = rand.nextInt(riddles.size());
     String riddel = riddles.get(index);
     System.out.println(riddel);
     String answer = answers.get(index);
     return answer;
  }

  private void loot(LinkedList<Artifact>things)
  {
    for(Artifact i : things)
      Ogre.addArtifact(i);
  }

  private void getPrize()
  {
    if(!Ogre.hasSomething())
    {
      io.display("YOU WON! But the Ogre has nothing for you :(");
      return;
    }
    String display = "\n\n\n\n\n\nYikes!! YOU'RE SO SMART.\n\n";
    display +="YOU WON! You get to pick one item from the Ogre's inventory\n";
    display += Ogre.viewInventory();
    //String[] OgreThings = Ogre.artNames();
    Object[] options = Arrays.copyOf(Ogre.artNames(), Ogre.artNames().length, Object[].class);
    ImageIcon icon = new ImageIcon("GreenOgre.png");
    GUI_1.setOptionPane(display, "You Won!!", icon, options);
    String prize = io.getLine();
    try{
      Drop thing = new Drop(Ogre, prize.trim());
      thing.execute();
      Get that = new Get(victim, prize.trim());
      that.execute();
    }catch(NullPointerException e){};
  }

  public static void addRiddle(Scanner scan)
  {
    String line = CleanLineScanner.getCleanLine(scan);
    Scanner lineScan = new Scanner(line);

    int lines = lineScan.nextInt();
    String riddle = "";
    for(int i = 0; i < lines; i++)
      riddle += CleanLineScanner.getCleanLine(scan)+"\n";
    
    line = CleanLineScanner.getCleanLine(scan);
    lineScan = new Scanner(line);

    String answer = lineScan.next();

 //   System.out.println(riddle+"\n\n"+answer);

    riddles.add(riddle);
    answers.add(answer.trim());
  }
}

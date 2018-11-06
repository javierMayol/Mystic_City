 /*
 *  Neel Patel 	NetID : npate315	UIN : 674004711
 * Keval Patel
 * Javier Mayol NetID : cmayol2		UIN : 671352495
 */
import java.util.*;

public class Riddle implements Move
{
  private static Character Ogre;
  private Character victim;
  private keyboardScanner keyboard;
  private static LinkedList<String>riddles = new LinkedList<String>();//Try includding riddles in the GDF document.
  private static LinkedList<String>answers = new LinkedList<String>();

  public Riddle(){}

  public Riddle(Character A)
  {
    victim = A;
    keyboard = keyboard.getInstance();
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
    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n"); 
    System.out.println("HA HA HA! Let's see if you really know this stuff!\n");//Change for something better.
    String right_answer = pickRiddle();
    String answer = keyboard.getInput();
    if(!(answer.equalsIgnoreCase(right_answer)))
    {
      loot(victim.emptyInventory());
      System.out.println("\n\n\n\n\n\nHA HAHA HA!!! YOU LOSE !!!!!!\n");
      victim.message("\n>You have a new message:\n\n  "+Ogre.name()+" has stolen everything from you.");
    }
    else
      System.out.println("\n\n\n\n\n\nYikes!! YOU'RE SO SMART.\n");
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

    System.out.println(riddle+"\n\n"+answer);

    riddles.add(riddle);
    answers.add(answer.trim());
  }
}

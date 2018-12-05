 /*
 *  Neel Patel 	NetID : npate315	UIN : 674004711
 * Keval Patel
 * Javier Mayol NetID : cmayol2		UIN : 671352495
 */
import java.util.*;

public class Riddle implements Move
{
  private static Character Ogre;
  private static Character victim;
  //private keyboardScanner keyboard;
  private static LinkedList<String>riddles = new LinkedList<String>();//Try includding riddles in the GDF document.
  private static LinkedList<String>answers = new LinkedList<String>();
  private IO io;
  private static String right_answer;
  public Riddle(){}

  public Riddle(Character A)
  {
    victim = A;
    this.io = new IO();
    this.io.selectInterface(io.TEXT);
    right_answer = "";
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
    String display = "\n\n\n\n\n\n\n\n\n\n\n\n"; 
    display +="HA HA HA! Let's see if you really know this stuff!\n\n";//Change for something better.
    //GUI pickRiddle method.
    Random rand = new Random();
    int index = rand.nextInt(riddles.size());
    String riddle = riddles.get(index);
    this.right_answer = answers.get(index);
    display += riddle;
    victim.message(display);
    victim.needResponse(Ogre);
  }
  public static void Answer(String s)
  {
    String answer = s; //victim.io.getLine();
    if(!(answer.equalsIgnoreCase(right_answer)))
    {
      loot(victim.emptyInventory());
      victim.message("\n\n\n\n\n\nHA HAHA HA!!! YOU LOSE !!!!!!\n\n");
      victim.message("\n>You have a new message:\n\n  "+Ogre.name()+" has stolen everything from you.");
    }
    else
    {
      victim.message("\n\n\n\n\n\nYikes!! YOU'RE SO SMART.\n\n");
      getPrize();
    }
  }

  private String pickRiddle()
  {
     Random rand = new Random();
     int index = rand.nextInt(riddles.size());
     String riddle = riddles.get(index);
     io.display(riddle);
     String answer = answers.get(index);
     return answer;
  }

  private static void loot(LinkedList<Artifact>things)
  {
    for(Artifact i : things)
      Ogre.addArtifact(i);
  }

  private static void getPrize()
  {
    if(!Ogre.hasSomething())
    {
      victim.message("YOU WON! But the Ogre has nothing for you :(");
      return;
    }
    victim.message("YOU WON!"); // You get to pick one item from the Ogre's inventory");
    //Ogre.viewInventory();
    String prize = NPC.randomArtifact(Ogre.shuffleArtifacts());
    Drop thing = new Drop(Ogre, prize.trim());
    thing.execute();
    Get that = new Get(victim, prize.trim());
    that.execute();
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

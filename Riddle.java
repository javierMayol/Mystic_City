import java.util.*;

public class Riddle implements Move
{
  private static Character Ogre;
  private Character victim;
  private keyboardScanner keyboard;
  private LinkedList<String>riddles;//Try includding riddles in the GDF document.
  private LinkedList<String>answers;

  public Riddle(){}

  public Riddle(Character A)
  {
    victim = A;
    keyboard = keyboard.getInstance();
    riddles = new LinkedList<String>();
    answers = new LinkedList<String>();
    addRiddle();
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
      loot(victim);
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

  private void loot(Character poor_guy)
  {
    int n = NPC.nameListSize();
    String item = new String();

    Drop dropped = new Drop();
    Get getit = new Get();
    
    for(int i = 0; i < n; i++)
    {
       item = NPC.removeItemName();
//       System.out.println("Artifact name ogre's list "+item);
       if(!poor_guy.hasSomething()) break;
         dropped = new Drop(poor_guy, item);
         dropped.execute();
       if(dropped == null)
        continue;
       getit = new Get(Ogre, item);
       getit.execute();
       if(getit == null)
         continue;
       NPC.addItemName(item);
       Ogre.display();
    }
  }

  private void addRiddle()
  {
     String riddle1 = "Which of the following is NOT true of static class\nmethods?\nA. Static class methods can be called through any\ninstance of the class.\nB. Static class methods can be called through the\nname of the class, without using a class object.\nC. Static class methods can only access static data of\nthe class. ( Unless an object is passed in. )\nD. Static class methods can only access public and\nprotected data of the class.\nE. Static class methods do not have a “this” reference\n";

    String riddle2 = "What is the difference between Objects and Classes?\nA. In Java, Objects are any descendent of the\nObject class, and Classes descend from Class.\nB. Java programmers write Classes, and C++\nprogrammers write Objects.\nC. Objects are instantiations of Classes.\nD. Objects in mirrors may be closer than they\nappear.\nE. There is no difference. The two terms are\nsynonyms.";
    riddles.add(riddle1);
    riddles.add(riddle2);
    answers.add("D");
    answers.add("C");
  }
}

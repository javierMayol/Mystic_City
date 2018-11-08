 /*
 *  Neel Patel 	NetID : npate315	UIN : 674004711
 * Keval Patel
 * Javier Mayol NetID : cmayol2		UIN : 671352495
 */
import java.util.*;

public class UI implements DecisionMaker 
{
  private Character character;
  private Place place;
  private StringPairCompare word;
  private keyboardScanner keyboard;

  //Constructor
  public UI(){}
  public Move getMove(Character client, Place where)
  {
     this.character = client;
     this.place = where; 
     keyboard = keyboard.getInstance();    
     return this.execCommand(keyboard.commandLinePrompt(character.name()));
  }

  private String getCommand(String userInput)
  {
     String cmd = new String();
     try{
       word = word.getInstance();
       cmd = word.getWordAt(1, userInput).toUpperCase();
     }
     catch(NullPointerException e){
     }
     return word.getString().toUpperCase();
  }

  private String getArgument(String userInput)
  {
     String args = new String();
     try{
       word = word.getInstance();
       args = word.getWordsFrom(2, userInput);
     }
     catch(NullPointerException e){
     }
     //System.out.println("getArg "+userInput);
     return word.getString().replaceAll("\\s+"," ").trim();
  }

  private Move execCommand(String userInput)
  {
     String str = getCommand(userInput);
     String arg;

    //If the user input EXIT or QUIT, the game is terminated.
    if("QUIT".equalsIgnoreCase(str)||"EXIT".equalsIgnoreCase(str)||"Q".equalsIgnoreCase(str))
      return new Exit(character);

    //Display information of the current place.
    else if("LOOK".equalsIgnoreCase(str))
    {
      info();
      return null;
    }
     
    else if("GO".equalsIgnoreCase(str))
    {
      arg = getArgument(userInput);
      return new Go(character, arg.toUpperCase());
    }

    else if("GET".equalsIgnoreCase(str))
    {
      arg = getArgument(userInput);
      return new Get(character, arg);
    }

    else if("DROP".equalsIgnoreCase(str))
    {
      arg = getArgument(userInput);
      return new Drop(character, arg);
    }

    else if("USE".equalsIgnoreCase(str))
    {
      arg = getArgument(userInput);
      if(arg == null) return null;
      return new Use(character, arg.toLowerCase());
    }
 
    else if("TALK".equalsIgnoreCase(str))
    {
      arg = getArgument(userInput);
     // System.out.println("TALK "+arg);
      return new Message(character, arg);
    } 
 
    else if("ASK".equalsIgnoreCase(str))
    {
      arg = getArgument(userInput);
      //System.out.println("ASK "+arg);
      return new Request(character, arg);
    } 
 
    else if("TRADE".equalsIgnoreCase(str))
    {
      arg = getArgument(userInput);
      Character requester = character.current.listening(arg.toLowerCase());
      if(requester == null) return null;
      return new Trade(character, requester);
    } 
 
    else if("I".equalsIgnoreCase(str)||"INVE".equalsIgnoreCase(str)||"INVENTORY".equalsIgnoreCase(str))
      character.viewInventory();

    return null;
  }

  private void info()
  {
   System.out.println("\nSOME COMMANDS:\nJust type GO and the direction you want to travel to.\n\tExample: 'GO South' or 'GO S'.");
   System.out.println("To get an item from the current place, type 'GET' SPACE 'name of the artifact'.");
   System.out.println("To drop an item in the current place, type 'DROP' SPACE 'name of the artifact'.");
   System.out.println("If you want to use a key, type 'USE' SPACE 'name of the artifact key'.");
   System.out.println("To know what items you have in the invetory just type 'Inventory' or 'inve'.");
   System.out.println("Type 'Quit', 'Exit' or 'q' to exit the game.\n");   
   character.getCurrentPlace().display();
  }
}
  

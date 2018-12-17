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
  //private keyboardScanner keyboard;
  private IO io;

  //Constructor
  public UI()
  {
    this.io = new IO();
    this.io.selectInterface(io.TEXT);
  }
  public Move getMove(Character client, Place where)
  {
     this.character = client;
     this.place = where;
     this.io.displayPrompt(character.name()+" "+character.pInter()); 
     return this.execCommand(io.getLine());
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
     catch(ArrayIndexOutOfBoundsException d){
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

    else if("GUI".equalsIgnoreCase(str))
    {
      try {
        arg = getArgument(userInput);
        if(Integer.parseInt(arg) == 0) 
          character.setInterface(io.TEXT);
        if(Integer.parseInt(arg) == 1) 
          character.setInterface(io.GUI_1);
        if(Integer.parseInt(arg) == 2) 
          character.setInterface(io.GUI_2);
        if(Integer.parseInt(arg) == 3) 
          character.setInterface(io.GUI_3);
        if(Integer.parseInt(arg) > 3)
	  System.out.println("GUI not implemented."); 
      }
      catch(NumberFormatException e)
      {
        return null;
      }
      return null;
    }

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
      if(character.getPlayerInterface() == io.GUI_1)
      {
        io.selectInterface(character.getPlayerInterface());
        Object [] options = Arrays.copyOf(character.current.artNames(), character.current.artNames().length, Object[].class);
        GUI_1.setOptionPane("Place artifacts","Select artifact",null, options);
        arg = io.getLine();
      }
      else
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
      return new Trade(character); 
    } 
 
    else if("I".equalsIgnoreCase(str)||"INVE".equalsIgnoreCase(str)||"INVENTORY".equalsIgnoreCase(str))
      character.viewInventory();

    return null;
  }

  private void info()
  {
    String display = new String();
    display = "\nSOME COMMANDS:\nJust type GO and the direction you want to travel to.\n\tExample: 'GO South' or 'GO S'.\n";
    display += "To get an item from the current place, type 'GET' SPACE 'name of the artifact'.\n";
    display += "To drop an item in the current place, type 'DROP' SPACE 'name of the artifact'.\n";
    display += "If you want to use a key, type 'USE' SPACE 'name of the artifact key'.\n";
    display +="To know what items you have in the invetory just type 'Inventory' or 'inve'.\n";
    display += "Type 'Quit', 'Exit' or 'q' to exit the game.\n";   
    String displayPlayer = character.getCurrentPlace().display();
    io.display(displayPlayer);
    display += displayPlayer;
    System.out.println(display);
  }
}
  

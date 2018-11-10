 /*
 *  Neel Patel 	NetID : npate315	UIN : 674004711
 * Keval Patel
 * Javier Mayol NetID : cmayol2		UIN : 671352495
 */
import java.util.*;

public class Player extends Character
{
  private static int player_num = 0;
 
  public Player(Scanner scan) 
  {
    super(scan);
    decider = new UI();
    player_num++;
  } 

  public Player(Place place, int id, int age, String name_character, String desc)
  {
    super(place, id, age, name_character, desc);
    decider = new UI();
    player_num++;
  }
  //Step by step Character constructor. Prints a prompt to 
  //command line and waits for the user to input a string.
  public static void createNewPlayer()
  {
    if(characters.size() > 15)
    {
      System.out.println("There are already too many characters");
      return;
    }

    keyboard = keyboard.getInstance(); //Initialize keyboard;

    //1. Name the character.
    System.out.println("How do you want to name your character?");
    String inputName = keyboard.getInput();

    //2. Describe the character.
    System.out.println("In few words describe "+inputName+".");
    String inputDesc = keyboard.getInput();
 
    //3. Randomly assign a place for the character.
    Place where = Place.chooseRandomPlace();
  
    //4. Generate a random ID
    Random rand = new Random();
    int ID_;
    ID_ = rand.nextInt(50);

    //5. Age of the character.
    System.out.println("How old is "+inputName);
    int age = keyboard.getInt();

    while(characters.containsKey(ID_))
      ID_ = rand.nextInt(50);
    //5. Finish.

    new Player(where, ID_, age, inputName, inputDesc);
    System.out.println("We have created "+inputName+". It lives in "+where.name()+".");
  }

  public static void minPlayerNum(int players_qty)
  {
    if(players_qty > 2) 
    {
      for(int i = 1; i < players_qty; i++)
        createNewPlayer();
    }
    else if(players_qty > 15)
      System.out.println("Too many players for this humble game.");
  }

  public static int retrievePlayer_num()
  {
    return player_num;
  }

  public static void decreasePlayer_num()
  {
    player_num--;
  }

  
  public LinkedList<Artifact> emptyInventory()
  {
    LinkedList<Artifact> loot = new LinkedList<Artifact>(inventory.values());
    this.inventory.clear();
    this.points = 0;
    this.artifacts_weight = 0;
    return loot;    
  }
 
  public void makeMove ()
  {
    Move move = decider.getMove(this, current);
    while(move == null)
      move = decider.getMove(this, current);
    move.execute();
  }
} 

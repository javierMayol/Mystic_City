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
    this.io = new IO();
    this.io.selectInterface(io.TEXT);
  } 

  public Player(Place place, int id, int age, String name_character, String desc)
  {
    super(place, id, age, name_character, desc);
    decider = new UI();
    player_num++;
    this.io = new IO();
    this.io.selectInterface(io.TEXT);
  }
  //Step by step Character constructor. Prints a prompt to 
  //command line and waits for the user to input a string.
  public static void createNewPlayer()
  {
    if(characters.size() > 15)
    {
      io.display("There are already too many characters.");
      return;
    }

    int id = player_num;

    //1. Name the character.
    io.display("How do you want to name your character?");
    String inputName = io.getLine();
    if(inputName == null || inputName.isEmpty())//If the user doesn't input a name.
      inputName = Integer.toString(id++);//We create one to avoid error of player counts.

    //2. Describe the character.
    io.display("In few words describe "+inputName+".");
    String inputDesc = io.getLine();
 
    //3. Randomly assign a place for the character.
    Place where = Place.chooseRandomPlace();
  
    //4. Generate a random ID
    Random rand = new Random();
    int ID_;
    ID_ = rand.nextInt(50);

    int cnt = 0, max = 4, age = 0;

    //5. Age of the character.
    io.display("How old is "+inputName+"?");
    while(true) 
    {
      try
      {
        age = Integer.parseInt(io.getLine());
        break;
      }
      catch(NumberFormatException e)
      {
        //If exception occurs, give the player another chance to enter int.
        io.display("Please, enter an integer for the age of "+inputName+".");
        continue;
      }
    }

    while(characters.containsKey(ID_))
      ID_ = rand.nextInt(50);

    //6. Finish.
    new Player(where, ID_, age, inputName, inputDesc);
    io.display("We have created "+inputName+". It lives in "+where.name()+".");
  }

  public static void minPlayerNum(int players_qty)
  {
    if(players_qty > 2) 
    {
      for(int i = 2; i < players_qty; i++)
        createNewPlayer();
    }
    else if(players_qty > 15)
      io.display("Too many players for this humble game.");
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

 /*
 * Neel Patel 	NetID : npate315	UIN : 674004711
 * Keval Patel
 * Javier Mayol NetID : cmayol2		UIN : 671352495
 */
import java.util.*;

abstract class Character
{
//****************************** Atributes ***************************************
  protected Place current;
  protected int ID;
  protected int age;
  protected String name;
  protected String description;
  //protected static HashMap<Integer, Character> characters = new HashMap<Integer, Character>();
  protected static HashMap<String, Character> characters = new HashMap<String, Character>();
  protected TreeMap<String, Artifact> inventory;
  //protected static keyboardScanner keyboard;
  protected StringPairCompare str_format;
  protected Printer printer;
  protected DecisionMaker decider;
  protected boolean artifact_handler;
  protected int artifacts_weight;
  protected boolean notification;
  protected boolean accept;
  protected boolean needResponse;
  protected String message;
  protected int points;
  private boolean player_out;
  protected static IO io;
  protected int playerInterface;
  private Character requester;
  protected boolean riddle;

//******************************* Constructors ************************************
  public Character(){}

  public Character(Scanner scan)
  {
    try
    {
      //while(!scan.hasNextInt())scan.nextLine();
      String line = CleanLineScanner.getCleanLine(scan);
      Scanner lineScan = new Scanner(line);

      this.current = Place.getPlaceByID(lineScan.nextInt());

      line = CleanLineScanner.getCleanLine(scan);
      lineScan = new Scanner(line);

      this.ID = lineScan.nextInt();
      this.age = lineScan.nextInt();

      lineScan.skip("[ \t]*");
      this.name = lineScan.nextLine();

      line = CleanLineScanner.getCleanLine(scan);
      lineScan = new Scanner(line);

      int n = lineScan.nextInt();
      this.description = "";
      for(int i = 0; i < n; i++)
        this.description += CleanLineScanner.getCleanLine(scan)+"\n";
      this.inventory = new TreeMap<String, Artifact>(String.CASE_INSENSITIVE_ORDER);
      //characters.put(ID, this);

      characters.put(Integer.toString(ID), this);
      this.current.addCharacter(this);

      if(this instanceof Player)
        this.current.setCurrentCharacter();
      this.str_format = str_format.getInstance();
      this.printer = printer.getInstance();
      //this.keyboard = keyboard.getInstance();
      this.player_out = false;
      this.artifact_handler = false;
      this.artifacts_weight = 0;
      this.notification = false;
      this.accept = false;
      this.message = new String();
      this.needResponse = false;
      this.points = 0;
      this.requester = null;
      this.riddle = false;
    }
    catch(Exception e) {
      System.err.println("Error : Verify that the file is correctly formatted to initialize Character class.");
    }
  }

  public Character(Place place, int id, int age, String name_character, String desc)
  {
    this.player_out = false;
    this.artifact_handler = false;
    this.artifacts_weight = 0;
    this.notification = false;
    this.accept = false;
    this.message = new String();
    this.points = 0;
    this.current = place;
    this.ID = id;
    this.age = age;
    this.name = name_character;
    this.description = desc;
    this.inventory = new TreeMap<String, Artifact>(String.CASE_INSENSITIVE_ORDER);
    //characters.put(ID, this);
    characters.put(Integer.toString(ID), this);
    this.current.addCharacter(this);
    if(this instanceof Player)
      this.current.setCurrentCharacter();
    this.str_format = str_format.getInstance();
    this.printer = printer.getInstance();
    this.requester = null;
    //this.keyboard = keyboard.getInstance();
  }

//****************************** Methods : atribute related *********************
  public static Character getCharacterByID(int ID)
  {
    return characters.get(Integer.toString(ID));
  }

  public String pInter()
  {
    switch(playerInterface){ 
      	case 0:
 	   return "TEXT";
	case 1:
	   return "GUI 1";
	case 2:
	   return "GUI 2";
	case 3:
	   return "GUI 3";
    }
    return null;
  }
  //getID() : Returns ID of character. Used in ..
  public int getID()
  {
    return this.ID;
  }

  //name() : Returns the name of the character.
  public String name()
  {
    return this.name.trim();
  }

  //removeCharacter() : Remove character if command was exit.
  public void removeCharacter()
  {
    characters.remove(this.ID);
  }

  //removePlayer() : Used in Game class to remove from Game's character collection.
//  abstract boolean removePlayer();

  //getCurrentPlace() : Verify is this method is necessay. Can be substitud by protected current.
  public Place getCurrentPlace()
  {
    return current;
  }

  public boolean is_out()
  {
    return player_out;
  }

  public boolean removePlayer()
  {
    this.player_out = true;
    return player_out;
  }

  public static Iterator<Character> getIterator()
  {
    return characters.values().iterator();
  }

//****************************** GUI or TextInterface implementor ***********************
  public void setInterface(int n)
  {
    playerInterface = n;
    io.selectInterface(n);
  }

  public IO get_io()
  {
    return io;
  }
  
  public int getPlayerInterface()
  {
    return playerInterface;
  }
//****************************** Methods : Artifact related *********************
  public boolean hasSomething()
  {
    return !inventory.isEmpty();
  }

  public void addArtifact(Artifact a)
  {
    this.artifacts_weight += a.size();
  //  if(this.age > 65){


    if(!artifact_handler && artifacts_weight >=20)
    {
      this.artifacts_weight -= a.size();
      current.addArtifact(a);
      if(this instanceof Player)
        io.display("\n\n\n\n\n\n  TOO MUCH WEIGHT. You'd need something to carry your items.\n");
      return;
    }
    this.points += a.value();
    inventory.put(a.name(), a);
  }

  public String hasThing(String key)
  {
    try{
      for(String k : inventory.keySet())
      {
        if(key.contains(k.toLowerCase()))
        return k;
      }
    }catch(NullPointerException e) {}
    return key;
  }

  public Artifact use(String thing)
  {
    if((thing = hasThing(thing)) != null)
      return inventory.get(thing);
    else if(this instanceof Player)
      for(String k : inventory.keySet())
      {
        thing = strCheck(k, thing);
        use(thing);
      }
    System.out.println(thing);
    return null;
  }

  public Artifact drop(String key)
  {
    if(inventory.containsKey(key.toLowerCase()))
    {
      Artifact removed = inventory.get(key);
      this.artifacts_weight -= removed.size();
      this.points -= removed.value();
      inventory.remove(key);
      return removed;
    }
    else if(this instanceof Player)
      for(String k : inventory.keySet())
      {
        key = strCheck(k, key);
        if(key.contains(k))
        {
          key = k;
          drop(key);
        }
      }
    if(this instanceof Player)
      io.display("\n\n\n\n\n\n  :ARTIFACT NOT FOUND IN THE INVENTORY.\n");
    return null;
  }

  public void canLift()
  {
    artifact_handler = true;
  }

  public boolean liftMore()
  {
    return artifact_handler;
  }

  public void addPoints(int value)
  {
     this.points += value;
  }

  //shuffleArtifacts() : To be used by NPC class mostly. Returns an artifact from inventory.
  public String shuffleArtifacts()
  {
    if(!inventory.isEmpty())
    {
      Random rand = new Random();
      List<String>keys = new ArrayList<String>(inventory.keySet());
      String thing = keys.get(rand.nextInt(keys.size()));
      return thing;
    }
    return null;
  }

  public void dropAll()
  {
    for(Artifact a : inventory.values())
    {
      Place random = Place.chooseRandomPlace();
      if(!random.name().equalsIgnoreCase("exit")||!random.name().equalsIgnoreCase("Nowhere"))
        random.addArtifact(a);
    }
    this.inventory = null;
    this.points = 0;
    this.artifacts_weight = 0;
  }
  //To be implemented differently in Player and NPC
  abstract LinkedList<Artifact> emptyInventory();

  public String[] artNames()
  {
    try{ 
       String[] arts = new String[inventory.size()];
       int in = 0;
       for(Artifact i : inventory.values())
       {
         arts[in] = i.name();
         in++;
       }
       return arts;
    }catch(NullPointerException e){}
    return null;
  }

  public int getPoints()
  {
    return points;
  }
//****************************** Methods : Move related *********************
  abstract void makeMove();

  public void goTo( Place new_current )
  {
     current = new_current;
  }

  public void message(String rec)
  {
    if(this.notification)
      this.message += rec+"\n";
    else
    {
      this.notification = true;
      this.message = rec+"\n";
    }
  }

  public boolean gotNotification()
  {
    return notification;
  }

  public void notification()
  {
    io.display(this.message);
    this.notification = false;
  }

  public void awaiting( boolean is_it)
  {
    this.accept = is_it;
  }

  public boolean isAwaiting()
  {
    return this.accept;
  }

  public void needResponse(Character c)
  {
    this.needResponse = true;
    this.requester = c;
  }  

  protected void respond(String s)
  {
    requester.message(s);
  }

  public String viewInventory()
  {
   String display = ""; 
   if(inventory.isEmpty())io.display("The inventory is empty.");
    else
    {
      display = "---------Items in the inventory---------\n";
      int i = 0, val = 0;
      for(String k : inventory.keySet())
      {
        String key = k;
        i++;
        display += i+". "+inventory.get(key).name()+"\t pts : "+inventory.get(key).value()+"\n";
        //val += inventory.get(key).value();
      }
      display += "-----------Total points: "+this.points+"-------------\nweight "+artifacts_weight+"\n";
      io.display(display);
    }
    return display;
  }
//******************************** String check method **********************************
  private String strCheck(String buff, String input)
  {
    //keyboard = keyboard.getInstance();
    str_format.strSpellCheck(buff, input);//Input check enhancement.
    if(str_format.match())
    {
      String display = "Did you mean "+buff+"? > [ 'y' | 'n' ]";
      Object[] options = {"yes", "no"};
      GUI_1.setOptionPane(display, "spell check", null, options);
      String str = io.getLine();
      if(str.equalsIgnoreCase("y")||str.equalsIgnoreCase("yes"))
        return str_format.getString();
      else
        return input;
    }
    return input;
  }

//******************************** Stdout printing methods **********************************
  //display() : Displays information of the character. User friendly.
  public String display()
  {
    String display = this.name+"\n "+this.description+"\n";
    display += viewInventory();
    display += "----------------------------------------------------------------------------------\n";
    io.display(display);
    return display;
  }

  public void print()
  {
    String name_age = this.name+" age:"+this.age;
    String curr = this.current.name();
    String type = new String();
    List<String>secA = new ArrayList<String>();
    if(!curr.startsWith("Room"))
      curr = printer.getInitials(curr);
    //Format list of artifacts.
    int n = inventory.size();
    for(String i : inventory.keySet())
    {
      String val = Integer.toString(inventory.get(i).value());
      String thing_val = inventory.get(i).name()+" pts:"+val;
      secA.add(thing_val);
    }
    if(this instanceof Player)
      type = "Player";
    else
      type = "NPC";
    printer.print_info(name_age, this.ID, secA,type,curr);
  }
}

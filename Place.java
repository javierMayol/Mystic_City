 /*
 *  Neel Patel 	NetID : npate315	UIN : 674004711
 * Keval Patel
 * Javier Mayol NetID : cmayol2		UIN : 671352495
 */
/*
* Javier Mayol, accc: cmayol
* cs-342 Software Development
* Professor John Bell
* Game version 2.1
* Implementation of the Place Class for the project 2.
*/
import java.util.*;
import java.util.Vector;
//import java.io.*;
    
public class Place {

//****************************** Atributes ***************************************
  private static HashMap<String, Place> places = new HashMap<String, Place>();
  private static boolean firstTime;
  private int ID;
  private String name;
  private String description;
  private Vector<Direction> directions;
  private TreeMap<String, Artifact> artifacts;
  private TreeMap<String, Character> characters;
  private StringPairCompare str_format; 
  private static Printer printer;
  private boolean isPlayer;
  private keyboardScanner keyboard;

//******************************* Constructors ************************************
  public Place(){}

  public Place(Scanner s){
    this.directions = new Vector<Direction>();
    this.characters = new TreeMap<String, Character>(String.CASE_INSENSITIVE_ORDER); 
    this.artifacts = new TreeMap<String, Artifact>(String.CASE_INSENSITIVE_ORDER); 
    try {
      while(!s.hasNextInt())s.nextLine(); 
        ID = s.nextInt();
        name = s.findInLine("[[a-zA-Z']+\\s|[a-zA-Z']+\\s(\\d)]+").trim();
        s.nextLine();
        int n = s.nextInt();
        description = "";
        for(int i = 0; i <= n; i++)
          description += s.nextLine().trim() + "\n";
    }
    catch(Exception e) {
      System.err.println("Error: Verify that the file is correctly formatted to initialize place objects.");
    }
    if(!places.containsKey(ID))//Checks for duplicates.    
      places.put(Integer.toString(ID), this);
    else 
      return;
    str_format = str_format.getInstance();
    printer = printer.getInstance();
    isPlayer = false;
    firstTime = true;
    keyboard = keyboard.getInstance();
  }
  
  //Old constructor.
  public Place (int ID, String name, String description) {
    this.directions = new Vector<Direction>();
    this.places.put(Integer.toString(ID), this);
    this.artifacts = new TreeMap<String, Artifact>(String.CASE_INSENSITIVE_ORDER); 
    this.characters = new TreeMap<String, Character>(String.CASE_INSENSITIVE_ORDER); 
    this.ID = ID;
    this.name = name;
    this.description = description; 
    str_format = str_format.getInstance();
    printer = printer.getInstance();
    isPlayer = false;
    firstTime = true;
    keyboard = keyboard.getInstance();
  }

//****************************** Methods : atribute related *********************
  //name() : Returns the name of the place.
  public String name() 
  {
    return name.trim();
  }

  public int getID()
  {
    return this.ID;
  }

  //description() : Returns the description of the place.
  public String description() 
  {
    return description.trim();
  }

  //addDirection() : Takes as a parameter a Direction object and adds it to direc array.  
  public void addDirection(Direction dir){
    directions.add(dir);
  }

  //Used in Player class : createNewPlayer();
  public boolean IDAvailable(int ID) 
  {
    return places.containsKey(Integer.toString(ID));
  }

//****************************** Methods : for game navigation *********************
  public static Place getPlaceByID(int ID){
    if(firstTime){
      firstTime = false;
      Place exit = new Place(1,"Exit", "This way out."); 
      Place Nowhere = new Place(0,"Nowhere","This is a void.");
    }
    return places.get(Integer.toString(ID));
  }

  //followDirection() : Checks to see if this place has a valid unlocked Direction
  //corresponding to the String passed, and if so, returns the Place arrived at by following that
  //Direction. Otherwise it simply returns itself, i.e. “this”.
  public Place followDirection(String direction){
    for(Direction i: directions) {
      if(i.match(direction)) //Call to match() of Direction class.
         return i.follow();
    }
    return this;
  }  

  public String AI_navigation()
  {
    if(directions.isEmpty()) return null;
    else if(!isPlayer)
    {
      Random rand = new Random();
      int index = rand.nextInt(directions.size());
      return directions.get(index).cardinal_pt();
    }
    return null;
  }    

  public void useKey(int key, String direction)
  {
    boolean q = true;
    //System.out.println("dir is "+dir);
    for(Direction i: directions) {
      if(i.match(direction)){ //Call to match() of Direction class.
         if(i.useKey(key)) return;
         else q =false;
      }
    }
    if(!q && isPlayer)
      System.out.println("\n\n\n\n\n\n  :THIS KEY WON'T OPEN THIS DOOR.\n");
  }

//****************************** Other methods : Character related *********************
  //Populates the characters collection.
  public void addCharacter(Character a)
  {
    characters.put(a.name(), a);
  }
  //Removes an element from characters collection.
  public void removeCharacter(String name)
  {
    if(characters.containsKey(name))
      characters.remove(name);
    else if(isPlayer)
      System.out.println("Character not found in current place.");
  }

  // Used to randomly assign a place to characters.
  public static Place chooseRandomPlace()
  {
    Random rand = new Random();
    List<String>keys = new ArrayList<String>(places.keySet());
    String randK = keys.get(rand.nextInt(keys.size()));
    int randKey = Integer.parseInt(randK);
    return getPlaceByID(randKey);
  }

  public int num_characters()
  {
    return characters.size();
  }

  //Implement Use get character by ID. Identifies whether there's a player or NPC.
  //If the Id belongs to a player, some info is printer in STDOUT.
  public void setCurrentCharacter()
  {
    isPlayer = true;
  }

  public Character listening(String name)
  {
    if(characters.containsKey(name))
      return characters.get(name);
    return null;
  }
      
//****************************** Methods : Artifact related *********************
  //Adds an artifact to this place's collection of artifacts.
  public void addArtifact(Artifact a)
  {
    artifacts.put(a.name(), a);
  }

  //Remove an artifact from this place collection and returns the removed artifact.
  public Artifact removeArtifact(String key)
  {
    if(artifacts.containsKey(key))
    {
      Artifact removed = artifacts.get(key);
      artifacts.remove(key);
      return removed;    
    }
    else if(isPlayer)
      for(String k : artifacts.keySet())
      {
        key = strCheck(k, key);
        Artifact removed = artifacts.get(key);
        artifacts.remove(key);
        return removed;    
      }
    else if(isPlayer)
      System.out.println("\n\n\n\n\n\n  :ARTIFACT NOT FOUND IN CURRENT PLACE.\n");
    return null; //Artifact();//try retun null at this point.
  }

  public String hasThing(String key)
  {
    for(String k : artifacts.keySet())
    {
      if(key.contains(k.toLowerCase()))
        return k;
    }
    return key;
  }

  //Used in AI class to assing arguments.
  public String shuffleArtifacts()
  {
    if(!artifacts.isEmpty())
    {
      Random rand = new Random();
      List<String>keys = new ArrayList<String>(artifacts.keySet());
      String thing = keys.get(rand.nextInt(keys.size()));
      return thing;
    }
    return null;
  }

//********************************* Spell Check implementation ***************************** 
  private String strCheck(String buff, String input)
  {
    str_format.strSpellCheck(buff, input);//Input check enhancement.
    if(str_format.match())
    {
      System.out.println("Did you mean "+str_format.getString()+"? > [ 'y' | 'n' ]");
      String str = keyboard.getInput();
      if(str.equalsIgnoreCase("y")||str.equalsIgnoreCase("yes"))
        return str_format.getString(); 
      else
        return input; 
    }
    return input;
  } 

//******************************** Stdout printing methods **********************************
  //display() : Displays information of the place. User friendly.
  public void display()
  { 
	  System.out.println("\n>Currently at "+this.name()+".");
	  System.out.println("----------------------------------------------------------------------------------");
	  System.out.println(this.description());
	  System.out.println("\n>Artifacts available:");

	  if(artifacts.isEmpty())
		  System.out.println("\tnone");
	  else
	  {
		  for(String i: artifacts.keySet())
			  System.out.println("  *"+artifacts.get(i).name());
	  }
	  System.out.println("\n>Characters in this place:");
	  if(!characters.isEmpty())
	  {
		  for(String i: characters.keySet())
		  {
			  if(characters.get(i) instanceof NPC)
				  System.out.print("NPC: ");
			  else if(characters.get(i) instanceof Player)
				  System.out.print("Player: ");
			  characters.get(i).display();
		  }
	  }
	  else{ 
		  System.out.println("\tnone");
	  }

	  System.out.println("\n>Directions available:");
	  if(!directions.isEmpty()){
		  for(Direction d : directions){
			  //System.out.println(d.print());i
				d.print();
		  }	

	  }
	  else{
		  System.out.println("You locked in");
	  }
  }
  //helper for printAll()
  private void print_directions()
  {
    for(Direction i : directions)
      i.print();
  }

  //helper for printAll()
  public void print_characters()
  {
    for(String i : characters.keySet())
      characters.get(i).print();
  }

  //helper for printAll()
  public void print_artifacts()
  {
    for(String i : artifacts.keySet())
      artifacts.get(i).print();
  }

  //Debugging method to output info about this place.
  public void print()
  {
    String dir = new String();
    for(Direction d : directions)
    {
      if(d.isLocked())
        dir += d.cardinal_pt()+"/"+", ";
      else
        dir += d.cardinal_pt()+", ";
    }
    List<String>secA = new ArrayList<String>();

    //-Format list of artifacts.
    int n = artifacts.size();
    for(String i : artifacts.keySet())
    {   
      String val = Integer.toString(artifacts.get(i).value());
      String thing_val = artifacts.get(i).name()+" pts:"+val;
      secA.add(thing_val);
    }
    printer.print_info(this.name, this.ID, secA,"",dir);
  }

  //Debugging method to output info about all Place Class objects.
  public static void printAll()
  {
    System.out.println("\n(((((((((((((((((((((((((((((( PRINTING ALL INFO OF PLACES ))))))))))))))))))))))))))))))");
    System.out.println("\n\t   name \t\t\t  Artifacts\t\t  Directions");
    System.out.println("----------------------------------------------------------------------------------");
    for(String i : places.keySet())
    {
      places.get(i).print();
    }
    System.out.println("'/' indicates direction is locked.\n\n\t\t\t\tCharacters' info");
    System.out.println("\n\t\tCharacter \t\t  Possessions\t\t   Type\t   Place");
    System.out.println("----------------------------------------------------------------------------------");
    for(String i : places.keySet())
    {
      places.get(i).print_characters();
    }
    System.out.println("\n\t\t\t\tDirections' info\n");
    System.out.println("----------------------------------------------------------------------------------");
    for(String i : places.keySet())
    {
      places.get(i).print_directions();
    }
    System.out.println("\n\t\t\t\tArtifacts' info\n");
    System.out.println("----------------------------------------------------------------------------------");
    for(String i : places.keySet())
    {
      places.get(i).print_artifacts();
    }
    System.out.println("----------------------------------------------------------------------------------");
  }
}

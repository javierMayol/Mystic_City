/*
 * Neel Patel 	NetID : npate315	UIN : 674004711
 * Keval Patel
 * Javier Mayol NetID : cmayol2		UIN : 671352495
 *
 * The class where you can play game which as information about place, 
 * you can also add places to the game 
 *
 * >CleanLine method take the whole line as an input and clean out the 
 * comment out part and any leading or tailing spaces
 * and return the clean line without any comments and extra spaces
 */		 

import java.util.*;

public class Game {

  private String game_name;// name of the game
  private static LinkedList<Character>players;	
  private int nCharacters;
  private keyboardScanner keyboard;		
  // game constructor that we are not using anymore
  Game(String name){
    game_name = name;
  }

  // game constructor that take open file  as an parameter 
  // and parse it to different variables and send it to
  // Place, Direction,Artifact class as need it 
  Game(Scanner file, int minPlayerQty){
    String line = CleanLineScanner.getCleanLine(file);
    Scanner lineScan = new Scanner(line);
    String type = lineScan.next();
			
    // if file match do not the extension exit the program
    if(!type.equalsIgnoreCase("GDF")) {
      System.err.println("Error parsing file!!");
      System.exit(-1);
    }
			
    // check for version of the file wather it's valid or not
    double version = lineScan.nextDouble();
    if(version != 3.1 && version != 4.0) {
      System.err.println("Not valid version of the file!!");
      System.exit(-2);
    }			
    lineScan.skip("[ \t]*");// skip white space or tab
    game_name = lineScan.nextLine();
			
    // get the number of places and create that may places by passing file to place class
    int nPlaces = keyCount(file,"PLACES");
    for(int i = 0; i < nPlaces; i++) {
      new Place(file);
    }
			
// create a directions by passing file to direction class
    int nDirections = keyCount(file,"DIRECTIONS");
    for(int i = 0; i < nDirections; i++) {
      new Direction(file);
    }
   // create character 
   nCharacters = keyCount(file,"CHARACTERS");
   for(int i = 0; i < nCharacters; i++) {
     String playerType = file.next();
     // check if the character is player, if it is then create a player object 
     //otherwise create a Non player character(NPC)
     if(playerType.equalsIgnoreCase("PLAYER")) {
       new Player(file);
     }
     else {
       new NPC(file);
      }
    }
    Riddle.getOgre();
    Player.minPlayerNum(minPlayerQty);
    //create artifacts and placed at appropriate positions
    int nArtifacts = keyCount(file,"ARTIFACTS");
    for(int i = 0; i < nArtifacts; i++) {
      line = CleanLineScanner.getCleanLine(file);
      lineScan = new Scanner(line);				
      int position = lineScan.nextInt();
      //if it's id < 0 add artifact to player's collections otherwise add it to place
      if(position < 0) {
        Character.getCharacterByID(-position).addArtifact(new Artifact(file));
      }
      else if(position == 0) {
        Place.chooseRandomPlace().addArtifact(new Artifact(file));
      }
      else 
      {// add it to pace
        Place.getPlaceByID(position).addArtifact(new Artifact(file));
      }
    }
    int nRiddles = keyCount(file,"RIDDLES");
    for(int i = 0; i < nRiddles; i++)
      Riddle.addRiddle(file);
    players = Player.getList();
    keyboard = keyboard.getInstance();
  }
  // helper method to find the number of places, directions, characters, and 
  //artifacts during the file reading to make file reading easier
  private int keyCount(Scanner file, String key) {
    String line = CleanLineScanner.getCleanLine(file);
    Scanner lineScan = new Scanner(line);
    String word = lineScan.next();
    if(!word.equalsIgnoreCase(key)) {
      System.err.println(key + " not found !!");
      System.exit(-4);
     }
     int count = lineScan.nextInt();
     if(count <= 0) {
       System.err.println("Invalid numbers!! ");
       System.exit(-5);
     }
     lineScan.close();
     return count;
  }

  private static void removePlayer(int index)
  {
    if(index == 0 && !players.isEmpty())
    {
      Character tmp = players.remove();
      return;
    }
    for(int i = 0; i < index; i++)
    {
      if(!players.isEmpty())
      {
        Character tmp = players.remove();
        players.add(tmp);
      }
    }
    if(!players.isEmpty())
      players.remove();
  }
  //play() : Infinte loop that takes command line input to navigate through the game.
  public void play() 
  {
    try 
    {
      players = Character.getList();
      Character curr_player = players.get(0);//For initiallizing.
      Character dummy = NPC.sendDummy();
      System.out.println("*********************"+ this.game_name +"  started!"+"********************");
      int Player_num = 0;
      for(Character c : players)
       {
        if (c instanceof Player)
        {
          Player_num++;
          System.out.println("Player "+Player_num+": "+c.name()+". Location: "+c.getCurrentPlace().name());
        }
      }
      System.out.println("*****************************LET'S PLAY*****************************");

      int index = 0;
      //Infinet loop. Uses scanner objects to get inputs from the user.
      while(true) 
      { 
        if(Player.retrievePlayer_num() < 1)break;
        //Place.printAll();
        for(Character c : players)
        { 
          curr_player = c;
          if(c instanceof Player)
          {
            System.out.println(">Current place: "+curr_player.current.name());
            System.out.println("\nOK, "+curr_player.name().toUpperCase()+", YOUR TURN.\n");
            if(curr_player.gotNotification())
               curr_player.notification();
            curr_player.makeMove();
            System.out.print("\n\n\n");
            if(curr_player.is_out())
            {
              index = players.indexOf(c);
              break;
            }
          }
        }
        if(curr_player.is_out()) removePlayer(index); 
        NPC.dummyMoves(players.size());
        System.out.print("\n\n\n");
      } 
    } 
    catch(InputMismatchException ie){ 
      System.out.println("Mismatched input.");
    }
  }	
}

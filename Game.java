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
import java.lang.reflect.Method;

public class Game {

  private String game_name;// name of the game
  private int nCharacters;
  //private keyboardScanner keyboard;		
  private static IO io;

  Game(){};

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
    //keyboard = keyboard.getInstance();
    this.io = new IO();
    this.io.selectInterface(io.TEXT);
  }

//****************************** GUI or TextInterface implementor ***********************
  static void ioChange(int n)
  {
    io.selectInterface(n);
  }
//**************************** END OF GUI or TextInterface implementor *********************
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

  //play() : Infinte loop that takes command line input to navigate through the game.
  public void play() 
  {
    try 
    {
      io.display("*********************"+ this.game_name +"  started!"+"********************");
      int Player_num = 0;
      Iterator<Character> characterIterator = Character.getIterator();
      while(characterIterator.hasNext())
      {
        Character c = characterIterator.next();
        if (c instanceof Player)
        {
          Player_num++;
          io.display("Player "+Player_num+": "+c.name()+". Location: "+c.getCurrentPlace().name());
        }
      }
      io.display("*****************************LET'S PLAY*****************************");
     
      int index = 0;
      //Infinet loop. Uses scanner objects to get inputs from the user.
      while(true) 
      { 
        characterIterator = Character.getIterator();
        while(characterIterator.hasNext())
        {
          Character c = characterIterator.next();
          if(!c.is_out())
          {
            if(c instanceof Player)
            {
  	      //Place.printAll();//For debugging purposess.
              c.message(">Current place: "+c.current.name()+"\n"+c.current.description());
              c.message("\nOK, "+c.name().toUpperCase()+", YOUR TURN.\n");
            }
       	    if(c.hasSomething())
      	      GUI_1.gettingArtifacts(c.artNames());
            else
      	      GUI_1.gettingArtifacts(null);
            c.makeMove();
          }
          if(Player.retrievePlayer_num() < 1)break;
        }
        if(Player.retrievePlayer_num() < 1)break;
      } 
    } 
    catch(InputMismatchException ie){ 
      io.display("Mismatched input.");
    }
    io.display("Exit the Game. Hope you enjoyed it!!");
  }	
}

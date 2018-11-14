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
	private keyboardScanner keyboard;		

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
		if(version != 3.1 && version != 4.0 && version != 5.0){
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
	
	private void groupInfo()
	{
	System.out.println("\n\n*************************GROUP INFO*********************************");
	System.out.println("GROUP : 36");
	System.out.println("Members:");
	System.out.println("\tName: Javier Mayol  NETID: cmayol2");
	System.out.println("\tName: Neel Patel    NETID: npate315");
	System.out.println("\tName: Keval Patel   NETID: kpate283\n\n");
	}
//			this.age = lineScan.nextInt();
	public void play() 
	{
		groupInfo();
		try 
		{
			System.out.println("*********************"+ this.game_name +"  started!"+"********************");
			int Player_num = 0;
			Iterator<Character> characterIterator = Character.getIterator();
			while(characterIterator.hasNext())
			{
				Character c = characterIterator.next();
				if (c instanceof Player)
				{
					Player_num++;
					System.out.println("Player "+Player_num+": "+c.name()+". Location: "+c.getCurrentPlace().name());
				}
			}
			System.out.println("*****************************LET'S PLAY*****************************");
		//	groupInfo();
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
							Place.printAll();
							System.out.println(">Current place: "+c.current.name()+"\n"+c.current.description());
							System.out.println("\nOK, "+c.name().toUpperCase()+", YOUR TURN.\n");
						}
						if(c.gotNotification())
							c.notification();
						c.makeMove();
					}
					if(Player.retrievePlayer_num() < 1)break;
				}
				if(Player.retrievePlayer_num() < 1)break;
			} 
		} 
		catch(InputMismatchException ie){ 
			System.out.println("Mismatched input.");
		}
	}	
}

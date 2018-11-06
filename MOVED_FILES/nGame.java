/*
Name : Neel Patel
Net ID : npate315
UIN : 674004711
*/
//package proj1;
// The class where you can play game which as information about place, you can also add places to the game 

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class Game {
	
		private String game_name;										// name of the game
		private ArrayList<Place> places = new ArrayList<Place>();		// collection of places but we are not using it for now 
		private static Place current;											// store the current place
		private ArrayList<Artifact> player_collection = new ArrayList<Artifact>();		// player's artifacts collections
		private ArrayList<Character> characters = new ArrayList<Character>();
		static int first = 0;										// use to set the starting place
		private int nCharacters;
		
		// game constructor that we are not using anymore
		Game(String name){
		game_name = name;
	}

		/*
		 * game constructor that take open file  as an parameter and parse it to different variables and send it to
		 * Place, Direction,Artifact class as need it
		 */
		Game(Scanner file){
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
			
			lineScan.skip("[ \t]*");                         // skip white space or tab
			game_name = lineScan.nextLine();
			
			// get the number of places and create that may places by passing file to place class
			int nPlaces = keyCount(file,"PLACES");
			for(int i = 0; i < nPlaces; i++) {
				places.add(new Place(file));
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
			
				// check if the character is player, if it is then create a player object otherwise create a Non player character(NPC)
				if(playerType.equalsIgnoreCase("PLAYER")) {
				//	Place.getPlaceByID(position).addCharacter(new Player(file,0));
					characters.add(new Player(file,0));
				}else {
				//	Place.getPlaceByID(position).addCharacter(new NPC(file,0));
					characters.add(new NPC(file,0));
				}
			}
			
			//create artifacts and placed at appropriate positions
			int nArtifacts = keyCount(file,"ARTIFACTS");
			for(int i = 0; i < nArtifacts; i++) {
				line = CleanLineScanner.getCleanLine(file);
				lineScan = new Scanner(line);
				
				int position = lineScan.nextInt();
				//if it's id < 0 add artifact to player's collections otherwise add it to place
				if(position < 0) {
					Character.getCharacterByID(-position).addArtifact(new Artifact(file));
				}else if(position == 0) {
					Place.getPlaceByID(23).addArtifact(new Artifact(file));
				}else {
			    // add it to pace
				Place.getPlaceByID(position).addArtifact(new Artifact(file));
				}
			}
		}
		
		/*
		 * cleanLine method take the whole line as an input and clean out the comment out part and any leading or tailing spaces
		 * and return the clean line without any comments and extra spaces
		 */
//	public static String cleanLine(String line) {
//		int index = line.indexOf("//");
//	//	System.out.println(index);
//		if(index == -1) return line;
//	//	System.out.println(line);
//		return line.substring(0, index).trim();
//				
//	}// end cleanLIne
	
	//this method help to set starting place 
//		static void setFirst(int id) {
//			first = id;
//		}
		// static methode 
		static Place getCurrentPlace() {
			return current;
		}
		
	
//		public int getIdx(String s) {
//			int index= 0;
//			for(Artifact a : player_collection) {
//				if(s.equals(a.name())) return index;
//				index++;
//			}
//			System.out.println("Player doen't carry this "+ s);
//			return -1;
//		}
		void printInve() {
			int totalValue = 0;
			int totalWeight = 0;
			System.out.println("Player's Inventory\n");
			System.out.println("Name\t\t" + "Value\t" + "Weight(in pound)");
			for(Artifact a : player_collection) {
				totalValue += a.value();
				totalWeight += a.weight();
				System.out.println(a.name() + "\t" + a.value() + "\t" + a.weight());
			}
			System.out.println("\nTotal value = " + totalValue);
			System.out.println("Total weight = " + totalWeight + " pound");
		}
	
		
// print the information about the current place and artifacts collection player have at this point 
// it also give us the information about where you can go from here
	public void print() {
	
		System.out.println("Contents of the game " + game_name + ":");
		for(Place p: places) {
			p.print();
		}
	}
	

//print the information about current place

	public void display() {
		
		for(Place p: places) {
			p.display();
		}		
	}
	
	/*
	 * this method start the game. This method take the user input as an instruction and according to the instruction call the different method
	 */
	public void play() {
		// start playing thr game
		System.out.println("**********Information aboove this point is Debuging purpose only**********\n\n");
	
		myInfo();
		System.out.println("Wel Come to " + game_name);
		
		// if number of players not created by data file then ask user for how many player they want to add and ask for their IDs, names, and descrition
		if(nCharacters == 0) {
			System.out.print(" how many characters whould you like to create: ");
			Scanner input = ScannerFactory.getKeyboardScanner();
			nCharacters = input.nextInt();
			for(int i = 0; i < nCharacters; i++) {
				System.out.print("Enter ID:");
				int id = input.nextInt();
				System.out.print("Enter name: ");
				String name = input.next();
				System.out.print("Enter desription: ");
				String des = input.next();
				
				// create new player
				characters.add(new Player(id,name,des));
			}
			
		}
		
		
		while(true) {
			for(Entry<Integer, Character> entry : Character.characters.entrySet()) {
				System.out.println(entry.getKey());
				if(entry.getValue() instanceof Player)
					((Player)entry.getValue()).makeMove();
				else
					((NPC)entry.getValue()).makeMove();
			}
		}
		
		
	//	current = Place.getPlaceByID(first);
	//	Scanner sc = new Scanner(System.in).useDelimiter("\\n"); // initialize the scanner for player's instruction
		//
//		while(true) {
//		String input;
//		//display();
//		
//		System.out.print("\nwhere do you want to go:");
//		input = sc.nextLine();								// take the player's instruction
//		
//		if(input.contains("EXIT") || input.contains("QUIT")) return;	// if it is EXIT or QUIT to end the game
//		
//		if(input.contains("LOOK")) {							// this print out information about current place and player's collection so far
//			
//			print();
//			
//			continue;
//		}
//		Scanner line = new Scanner(input);
//		String token = line.next();
//		//System.out.println(token);
//		if(token.equals("GET")) {						// add artifact to player's collection
//			String artifact = line.nextLine().trim();
//						int id = current.available(artifact);
//			
//			if(id >= 0) {
//				Artifact a = current.removeArtifact(id);
//				//System.out.println(a.name());
//				player_collection.add(a);
//			}
//			continue;
//		}
//		 if(token.equals("DROP")) {
//				String art = line.nextLine().trim();
//					System.out.println(art);
//
//				int idx = getIdx(art);
//				System.out.println(idx);
//				if(idx < 0) continue;
//				current.addArtifact(player_collection.remove(idx));
//			
//			continue;
//		}
//		 if(token.equals("USE")) {
//			 String artifact = line.nextLine().trim();
//			 int index = getIdx(artifact);
//			 player_collection.remove(index).use();
//			 continue;
//		 }
//		 System.out.println(token);
//		 if((token.equals("INVE")) || (token.equals("INVENTORY"))) {
//			 
//			printInve();
//			 continue;
//		 }
//	
//		current = current.followDirection(input);			// move to next place as instructed
//			
//		}
	}

	// helper method to find the number of places, directions, characters, and artifacts during the file reading to make file reading easier
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
	
// Author's Information
	private void myInfo() {
		System.out.println("Name: Neel Patel");
		System.out.println("Net ID: npate315");
		System.out.println("UIN: 674004711");
		System.out.println("Project 2");
		System.out.println("\n");
	}
	
}

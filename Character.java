//package proj1;

/*
	name : Neel Patel
	netID: npate315	
	UIN  : 674004711

	this is the parents class for player and non player characters has all information about given characters
*/

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Character {
	
	// class atributes to store the class inormatioins
	protected int ID;
	protected String name;
	protected String description;
	protected Place current;
	private ArrayList<Artifact> player_collections = new ArrayList<Artifact>();         // collection artifacts at the place

	public static TreeMap<Integer, Character> characters = new TreeMap<Integer, Character>();         //static collection of places
	
	
	// constructor for the place class
	public Character(Scanner sc, int version) {
		String line = CleanLineScanner.getCleanLine(sc);
		Scanner lineScan = new Scanner(line);
		
		int position = lineScan.nextInt();
		if(position == 0) position = 12;
		
		 line = CleanLineScanner.getCleanLine(sc);
		 lineScan = new Scanner(line);
		//System.out.println(line);
		
		 ID = lineScan.nextInt();                      // read ID
		lineScan.skip("[ \t]*");                       // skip tab or white space
		name = lineScan.nextLine();                    // read name of the character
		 line = CleanLineScanner.getCleanLine(sc);
		 lineScan = new Scanner(line);
		 int nDes = lineScan.nextInt();                // read the number of lines for description
		 description = " ";
		 for(int i = 0; i < nDes; i++) {
			 description = description + CleanLineScanner.getCleanLine(sc);     // add description for the character
		 }
		characters.put(ID, this);                          // add character to static collections
		current = Place.getPlaceByID(position);            // place Id at appropriate position
		current.addCharacter(this);                        // make that place is current position for this player
	}
	
	public Character(int id, String name, String Des) {
		ID = id;
		this.name = name;
		description = Des;
		characters.put(ID, this);
		current = Place.getPlaceByID(12);
		current.addCharacter(this);
		
	}
	// get the character by its ID from static collection of characters 
	public static Character getCharacterByID(int id) {
		return characters.get(id);
	}
	
	// below is all getters for this class
	public String getName() {
		return name;
	}
	 public void addArtifact(Artifact a) {
		 player_collections.add(a);
	 }
	 
	 public Artifact getArtifact(int pos) {
		 return player_collections.get(pos);
	 }
	 
	 public boolean removeArtifact(Artifact a) {
		 return player_collections.remove(a);
	 }
	
	 public int collectionSize() {
		 return player_collections.size();
	 }
	public void makeMove() {
		
		// this method implemented by its child class depending on playing chracter or non plying character
	}
	
	// print the information about collection of artifacts player has like its value and weight 
	public void printInve() {
		int totalValue = 0;
		int totalWeight = 0;
		System.out.println("Player's Inventory\n");
		System.out.println("Name\t\t" + "Value\t" + "Weight(in pound)");
		for(Artifact a : player_collections) {
			totalValue += a.value();
			totalWeight += a.weight();
			System.out.println(a.name() + "\t" + a.value() + "\t" + a.weight());
		}
		System.out.println("\nTotal value = " + totalValue);
		System.out.println("Total weight = " + totalWeight + " pound");
	}
	

	// print character informtion about debug 
	public void print() {
		System.out.println("  " + name + " has id " + ID);
		System.out.println("\t" + description);
		display();
	}
	
	// displying information while playing the game like it's description
	public void display() {
		System.out.println("  my collections of Artifacts:");
		 	for(Artifact a : player_collections) {
		 		System.out.println("\t" + a.name());
		 	}
		
	}
	
	
}

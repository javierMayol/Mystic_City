/*
Name : Neel Patel
Net ID : npate315
UIN : 674004711

*/
//package proj1;

/*
Place class has the informatin about the place like name , discription and all directions you can go from this place
Also it has static collection of places

*/
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Place {
	private int ID;
	private String place_name;
	private String des;
	private ArrayList<String> description = new ArrayList<String>();
	private ArrayList<Direction> directions = new ArrayList<Direction>();
	private ArrayList<Artifact> artifacts = new ArrayList<Artifact>();
	private ArrayList<Character> characters = new ArrayList<Character>();
	//public static ArrayList<Place> places = new ArrayList<Place>();
	public static TreeMap<Integer,Place> tm_places = new TreeMap<Integer,Place>();
	int number_places;
	int des_lines;
	private boolean firstPlace = true; 

// constructor for place take name and discription of the place

	Place(int id, String name, String des){
		ID         = id;
		place_name = name;
		//des        = description;
		description.add(des);
		tm_places.put(ID, this);
		//current_place = false;
		
	}
	/*
	 * Place constructor take the open file as an argument and parse it in to different field like name , id, and description about the place
	 */
	Place(Scanner file){
		String line = CleanLineScanner.getCleanLine(file);
		Scanner lineScan = new Scanner(line);
		ID = lineScan.nextInt();
		lineScan.skip("[ \t]*");
		place_name = lineScan.nextLine();
		
		line = CleanLineScanner.getCleanLine(file);
		lineScan = new Scanner(line);
		int nDes = lineScan.nextInt();
		for(int i = 0; i < nDes; i++) {
			line = CleanLineScanner.getCleanLine(file);
			description.add(line);
		}
		tm_places.put(ID, this);
		if(firstPlace) {
			firstPlace = false;
			new Place(1,"Exit","representing the EXIT");
			new Place(0,"Nowhere","place doesnot exist");
		}
	}
//	Place(Scanner sc){
//	//	System.out.println(sc.nextLine());
//	//this(sc);
//	int n = sc.nextInt();
//	 	int number_places = n;
////		System.out.println(number_places);
//	while(number_places > 0) {
//		//String line = sc.nextLine();
//		String line = Game.cleanLine(sc.nextLine());
//	//System.out.println(line);
////		line = Game.cleanLine(line);
//	//	while(number_places > 0) {
//			 line = Game.cleanLine(sc.nextLine());
//			
//			while(line.length() == 0) {
//				line = Game.cleanLine(sc.nextLine());
//			}
//		//	System.out.println(line);
//			Scanner line_scanner = new Scanner (line);
//			ID = line_scanner.nextInt();
//			if(n == number_places) Game.setFirst(ID);
//			place_name = line_scanner.nextLine();
//		//	System.out.println(ID);
//		//	System.out.println(place_name);
//			 line = Game.cleanLine(sc.nextLine());
//			while(line.length() == 0) {
//				line = Game.cleanLine(sc.nextLine());
//			}
//			 line_scanner = new Scanner (line);
//			 des_lines = line_scanner.nextInt();
//			 int m = des_lines;
//			 String desc = "";
//			 while(m > 0) {
//				 line = Game.cleanLine(sc.nextLine());
//					while(line.length() == 0) {
//						line = Game.cleanLine(sc.nextLine());
//					}
//					//desc = desc + line;
//					description.add(line);
//					m--;
//			 }
//			 Place.tm_places.put(ID, new Place(ID,place_name,desc));
//			 number_places--;
//		}		 
//		
//	this.print();
//		
//		 return;
//		
//	}
	
	
	public Artifact removeArtifact(int id) {
		
		 return artifacts.remove(id); 
		}

	
			
					
		
//	}
	public int available(String s) {
		int index = 0;
		for(Artifact a : artifacts) {
			System.out.println(a.name());
			if(s.equals(a.name())) {
				if(a.weight() < 0) {
					System.out.println("It too heavy you can not carry..");
					return -1;
				}
				
				return index;
			}
			index++;
		}
		System.out.println("Artifact is not available at this place..");
		return -1;
	}
	public void addCharacter(Character c) {
		characters.add(c);
	}
	// add artifact to current place
	public void addArtifact(Artifact a) {
		artifacts.add(a);
	}
	
	// get the place by its ID and its static because direction class and artifact class use it to store them self in to place
	static Place getPlaceByID(int id) {
		return tm_places.get(id);
		
	}
	// use the key to unlock the direction
	void useKey(Artifact a) {
		for(Direction d : directions) {
			d.useKey(a);	
		}
	}
	
	// return the name of the place
	public String name() {
		return place_name;
	}
	
	// print out information about direction available to this place and all artifacts available at current place
	public void description() {
		for(String s : description) {
			System.out.println(s);
		}
	//	return des;
	}

// add direction in to this place

	public void addDirection(Direction d) {
	
		directions.add (d);
	}
	
	public Artifact removeArtifactByName(String s) {
		for(Artifact a : artifacts) {
			if(a.match(s)) {
				artifacts.remove(a);
				return a;
			}
		}
		return null;
	}

//by calling following function you can go to next place with valid direction

	public Place followDirection(String s) {
		for(Direction d : directions) {
			if(d.match(s) && !d.isLocked()) return d.follow();
		}
		System.out.println("\tNot valid direction");
		return this;
	}
	
	public boolean isExit() {
		return (ID == 1 || ID == 0);
	}
	
	public void display() {
		System.out.println(place_name + ":");
		System.out.print("\t");
		description();
		//System.out.println("Direction");
	}

// print the informaiton about place

	public void print() {
	//	for(Place p : tm_places) {
		System.out.println("Place # " + ID + " - " + place_name);
		for(String d : description) {
			System.out.println("\t" + d);
		}
		if(directions.size() > 0) {
			System.out.println("  Available directions: ");
			for(Direction d : directions) {
				d.print();
			}
		}else {
			System.out.println("This place has no directions available!");
		}
		if(artifacts.size() > 0) {
			System.out.println("  Available Artifacts: ");
			for(Artifact a : artifacts) {
				a.print();
			}
		if(characters.size() > 0) {
			System.out.println("  Players present at this location: ");
			for(Character c : characters) {
				c.print();
			}
		}
		}
////		for(Map.Entry<Integer, Place> p : tm_places.entrySet()) {
////			Integer i = p.getKey();
////			Place pl = p.getValue();
//	
//			System.out.println("Available directions: ");
//			for(Direction d : directions) {
//				System.out.println("\t");
//			d.print();
////		}
//		
//		}
//	
//	//	System.out.println("Name:\t"+place_name);
//	//	System.out.println("ID:\t"+ ID);
//		System.out.println("Artifacts:\t");
//		for(Artifact a : artifacts) {
//			System.out.println(a.name());
		//}
	//	System.out.println("Description:\t"+des);
		
		System.out.println("\n");
	}
//	}
}

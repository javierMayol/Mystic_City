/*
class Pet.java
	this is child class of NPC class. this class is like player can have their personal pet that they can use to 
	some places where player not allowed and get the artifacts for them.


*/


import java.util.*;

public class Pet extends NPC{

	Player owner;

	Pet(Scanner sc){
		super(sc);
		owner = null;
	}
	Pet(Place p, int id, int age, String name, String desc){
		super(p, id, age, name, desc);
		owner = null;
	}

	public void setOwner(Player p){
		
		owner = p;
	
	}

	public void removeOwner(){
		owner = null;
	}

}

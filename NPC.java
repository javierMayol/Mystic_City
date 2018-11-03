//package proj1;
/*
	Name : Neel Patel
	netID: npate315	
	uin  : 674004711

	it has information about non playing characters 
*/

import java.util.Scanner;

public class NPC extends Character {
	
	private DecisionMaker dm = new AI();
	public NPC(Scanner sc, int version) {
		super(sc, version);
		// TODO Auto-generated constructor stub
	}
	
	// they also do things but their own because they are crazy out of controls
	public void makeMove() {
		
		Move m = dm.getMove(this, super.current);
		((GO)m).execute();
	}
}

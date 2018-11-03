//package proj1;
/*
	Name : Neel Patel
	netID: npate315	
	uin  : 674004711

*/

import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Character {
	private DecisionMaker dm = new UI();

	private ArrayList<Artifact> player_collections = new ArrayList<Artifact>();

	public Player(Scanner sc, int version) {
		super(sc, version);
		// TODO Auto-generated constructor stub
	}
	public Player(int id, String name, String des) {
		super(id,name,des);
		// TODO Auto-generated constructor stub
	}
	public void addArtifact(Artifact a) {
		player_collections.add(a);

	}

	// make move based on the user input
	public void makeMove() {
		//System.out.println(super.current.name());
		//	System.out.println(this.name);
		Move m = dm.getMove(this, super.current);
		//	System.out.println(m);
		if(m == null) return;
		if(m instanceof EXIT) {
			//System.out.println("exit");
			((EXIT)m).execute();
		}else if(m instanceof LOOK) {
			((LOOK)m).execute();

		}else if(m instanceof GO) {
			((GO)m).execute();
			super.current = ((GO)m).pl;
			System.out.println(super.current.name());
		}else if(m instanceof GET) {
			((GET)m).execute();
		}else if(m instanceof INVENTORY) {
			((INVENTORY)m).execute();
		}

	}
}

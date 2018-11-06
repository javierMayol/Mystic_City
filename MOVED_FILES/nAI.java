//package proj1;
/*Name : Neel Patel
  netID: npate315
  UIN  : 674004711
	this class make the deicision to move for a non playing player. it doen't required a user input. it does randomly
	it is also implements the DecisionMaker iterface.

*/

import java.util.Random;

public class AI implements DecisionMaker{
		
	// implements from decision maker
	public Move getMove(Character c, Place p) {
		//	System.out.println("Hello....");
			System.out.println(c.getName() +  " currently at ");
			p.display();
			c.display();
//			Random r = new Random();
//			int move = (r.nextInt(3-0) + 0);
			
		//	String moveType = moves[move];
			String moveType = "GO";
			String arg2 = "N";
			//if(moveType.equalsIgnoreCase("GO")) {

				// return the object of the move GO	
				return (new GO(arg2,c,p));
		//	}
	}
}

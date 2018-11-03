//package proj1;
/*
	Name : Neel Patel
	netID: npate315	
	uin  : 674004711

	this is the decision making for playing character
*/

import java.util.Scanner;

public class UI implements DecisionMaker{
	
	public Move getMove(Character c, Place p) {
		//	System.out.println("Hello....");
		System.out.println(c.getName() +  " currently at ");
		p.display();
		c.display();
		System.out.print("\n\nWhat would you like to do: ");
		Scanner userInput = ScannerFactory.getKeyboardScanner();
		String line = userInput.nextLine().trim();
		Scanner lineScan = new Scanner(line);
		String moveType = lineScan.next();
		lineScan.skip("[ \t]*");
		//	String arg2 = lineScan.nextLine();

		// get the userinput what the want to do on current situations

		if(moveType.equalsIgnoreCase("GO")) {
			lineScan.skip("[ \t]*");
			String arg2 = lineScan.nextLine();
			return (new GO(arg2,c,p));
		}else if(moveType.equalsIgnoreCase("LOOK")) {
			//	lineScan.skip("[ \t]*");
			//	String arg2 = lineScan.nextLine();
			return new LOOK("",c,p);
		}else if(moveType.equalsIgnoreCase("DROP")) {
			lineScan.skip("[ \t]*");
			String arg2 = lineScan.nextLine();
			return new  DROP(arg2,c,p);
		}else if(moveType.equalsIgnoreCase("GET")) {
			lineScan.skip("[ \t]*");
			String arg2 = lineScan.nextLine();
			return new  GET(arg2,c,p);
		}else if(moveType.equalsIgnoreCase("USE")) {
			lineScan.skip("[ \t]*");
			String arg2 = lineScan.nextLine();
			return new  USE(arg2,c,p);
		}
		else if(moveType.equalsIgnoreCase("INVENTORY") || moveType.equalsIgnoreCase("INVE")) {
			//		lineScan.skip("[ \t]*");
			//		String arg2 = lineScan.nextLine();
			return new  INVENTORY("",c,p);
		}else if (moveType.equalsIgnoreCase("EXIT") || moveType.equalsIgnoreCase("QUIT")) {
			//	System.out.println(moveType);
			return (new  EXIT("",c,p));

		}
		return null;
	}

}

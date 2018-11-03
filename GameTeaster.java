/*
Name : Neel Patel
Net ID : npate315
UIN : 674004711
*/

//package proj1;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

// this the game teaster class where I test my code.
// I hardcode this class for testing purpose

public class GameTeaster {
	public static void main(String[] args) throws Exception{
		// create a new game
//File file = new File("C:\\Users\\Neel\\Desktop\\MystiCity.txt");
	//	System.out.println(args[2]);
   		File file = new File(args[0]);
		Scanner sc = new Scanner(file);
		Game g = new Game(sc);
//		for(Map.Entry<Integer, Place> p : Place.tm_places.entrySet()) {
//			Integer i = p.getKey();
//			Place pl = p.getValue();
//			
//			System.out.println(i + pl.name() + "\t");
//			pl.description();
//			
//			pl.print();
//		}
		
//		 // play the game
//
		g.print();
		g.play();
		 
		 System.out.println("Exit the Game. Hope you enjoyed it!!");
	}
}

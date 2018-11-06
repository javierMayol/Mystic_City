/*
 * Neel Patel 	NetID : npate315	UIN : 674004711
 * Keval Patel
 * Javier Mayol NetID : cmayol2		UIN : 671352495
*/

//package proj1;

import java.io.*;
import java.util.*;

// this the game teaster class where I test my code.
// I hardcode this class for testing purpose

public class GameTeaster {

  public static void main(String[] args) throws Exception{
    String fileName = "MysticCity4.txt";
    Scanner sc = null;
    int player_qty = 1;

    if(args.length > 0) fileName = args[0];
    if(args.length > 1) player_qty += Integer.parseInt(args[1]);

    File file = null;

    try
    {        
      sc = new Scanner(new File(fileName));
    }

    catch(FileNotFoundException e)
    {
      System.err.println("File not found. Enter the name of the file");
      sc = new Scanner(System.in);
      sc = new Scanner( new File(sc.nextLine()));
    }

    Game g = new Game(sc, player_qty);
    //g.print();
    g.play();
    System.out.println("Exit the Game. Hope you enjoyed it!!");
  }
}

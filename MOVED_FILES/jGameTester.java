/*
* Javier Mayol, accc: cmayol
* cs-342 Software Development
* Professor John Bell
* Game version 2.0:
* Implementation of the GameTester Class.
* Conatins main() method. This is used as the game driver.
* This class first reads a file to initialize the game. 
* All classes get pass the file by a Scanner object. The 
* file is first formatted to remove comments lines starting
*  with "//". No instances of classes need to be initialized 
* by the GameTester class in this implementation.
*/

import java.io.*;
import java.util.*;

public class GameTester {

  public GameTester() 
  {
   //add constructor stub. 
  }

  public static void main (String[] args) throws IOException{ 

    System.out.println("\nJavier Mayol :"+" cmayol2");

    String fileName = "MysticCity_40_untested.gdf";
    int players_qty = 2;

    if(args.length > 0) fileName = args[0];//Checks if the user input a file.
    if(args.length > 1) players_qty += Integer.parseInt(args[1]);//Checks if the user input number of characters.
/*    if(players_qty > 5)
    {
      System.out.println("Too many players for this humble game.");
      players_qty = 2;
    }
*/
    Scanner infile = null;

    try {
      infile = new Scanner(CleanLineScanner.fileCleaner(new File (fileName), fileName));
    }
    catch(FileNotFoundException e)
    {
      System.out.println("Error : File "+fileName+" not found.\n");
      infile = new Scanner(CleanLineScanner.fileCleaner(new File (fileName), fileName));
    }

    Game game = new Game(infile, players_qty);
   
    Place.printAll();

    game.play();
 
    infile.close(); 
  
  }
}

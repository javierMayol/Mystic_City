 /*
 *  Neel Patel 	NetID : npate315	UIN : 674004711
 * Keval Patel
 * Javier Mayol NetID : cmayol2		UIN : 671352495
 */
//package proj1;

/*
	name : Neel Patel
	netID: npate315
	UIN  : 674004711	

	this is a helper class to clean the line from data file 
	clean out comments and space or tabs from the data file
        Uses polymorphism to evaluate either a scanner or a string
 	object passed as argument.
*/

import java.util.Scanner;

public class CleanLineScanner {
	
  public static String getCleanLine(Scanner sc) {
    String line = null;		
    // run the while loop until we get the clean line
    while(true) {
      if(!sc.hasNextLine()) break;      // if no line found			
      line = sc.nextLine();
      int comment = line.indexOf("//");  // if we fond comment 
      if(comment == 0) continue;			
      if(comment > 0)
        line = line.substring(0, comment);  // get the substring leave the comments out
      line = line.trim();
      if(line.length() > 0) return line;		
    }		
    // return the clean line
    return line;
  }
  public static String getCleanLine( String str )
  {
     str = str.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)|(?::-)","");
     str = str.trim();
     if(str.length() > 0) return str;
     return str;
    //End of Clean Line method.
  }

  //implementing polymorphism for getCleanLine that returns int.
  public static int getCleanLine(int i)
  {
    return i;
  }
}

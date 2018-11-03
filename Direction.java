/*
* Javier Mayol, accc: cmayol
* cs-342 Software Development
* Professor John Bell
* Game version 2.0
* Implementation of the Direction Class for the project 2.
* Enhancing functionality. This class implements an enum
* class to compare the input of the user against the 
* directions assigned for this version of the game. A Scanner
* object is pass to the constructor to  initialize all the 
* class variable 
*/
import java.util.*;
//import java.util.EnumSet;

public class Direction {

  private int ID;
  private Place from;
  private Place to;
  private DirType dir;
  private int lockPattern;
  private boolean locked;
  private Printer printer;

  //Scanner constructor.
  public Direction(Scanner s){
    try{
      while(!s.hasNextInt())s.nextLine();
      this.ID = s.nextInt();
      this.from = Place.getPlaceByID(s.nextInt());
      this.dir = DirType.valueOf(s.next());
      int t = s.nextInt();
      this.to = Place.getPlaceByID((t < 0) ? -t : t);
      if(t == 0)this.lock();
      this.lockPattern = s.nextInt();
      if(lockPattern > 0) locked = true;
      from.addDirection(this);
      printer = printer.getInstance();
    }
    catch(Exception e) {
      System.out.println("Error: Verify that the file is correctly formatted to initialize direction objects.");
    }
  }

  //Constructor.
  public Direction( int ID, int from, int to,  String dir) { 
     this.from = Place.getPlaceByID(from);
     this.to = Place.getPlaceByID(to);  
     this.dir = DirType.valueOf(dir);
     this.locked = false;
     this.lockPattern = 1;
     printer = printer.getInstance();
  }
  
  //Returns abbreviation of the direction.

  //match() : Verify the string passed by user is a match.
//  public boolean match(String s) {
//    return dir.match(s);
//  }

  public String cardinal_pt()
  {
    return dir.toStringAbbr();
  }

  public String directionToString()
  {
    return dir.toString();
  }

  //lock() : Locks the direction object.
  public void lock() {
    locked = true;
  }

  //unlock() : Unlocks the direction object.
  public void unlock() {
    locked = false;
  }

  //isLocked() : Returns true if the direction is locked, false otherwise.
  public boolean isLocked() {
    return locked;
  }

  //match() : Verify id the string passed by user is a match.
  public boolean match(String s) {
    return dir.match(s);
  }

  //Attemps to open a locked direction if Artifact object pattern matches.
  public boolean useKey(int keyPattern){
    if(keyPattern == lockPattern)
    {
      locked = !locked;
      return true;
    }
    return false;
  }

  //follow() : Returns the “to” Place corresponding to this Direction if it is unlocked.
  //Otherwise it returns the “from” Place.
  public Place follow() {
    if(locked == false) return to;   
    return from;
  }

  
  public void print()
  {
    String name = dir.toString();
    String from = this.from.name();
    String to = this.to.name();
    if(!from.startsWith("Room"))
      from = printer.getInitials(from);
    if(!to.startsWith("Room"))
      to = printer.getInitials(to);
    String secA = "from:"+from+" to:"+to;
    String closed = "lock:";
    if(this.locked)
      closed += "yes";
    else
      closed += "no";
    String secC = "Pattern "+Integer.toString(this.lockPattern);
    printer.print_info(name, this.ID, secA, closed, secC);
  }

  //Enumarator class to determine the directions in the game.
  private enum DirType {
    NONE("None","None"), 
    N("N","North"), 
    S("S","South"), 
    E("E","East"), 
    W("W","West"), 
    U("U","Up"), 
    D("D", "Down"), 
    NE("NE","Northeast"), 
    NW("NW","Northwest"), 
    SE("SE", "Southeast"), 
    SW("SW","Southwest"), 
    NNE("NNE","North-Northeast"), 
    NNW("NNW","North-Northwest"), 
    ENE("ENE","East-Northeast"), 
    WNW("WNW","West-Northwest"), 
    ESE("ESE","East-Southeast"), 
    WSW("WSW","West-Southwest"), 
    SSE("SSE","South-Southeast"), 
    SSW("SSW","South-Southwest");

    private String abbr;
    private String text;

    private DirType(String abbr, String text){
      this.abbr = abbr;
      this.text = text;
    }
    public String toString(){
      return text;
    }
    public String toStringAbbr(){
      return abbr;
    }
    public boolean match(String dir) {
      if(text.equalsIgnoreCase(dir) || abbr.equalsIgnoreCase(dir)) return true;
      else return false;
    }
  }
}

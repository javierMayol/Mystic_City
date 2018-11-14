/* 
 * Neel Patel 	NetID : npate315	UIN : 674004711
 * Keval Patel
 * Javier Mayol NetID : cmayol2		UIN : 671352495
* cs-342 Software Development
* Professor John Bell
* Game version 2.0
* Implementation of the Artifact Class for the project 2.
* This class implements the functions described in the handouut
* for the behavior and atributes of artifacts in the game.
* Implements prototype design pattern.
*/

import java.util.*;

public class Artifact
{
  private int ID, value, mobility, keyPattern;
  private String name, description;
  private Printer printer;
  private static HashMap<Integer, Integer> prizes = new HashMap<Integer, Integer>();
  
  //Void argument constructor. Constructs a non-artifact.
  public Artifact(){}

  public Artifact(Scanner s){
    try{
      while(!s.hasNextInt())s.nextLine();
      this.ID = s.nextInt();
      this.value = s.nextInt(); 
      this.mobility = s.nextInt();
      this.keyPattern = s.nextInt();
      this.name = s.findInLine("[[a-zA-Z'-]+\\s]+").trim();
      s.nextLine();
      int n = s.nextInt();
      this.description = s.nextLine();
      for(int i = 0; i < n; i++)
        this.description = s.nextLine();
      printer = printer.getInstance();
      if(this.mobility < 0)prizes.put(ID, this.value);
    }
    catch(Exception e) {
      System.out.println("Error: Verify that the file is formatted correctly to initialize artifact objects.");
    }
  }

  //Returns value of the artifact.
  public int value(){
    return this.value;
  }

  //size() AKA mobility. size() < 0 indicates an artifact that cannot be moved.
  public int size(){
    return this.mobility;
  }

  public int getID()
  {
    return this.ID;
  }

  public String name(){
    return this.name.trim();
  }

  public String description() {
    return this.description.trim();
  }

  public int get_key(){
    return keyPattern;
  }

  public int open(int ID)
  {
    if(prizes.isEmpty()) return 0;
    return prizes.remove(ID);
  }

  public void print()
  {
      String val = "value "+this.value;
      String mov = "mobility "+this.mobility;
      String pattern = "Pattern "+this.keyPattern;
      printer.print_info(this.name, this.ID,val,mov,pattern); 
  } 
}

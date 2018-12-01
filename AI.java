//MAke the ogre still all user's artifcat if they 
 /*
 * Neel Patel 	NetID : npate315	UIN : 674004711
 * Keval Patel
 * Javier Mayol NetID : cmayol2		UIN : 671352495
*/ 
//find themselvs in the same room. Later the Ogre
//will drop the artifcat as he moves from room to room.

import java.util.*;

public class AI implements DecisionMaker 
{
  private Character character;
  private Place place;
  private StringPairCompare word;
  //private keyboardScanner keyboard;

  //Constructor
  public AI(){}
  public Move getMove(Character client, Place where)
  {
     this.character = client;
     this.place = where; 
     return this.execCommand();
  }

  private String AI_cmd()
  {
    String [] cmdBank = new String[]{"GO", "GO", "GO", "GET", "USE", "DROP"};
    Random rand = new Random();
    int index = rand.nextInt(cmdBank.length);
    return cmdBank[index];
  }

  private String go_arg()
  {
    return character.current.AI_navigation();
  }

  private String get_arg()
  {
    String thing = character.current.shuffleArtifacts();
    return thing;
  }

  private String use_arg()
  {
    String thing = NPC.randomArtifact(character.shuffleArtifacts());
    if(thing == null)thing = "none";
    return thing;
  }

  private Move execCommand()
  {
     String str = AI_cmd();
     String arg;

    if("GO".equalsIgnoreCase(str))
    {
      arg = go_arg();
      if(arg == null)return null;
      if(arg.equalsIgnoreCase("Exit"))return null;
      //System.out.println(character.name()+" going "+arg);
      return new Go(character, arg);
    }

    else if("GET".equalsIgnoreCase(str))
    {
      arg = get_arg();
      if(arg == null)return null;
      //System.out.println(character.name()+" getting "+arg+" in "+character.current.name());
      return new Get(character, arg);
    }

    else if("USE".equalsIgnoreCase(str))
    {
      arg = use_arg();
      if(arg == null)return null;
      //System.out.println(character.name()+" using "+arg+" in "+character.current.name());
      return new Use(character, arg);
    } 

    else if("DROP".equalsIgnoreCase(str))
    {
      if(character.name().equals("A Cheerful Leprechaun")) return null;
      arg = use_arg();
      if(arg == null)return null;
      //System.out.println(character.name()+" Dropping "+arg+" in "+character.getCurrentPlace().name());
      return new Drop(character, arg);
    }

    return null;
  }
}
  

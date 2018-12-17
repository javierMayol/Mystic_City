 /*
 *  Neel Patel 	NetID : npate315	UIN : 674004711
 * Keval Patel
 * Javier Mayol NetID : cmayol2		UIN : 671352495
 */
import java.util.*;

public class Go implements Move
{
  private Character character;
  private String direction;
  private Character Ogre;
  private IO io;

  public Go(Character client, String direc)
  {
     this.character = client;
     this.direction = direc;
     this.io = new IO();
     this.io.selectInterface(io.TEXT);
  }
  public void execute()
  {
     character.getCurrentPlace().removeCharacter(character.name());
     Place next = null;
     if(this.direction.equalsIgnoreCase("None"))
       next = Place.chooseRandomPlace();
     else
       next = character.current.followDirection(direction); 
     if(next.getID() == character.current.getID() && character instanceof Player)
       io.display("Oh no!! This direction is locked.");
     character.goTo(next);
     character.getCurrentPlace().addCharacter(character);

     if(character.current.getID() == 1)//||character.current.getID() == 0)
     {
       char sadFace = '\u2639';
       if(character instanceof Player)
         io.display("\nYou have exited the game."+sadFace);
       Exit exit = new Exit(character); 
       exit.execute();
     }

     if(character instanceof Player)
       character.current.setCurrentCharacter();
     Ogre = Riddle.getOgre();
     if(Ogre != null)
     {
       String Ogre_location = Ogre.current.name();
       //String nam = Ogre.name();
       //System.out.println("Ogre's at "+Ogre_location+" "+nam);
       if(character.current.name().equals(Ogre_location))
       {
         if(character instanceof Player)
         {
           if(!character.hasSomething())
           {
             io.display(">Ogre\n  Oh man! you broke. I'm not wasting my time on you.\n");
             return;
           }
           Riddle riddle = new Riddle(character);
           riddle.execute();
         }
       }
     }
     else
     {
     System.out.println("Character current.followDirection failed || Riddle instantiation failed.");
       return;
     }  
  }
}

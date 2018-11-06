 /*
 *  Neel Patel 	NetID : npate315	UIN : 674004711
 * Keval Patel
 * Javier Mayol NetID : cmayol2		UIN : 671352495
 */
import java.util.*;

public class Exit implements Move
{
  private Character character;

  public Exit(Character client)
  {
    this.character = client;
  }

  public void execute()
  {
    String name = character.name();
    character.dropAll();
    character.removePlayer();
    Player.decreasePlayer_num();
    character.getCurrentPlace().removeCharacter(name);
    character.removeCharacter();
    if(character instanceof Player)
      System.out.println("Bye, "+name+"!\n");
  }
}

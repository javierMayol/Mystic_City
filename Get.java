 /*
 *  Neel Patel 	NetID : npate315	UIN : 674004711
 * Keval Patel
 * Javier Mayol NetID : cmayol2		UIN : 671352495
 */
import java.util.*;

public class Get implements Move
{
  private Artifact thing;
  private Character character;
  //private keyboardScanner keyboard;
  private IO io;

  public Get(){}

  public Get(Character client, String artifact)
  {
    this.character = client;
    this.thing = character.getCurrentPlace().removeArtifact(artifact);
    //keyboard = keyboard.getInstance();
    this.io = new IO();
    this.io.selectInterface(io.TEXT);
  }
  public void execute()
  {
    if(this.thing == null) return;
    if("Leather bag".equals(this.thing.name()))character.canLift();
    if(this.thing.size() < 0)
    {
      character.getCurrentPlace().addArtifact(this.thing);
      if(character instanceof Player)
      {
        io.display("Cannot get artifact. It might be too heavy.");
        //keyboard.getInput();
        return;
      }
    }
    this.character.addArtifact(thing);
 //   GUI_1.gettingArtifacts(character.artNames());
  }

}

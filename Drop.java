 /*
 *  Neel Patel 	NetID : npate315	UIN : 674004711
 * Keval Patel
 * Javier Mayol NetID : cmayol2		UIN : 671352495
 */
import java.util.*;

public class Drop implements Move
{
  private Character character;
  private String Key;
  private Artifact removed;

  public Drop(){}

  public Drop(Character client, String key)
  {
    this.character = client;
    this.Key = key;
  }
  public void execute()
  {
    this.removed = character.drop(Key);
    if(this.removed == null) return;
    this.character.getCurrentPlace().addArtifact(removed);
//    System.out.println(character.name()+" dropped "+Key);
  }
}

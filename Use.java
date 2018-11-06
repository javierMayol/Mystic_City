 /*
 *  Neel Patel 	NetID : npate315	UIN : 674004711
 * Keval Patel
 * Javier Mayol NetID : cmayol2		UIN : 671352495
 */
import java.util.*;

public class Use implements Move
{
  private Character character;
  private String Key;
  private String direction; 
  private Artifact thing;
  private keyboardScanner keyboard;

  public Use(Character client, String key)
  {
    this.character =  client;
    this.Key = key;
    this.direction = new String();
    keyboard = keyboard.getInstance();
  }

  public void execute()
  {
    parse(); 
    if(this.thing == null)return;
    if(this.thing.get_key() > 0)// && this.character instanceof Player)
    {
      character.current.useKey(thing.get_key(), direction);
      Go going = new Go(character, direction);
      going.execute();
    }
    else if(this.direction.equals("open"))
      openArtifact();
    else if("Parchment scroll".equalsIgnoreCase(this.thing.name()))
       readScroll();
  }

  private void parse()
  {
    String holder = this.Key;
    if(holder.contains("open"))
    {
      this.direction = "open";
      this.Key = character.current.hasThing(holder.trim());
      if((this.thing = character.current.removeArtifact(this.Key)) == null)return;
    }
    else
    {
      this.Key = character.hasThing(holder.trim()); 
      this.direction = holder.substring(Key.length()).trim();
      if((this.thing = this.character.use(Key)) == null)return;
    }
  }

  private void openArtifact()
  {
    if(thing.size() >= 0)
    {
      character.current.addArtifact(this.thing);
    }
    else
    {
      int points = this.thing.open(this.thing.getID());
      character.addPoints(points);
      character.current.addArtifact(this.thing);
    }
  }

  private void readScroll()
  {
    String scroll = "&&************** Old Parchment Scroll *****************&&\n   This is just an old scroll.";
    System.out.println("\n"+scroll+"\n\n&&*****************************************************&&");
  } 
}

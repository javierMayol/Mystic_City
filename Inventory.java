 /*
 *  Neel Patel 	NetID : npate315	UIN : 674004711
 * Keval Patel
 * Javier Mayol NetID : cmayol2		UIN : 671352495
 */
import java.util.*;

public class Inventory implements Move
{
  Character character;
  public Inventory(Character client)
  {
    this.character = client;
  }
  public void execute()
  {
    this.character.viewInventory();
  }
}

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

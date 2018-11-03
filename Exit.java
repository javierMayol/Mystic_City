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
    character.removePlayer();
    Player.decreasePlayer_num();
    character.getCurrentPlace().removeCharacter(name);
    character.removeCharacter();
    if(character instanceof Player)
      System.out.println("Bye, "+name+"!\n");
  }
}

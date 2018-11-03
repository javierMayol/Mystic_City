import java.util.*;

public class Get implements Move
{
  private Artifact thing;
  private Character character;
  private keyboardScanner keyboard;

  public Get(){}

  public Get(Character client, String artifact)
  {
    this.character = client;
    this.thing = character.getCurrentPlace().removeArtifact(artifact);
    keyboard = keyboard.getInstance();
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
        System.out.println("Cannot get artifact. It might be too heavy.");
        keyboard.getInput();
        return;
      }
    }
    this.character.addArtifact(thing);
  }

}

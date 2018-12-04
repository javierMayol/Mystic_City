import java.util.*;

public class HollyWand extends ArtifactUse
{
  private IO io;
  public HollyWand()
  {
    this.io = new IO();
    io.selectInterface(io.TEXT);;
  }

  public void use()
  {
    Character c = getCharacter();
    io.display("Tell the Holly wand where to.");
    String destination = io.getLine();
    Place placeTo = Place.placeByName(destination);;
    if(placeTo == null) 
    {
      Go magicMove = new Go(getCharacter(), "None");
      magicMove.execute();
    }
    else
    {
      c.getCurrentPlace().removeCharacter(c.name());
      c.goTo(placeTo);
      c.getCurrentPlace().addCharacter(c);
      c.current.setCurrentCharacter();
    } 
  }
}
   

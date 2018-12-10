/*
  Javier Mayol
  cmayol
	Artifact power for
    	HollyWand.
	Follows a Prototype design pattern.
*/
import java.util.*;

public class HollyWand extends ArtifactUse
{
  private IO io;
  public HollyWand()
  {
    this.io = new IO();
    this.io.selectInterface(io.TEXT);
  }

  public void use()
  {
    Character c = getCharacter();
    if(c.getPoints() >= 200)
    {
      io.selectInterface(c.getPlayerInterface());
      io.displayPrompt("Holly Wand");
      io.display("Tell the Holly wand where to.");
      String [] options = {"12", "24", "108", "105"}; 
      // GUI_1.gettingArtifacts(options);
      GUI_1.setWindow(true);
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
      c.addPoints(-200);
    } 
  }
}
   

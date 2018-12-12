/*
  Javier Mayol
  cmayol
	Artifact power for
    	HollyWand.
	Follows a Prototype design pattern.
*/
import java.util.*;
import javax.swing.*;

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
    if(c.getPoints() >= 100)
    {
      io.selectInterface(c.getPlayerInterface());
      ImageIcon icon =  new ImageIcon("HollyWand.png");
      GUI_1.setOptionPane("Tell the Holly Wand where to.", "Holly Wand", icon, null);
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
      c.addPoints(-100);
    } 
  }
}
   

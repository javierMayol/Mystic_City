/*
  Javier Mayol
  cmayol
	Artifact power for
	Parchment scroll artifact.
	Follows a Prototype design pattern.
*/
import java.util.*;

public class ReadScroll extends ArtifactUse
{
  private IO io;

  public ReadScroll()
  {
    this.io = new IO();
    this.io.selectInterface(io.TEXT);
  }

  public void use()
  {
    Character c = getCharacter();
    String display = "**************& Parchment Scroll &*************\n";
    display +="\t\t\tXYZZY\n";
    display += "********************************************\n";
    io.selectInterface(c.getPlayerInterface());
    //Add options to do with the scroll.
    GUI_1.setOptionPane(display,"Parchment Scroll", null, null);
    io.getLine();
  }
}
   

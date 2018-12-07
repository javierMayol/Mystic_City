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
    io.display("*********************& Parchment Scroll &*************************");
    io.display("\t\t\tXYZZY");
    io.display("******************************************************************");
  }
}
   

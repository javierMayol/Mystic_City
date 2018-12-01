import java.util.*;

public class ReadScroll extends ArtifactUse
{
  private IO io;

  public ReadScroll()
  {
    this.io = new IO();
  }

  public void use()
  {
    io.display("*********************& Parchment Scroll &*************************");
    io.display("\t\t\tXYZZY");
    io.display("******************************************************************");
  }
}
   

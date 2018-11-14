import java.util.*;

public class HollyWand extends ArtifactUse
{
  public HollyWand(){}

  public void use()
  {
    Go magicMove = new Go(getCharacter(), "None");
    magicMove.execute();
  }
}
   

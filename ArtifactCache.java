import java.util.*;

public class ArtifactCache 
{
  private static Hashtable<Integer, ArtifactUse> ArtHash = new Hashtable<Integer, ArtifactUse>();

  public static ArtifactUse getArtifact(int ID)
  {
    ArtifactUse cachedArt = ArtHash.get(ID);
    try{
      return (ArtifactUse) cachedArt.clone();
    }
    catch(NullPointerException e){}
    return null;
  }

  public static void loadCache()
  {
    //Parchment scroll
    ReadScroll scroll = new ReadScroll();
    scroll.setID(5);
    ArtHash.put(scroll.getID(), scroll);

    //Holly Wand
    HollyWand wand = new HollyWand();
    wand.setID(4);
    ArtHash.put(wand.getID(), wand);
  }
}

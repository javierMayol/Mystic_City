import java.util.*;

public abstract class ArtifactUse implements Cloneable
{
  private int ID;
  private Character character;
  
  abstract void use();

  public void setID(int ID)
  {
     this.ID = ID;
  }

  public void setCharacter( Character c)
  {
    this.character = c;
  }

  public Character getCharacter()
  {
    return character;
  }

  public int getID()
  {
    return this.ID;
  }

  public Object clone() 
  {
    Object clone = null;
    try 
    {
      clone = super.clone();
    }
    catch(CloneNotSupportedException e) 
    {
      e.printStackTrace();
    }
    return clone;
  }
}


import java.util.*;

public class IO implements UserInterface
{
  public static final int TEXT = 0;
  public static final int GUI_1 = 1;
  public static final int GUI_2 = 2;
  public static final int GUI_3 = 3;
  //implementor might need to be static.
  private UserInterface implementor;
	
  public void display(String s)
  {
    implementor = new TextInterface(); 
    implementor.display(s);
  }

  public String getLine()
  {
    implementor = new TextInterface();
    return implementor.getLine();
  }

  public void selectInterface(int n)
  { }
}

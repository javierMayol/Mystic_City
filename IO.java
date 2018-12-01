import java.util.*;

public class IO implements UserInterface
{
  public static final int TEXT = 0;
  public static final int GUI_1 = 1;
  public static final int GUI_2 = 2;
  public static final int GUI_3 = 3;
  //implementor might need to be static.
  private UserInterface implementor;

  //The default implementor if not otherwise specified should be the TextInterface.
  public IO()
  {
    implementor = new TextInterface(); 
  }
	
  public void display(String s)
  {
    implementor.display(s);
  }

  public String getLine()
  {
    return implementor.getLine();
  }

  public void selectInterface(int n)
  { }
}

/*	Javier Mayol 	ACCC: cmayol
#  	Neel Patel 	NETID : npate315	UIN : 674004711
# 	Keval Patel
*/
import java.util.*;

public class IO //implements UserInterface
{
  public static final int TEXT = 0;
  public static final int GUI_1 = 1;
  public static final int GUI_2 = 2;
  public static final int GUI_3 = 3;
  //implementor might need to be static.
  private static UserInterface implementor;

  //The default implementor if not otherwise specified should be the TextInterface.
  public IO()
  { }

  public void display(String s)
  {
    implementor.display(s);
  }

  public void displayPrompt(String s)
  {
    implementor.displayPrompt(s);
  }

  public String getLine()
  {
    return implementor.getLine();
  }
  public void selectInterface(int n)
  {

    switch (n) {
	case 0:
	  implementor = new TextInterface(); 
	  break;
	case 1:
	  implementor = new GUI_1();
	  break;
	}
  }
}


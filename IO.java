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
    String cmd = implementor.getLine();
    return cmd;
  }

  public void selectInterface(int n)
  {

    switch (n) {
        case 0:
    	       implementor = new TextInterface(); 
               //display("implementor"); 
    	       break;
        case 1:
   	       implementor = new GUI_1(); //(GUI_1)implementor; 
	       break;
	case 2:
		//implementor = new GUI_2();
		break;
	case 3:
		implementor = new GUI_3();
		break;
    }
/*
    if(n == GUI_1)
    {
      implementor = new GUI_1(); //(GUI_1)implementor; 
    }
    else
      implementor = new TextInterface(); 
*/
  }
}

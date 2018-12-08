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

<<<<<<< HEAD
  public String getLine()
  {
    return implementor.getLine();
  }
=======
	public void displayPrompt(String s)
	{
		implementor.displayPrompt(s);
	}
>>>>>>> 952562f9bff6845f7aa5ad4b6a4fdba3300bceaa

	public String getLine()
	{
		String cmd = implementor.getLine();
		return cmd;
	}

<<<<<<< HEAD
    switch (n) {
        case 0:
    	       implementor = new TextInterface(); 
               //display("implementor"); 
    	       break;
        case 1:
   	       implementor = new GUI_1();
	       break;
	case 2:
		//implementor = new GUI_2();
		break;
	case 3:
		implementor = new GUI_3();
		break;
    }
  }
=======
	public void selectInterface(int n)
	{

		switch (n) {
			case 0:
				implementor = new TextInterface(); 
				//display("implementor"); 
				break;
			case 1:
				implementor = new GUI_1();
				break;


			case 2:
				//implementor = new GUI_2();
				break;
			case 3:
				implementor = new GUI_3();
				break;
		}

		/*
		   case 2:
		   implementor = new GUI_2();
		   break;
		   case 3:
		   implementor = new GUI_3();
		   break;
		 */
	}
>>>>>>> 952562f9bff6845f7aa5ad4b6a4fdba3300bceaa
}


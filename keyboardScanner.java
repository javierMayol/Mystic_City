 /*
 *  Neel Patel 	NetID : npate315	UIN : 674004711
 * Keval Patel
 * Javier Mayol NetID : cmayol2		UIN : 671352495
 */
import java.util.*;

public class keyboardScanner
{
  private static keyboardScanner instance = null;
  private String input;

  protected keyboardScanner(){};
  
  public static keyboardScanner getInstance()
  {
    if(instance == null) instance = new keyboardScanner();
    return instance;
  }
  
  public String getInput()
  {
     Scanner scan = new Scanner(System.in);
     this.input = CleanLineScanner.getCleanLine(scan);
     return input;
  }
  public String commandLinePrompt(String prompt)
  {
    this.input = prompt+">";
    System.out.print(input);
    return getInput();
  }
}

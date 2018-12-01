 /*
 *  Neel Patel 	NetID : npate315	UIN : 674004711
 * Keval Patel
 * Javier Mayol NetID : cmayol2		UIN : 671352495
 */
import java.util.*;
import java.lang.*;

public class keyboardScanner
{
  private static keyboardScanner instance = null;
  private String input;
  private int num;
  protected keyboardScanner(){};
  private IO io = new IO();

  public static keyboardScanner getInstance()
  {
    if(instance == null) instance = new keyboardScanner();
    return instance;
  }

  public String getInput()
  {
     Scanner scan = new Scanner(System.in);
     this.input = CleanLineScanner.getCleanLine(scan.nextLine());
     return input;
  }

  public String commandLinePrompt(String prompt)
  {
    this.input = prompt+">";
    io.display(input);
    //System.out.print(input);
    return io.getLine();
  }
}

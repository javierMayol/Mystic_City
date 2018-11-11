 /*
 *  Neel Patel 	NetID : npate315	UIN : 674004711
 * Keval Patel
 * Javier Mayol NetID : cmayol2		UIN : 671352495
 */
import java.util.*;

public class Trade implements Move
{
  private static String offer;
  private static String good;
  private Character lender;
  private Character requester;
  private keyboardScanner keyboard;

  public Trade(Character A, Character B)
  {
    this.lender = A;
    this.requester = B;
    keyboard = keyboard.getInstance();
  }

  public void execute()
  {
    if(requester.isAwaiting()&&(offer != null || good != null))
    {
      System.out.println("Would you like to accept "+requester.name()+" offer?"+offer+" "+good);
      String answer = keyboard.getInput();
      if("Yes".equalsIgnoreCase(answer) || "Y".equalsIgnoreCase(answer))
      {
        Drop thing = new Drop(requester, offer);
        thing.execute();
        Get that = new Get(lender, offer);
        that.execute();
        thing = new Drop(lender, good);
        thing.execute();
        that = new Get(requester, good);
        that.execute();
        requester.awaiting(false);
        String greeting = "\n>You have a message from "+lender.name()+"\n\nThanks for your business!";
        requester.message(greeting);
      }
      else
      {
        String reject = "\n>You have a message from "+lender.name()+"\n\nSorry, not interested.";
        requester.message(reject);
        requester.awaiting(false);
      }  
    }
  }
  public static void setOffer(String thing)
  {
    offer = thing;
  }
  
  public static void setGood(String that)
  {
    good = that;
  }
}

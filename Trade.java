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
  private static Character requester;
  //private keyboardScanner keyboard;
  private IO io;

  public Trade(Character A)
  {
    this.lender = A;
    //keyboard = keyboard.getInstance();
    this.io = new IO();
    this.io.selectInterface(io.TEXT);
  }

  public void execute()
  {
    if(requester == null)
       startTrade();
    else
       finishTrade();
  }
  private void startTrade()
  {
    io.selectInterface(lender.getPlayerInterface());
    String display = "Who would you like to trade with?";
    Object[] options = Arrays.copyOf(lender.current.charNames(), lender.current.charNames().length, Object[].class);
    GUI_1.setOptionPane(display, "TRADE", null, options);
    String character = io.getLine();
    try{
      Request ask = new Request(lender, character);
      ask.execute();
    }catch(NullPointerException e){}
  }
  private void finishTrade()
  {
    try
    {
      if(requester.isAwaiting()&&(offer != null || good != null))
      {
        Character person = lender.current.listening(requester.name());
       if(person == null)return;
       io.selectInterface(lender.getPlayerInterface());
       Object[] options = {"YES", "NO"};
       GUI_1.setOptionPane("Would you like to accept "+requester.name()+" offer?\n","Trade", null, options);
       String answer = io.getLine();
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
         String greeting = "\n>You have a message from "+lender.name()+"\n\nThanks for your business!\n";
         requester.message(greeting);
       }
     }
     else
     {
       String reject = "\n>You have a message from "+lender.name()+"\n\nSorry, not interested.\n";
       requester.message(reject);
       requester.awaiting(false);
     }
     requester = null;  
    }
    catch(NullPointerException e){}
  }
  public static void setOffer(String thing)
  {
    offer = thing;
  }
  
  public static void setGood(String that)
  {
    good = that;
  }
  public static void setRequester(Character r)
  {
    requester = r;
  }
}

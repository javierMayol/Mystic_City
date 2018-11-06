 /*
 *  Neel Patel 	NetID : npate315	UIN : 674004711
 * Keval Patel
 * Javier Mayol NetID : cmayol2		UIN : 671352495
 */
import java.util.*;

public class Request implements Move
{
  private Character character;
  private String argument;
  private StringPairCompare str_format;
  private keyboardScanner keyboard;

  public Request(Character A, String arg)
  {
    this.character = A;
    this.argument = arg;
    str_format = str_format.getInstance();
    keyboard = keyboard.getInstance();
  }

  public void execute()
  {
    if(argument == null) return;

    //Look for the supplier
    String player = argument;
    if(player == null) return;
    Character supplier = character.current.listening(player);
    if(supplier == null)return;

    System.out.println("What would you like to trade?");
    String thingA = keyboard.getInput(); 
    if(thingA == null) return;
    Artifact offer = character.use(thingA);
    if(offer == null) return;
    thingA = offer.name();
    Trade.setOffer(thingA);
    int val = offer.value();
    String value = Integer.toString(val);
    thingA += " "+value+"pts";

    System.out.println("What item are you interested from "+supplier.name()+"?");
    String thingB = keyboard.getInput();
    if(thingB == "null") return;
    Artifact good = supplier.use(thingB);
    if(good == null) return;
    thingB = good.name();
    Trade.setGood(thingB);
    val = good.value();
    value = Integer.toString(val);
    thingB += " "+value+"pts";

//    if(character.getID() == supplier.getID())return;

    String message = ">You have a request from "+character.name()+":";
    String message2 = "\nWould you like to trade "+thingA+" for "+thingB+"?\nType TRADE "+character.name();
    message += " "+message2;
    character.awaiting(true);
    supplier.message(message);
  }
}
        

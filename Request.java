 /*
 *  Neel Patel 	NetID : npate315	UIN : 674004711
 * Keval Patel
 * Javier Mayol NetID : cmayol2		UIN : 671352495
 */
import java.util.*;

public class Request implements Move
{
  private Character character;
  private Character supplier;
  private String argument;
  private StringPairCompare str_format;
  private String thingA, thingB;
  //private keyboardScanner keyboard;
  private IO io;

  public Request(Character A, String arg)
  {
    this.character = A;
    this.argument = arg;
    str_format = str_format.getInstance();
    this.io = new IO();
    this.io.selectInterface(io.TEXT);
  }

  public void execute()
  {
    if(argument == null) return;

    //Look for the supplier
    String player = argument;
    if(player == null) return;
    supplier = character.current.listening(player);
    if(supplier == null)return;

    io.selectInterface(character.getPlayerInterface());
    //String[] things = character.artNames();
    Object[] options = Arrays.copyOf(character.artNames(), character.artNames().length, Object[].class);
    GUI_1.setOptionPane("What would you like to trade?","Trade", null, options);
    thingA = io.getLine(); 
    sendIt();
  }
  
  private void sendIt()
  {
    if(thingA == null) return;
    Artifact offer = character.use(thingA);
    if(offer == null) return;
    thingA = offer.name();
    Trade.setOffer(thingA);
    int val = offer.value();
    String value = Integer.toString(val);
    thingA += " "+value+"pts";
    finishRequest();
  }  

  private void finishRequest()
  {
    Object[] options = Arrays.copyOf(supplier.artNames(), supplier.artNames().length, Object[].class);
    GUI_1.setOptionPane("What item are you interested in from "+supplier.name()+"?","Trade", null, options);
    thingB = io.getLine(); 
    if(thingB == null) return;
    Artifact good = supplier.use(thingB);
    if(good == null) return;
    thingB = good.name();
    Trade.setGood(thingB);
    Trade.setRequester(character);
    int val = good.value();
    String value = Integer.toString(val);
    thingB += " "+value+"pts";
    // if(character.getID() == supplier.getID())return;
    String message = ">You have a request from "+character.name()+":";
    String message2 = "\nWould you like to trade "+thingA+" for "+thingB+"?\nType TRADE to continue.";
    message += " "+message2+"\n\n";
    character.awaiting(true);
    supplier.message(message);
  }
}
        

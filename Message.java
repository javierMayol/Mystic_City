import java.util.*;

public class Message implements Move
{
  private Character character;
  private String argument;
  //private keyboardScanner keyboard;
  private StringPairCompare str_format;
  private IO io;

  public Message(Character A, String arg)
  {
    this.character = A;
    this.argument = arg;
    //keyboard = keyboard.getInstance();
    str_format = str_format.getInstance();
    this.io = new IO();
    this.io.selectInterface(io.TEXT);
  }

  public void execute()
  {
    String player = argument.trim();

    //Look for the supplier
    if(player == null) return;
    Character supplier = character.current.listening(player.toLowerCase().trim());
    if(supplier == null)return;
    String message = "\n>You have a messag from "+character.name()+":\n\n  ";
    io.selectInterface(character.getPlayerInterface());
    GUI_1.setWindow(true);
    io.display("Message to "+supplier.name());
    message += io.getLine(); 
    supplier.message(message);
    return;
 }
}
        

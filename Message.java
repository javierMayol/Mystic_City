import java.util.*;

public class Message implements Move
{
  private Character character;
  private String argument;
  private keyboardScanner keyboard;
  private StringPairCompare str_format;

  public Message(Character A, String arg)
  {
    this.character = A;
    this.argument = arg;
    keyboard = keyboard.getInstance();
    str_format = str_format.getInstance();
  }

  public void execute()
  {
    String player = argument.trim();

    //Look for the supplier
    if(player == null) return;
    Character supplier = character.current.listening(player.toLowerCase().trim());
    if(supplier == null)return;
    String message = "\n>You have a messag from "+character.name()+":\n\n  ";
    message += keyboard.getInput(); 
    supplier.message(message);
    return;
 }
}
        

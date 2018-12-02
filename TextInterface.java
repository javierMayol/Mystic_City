import java.util.*;

public class TextInterface implements UserInterface
{
  private static keyboardScanner keyboard;

  public TextInterface()
  {
    keyboard = keyboard.getInstance();
  } 

  public void display(String s)
  {
    System.out.println(s); 
  }

  public String displayPrompt(String s)
  {
    return keyboard.commandLinePrompt(s);
  }

  public String getLine()
  {
    return keyboard.getInput();
  }
}

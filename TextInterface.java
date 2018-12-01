import java.util.*;

public class TextInterface implements UserInterface
{
  private static keyboardScanner keyboard;

  public void display(String s)
  {
    System.out.println(s); 
  }
  public String getLine()
  {
    keyboard = keyboard.getInstance();
    return keyboard.getInput();
  }
}

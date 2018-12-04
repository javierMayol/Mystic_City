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

  public void displayPrompt(String prompt)
  {
    String input = prompt+">";
    System.out.print(input);
  }

  public String getLine()
  {
    return keyboard.getInput();
  }
}

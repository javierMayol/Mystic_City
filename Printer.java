 /*
 *  Neel Patel 	NetID : npate315	UIN : 674004711
 * Keval Patel
 * Javier Mayol NetID : cmayol2		UIN : 671352495
 */
import java.util.*;

public class Printer
{
  private static Printer instance = null;
  private static StringPairCompare str_format;
  private String title;
  private boolean format_title;
  private static IO io;

  protected Printer(){} 
  
  public static Printer getInstance()
  {
    if(instance == null)
    {
      instance = new Printer();
      io = new IO();
      io.selectInterface(io.TEXT);
      str_format = str_format.getInstance();
    }
    return instance;
  }
 
  public void print_info(String name, int ID, String secA, String secB, String secC )
  {
    String format_str;
    format_str = String.format("|%23s ID:%3d|%28s|%8s|%8s",name,ID,secA,secB,secC);
    io.display(format_str);
    io.display("----------------------------------------------------------------------------------");
  }
  
  public void print_info(String name, int ID, List<String> secA, String secB, String secC )
  {
    String format_str;
    String space = "";
    if(secA.isEmpty())
    {
      format_str = String.format("|%23s ID:%3d|%28s|%8s|%8s",name,ID,space,secB,secC);
      io.display(format_str);
      io.display("----------------------------------------------------------------------------------");
    }
    else
    {
      format_str = String.format("|%23s ID:%3d|%28s|%8s|%8s",name,ID,secA.get(0),secB,secC);
      io.display(format_str);
      int n = secA.size();
      if(n > 1)
      {
        for(int i = 1; i < n; i++)
        {
          format_str = String.format("|%23s    %3s|%28s|%8s|%8s",space,space,secA.get(i),space,space);
          io.display(format_str);
        }
      }   
      io.display("----------------------------------------------------------------------------------");
    }
  }
  
  public void setTitleF(String title)
  {
     this.title = title;
  }

  public void setTitle(String title)
  {
     this.title = title;
  }
  
  public String getInitials(String str)
  {
    int index = 1;
    int size = str.length();
    int stopper = 0;
    String word = new String();
    char [] initials = new char[size];
    while(stopper <= size)
    {
      word = str_format.getWordAt(index,str);
      initials[index - 1] = word.charAt(0);
      stopper += word.length() + 1;
      index++;
    }
    str = new String(initials);
    return str.trim();
  }
}


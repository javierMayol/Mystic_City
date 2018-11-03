/*
* String strPairCheck() :
* 	Checks the matching and alignment of two strings. 
*	This method allows the user to input a string with
*	a spelling penalty of at most 5.
* 	The first argument is a buffer string which is to 
* 	be compared with the second string argument.
* 	Matching refers to 
*       The int counter variable stores the number of
*      	penalties for mismatch and dislaignents.
* 	The mismatch penalty is 1.
* 	The alignment penalty follows the mismatch penalty
* 	and adds 1 to the counter variable. 
*/
import java.util.*;

public class StringPairCompare
{
  private String buffer, str;
  private boolean match;
  private double lowerBound;
  private double upperBound;

  private static StringPairCompare instance = null;

  protected StringPairCompare(){  
    this.buffer = new String();
    this.str = new String();
    this.match = false;
    this.lowerBound = 0.25;
    this.upperBound = 1.333;
  }
  
  public static StringPairCompare getInstance()
  {
    if(instance == null) instance = new StringPairCompare();
    return instance;
  }

  public String getString()
  {
    return this.str;
  }

  private StringTokenizer parse_str(String userInput)
  {
    this.str = userInput;
    StringTokenizer strT = new StringTokenizer(str);
    return strT;
  }
 
  //Returns word at position indicated by first int argument.
  public String getWordAt(int index, String inputUser)
  { 
    if(inputUser.isEmpty()){
      System.out.println("String is empty.");
       this.str = "";
       return null;
    }
    //add warning for number of indexes.
    StringTokenizer strT = parse_str(inputUser);
    String[] array = new String[strT.countTokens()];
    int i = 0;
    while(strT.hasMoreTokens())
    {
      array[i] = strT.nextToken();
      i++;
    }
    if(index >= 1)
      this.str = array[index - 1];
    else
      return null;
    return this.str;
  }

  public String getWordsFrom(int index, String inputUser)
  {
    //add warning for number of inderes.
    StringTokenizer strT = parse_str(inputUser);
    int length = strT.countTokens();
    if(index < 1) return null;
    if(index > length)
    {
      System.err.println("The string doesn't have that many words.");
      return null;
    }
    String[] array = new String[strT.countTokens()];
    int i = 0;
    while(strT.hasMoreTokens())
    {
      array[i] = strT.nextToken();
      i++;
    }
    this.str = "";
    for(i = index - 1; i < length; i++)
      this.str += array[i]+" ";
    return this.str.trim();
  }    

  public void strSpellCheck(String buff, String userInput)
  {
   
    this.buffer = buff;
    String lBuffer = buff; //Local buffer. Saves inputted the buffer.
    this.str = userInput;

    //If buffer has 4 or less letters it can't really hold a penalty of 5 condition. 
    if((buffer.length() <= 4))
    { 
      this.str = buffLessThan4(buffer, userInput);
      return;
    }

    StringTokenizer strT = parse_str(str);
    StringTokenizer bufferT = parse_str(buffer);
    String[] catchT = new String[strT.countTokens()], matchT = new String[bufferT.countTokens()];
    int min = Math.min(strT.countTokens(), bufferT.countTokens());

    //while(bufferT.hasMoreTokens())
    for(int i =0; i < min; i++)
    {
      str = strT.nextToken();
      buffer = bufferT.nextToken();
      if(!str.equalsIgnoreCase(buffer))
      {
        catchT[i] = str.toLowerCase().trim();
        matchT[i]= buffer.toLowerCase().trim();
      }
    //  if(!strT.hasMoreTokens())break;
    //  System.out.println(buffer+" "+str);
    }
    char[] c;// = catchT.toCharArray();
    char[] b;// = matchT.toCharArray();

    if(matchT.length < catchT.length) min = matchT.length; 
    else if(matchT.length == catchT.length) min = matchT.length; 
    else min = catchT.length;

    int counter = 0;
    double n = 0, d = 0;
    for(int i = 0; i < min; i++)
     {
       if(matchT[i] != catchT[i])
       {
         d += (double) matchT[i].length();
         n += (double) catchT[i].length();
         b = matchT[i].toCharArray();
         c = catchT[i].toCharArray();
         int minC = Math.min(b.length, c.length);
	 if((matchT[i].contains(catchT[i]))||(catchT[i].contains(matchT[i])))
	 {
	   counter += Math.abs(matchT[i].length() - catchT[i].length());
	  // System.out.println("Contains "+counter+"\n"+matchT[i]+" "+catchT[i]);
	   continue;
         }
	 if(b[0] != c[0])
         {
	   counter++;
	  // System.out.println("Second if"+counter+" "+b[0]+" "+c[0]+" "+matchT[i]+" "+catchT[i]);
 	   if(b[0] != c[1])
	   {
             counter++;
            // System.out.println("Second if"+counter+" "+b[0]+" "+c[1]+" "+matchT[i]+" "+catchT[i]);
	   }
	 }
         for(int j = 1; j < minC-1; j++)
         {
           if(b[j] != c[j])
           {
	     counter++;
             if(b[j] != c[j-1] && b[j] != c[j+1])
 	       counter++;
             //System.out.println("Second if"+counter+" "+b[j]+" "+c[j]+" "+matchT[i]+" "+catchT[i]);
	   }
         }
	 if(b[minC - 1] != c[minC -1])
         {
	   counter++;
	   //System.out.println("Second if"+counter+" "+b[minC -1]+" "+c[minC -1]+" "+matchT[i]+" "+catchT[i]);
 	   if(b[minC -1] != c[minC - 2])
	   {
             counter++;
             //System.out.println("Second if"+counter+" "+b[minC - 1]+" "+c[minC - 2]+" "+matchT[i]+" "+catchT[i]);
           }
         }
       }
       //System.out.println("Catch length "+n+"\nMatch length "+d);
     }
     double mean = n / d;
//     System.out.println("Mean is : "+mean+"\nmatchT len :"+matchT.length+"\ncatchT len :"+catchT.length+"\n"+counter);
//     System.out.println("Buffer len :"+lBuffer.length()+"\nuserInput :"+userInput.length());

     String tok = "";

     while(strT.hasMoreTokens())
       tok += strT.nextToken();

     if(counter == 0) 
     {
       this.str = userInput.trim(); 
       return;
     }

     else if((counter <= 5) && ((mean >= this.lowerBound )&&(mean <= this.upperBound)))
     {
       match = true;
       lBuffer += " "+tok;
       this.str = lBuffer.trim();
     }
     else
     {
       match = false; 
       this.str = userInput;
     }
   }

   private String buffLessThan4(String buff, String input)
   {
      if(buff.length() > input.length() && buff.contains(input))
        return buff;
      else
        return input; 
   }

   public boolean match()
   {
      return this.match;
   }
}

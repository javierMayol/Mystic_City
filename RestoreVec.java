import java.util.*;

public class RestoreVec
{
  public static void main(String [] arg){

    HashMap<Integer, Integer> str_l = new HashMap<Integer, Integer>();
    String a = "Leather bag";
    String b = "rubbies";
    String c = "brass key";
    ArrayList<String> str_arr = new ArrayList<String>();

    str_arr.add(a); str_arr.add(b); str_arr.add(c);

    String str = "open Leather bag d";
    for(String i : str_arr)
    {
      if(str.contains(i))
        System.out.println(i+" is contained in "+str);
    }

    str_l.put(1, 2014); str_l.put(2, 2015); str_l.put(3, 2016); str_l.put(4, 2017); str_l.put(5, 2018);

    for(Integer i : str_l.keySet())
      System.out.println(str_l.get(i));
  }
}  

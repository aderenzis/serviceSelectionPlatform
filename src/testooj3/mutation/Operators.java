package testooj3.mutation;

public class Operators 
{
  public final static int CL = 1;
  public final static int IN = 2;
  public final static int PI = 3;
  public final static int ZE = 4;
  public final static int MAX = 5;
  public final static int MIN = 6;
  public final static int NEG = 7;
  public final static int ADD = 8;
  public final static int SUB = 9;
  public final static int RC = 10;
  
  public static String getName(int op) 
  {
    switch (op)
    {
      case CL: return "CL";
      case IN: return "IN";
      case PI: return "PI";
      case ZE: return "ZE";
      case MAX: return "MAX";
      case MIN: return "MIN";
      case NEG: return "NEG";
      case ADD: return "ADD";
      case SUB: return "SUB";
      case RC: return "RC";
    }
    return "";
  }
}
package testooj3.mutation.grouped.multiple;
import java.util.Vector;

public class Pair 
{
  String mTipo;
  int mLinea1;
  int mLinea2;
  
  public Pair(String tipo, int a, int b)
  {
    mTipo=tipo;
    mLinea1=Math.min(a, b);
    mLinea2=Math.max(a, b);
  }
  
  public String getTipo() 
  {
    return mTipo;
  }
  
  public int getLinea1() 
  {
    return mLinea1;
  }
  
  public int getLinea2() 
  {
    return mLinea2;
  }
  
  public static void add(Vector v, Pair p) 
  {
    for (int i=0; i<v.size(); i++) {
      Pair t=(Pair) v.elementAt(i);
      if (t.mLinea1==p.mLinea1 && t.mLinea2==p.mLinea2 && t.mTipo.equals(p.mTipo))
        return;
    }
    v.add(p);
  }
}
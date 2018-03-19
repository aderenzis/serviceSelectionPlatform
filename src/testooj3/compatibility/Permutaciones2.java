package testooj3.compatibility;
import java.util.Enumeration;
import java.util.Vector;
import java.util.List;

/**
 * @author israel
 */

public class Permutaciones2
{
	public Vector p;
	
    public Permutaciones2(){
    	this.p = new Vector();
    }
    
    public void escribe(String a, Vector conjunto) {
        if (conjunto.size()==1)
        {
            this.p.add(a+" "+conjunto.get(0));
        }
       
        
        for (int i=0;i<conjunto.size();i++)
        {
        	String b = (String) conjunto.get(i); 
            conjunto.removeElementAt(i);
            escribe (a+" "+b, conjunto);
            conjunto.add(i,b);
        }
    }

    public void MuestraElementosVector(Vector v){
		Enumeration enume=v.elements();
        while(enume.hasMoreElements()){
//            System.out.println(enume.nextElement());
        }
	}
    
    public Vector UnionVectores(Vector a, Vector b){
    	Vector union = new Vector();
    	Enumeration enumA=a.elements();

    	if (a.size()!=0){
    		if (b.size()!=0){
		        while(enumA.hasMoreElements()){
		        	Enumeration enumB=b.elements();
		        	Vector d = (Vector) enumA.nextElement(); 
		        	while(enumB.hasMoreElements()){
		        		Vector e = (Vector) enumB.nextElement();
		        		Vector ne = new Vector();
	        			ne = UnionVChars(d,e);
		        		union.add(ne);
		        	}
		        }
    		}
    	}
    	else
    		union=b;
    	return union;
    }
    
    
    public Vector Cad2Vector(String cad){
    	Vector v = new Vector();
    	String pal="";
    	char c;
    	for (int i=0;i<cad.length();i++){
			c=cad.charAt(i);
			if (c == ' '){
				if (pal.length() != 0)
					v.add(pal.trim());
					pal="";
			}
			else{
				pal = pal + c;
				if (i == cad.length()-1)
				v.add(pal.trim());
			}
		}
    	return v;
    }
    public Vector UnionVChars(Vector a, Vector b){
    	Vector union = new Vector();
    	/*System.out.println("Vector a: (en UnionVChars)");
    	MuestraElementosVector(a);
    	System.out.println("Vector b: (en UnionVChars)");
    	MuestraElementosVector(b);    */
    	for (int i = 0;i<a.size();i++){
    		String c = (String) a.get(i);
    		union.add(c);
    		}
    	for (int i = 0;i<b.size();i++){
    		String d = (String) b.get(i);
    		union.add(d);
    		}
    	/*System.out.println("Union: (en UnionVChars)");
    	MuestraElementosVector(union);*/
    	return union;
    }
    
    public void VecString2VecVec(Vector vstring){
    	Vector v=new Vector();
    	Enumeration venum=this.p.elements();
        while(venum.hasMoreElements()){
            String cad = (String) venum.nextElement();
            v.add(Cad2Vector(cad));	

        }
    	this.p=v;
    }
}
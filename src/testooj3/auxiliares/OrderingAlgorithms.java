package testooj3.auxiliares;

import java.util.Vector;

import testooj3.domain.Operation;

public class OrderingAlgorithms {
	   public static void bubbleSort(Vector<Operation> elements)
	    {
	        int out;
	        int in;
	        for (out = elements.size() - 1; out > 0; out--) {
	            for (in = 0; in < out; in++) {
	                Operation x = elements.get(in);
	                Operation y = elements.get(in+1);
	                if (x.getId().compareTo(y.getId())>0) {
	                    swap(elements, in, in+1);
	                }
	            }
	        }
	    }

	    private static void swap(Vector<Operation> elements, int i, int j)
	    {
	        Operation temp = elements.get(i);
	        elements.setElementAt(elements.get(j), i);
	        elements.setElementAt(temp, j);
	    }
}

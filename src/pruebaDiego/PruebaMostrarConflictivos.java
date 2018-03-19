package pruebaDiego;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import testooj3.compatibility.InterfacesCompatibilityChecker;
import testooj3.domain.Configuration;
import testooj3.gui.JDClassPathExplorer;
import testooj3.gui.compatibility.JFCompatibility;

public class PruebaMostrarConflictivos {

	public static void main (String [] args)
	{
		/** en JFCompatibility el boton "Calculate compatibility" llama a este metodo*/
//		protected void calculateCompatibility() {
//			try {
//				String candidateClass=this.jtfCandidateClass.getText();
//				String originalClass=this.jtfOriginalClass.getText();
//				String cp1=this.jtfClassPath1.getText();
//				String cp2=this.jtfClassPath2.getText();
//				this.ich=new InterfacesCompatibilityChecker(this, 
//						originalClass, candidateClass, this.jchInheritedOperations.isSelected(),
//						cp1, cp2);
//				Thread t=new Thread(ich);
//				t.start();
//			} catch (Exception e) {
//				logError(e.toString());
//				this.parentWindow.logError("Error calculating compatibility: " + e.toString());
//			}
//		}
		
		/**cableando */
		//seleccion de clase candidata
		String classpath="D:/Dropbox/workspace/TestOojAlan/bin";
		Configuration.getInstance().setClassPath(classpath);
		JDClassPathExplorer classC=new JDClassPathExplorer();
		classC.setModal(true);
		classC.setVisible(true);
		String originalClass=classC.getSelectedClass();
		String cp1=classpath;
//		
		//seleccion de la clase requerida
		JDClassPathExplorer classR=new JDClassPathExplorer();
		classR.setModal(true);
		classR.setVisible(true);
		String candidateClass =classR.getSelectedClass();
		String cp2=classpath;
		JFCompatibility parentWindows=new JFCompatibility();
		try {
			
			InterfacesCompatibilityChecker ich=new InterfacesCompatibilityChecker(parentWindows, 
					originalClass, candidateClass, false, cp1, cp2);
//			Thread t=new Thread(ich);
//			t.start();
			ich.run();
			
			String soloParaVer=ich.getCompatibilities().toString();
//			System.out.println(soloParaVer);
			
//			Vector compat;
//			Iterator it2;
			Iterator it = ich.getCompatibilities().entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry e = (Map.Entry)it.next();
				System.out.println(e.getKey() + " " + e.getValue());
				
				//recorremos el Vector en busqueda de conflictivos!
				Vector compat=(Vector) e.getValue();
//				Iterator it2=compat.iterator();
//				while(it2.hasNext())
//				{
//					
//				}
				
				/**solo me interesa saber cuales son conflictivos...
				 * es decir... en caso de encontrarse al menos 1 coincidencia conflictiva basta por ahora
				 * 
				 */ 
				
				if(compat.size()>=2)//puede ser conflictivo
				{
					Vector compatible1=(Vector)compat.get(0);
					Vector compatible2=(Vector)compat.get(1);
					String clasificacion1=(String)compatible1.get(1);//n_exact
					String clasificacion2=(String)compatible2.get(1);
					if(clasificacion1.equals(clasificacion2))
						System.out.println("El metodo "+e.getKey()+" es conflictivo con clasificacion "+clasificacion1);
					else
						System.out.println("El metodo "+e.getKey()+" no es conflictivo");
				}
				else
				{
					System.out.println("No es conflictivo..");
				}
			}
			
			
		}
		catch(Exception e)
		{
			parentWindows.logError("Error calculating compatibility: " + e.toString());
		}
		
	}
	
}

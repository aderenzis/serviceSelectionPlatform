package pruebaDiego;



import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import banco2.Cliente;

public class MasPruebas {

	/**
	 * @param args
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		// TODO Auto-generated method stub

		
		banco2.Cliente cl= new Cliente();
		Class cla=cl.getClass();
		Field[] campos=cl.getClass().getDeclaredFields();
		Field[] campos2=cl.getClass().getFields();
//		Long valor=(Long)campos2[0].get(cl);
		boolean acc=campos[0].isAccessible();
		campos[0].setAccessible(true);
		try{
			Object valor=campos2[0].get(cl);
		}catch(Exception e)
		{
			e.getMessage();
		}
		
		System.out.println(cla.getFields().toString());
//		Field f= cl.getClass().getField("nombre");
		Field[] fs= cla.getDeclaredFields();
		System.out.println("aassdasd");
		System.out.println(fs.toString());
		Field atributo = fs[0];
		String nombreAtributo = atributo.getName();
		System.out.println("Nombre del Atributo: " + nombreAtributo);
		Object tipoAtributo = atributo.getType();
		System.out.println("Tipo del Atributo: " + tipoAtributo);
		
		try{
//			Object valor = atributo.get(cl);
//			System.out.println(valor.toString());
//			Field field = cla.getField("dni");
//	        Integer val = (Integer) field.get(valor);
//	        System.out.println("Altura: " + val.toString());
		
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
//		Class persona = Cliente.class;
//		Field nombre = persona.getField(nombreAtributo);
		 
//		Cliente instanciaPersona = new Cliente();
		 
//		Object valor= (String) nombre.get(instanciaPersona);
//		nombre.set(instanciaPersona, valor);
	}

}

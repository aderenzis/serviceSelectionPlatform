package testooj3.persistence.xmi;

import org.w3c.dom.Node;

public class XMIUtils {
	
	
	public static String getType(Node nodo){
		return nodo.getAttributes().getNamedItem("xmi:type").getNodeValue();
	}
	
	public static String getName(Node nodo){
		return nodo.getAttributes().getNamedItem("name").getNodeValue();
	}
	
	public static String getId(Node nodo){
		return nodo.getAttributes().getNamedItem("xmi:id").getNodeValue();
	}
	
	public static String getIdRef(Node nodo){
		return nodo.getAttributes().getNamedItem("xmi:idref").getNodeValue();
	}
	
	public static String getUid(Node nodo){
		return nodo.getAttributes().getNamedItem("xmi:uuid").getNodeValue();
	}
	
}

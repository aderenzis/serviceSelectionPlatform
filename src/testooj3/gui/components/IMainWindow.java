package testooj3.gui.components;

import testooj3.domain.Interface;
import testooj3.domain.TField;
import testooj3.domain.TOperation;
import testooj3.domain.TParameter;
import testooj3.domain.states.State;

/**
 * @author Administrador
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IMainWindow {

    /**
     * @param c
     */
    void showMembers(Interface c);
    
    void log(String msg);

    /**
     * @param string
     */
    void logError(String string);

    /**
     * @param c
     */
    void setClass(Interface c);


    /**
     * @param p
     */
    void parameterSelected(TParameter p);

    /**
     * @param operation
     */
    void methodSelected(TOperation operation);

    /**
     * @param selectedState
     */
    void stateSelected(State selectedState);
    
    void fieldSelected(TField selectedField);

}

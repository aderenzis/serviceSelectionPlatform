package testooj3.domain;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author Administrador
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class TestMethodLine implements Serializable {
    protected boolean mIndented;

    public abstract String toString();
    
    public void indent(boolean action) {
        mIndented=action;
    }
}

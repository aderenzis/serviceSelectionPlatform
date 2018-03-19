package testooj3.domain.testmethodlines;

import testooj3.domain.TestMethodLine;

public class APeloLine extends TestMethodLine {

    private String mTexto;

    public APeloLine(String texto) {
        this.mTexto=texto;
    }

    public String toString() {
        String result="\t\t" + mTexto + "\n";
        if (mIndented) result="\t" + result;
        return result;
    }

}

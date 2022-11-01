package dominio;

public class NodoABBCentros {
    private CentroUrbano cu;
    private NodoABBCentros izq;
    private NodoABBCentros der;
    public  NodoABBCentros(CentroUrbano cu) {
        this.cu = cu;
        this.izq = null;
        this.der = null;
    }
    public  NodoABBCentros (CentroUrbano cu, NodoABBCentros izq, NodoABBCentros der) {
        this.cu = cu;
        this.izq = izq;
        this.der = der;
    }
    public CentroUrbano getCentro() {
        return cu;
    }

    public NodoABBCentros getIzq() {
        return izq;
    }

    public void setIzq(NodoABBCentros izq) {
        this.izq = izq;
    }

    public NodoABBCentros getDer() {
        return der;
    }

    public void setDer(NodoABBCentros der) {
        this.der = der;
    }

}

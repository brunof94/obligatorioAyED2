package dominio.Estructuras;

import dominio.Clases.CentroUrbano;

public class NodoABBCentros {
    private CentroUrbano cu;
    private NodoABBCentros izq;
    private NodoABBCentros der;

    public NodoABBCentros(CentroUrbano cu) {
        this.cu = cu;
        this.izq = null;
        this.der = null;
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

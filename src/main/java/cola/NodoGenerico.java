package cola;

public class NodoGenerico<T> {
    private T dato;

    private NodoGenerico<T> sig;

    public NodoGenerico(T dato, NodoGenerico<T> sig) {
        this.dato = dato;
        this.sig = sig;
    }
    public NodoGenerico(T dato) {
        this.dato = dato;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoGenerico<T> getSig() {
        return sig;
    }

    public void setSig(NodoGenerico<T> sig) {
        this.sig = sig;
    }
}

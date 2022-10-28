package dominio;

public class NodoABB {
    private Jugador jugador;

    public Jugador getJugador() {
        return jugador;
    }

    private NodoABB izq;
    private NodoABB der;

    public NodoABB getIzq() {
        return izq;
    }

    public void setIzq(NodoABB izq) {
        this.izq = izq;
    }

    public NodoABB getDer() {
        return der;
    }

    public void setDer(NodoABB der) {
        this.der = der;
    }


    public NodoABB(Jugador jugador) {
        this.jugador = jugador;
        this.izq = null;
        this.der = null;
    }

    public NodoABB(Jugador jugador, NodoABB izq, NodoABB der) {
        this.jugador = jugador;
        this.izq = izq;
        this.der = der;
    }


}



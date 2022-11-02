package dominio.Estructuras;

import dominio.Clases.Jugador;

public class NodoABBJugador {
    private Jugador jugador;

    public NodoABBJugador(Jugador jugador) {
        this.jugador = jugador;
        this.izq = null;
        this.der = null;
    }

    public Jugador getJugador() {
        return jugador;
    }

    private NodoABBJugador izq;
    private NodoABBJugador der;

    public NodoABBJugador getIzq() {
        return izq;
    }

    public void setIzq(NodoABBJugador izq) {
        this.izq = izq;
    }

    public NodoABBJugador getDer() {
        return der;
    }

    public void setDer(NodoABBJugador der) {
        this.der = der;
    }
}



package dominio;

import interfaz.TipoJugador;

public class ABB {

    private NodoABB raiz;

    public ABB() {
        this.raiz = null;
    }

    public static Jugador buscarJugadorCedula(String ci) {
        return null;
    }

    public static void registrarJugador(String ci, String nombre, int edad, String escuela, TipoJugador tipo) {
    }

    public void insertar(Jugador jugador) {
        if (this.raiz == null) {
            this.raiz = new NodoABB(jugador);
        } else {
            insertarRec(this.raiz, jugador);
        }
    }

    private void insertarRec(NodoABB nodo, Jugador jugador) {
        //Falta crear la comparacion
        int resultadoComparar = 1; //Resultado de la comparacion con compareto
        if (resultadoComparar > 0) {
            if (nodo.getIzq() == null) {
                nodo.setIzq(new NodoABB(jugador));
            } else {
                insertarRec(nodo.getIzq(), jugador);
            }
        } else if (resultadoComparar < 0) {
            if (nodo.getDer() == null) {
                nodo.setDer(new NodoABB(jugador));
            } else {
                insertarRec(nodo.getDer(), jugador);
            }
        }
    }
    public void listarAscendente() {
        System.out.println(listarAscendente(raiz));
    }

    private String listarAscendente(NodoABB nodo) {
        return "";
    }

    public void listarDescendente() {
        System.out.println(listarDescendente(raiz));
    }
    private String listarDescendente(NodoABB nodo) {
        return "";
    }


}

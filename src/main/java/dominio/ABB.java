package dominio;

public class ABB {

    private NodoABB raiz;

    public ABB() {
        this.raiz = null;
    }

    // Pos: Inserta el dato pasado como parametro en el arbol manteniendolo ordenado.
    public void insertar(Jugador dato) {
        if (this.raiz == null) {
            this.raiz = new NodoABB(dato);
        } else {
            insertarRec(this.raiz, dato);
        }
    }

    private void insertarRec(NodoABB nodo, Jugador dato) {
        //Falta crear la comparacion
        int resultadoComparar = 1; //Resultado de la comparacion con compareto
        if (resultadoComparar > 0) {
            if (nodo.getIzq() == null) {
                nodo.setIzq(new NodoABB(dato));
            } else {
                insertarRec(nodo.getIzq(), dato);
            }
        } else if (resultadoComparar < 0) {
            if (nodo.getDer() == null) {
                nodo.setDer(new NodoABB(dato));
            } else {
                insertarRec(nodo.getDer(), dato);
            }
        }
    }

    /**
     * Ejercicio 5 - Parte B
     *
     * @return Retorna true sii el dato pasado como parámetro pertenece al ABB.
     */
    public boolean pertenece(int dato) {
        return pertenece(dato, raiz);
    }

    private boolean pertenece(int dato, NodoABB nodo) {
        return false;
    }

    public void listarAscendente() {
        System.out.println(listarAscendente(raiz));
    }

    private String listarAscendente(NodoABB nodo) {
        return "";
    }

    /**
     * Ejercicio 5 - Parte D
     * Lista en pantalla los elementos del ABB ordenados de mayor a menor.
     */
    public void listarDescendente() {
        System.out.println(listarDescendente(raiz));
    }

    private String listarDescendente(NodoABB nodo) {
        return "";
    }

    public int borrarMinimo() {
        return 0;
    }

    public int elementosMayoresA(int k) {
        return 0;
    }

    public int cantidadDeElementosEnRango(int desde, int hasta) {
        return 0;
    }


}

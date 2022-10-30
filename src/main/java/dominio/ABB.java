package dominio;

import cola.NodoGenerico;
import dominio.Lista.ListaSimpleEncadenada;
import interfaz.TipoJugador;

public class ABB {

    private NodoABB raiz;

    public ABB() {
        this.raiz = null;
    }

    public static Jugador buscarJugadorCedula(String ci) {
        return null;
    }

    public void registrarJugador(String ci, String nombre, int edad, String escuela, TipoJugador tipo) {
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
    private ListaSimpleEncadenada listaAscJugadores() {
        ListaSimpleEncadenada lista = new ListaSimpleEncadenada();
        listaAscJugadores(raiz,lista);
        return lista;
    }

    private void listaAscJugadores(NodoABB nodo, ListaSimpleEncadenada lista) {
        if(nodo !=null){
            listaAscJugadores(nodo.getIzq(),lista);
            lista.agregarAlPrincipio(nodo.getJugador());
            listaAscJugadores(nodo.getDer(),lista);
        }

    }
    public String listarJugadoresAscendente(){
        String str= "";
        ListaSimpleEncadenada lista = listaAscJugadores();
        NodoGenerico aux = lista.getPrimero();
        while (aux != null) {
            str+= aux.getDato().toString();
            aux = aux.getSig();
        }
        return str;
    }
    public String listarJugadoresDescendente(){
        String str= "";
        ListaSimpleEncadenada lista = listaAscJugadores();
        NodoGenerico aux = lista.getPrimero();
        while (aux != null) {
            str+= aux.getDato().toString();
            aux = aux.getSig();
        }
        return str;
    }

    public ListaSimpleEncadenada listarDescendente() {
        ListaSimpleEncadenada lista = new ListaSimpleEncadenada();
        listaAscJugadores(raiz,lista);
        return lista;
    }
    private void listarDescendente(NodoABB nodo, ListaSimpleEncadenada lista) {
        if(nodo !=null){
            listaAscJugadores(nodo.getIzq(),lista);
            lista.agregarAlPrincipio(nodo.getJugador());
            listaAscJugadores(nodo.getDer(),lista);
        }
    }
    
}

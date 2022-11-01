package dominio;

import cola.NodoGenerico;
import dominio.Lista.ListaSimpleEncadenada;
import interfaz.Consulta;
import interfaz.TipoJugador;

public class ABB {

    private NodoABB raiz;

    public ABB() {
        this.raiz = null;
    }



    public JugadorConNivel buscarJugadorCedula(String ci) {
        return buscarJugadorCedula(ci, raiz, 0);
    }

    private JugadorConNivel buscarJugadorCedula(String ci, NodoABB nodo, int i) {
        if(nodo == null) return null;
        Jugador j = new Jugador(ci,"",0,"",TipoJugador.INICIAL);
        if(nodo.getJugador().compareTo(j) == 0){
            return new JugadorConNivel(nodo.getJugador(), i);
        }
        if(nodo.getJugador().compareTo(j) < 0){
            return buscarJugadorCedula(ci,nodo.getDer(),++i);
        }
        return buscarJugadorCedula(ci,nodo.getIzq(),++i);
    }

    public void registrarJugador(String ci, String nombre, int edad, String escuela, TipoJugador tipo) {
    }

    public boolean insertar(Jugador jugador) {
        if (this.raiz == null) {
            this.raiz = new NodoABB(jugador);
            return true;
        } else {
            return insertarRec(this.raiz, jugador);
        }
    }

    private boolean insertarRec(NodoABB nodo, Jugador jugador) {
        //Falta crear la comparacion
        int resultadoComparar = jugador.compareTo(nodo.getJugador()); //Resultado de la comparacion con compareto
        if (resultadoComparar < 0) {
            if (nodo.getIzq() == null) {
                nodo.setIzq(new NodoABB(jugador));
                return true;
            } else {
                return insertarRec(nodo.getIzq(), jugador);
            }
        } else if (resultadoComparar > 0) {
            if (nodo.getDer() == null) {
                nodo.setDer(new NodoABB(jugador));
                return true;
            } else {
                return insertarRec(nodo.getDer(), jugador);
            }
        }else {
            return false;
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
        if(aux != null){
            str = aux.getDato().toString();
            aux = aux.getSig();
        }
        while (aux != null) {
            str = aux.getDato().toString() +  "|" + str;
            aux = aux.getSig();
        }
        return str;
    }
    public String listarJugadoresDescendente(){
        String str= "";
        ListaSimpleEncadenada lista = listaAscJugadores();
        NodoGenerico aux = lista.getPrimero();
        if(aux != null){
            str = aux.getDato().toString();
            aux = aux.getSig();
        }
        while (aux != null) {
            str+= "|" + aux.getDato().toString();
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

    public  String consultaDeJugadores(Consulta consulta) {
        Consulta.NodoConsulta nc = consulta.getRaiz();
        return "";
    }
}

package dominio.Estructuras;

import dominio.Estructuras.Auxiliares.NodoGenerico;
import dominio.Clases.Jugador;
import dominio.Clases.Auxiliares.JugadorConNivel;
import dominio.Estructuras.Auxiliares.ListaSimple;
import interfaz.Consulta;
import interfaz.TipoJugador;

public class ABBJugadores {

    private NodoABBJugador raiz;

    public ABBJugadores() {
        this.raiz = null;
    }


    public JugadorConNivel buscarJugadorCedula(String ci) {
        return buscarJugadorCedula(ci, raiz, 0);
    }

    private JugadorConNivel buscarJugadorCedula(String ci, NodoABBJugador nodo, int i) {
        if (nodo == null) return null;
        Jugador j = new Jugador(ci, "", 0, "", TipoJugador.INICIAL);
        if (nodo.getJugador().compareTo(j) == 0) {
            return new JugadorConNivel(nodo.getJugador(), i);
        }
        if (nodo.getJugador().compareTo(j) < 0) {
            return buscarJugadorCedula(ci, nodo.getDer(), ++i);
        }
        return buscarJugadorCedula(ci, nodo.getIzq(), ++i);
    }

    public void registrarJugador(String ci, String nombre, int edad, String escuela, TipoJugador tipo) {
    }

    public boolean insertar(Jugador jugador) {
        if (this.raiz == null) {
            this.raiz = new NodoABBJugador(jugador);
            return true;
        } else {
            return insertarRec(this.raiz, jugador);
        }
    }

    private boolean insertarRec(NodoABBJugador nodo, Jugador jugador) {
        //Falta crear la comparacion
        int resultadoComparar = jugador.compareTo(nodo.getJugador()); //Resultado de la comparacion con compareto
        if (resultadoComparar < 0) {
            if (nodo.getIzq() == null) {
                nodo.setIzq(new NodoABBJugador(jugador));
                return true;
            } else {
                return insertarRec(nodo.getIzq(), jugador);
            }
        } else if (resultadoComparar > 0) {
            if (nodo.getDer() == null) {
                nodo.setDer(new NodoABBJugador(jugador));
                return true;
            } else {
                return insertarRec(nodo.getDer(), jugador);
            }
        } else {
            return false;
        }
    }

    public String listarJugadoresAscendente() {
        String str = "";
        ListaSimple lista = listaAscJugadores();
        NodoGenerico aux = lista.getPrimero();
        while (aux != null) {
            str = "|" + aux.getDato().toString() + str;
            aux = aux.getSig();
        }
        if (!str.equals(""))
            return str.substring(1);
        return str;

    }

    private ListaSimple listaAscJugadores() {
        ListaSimple lista = new ListaSimple();
        listaAscJugadores(raiz, lista);
        return lista;
    }

    private void listaAscJugadores(NodoABBJugador nodo, ListaSimple lista) {
        if (nodo != null) {
            listaAscJugadores(nodo.getIzq(), lista);
            lista.agregarAlPrincipio(nodo.getJugador());
            listaAscJugadores(nodo.getDer(), lista);
        }

    }

    public String listarJugadoresDescendente() {
        String str = "";
        ListaSimple lista = listaAscJugadores();
        NodoGenerico aux = lista.getPrimero();
        while (aux != null) {
            str = str + "|" + aux.getDato().toString();
            aux = aux.getSig();
        }
        if (!str.equals(""))
            return str.substring(1);
        return str;
    }

    public String consultaDeJugadores(Consulta consulta) {
        Consulta.NodoConsulta nc = consulta.getRaiz();
        NodoABBJugador nodoJugador = raiz;
        String resultado = consultaDeJugadores(nc, nodoJugador, "");
        if (resultado.length() > 0) return resultado.substring(1);
        return "";
    }

    private String consultaDeJugadores(Consulta.NodoConsulta nc, NodoABBJugador nodoJugador, String s) {
        if (nodoJugador != null) {
            String consulta = consultaDeJugadores(nc, nodoJugador.getIzq(), s);
            if (filtro(nc, nodoJugador)) {
                consulta += "|" + nodoJugador.getJugador().getCedula();
            }
            consulta += consultaDeJugadores(nc, nodoJugador.getDer(), s);

            return consulta;
        }
        return "";
    }

    private boolean filtro(Consulta.NodoConsulta nc, NodoABBJugador nodoJugador) {

        switch (nc.getTipoNodoConsulta()) {
            case And:
                return filtro(nc.getIzq(), nodoJugador) && filtro(nc.getDer(), nodoJugador);
            case Or:
                return filtro(nc.getIzq(), nodoJugador) || filtro(nc.getDer(), nodoJugador);
            case EdadMayor:
                return nc.getValorInt() < nodoJugador.getJugador().getEdad();
            case NombreIgual:
                return nc.getValorString().equals(nodoJugador.getJugador().getNombre());
            case EscuelaIgual:
                return nc.getValorString().equals(nodoJugador.getJugador().getEscuela());
            default:
                return false;
        }
    }
}



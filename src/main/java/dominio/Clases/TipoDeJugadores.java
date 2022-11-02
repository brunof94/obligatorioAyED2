package dominio.Clases;

import dominio.Estructuras.Auxiliares.NodoGenerico;
import dominio.Estructuras.Auxiliares.ListaSimple;

public class TipoDeJugadores {
    private ListaSimple[] tipoDeJugadores;

    public TipoDeJugadores() {
        tipoDeJugadores = new ListaSimple[4];
        for (int i = 0; i < 4; i++) {
            tipoDeJugadores[i] = new ListaSimple();
        }
    }

    public void agregar(int indice, Jugador j) {
        tipoDeJugadores[indice].agregarAlPrincipio(j);
    }

    public String listaDeJugadoresPorTipo(int indice) {
        String str = "";
        ListaSimple lista = tipoDeJugadores[indice];
        NodoGenerico aux = lista.getPrimero();
        if (aux != null) {
            str += aux.getDato().toString();
            aux = aux.getSig();
        }
        while (aux != null) {
            str += "|" + aux.getDato().toString();
            aux = aux.getSig();
        }
        return str;
    }
}

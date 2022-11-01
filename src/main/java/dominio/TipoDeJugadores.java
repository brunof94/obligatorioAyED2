package dominio;

import cola.NodoGenerico;
import dominio.Lista.ListaSimpleEncadenada;

public class TipoDeJugadores {
    private ListaSimpleEncadenada[] tipoDeJugadores;

    public TipoDeJugadores() {
        tipoDeJugadores = new ListaSimpleEncadenada[4];
        for (int i = 0; i < 4; i++) {
            tipoDeJugadores[i] = new ListaSimpleEncadenada();
        }
    }
    public void agregar(int indice, Jugador j){
        tipoDeJugadores[indice].agregarAlPrincipio(j);
    }
    public String listaDeJugadoresPorTipo(int indice){
        String str= "";
        ListaSimpleEncadenada lista = tipoDeJugadores[indice];
        NodoGenerico aux = lista.getPrimero();
        if(aux != null){
            str+= aux.getDato().toString();
            aux = aux.getSig();
        }
        while (aux != null) {
            str+= "|" + aux.getDato().toString();
            aux = aux.getSig();
        }
        return str;
    }
}

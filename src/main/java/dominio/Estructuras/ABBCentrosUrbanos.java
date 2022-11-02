package dominio.Estructuras;

import dominio.Estructuras.Auxiliares.NodoGenerico;
import dominio.Clases.CentroUrbano;
import dominio.Estructuras.Auxiliares.ListaSimple;

public class ABBCentrosUrbanos {
    private NodoABBCentros raiz;
    //Bruno Costa n207609 // Bruno Fulco n194569 https://github.com/brunof94/obligatorioAyED2
    public ABBCentrosUrbanos() {
        this.raiz = null;
    }

    public boolean insertar(CentroUrbano cu) {
        if (this.raiz == null) {
            this.raiz = new NodoABBCentros(cu);
            return true;
        } else {
            return insertarRec(this.raiz, cu);
        }
    }

    private boolean insertarRec(NodoABBCentros nodo, CentroUrbano centroUrbano) {
        int resultadoComparar = centroUrbano.compareTo(nodo.getCentro()); //Resultado de la comparacion con compareto
        if (resultadoComparar > 0) {
            if (nodo.getIzq() == null) {
                nodo.setIzq(new NodoABBCentros(centroUrbano));
                return true;
            } else {
                return insertarRec(nodo.getIzq(), centroUrbano);
            }
        } else if (resultadoComparar < 0) {
            if (nodo.getDer() == null) {
                nodo.setDer(new NodoABBCentros(centroUrbano));
                return true;
            } else {
                return insertarRec(nodo.getDer(), centroUrbano);
            }
        } else {
            return false;
        }
    }

    private ListaSimple listaAsccentroUrbano() {
        ListaSimple lista = new ListaSimple();
        listaAsccentroUrbano(raiz, lista);
        return lista;
    }

    private void listaAsccentroUrbano(NodoABBCentros nodo, ListaSimple lista) {
        if (nodo != null) {
            listaAsccentroUrbano(nodo.getIzq(), lista);
            lista.agregarAlPrincipio(nodo.getCentro());
            listaAsccentroUrbano(nodo.getDer(), lista);
        }

    }

    public String listarcentroUrbanoAscendente() {
        String str = "";
        ListaSimple lista = listaAsccentroUrbano();
        NodoGenerico aux = lista.getPrimero();
        if (aux != null) {
            str = aux.getDato().toString();
            aux = aux.getSig();
        }
        while (aux != null) {
            str += "|" + aux.getDato().toString();
            aux = aux.getSig();
        }
        return str;
    }
}

package dominio;

import cola.NodoGenerico;
import dominio.Lista.ListaSimpleEncadenada;

public class ABBCentrosUrbanos {
    private NodoABBCentros raiz;

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
        }else {
            return false;
        }
    }
    private ListaSimpleEncadenada listaAsccentroUrbano() {
        ListaSimpleEncadenada lista = new ListaSimpleEncadenada();
        listaAsccentroUrbano(raiz,lista);
        return lista;
    }

    private void listaAsccentroUrbano(NodoABBCentros nodo, ListaSimpleEncadenada lista) {
        if(nodo !=null){
            listaAsccentroUrbano(nodo.getIzq(),lista);
            lista.agregarAlPrincipio(nodo.getCentro());
            listaAsccentroUrbano(nodo.getDer(),lista);
        }

    }
    public String listarcentroUrbanoAscendente(){
        String str= "";
        ListaSimpleEncadenada lista = listaAsccentroUrbano();
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
}

package dominio.Estructuras.Auxiliares;

public class ListaSimple<T> {

    private NodoGenerico<T> primero;
    private NodoGenerico<T> ultimo;
    private int cantNodos;


    public void agregarAlPrincipio(T valorNuevo) {
        NodoGenerico nuevo = new NodoGenerico<T>(valorNuevo);
        if (estaVacia()) {
            this.primero = nuevo;
            this.ultimo = nuevo;
        } else {
            nuevo.setSig(this.primero);
            this.primero = nuevo;
        }
        this.cantNodos++;
    }

    public void agregarAlFinal(T elementoNuevo) {
        NodoGenerico nuevo = new NodoGenerico<T>(elementoNuevo);
        if (estaVacia()) {
            this.primero = nuevo;
            this.ultimo = nuevo;
        } else {
            this.ultimo.setSig(nuevo);
            this.ultimo = nuevo;
        }
        this.cantNodos++;
    }


    public T eliminarPrincipio() {
        if (estaVacia()) {
            return null;
        }
        T devuelve = this.primero.getDato();
        this.primero = this.primero.getSig();
        this.cantNodos--;
        return devuelve;
    }

    public NodoGenerico<T> getPrimero() {
        return primero;
    }

    public int getLargo() {
        return this.cantNodos;
    }


    public boolean estaVacia() {
        return this.primero == null;
    }

}

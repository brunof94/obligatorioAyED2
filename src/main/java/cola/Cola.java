package cola;

public class Cola<T> {
   private NodoGenerico<T> inicio;
   private NodoGenerico<T> fin;
   private int largo;
    public Cola() {

    }

    public boolean esVacia() {
        return this.largo ==0;
    }


    public void encolar(T dato) {
        if(this.inicio==null){
            inicio = new NodoGenerico<T>(dato);
            fin = inicio;
        }else{
            fin.setSig(new NodoGenerico<T>(dato));
            fin = fin.getSig();
        }
        this.largo++;
    }


    public T desencolar() {
        T dato = this.inicio.getDato();
        inicio = inicio.getSig();
        this.largo--;
        if(this.inicio == null){
            fin = null;
        }
        return dato;
    }



}

package cola;

public class Cola<T> {
   private NodoCola<T> inicio;
   private NodoCola<T> fin;
   private int largo;


    public Cola() {

    }

    public boolean esVacia() {
        return this.largo ==0;
    }


    public void Encolar(T dato) {
        if(this.inicio==null){
            inicio = new NodoCola<T>(dato);
            fin = inicio;
        }else{
            fin.setSig(new NodoCola<T>(dato));
            fin = fin.getSig();
        }
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

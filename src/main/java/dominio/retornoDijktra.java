package dominio;

public class retornoDijktra {
    int costo;
    String camino;

    public retornoDijktra(int costo, String camino) {
        this.costo = costo;
        this.camino = camino;
    }

    public int getCosto() {
        return costo;
    }

    public String getCamino() {
        return camino;
    }
}

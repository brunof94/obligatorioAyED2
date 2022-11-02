package dominio.Clases.Auxiliares;

public class RetornoDijktra {
    int costo;
    String camino;

    public RetornoDijktra(int costo, String camino) {
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

package dominio;

public class Grafo {
    private int tope;
    private int cantidad;
    private CentroUrbano[] centros;
    private Camino[][] matAdy;

    public Grafo(int unTope){
        this.tope = unTope;
        this.centros = new CentroUrbano[unTope];
        this.matAdy = new Camino[unTope][unTope];
        for (int i = 0; i <this.tope; i++) {
            for (int j = 0; j < this.tope; j++) {
                this.matAdy[i][j] = null;
            }
        }
    }

    public boolean esLleno(){
        return this.cantidad == this.tope;
    }
    public boolean esVacio(){
        return this.cantidad == 0;
    }

    //PRE : !esLleno()
    private int obtenerPosLibre(){
        for (int i = 0; i < this.tope; i++) {
            if (this.centros[i]==null){
                return i;
            }
        }
        return -1;
    }
    private int obtenerPos(CentroUrbano cu){
        for (int i = 0; i < this.tope; i++) {
            if(this.centros[i].equals(cu)){
                return i;
            }
        }
        return -1;
    }
    public void agregarVertice(CentroUrbano cu){
        int pos = obtenerPos(cu);
        this.centros[pos] = cu;
        this.cantidad++;
    }
    public boolean existeCentro(CentroUrbano cu){
        return obtenerPos(cu) != -1;
    }
    public CentroUrbano buscarCentro(String codigo){
        for(CentroUrbano c:centros){
            if(c != null && codigo.equals(c.getCodigo())){
                return c;
            }
        }
        return null;
    }
    //existeCentro(origen) && existeCentro(destino) && !existeCamino
    public void agregarCamino(CentroUrbano origen, CentroUrbano destino, Camino camino){
        int posDestino = obtenerPos(destino);
        int posOrigen = obtenerPos(origen);
        matAdy[posDestino][posOrigen] = camino;
    }
    //existeCentro(origen) && existeCentro(destino)
    public boolean existeCamino(CentroUrbano origen, CentroUrbano destino){
        int posDestino = obtenerPos(destino);
        int posOrigen = obtenerPos(origen);
        return matAdy[posDestino][posOrigen] != null;
    }
    //existeCentro(origen) && existeCentro(destino) && !existeCamino
    public void borrarCamino(CentroUrbano origen, CentroUrbano destino){
        int posDestino = obtenerPos(destino);
        int posOrigen = obtenerPos(origen);
        matAdy[posDestino][posOrigen] = null;
    }
}

package dominio;

import cola.Cola;

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
    public void agregarCentro(CentroUrbano cu){
        int pos = obtenerPosLibre();
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
    public Camino buscarCamino(CentroUrbano origen, CentroUrbano destino){
        int posDestino = obtenerPos(destino);
        int posOrigen = obtenerPos(origen);
        return matAdy[posDestino][posOrigen];
    }
    //existeCentro(origen) && existeCentro(destino) && !existeCamino
    public void borrarCamino(CentroUrbano origen, CentroUrbano destino){
        int posDestino = obtenerPos(destino);
        int posOrigen = obtenerPos(origen);
        matAdy[posDestino][posOrigen] = null;
    }
    public void borrarCentro(CentroUrbano cu) {
        int pos = obtenerPos(cu);
        this.centros[pos] = null;
        for (int i = 0; i < this.tope; i++) {
            this.matAdy[pos][i] = null;
            this.matAdy[i][pos] = null;
        }
        this.cantidad--;
    }

    public void dfs(CentroUrbano cu) {
        boolean[] visitados = new boolean[this.tope];
        int pos = obtenerPos(cu);
        dfsRec(pos, visitados);
    }
    private void dfsRec(int pos, boolean[] visitados){
        //FALTA IMPLEMETNAR
    }
    public void bfs(CentroUrbano cu){
        int inicio = obtenerPos(cu);
        boolean[] visitados = new boolean[this.tope];
        Cola<Integer> cola = new Cola<>();
        cola.encolar(inicio);
        visitados[inicio]=true;
        while(!cola.esVacia()){
            int pos = cola.desencolar();
            System.out.println(centros[pos]);
            for (int i = 0; i < this.tope; i++) {
                if(this.matAdy[pos][i]!= null && !visitados[i]){
                    cola.encolar(i);
                    visitados[i] = true;
                }
            }
        }
    }

    public double dijkstra(CentroUrbano origen, CentroUrbano destino){
        int posDestino = obtenerPos(destino);
        int posOrigen = obtenerPos(origen);
        boolean[] visitados = new boolean[this.tope];
        double[] costos = new double[this.tope];
        CentroUrbano[] anterior = new CentroUrbano[this.tope];
        for (int i = 0; i < this.tope; i++) {
            costos[i] = Integer.MAX_VALUE;
            anterior[i] = null;
        }
        costos[posOrigen]=0;
        for (int i = 0; i < this.tope; i++) {
            int pos = obtenerSigNoVisitadoMenorDistancia(costos,visitados);
            if(pos != -1){
                visitados[pos]=true;
                for (int j = 0; j < this.tope; j++) {
                    if(matAdy[pos][j]!=null &&!visitados[j]){
                        double distanciaNueva = costos[pos] + matAdy[pos][j].getCosto();
                        if(distanciaNueva < costos[j]){
                            costos[j] = distanciaNueva;
                            anterior[j] = centros[pos];
                        }
                    }

                }
            }
        }
        return costos[posDestino];
    }

    private int obtenerSigNoVisitadoMenorDistancia(double[] costos, boolean[] visitados) {
        int posMin =-1;
        double min = Integer.MAX_VALUE;
        for (int i = 0; i < this.tope; i++) {
            if(!visitados[i] && costos[i]<min){
                min = costos[i];
                posMin = i;
            }
        }
        return posMin;
    }
}

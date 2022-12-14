package dominio.Estructuras;

import dominio.Estructuras.Auxiliares.Cola;
import dominio.Clases.CentroUrbano;
import dominio.Clases.Auxiliares.RetornoDijktra;
import interfaz.EstadoCamino;
//Bruno Costa n207609 // Bruno Fulco n194569 https://github.com/brunof94/obligatorioAyED2
public class GrafoCentrosUrbanos {
    private int tope;
    private int cantidad;
    private CentroUrbano[] centros;
    private Camino[][] matAdy;

    public GrafoCentrosUrbanos(int unTope) {
        this.tope = unTope;
        this.centros = new CentroUrbano[unTope];
        this.matAdy = new Camino[unTope][unTope];
        for (int i = 0; i < this.tope; i++) {
            for (int j = 0; j < this.tope; j++) {
                this.matAdy[i][j] = null;
            }
        }
    }

    public boolean esLleno() {
        return this.cantidad == this.tope;
    }

    public boolean esVacio() {
        return this.cantidad == 0;
    }

    //PRE : !esLleno()
    private int obtenerPosLibre() {
        for (int i = 0; i < this.tope; i++) {
            if (this.centros[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private int obtenerPos(CentroUrbano cu) {
        for (int i = 0; i < this.tope; i++) {
            if (this.centros[i].equals(cu)) {
                return i;
            }
        }
        return -1;
    }

    public void agregarCentro(CentroUrbano cu) {
        int pos = obtenerPosLibre();
        this.centros[pos] = cu;
        this.cantidad++;
    }

    public CentroUrbano buscarCentro(String codigo) {
        for (CentroUrbano c : centros) {
            if (c != null && codigo.equals(c.getCodigo())) {
                return c;
            }
        }
        return null;
    }

    //existeCentro(origen) && existeCentro(destino) && !existeCamino
    public void agregarCamino(CentroUrbano origen, CentroUrbano destino, Camino camino) {
        int posDestino = obtenerPos(destino);
        int posOrigen = obtenerPos(origen);
        matAdy[posOrigen][posDestino] = camino;
    }

    //existeCentro(origen) && existeCentro(destino)
    public Camino buscarCamino(CentroUrbano origen, CentroUrbano destino) {
        int posDestino = obtenerPos(destino);
        int posOrigen = obtenerPos(origen);
        return matAdy[posOrigen][posDestino];
    }

    //existeCentro(origen) && existeCentro(destino) && !existeCamino
    public void borrarCamino(CentroUrbano origen, CentroUrbano destino) {
        int posDestino = obtenerPos(destino);
        int posOrigen = obtenerPos(origen);
        matAdy[posOrigen][posDestino] = null;
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

    public String listadoDeCentrosPorSaltos(CentroUrbano cu, int saltos) {
        ABBCentrosUrbanos abb = bfs(cu, saltos);
        return abb.listarcentroUrbanoAscendente();
    }

    private ABBCentrosUrbanos bfs(CentroUrbano cu, int saltos) {
        ABBCentrosUrbanos abbAux = new ABBCentrosUrbanos();

        int[] nroSalto = new int[tope];
        int inicio = obtenerPos(cu);
        boolean[] visitados = new boolean[this.tope];

        Cola<Integer> cola = new Cola<>();
        cola.encolar(inicio);
        visitados[inicio] = true;

        while (!cola.esVacia()) {
            int pos = cola.desencolar();
            if (nroSalto[pos] <= saltos) {
                abbAux.insertar(centros[pos]);
            }

            for (int i = 0; i < this.tope; i++) {
                if (this.matAdy[pos][i] != null && !visitados[i]) {
                    cola.encolar(i);
                    nroSalto[i] = nroSalto[pos] + 1;
                    visitados[i] = true;
                }
            }
        }
        return abbAux;
    }

    public RetornoDijktra dijktraPorKilometro(CentroUrbano origen, CentroUrbano destino) {
        return dijkstra(origen, destino, "Kilometro");
    }

    public RetornoDijktra dijktraPorCostos(CentroUrbano origen, CentroUrbano destino) {
        return dijkstra(origen, destino, "Costo");
    }

    private RetornoDijktra dijkstra(CentroUrbano origen, CentroUrbano destino, String condicion) {
        int posDestino = obtenerPos(destino);
        int posOrigen = obtenerPos(origen);
        boolean[] visitados = new boolean[this.tope];
        double[] costos = new double[this.tope];
        CentroUrbano[] anterior = new CentroUrbano[this.tope];
        for (int i = 0; i < this.tope; i++) {
            costos[i] = Integer.MAX_VALUE;
            anterior[i] = null;
        }
        costos[posOrigen] = 0;
        for (int i = 0; i < this.tope; i++) {
            int pos = obtenerSigNoVisitadoMenorDistancia(costos, visitados);
            if (pos != -1) {
                visitados[pos] = true;
                for (int j = 0; j < this.tope; j++) {
                    Camino camino = matAdy[pos][j];
                    if (camino != null && !visitados[j] && camino.getEstado() != EstadoCamino.MALO) {
                        double distanciaNueva;
                        switch (condicion) {
                            case "Kilometro":
                                distanciaNueva = costos[pos] + matAdy[pos][j].getKilometros();
                                break;
                            case "Costo":
                                distanciaNueva = distanciaNueva = costos[pos] + matAdy[pos][j].getCosto();
                                break;
                            default:
                                distanciaNueva = 0;
                        }
                        if (distanciaNueva < costos[j]) {
                            costos[j] = distanciaNueva;
                            anterior[j] = centros[pos];
                        }
                    }

                }
            }
        }
        CentroUrbano centroActual = destino;
        String ret = "";
        while (centroActual != null) {
            int posicionCentro = obtenerPos(centroActual);
            ret = "|" + centroActual.toString() + ret;
            centroActual = anterior[posicionCentro];
        }

        return new RetornoDijktra((int) costos[posDestino], ret.substring(1));
    }

    private int obtenerSigNoVisitadoMenorDistancia(double[] costos, boolean[] visitados) {
        int posMin = -1;
        double min = Integer.MAX_VALUE;
        for (int i = 0; i < this.tope; i++) {
            if (!visitados[i] && costos[i] < min) {
                min = costos[i];
                posMin = i;
            }
        }
        return posMin;
    }
}


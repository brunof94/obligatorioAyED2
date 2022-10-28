package dominio;

import interfaz.EstadoCamino;

public class Camino {
    private CentroUrbano centroOrigen;
    private CentroUrbano centroDestino;
    private double costo;
    private double tiempo;
    private double kilometros;
    private EstadoCamino estado;

    public Camino(CentroUrbano centroOrigen, CentroUrbano centroDestino, double costo, double tiempo, double kilometros, EstadoCamino estado) {
        this.centroOrigen = centroOrigen;
        this.centroDestino = centroDestino;
        this.costo = costo;
        this.tiempo = tiempo;
        this.kilometros = kilometros;
        this.estado = estado;
    }

    public CentroUrbano getCentroOrigen() {
        return centroOrigen;
    }

    public CentroUrbano getCentroDestino() {
        return centroDestino;
    }

    public double getCosto() {
        return costo;
    }

    public double getTiempo() {
        return tiempo;
    }

    public double getKilometros() {
        return kilometros;
    }

    public EstadoCamino getEstado() {
        return estado;
    }
}

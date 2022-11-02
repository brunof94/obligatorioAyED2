package dominio.Estructuras;

import dominio.Clases.CentroUrbano;
import interfaz.EstadoCamino;
//Bruno Costa n207609 // Bruno Fulco n194569  https://github.com/brunof94/obligatorioAyED2
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

package sistema;

import dominio.Camino;
import dominio.CentroUrbano;
import dominio.Grafo;
import interfaz.Consulta;
import interfaz.EstadoCamino;
import interfaz.Retorno;
import interfaz.Sistema;
import interfaz.TipoJugador;

public class ImplementacionSistema implements Sistema {

    private Grafo grafo;
    @Override
    public Retorno inicializarSistema(int maxCentros) {
        if(maxCentros <= 5 ) return Retorno.error1("El sistema no puede tener menos de 5 centros");
        this.grafo = new Grafo(maxCentros);
        return Retorno.ok();
    }

    @Override
    public Retorno explorarCentroUrbano(boolean[] correctas, int[] puntajes, int minimo) {
        boolean minimoOk = minimo > 0;
        if(correctas == null || puntajes == null){
            return Retorno.error1("Los datos no son validos");
        }
        if(correctas.length < 3 || puntajes.length < 3){
            return Retorno.error2("Los arreglos tienen que contener mas de 3 elementos");
        }
        if(correctas.length != puntajes.length){
            return Retorno.error3("Los lenght de los arreglos son diferentes");
        }
        if(minimo <= 0){
            return Retorno.error4("El minimo debe ser mayor a 0");
        }
        int puntaje = 0;
        int consecutivas = 0;
        for(int i = 0; i < correctas.length; i++){
            if(correctas[i]){
                puntaje += puntajes[i];
                consecutivas++;
                if(consecutivas == 3){
                    puntaje += 3;
                }else if(consecutivas == 4){
                    puntaje += 5;
                }else if(consecutivas >4){
                    puntaje += 8;
                }
            }else{
                consecutivas = 0;
            }
        }

        if(puntaje >= minimo){
            return Retorno.ok(puntaje, "pasa");
        }
        return Retorno.ok(puntaje, "no pasa");
    }

    @Override
    public Retorno registrarJugador(String ci, String nombre,int edad, String escuela, TipoJugador tipo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno filtrarJugadores(Consulta consulta) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno buscarJugador(String ci) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarJugadoresPorCedulaAscendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarJugadoresPorCedulaDescendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarJugadoresPorTipo(TipoJugador unTipo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarCentroUrbano(String codigo, String nombre) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarCamino(String codigoCentroOrigen, String codigoCentroDestino, double costo, double tiempo, double kilometros, EstadoCamino estadoDelCamino) {
        if(costo <= 0 || tiempo <= 0 || kilometros <=0) return Retorno.error1("Los parametros double deben ser positivos");
        if(codigoCentroOrigen == null || codigoCentroDestino == null || estadoDelCamino == null || codigoCentroDestino.equals("") || codigoCentroOrigen.equals("")) return Retorno.error2("Los string no pueden ser null o vacio");
        CentroUrbano centroOrigen = grafo.buscarCentro(codigoCentroOrigen);
        if(centroOrigen == null) return Retorno.error3("No existe centro de origen");
        CentroUrbano centroDestino = grafo.buscarCentro(codigoCentroDestino);
        if(centroDestino == null) return Retorno.error4("No existe centro de destino");
        if(grafo.existeCamino(centroOrigen, centroDestino)) return Retorno.error5("Ya existe ese camino");
        Camino camino = new Camino(centroOrigen,centroDestino,costo,tiempo,kilometros,estadoDelCamino);
        grafo.agregarCamino(centroOrigen,centroDestino,camino);
        return Retorno.ok("ok");
    }

    @Override
    public Retorno actualizarCamino(String codigoCentroOrigen, String codigoCentroDestino, double costo, double tiempo, double kilometros, EstadoCamino estadoDelCamino) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listadoCentrosCantDeSaltos(String codigoCentroOrigen, int cantidad) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno viajeCostoMinimoKilometros(String codigoCentroOrigen, String codigoCentroDestino) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno viajeCostoMinimoMonedas(String codigoCentroOrigen, String codigoCentroDestino) {
        return Retorno.noImplementada();
    }
}

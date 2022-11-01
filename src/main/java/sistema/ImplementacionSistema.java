package sistema;

import cola.NodoGenerico;
import dominio.*;
import dominio.Lista.ListaSimpleEncadenada;
import interfaz.Consulta;
import interfaz.EstadoCamino;
import interfaz.Retorno;
import interfaz.Sistema;
import interfaz.TipoJugador;

import java.sql.Array;

public class ImplementacionSistema implements Sistema {

    private Grafo grafo;
    private ABB jugadores;
    private ListaSimpleEncadenada[] tipoDeJugadores;
    @Override
    public Retorno inicializarSistema(int maxCentros) {
        if(maxCentros <= 5 ) return Retorno.error1("El sistema no puede tener menos de 5 centros");
        this.grafo = new Grafo(maxCentros);
        this.jugadores = new ABB();
        tipoDeJugadores = new ListaSimpleEncadenada[4];
        for (int i = 0; i < 4; i++) {
            tipoDeJugadores[i] = new ListaSimpleEncadenada();
        }
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
        if(ci == null || nombre == null || escuela == null || ci == "" || nombre == "" || escuela == "" || tipo == null || edad < 1) return Retorno.error1("Alguno de los campos esta vacio");
        if(!Jugador.validarCedula(ci)) return Retorno.error2("El formato de la cedula es invalido");
        if(jugadores.buscarJugadorCedula(ci) != null) return Retorno.error3("Ya existe un usuario con esa cedula");
        Jugador j = new Jugador(ci, nombre, edad, escuela, tipo);
        if(jugadores.insertar(j)){
            TipoJugador tipoJugador = j.getTipoJugador();
            tipoDeJugadores[tipoJugador.getIndice()].agregarAlPrincipio(j);
            return Retorno.ok();
        }
        return Retorno.error3("Ya existe ese jugador");
    }

    @Override
    public Retorno filtrarJugadores(Consulta consulta) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno buscarJugador(String ci) {
        if(!Jugador.validarCedula(ci)) return Retorno.error1("La cedula no tiene un formato valido");
        Jugador j = jugadores.buscarJugadorCedula(ci);
        if(j == null) return Retorno.error2("Jugador no encontrado");
        return Retorno.ok(0,j.toString());
    }

    @Override
    public Retorno listarJugadoresPorCedulaAscendente() {

        return Retorno.ok(jugadores.listarJugadoresAscendente());
    }

    @Override
    public Retorno listarJugadoresPorCedulaDescendente() {
        return Retorno.ok(jugadores.listarJugadoresDescendente());
    }

    @Override
    public Retorno listarJugadoresPorTipo(TipoJugador unTipo) {
        if(unTipo == null)return Retorno.error1("Tipo de jugador null");
        String str= "";
        ListaSimpleEncadenada lista = tipoDeJugadores[unTipo.getIndice()];
        NodoGenerico aux = lista.getPrimero();
        if(aux != null){
            str+= aux.getDato().toString();
            aux = aux.getSig();
        }
        while (aux != null) {
            str+= "|" + aux.getDato().toString();
            aux = aux.getSig();
        }
        return Retorno.ok(str);
    }

    @Override
    public Retorno registrarCentroUrbano(String codigo, String nombre) {
        if(grafo.esLleno()) return Retorno.error1("Se llego al maximo de centros urbanos permitidos");
        if(codigo == null || nombre == null || codigo.equals("") || nombre.equals("")) return Retorno.error2("Algun dato esta vacio");
        if(grafo.buscarCentro(codigo) != null) return Retorno.error3("Ya existe un centro urbano con ese codigo");
        CentroUrbano centro = new CentroUrbano(codigo,nombre);
        grafo.agregarCentro(centro);
        return Retorno.ok();
    }

    @Override
    public Retorno registrarCamino(String codigoCentroOrigen, String codigoCentroDestino, double costo, double tiempo, double kilometros, EstadoCamino estadoDelCamino) {
        if(costo <= 0 || tiempo <= 0 || kilometros <=0) return Retorno.error1("Los parametros double deben ser positivos");
        if(codigoCentroOrigen == null || codigoCentroDestino == null || estadoDelCamino == null || codigoCentroDestino.equals("") || codigoCentroOrigen.equals("")) return Retorno.error2("Los string no pueden ser null o vacio");
        CentroUrbano centroOrigen = grafo.buscarCentro(codigoCentroOrigen);
        if(centroOrigen == null) return Retorno.error3("No existe centro de origen");
        CentroUrbano centroDestino = grafo.buscarCentro(codigoCentroDestino);
        if(centroDestino == null) return Retorno.error4("No existe centro de destino");
        if(grafo.buscarCamino(centroOrigen, centroDestino) != null) return Retorno.error5("Ya existe ese camino");
        Camino camino = new Camino(centroOrigen,centroDestino,costo,tiempo,kilometros,estadoDelCamino);
        grafo.agregarCamino(centroOrigen,centroDestino,camino);
        return Retorno.ok("ok");
    }

    @Override
    public Retorno actualizarCamino(String codigoCentroOrigen, String codigoCentroDestino, double costo, double tiempo, double kilometros, EstadoCamino estadoDelCamino) {
        if(costo <= 0 || tiempo <= 0 || kilometros <=0) return Retorno.error1("Los parametros double deben ser positivos");
        if(codigoCentroOrigen == null || codigoCentroDestino == null || estadoDelCamino == null || codigoCentroDestino.equals("") || codigoCentroOrigen.equals("")) return Retorno.error2("Los string no pueden ser null o vacio");
        CentroUrbano centroOrigen = grafo.buscarCentro(codigoCentroOrigen);
        if(centroOrigen == null) return Retorno.error3("No existe centro de origen");
        CentroUrbano centroDestino = grafo.buscarCentro(codigoCentroDestino);
        if(centroDestino == null) return Retorno.error4("No existe centro de destino");
        if(grafo.buscarCamino(centroOrigen, centroDestino) == null) return Retorno.error5("No existe ese camino");
        Camino camino = new Camino(centroOrigen,centroDestino,costo,tiempo,kilometros,estadoDelCamino);
        grafo.agregarCamino(centroOrigen,centroDestino,camino);
        return Retorno.ok("ok");
    }

    @Override
    public Retorno listadoCentrosCantDeSaltos(String codigoCentroOrigen, int cantidad) {
        return Retorno.ok(grafo.bfs(grafo.buscarCentro(codigoCentroOrigen),cantidad));
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

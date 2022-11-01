package dominio;

public class JugadorConNivel {

    Jugador jugador;

    public JugadorConNivel(Jugador jugador, int elementosRecorridos) {
        this.jugador = jugador;
        this.elementosRecorridos = elementosRecorridos;
    }

    int elementosRecorridos;

    public Jugador getJugador() {
        return jugador;
    }

    public int getElementosRecorridos() {
        return elementosRecorridos;
    }

    public void setElementosRecorridos(int elementosRecorridos) {
        this.elementosRecorridos = elementosRecorridos;
    }


}

package dominio;

import interfaz.TipoJugador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Jugador {

    private int cedulaInt;
    private String nombre;
    private int edad;
    private String escuela;
    private TipoJugador tipoJugador;
    private String cedula;

    public String getCedula() {
        return cedula;
    }

    public int getCedulaInt() {
        return cedulaInt;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getEscuela() {
        return escuela;
    }

    public TipoJugador getTipoJugador() {
        return tipoJugador;
    }

    public Jugador(String cedula, String nombre, int edad, String escuela, TipoJugador tipoJugador) {
        if(validarCedula(cedula)){
            this.cedula = cedula;
            this.cedulaInt = convertirCIaInt(cedula);
        }
        this.nombre = nombre;
        this.edad = edad;
        this.escuela = escuela;
        this.tipoJugador = tipoJugador;
    }
    public static boolean validarCedula(String cedula){
        String regex = "^[1-9][\\.]\\d{3}[\\.]?\\d{3}[\\.\\-/_]?[1-9]"; // https://twitter.com/federicod/status/1368944677370687495?lang=en
        String regexCorto = "^d{3}[\\.]?\\d{3}[\\.\\-/_]?[1-9]";
        Pattern pattern1 = Pattern.compile(regex);
        Matcher matcher1 = pattern1.matcher(cedula);
        Pattern pattern2 = Pattern.compile(regexCorto);
        Matcher matcher2 = pattern2.matcher(cedula);
        return matcher1.matches() || matcher2.matches();
    }

    private static int convertirCIaInt(String cedula){
        String retornoString = cedula.replaceAll("[^0-9]", "");
        return Integer.parseInt(retornoString);
    }


}

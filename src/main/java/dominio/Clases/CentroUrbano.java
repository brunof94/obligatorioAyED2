package dominio.Clases;

public class CentroUrbano implements Comparable<CentroUrbano> {
    private String codigo;
    private String nombre;

    public CentroUrbano(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return codigo + ";" + nombre;
    }

    @Override
    public int compareTo(CentroUrbano cu) {
        if (this.codigo.compareTo(cu.getCodigo()) == 0) return 0;
        if (this.codigo.compareTo(cu.getCodigo()) > 0) return 1;
        return -1;
    }
}

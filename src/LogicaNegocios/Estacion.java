package LogicaNegocios;

public class Estacion implements Comparable<Estacion> {

    private String cod_id;
    private String nombre;
    private String zona;

    public Estacion() {
        this.cod_id = "";
        this.nombre = "";
        this.zona = "";
    }

    public void validarDatos(String cod, String n, String z) {
        validarCodigo(cod);
        validarNombre(n);
        validarZona(z);
    }

    @Override
    public int compareTo(Estacion otra) {
        return this.cod_id.compareTo(otra.cod_id);
    }

    public void validarCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El codigo no puede estar vacio.");
        }
        setCod_id(codigo);
    }

    public void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        setNombre(nombre);
    }

    public void validarZona(String zona) {
        if (zona == null || zona.trim().isEmpty()) {
            throw new IllegalArgumentException("La zona no puede estar vacia.");
        }
        setZona(zona);
    }

    public String getCod_id() {
        return cod_id;
    }

    private void setCod_id(String cod_id) {
        this.cod_id = cod_id;
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getZona() {
        return zona;
    }

    private void setZona(String zona) {
        this.zona = zona;
    }

    @Override
    public String toString() {
        return "\nCodigo: " + cod_id
                + "\nNombre: " + nombre
                + "\nZona: " + zona;
    }

}

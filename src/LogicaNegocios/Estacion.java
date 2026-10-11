package LogicaNegocios;


public class Estacion {
    private String cod_id; 
    private String nombre;
    private String zona;

    public Estacion() {
        this.cod_id = "";
        this.nombre = "";
        this.zona = "";
    }
    
    public void cargarDatos(){
        
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
}

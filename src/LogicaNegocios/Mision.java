package LogicaNegocios;

public class Mision {
    private String cod_mision;
    private String origen;
    private String destino;
    private double distancia;
    private double peso;
    private String prioridad;
    private int estado; // 1-Pendiente 2-Asignado 3-Finalizado

    public Mision() {
        this.cod_mision = "";
        this.origen = "";
        this.destino = "";
        this.distancia = 0.0;
        this.peso = 0.0;
        this.prioridad = "";
        this.estado = 0;
    }
    
    public void setDatos(String cod_mision, String origen, String destino,
             double distancia, double peso, String prioridad, int estado) {
        ingresarCod_mision(cod_mision);
        ingresarOrigen(origen);
        ingresarDestino(destino);
        ingresarDistancia(distancia);
        ingresarPeso(peso);
        ingresarPrioridad(prioridad);
        ingresarEstado(estado);
    }
    
    public void ingresarCod_mision(String cod_mision) {

        if (cod_mision == null || cod_mision.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El codigo de la mision no puede estar vacio.");
        }

        this.cod_mision = cod_mision;
    }

    public void ingresarOrigen(String origen) {

        if (origen == null || origen.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El origen no puede estar vacio.");
        }

        this.origen = origen;
    }

    public void ingresarDestino(String destino) {

        if (destino == null || destino.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El destino no puede estar vacio.");
        }

        this.destino = destino;
    }

    public void ingresarDistancia(double distancia) {

        if (distancia <= 0) {
            throw new IllegalArgumentException(
                    "La distancia debe ser mayor a 0.");
        }

        this.distancia = distancia;
    }

    public void ingresarPeso(double peso) {

        if (peso <= 0) {
            throw new IllegalArgumentException(
                    "El peso debe ser mayor a 0.");
        }

        this.peso = peso;
    }

    public void ingresarPrioridad(String prioridad) {

        if (prioridad == null || prioridad.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "La prioridad no puede estar vacia.");
        }

        this.prioridad = prioridad;
    }

    public void ingresarEstado(int estado) {

        if (estado < 1 || estado > 3) {
            throw new IllegalArgumentException(
                    "El estado debe ser 1, 2 o 3.");
        }

        this.estado = estado;
    }        

    public String getCod_mision() {
        return cod_mision;
    }

    public void setCod_mision(String cod_mision) {
        this.cod_mision = cod_mision;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}

package LogicaNegocios;

public class Mision {

    private String cod_mision;
    private String origen;
    private String destino;
    private int distancia;
    private double peso;
    private String prioridad; //1-Alta 2-Media 3-Baja 
    private int estado; // 1-Pendiente 2-Asignado 3-Finalizado

    public Mision() {
        this.cod_mision = "";
        this.origen = "";
        this.destino = "";
        this.distancia = 0;
        this.peso = 0.0;
        this.prioridad = "";
        this.estado = 0;
    }

    public void setDatos(String cod_mision, String origen, String destino,
            int distancia, double peso, String prioridad, int estado) {
        setCod_mision(cod_mision);
        setOrigen(origen);
        setDestino(destino);
        setDistancia(distancia);
        setPeso(peso);
        setPrioridad(prioridad);
        setEstado(estado);
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

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
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

    @Override
    public String toString() {
        return "Misión: " + cod_mision
                + " | Origen: " + origen
                + " | Destino: " + destino
                + " | Distancia: " + distancia
                + " km"
                + " | Peso: " + peso + " kg"
                + " | Prioridad: " + prioridad
                + " | Estado: " + estado;
    }
}

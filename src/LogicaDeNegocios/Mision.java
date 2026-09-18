/*
 * Antes esta clase declaraba "package Mision;" aunque el archivo
 * fisicamente estaba dentro de la carpeta Modelo. Se corrige para que
 * coincida con su ubicacion real dentro de la capa Modelo.
 */
package Modelo;

public class Mision {
    private String cod_mision;
    private String origen;
    private String destino;
    private String distancia;
    private String peso;
    private String prioridad;
    private int estado; // 1-Pendiente 2-Asignado 3-Finalizado

    public Mision() {
        this.cod_mision = "";
        this.origen = "";
        this.destino = "";
        this.distancia = "";
        this.peso = "";
        this.prioridad = "";
        this.estado = 0;
    }

    public void setDatos(String cod_mision, String origen, String destino,
            String distancia, String peso, String prioridad, int estado) {
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

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
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

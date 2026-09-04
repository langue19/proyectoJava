/*
 * Capa MODELO: solo datos y logica de negocio. Sin Scanner, sin
 * System.out y sin dependencias de Vista/Controlador.
 */
package Modelo;

public abstract class VehiculoAutonomo {

    protected String cod_ident;
    protected String modelo;
    protected int nivel_bat;
    protected int capacidad_maxima;
    protected double km_reco;
    protected String estado;

    public VehiculoAutonomo() {
        this.cod_ident = "";
        this.modelo = "";
        this.nivel_bat = 0;
        this.capacidad_maxima = 0;
        this.km_reco = 0.0;
        this.estado = "";
    }

    /**
     * Carga los datos comunes a todo vehiculo. El controlador
       llama a este metodo con datos ya recolectados por la Vista) es
     * responsable de haberlos validado.
     */
    public void setDatosBase(String cod_ident, String modelo, int nivel_bat,
            int capacidad_maxima, double km_reco, String estado) {
        
        setCod_ident(cod_ident);
        setModelo(modelo);
        setNivel_bat(nivel_bat);
        setCapacidad_maxima(capacidad_maxima);
        setKm_reco(km_reco);
        setEstado(estado);
    }

    public String getCod_ident() {
        return cod_ident;
    }

    private void setCod_ident(String cod_ident) {
        this.cod_ident = cod_ident;
    }

    public String getModelo() {
        return modelo;
    }

    private void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getNivel_bat() {
        return nivel_bat;
    }

    protected void setNivel_bat(int nivel_bat) {
        this.nivel_bat = nivel_bat;
    }

    public int getCapacidad_maxima() {
        return capacidad_maxima;
    }

    private void setCapacidad_maxima(int capacidad_maxima) {
        this.capacidad_maxima = capacidad_maxima;
    }

    public double getKm_reco() {
        return km_reco;
    }

    private void setKm_reco(double km_reco) {
        this.km_reco = km_reco;
    }

    public String getEstado() {
        return estado;
    }

    private void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}

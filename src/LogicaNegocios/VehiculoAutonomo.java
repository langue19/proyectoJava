package LogicaNegocios;

public abstract class VehiculoAutonomo implements Comparable<VehiculoAutonomo> {

    protected String cod_ident;
    protected String modelo;
    protected int nivel_bat;
    protected int capacidad_maxima;
    protected double km_reco;
    protected int estado;

    public VehiculoAutonomo() {
        this.cod_ident = "";
        this.modelo = "";
        this.nivel_bat = 0;
        this.capacidad_maxima = 0;
        this.km_reco = 0.0;
        this.estado = 0;
    }

    protected abstract double bateriaNecesaria(int distancia);
//porque la formula depende de datos que la clase base no tiene, (necesitamos de la autonomia)

    protected abstract boolean cumpleRestriccionPropia(Mision m);

    public boolean puedeRealizarMision(Mision m) {
        return estado == 1 && nivel_bat >= bateriaNecesaria(m.getDistancia()) && capacidad_maxima >= m.getPeso() && cumpleRestriccionPropia(m);
    }

    public void actualizarEstado(int estado) {
        setEstado(estado); 
    }

    /**
     * Carga los datos comunes a todo vehiculo. El controlador llama a este
     * metodo con datos ya recolectados por la Presentacións
     */
    public void setDatosBase(String cod_ident, String modelo, int nivel_bat,
            int capacidad_maxima, double km_reco, int estado) {

        setCod_ident(cod_ident);
        setModelo(modelo);
        setNivel_bat(nivel_bat);
        setCapacidad_maxima(capacidad_maxima);
        setKm_reco(km_reco);
        setEstado(estado);
    }

    @Override

    public int compareTo(VehiculoAutonomo otroVehiculo) {

        // Compara los codigos de los vehiculos 
        return this.cod_ident.compareTo(otroVehiculo.cod_ident);

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

    public int getEstado() {
        return estado;
    }

    private void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "\n____Vehiculo____"
                + "\nCódigo identificador:" + cod_ident
                + "\nModelo: " + modelo
                + "\nNivel de bateria:" + nivel_bat
                + "\nCapacidad maxima de carga:" + capacidad_maxima
                + "\nKilometros recorridos:" + km_reco
                + "\nEstado:" + estado;
    }

}

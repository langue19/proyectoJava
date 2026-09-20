
package LogicaNegocios;

public abstract class VehiculoAutonomo implements Comparable<VehiculoAutonomo> {

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

    //public abstract boolean puedeRealizarMision(Mision m);

    public void actualizarEstado(String estado) {
    }

    /**
     * Carga los datos comunes a todo vehiculo. El controlador llama a este
     * metodo con datos ya recolectados por la Presentacións
     */
    public void setDatosBase(String cod_ident, String modelo, int nivel_bat,
            int capacidad_maxima, double km_reco, String estado) {

        ingresarCod_ident(cod_ident);
        ingresarModelo(modelo);
        ingresarNivel_bat(nivel_bat);
        ingresarCapacidad_maxima(capacidad_maxima);
        ingresarKm_reco(km_reco);
        ingresarEstado(estado);

    }
    @Override

    public int compareTo(VehiculoAutonomo otroVehiculo) {

        // Compara los codigos de los vehiculos 
        return this.cod_ident.compareTo(otroVehiculo.cod_ident);

    }
    
        protected void ingresarNivel_bat(int nivel_bat) {
        if (nivel_bat < 0 || nivel_bat > 100) {
            throw new IllegalArgumentException("El nivel de batería debe estar entre 0 y 100.");
        }

        this.nivel_bat = nivel_bat;
    }

    private void ingresarCapacidad_maxima(int capacidad_maxima) {
        if (capacidad_maxima <= 0) {
            throw new IllegalArgumentException("La capacidad máxima debe ser mayor a 0.");
        }

        this.capacidad_maxima = capacidad_maxima;
    }

    private void ingresarKm_reco(double km_reco) {
        if (km_reco < 0) {
            throw new IllegalArgumentException("Los kilómetros recorridos no pueden ser negativos.");
        }

        this.km_reco = km_reco;
    }

    private void ingresarCod_ident(String cod_ident) {
        if (cod_ident == null || cod_ident.trim().isEmpty()) {
            throw new IllegalArgumentException("El código identificador no puede estar vacío.");
        }

        this.cod_ident = cod_ident;
    }

    private void ingresarModelo(String modelo) {
        if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("El modelo no puede estar vacío.");
        }

        this.modelo = modelo;
    }

    private void ingresarEstado(String estado) {
        if (!estado.equals("Disponible")
                && !estado.equals("En misión")
                && !estado.equals("Fuera de servicio")) {

            throw new IllegalArgumentException("Estado inválido.");
        }

        this.estado = estado;
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

    @Override
    public String toString() {
        return "____Vehiculo____" +
                "\nCódigo identificador:"+ cod_ident +  
                "\nModelo: " + modelo + 
                "\nNivel de bateria:" + nivel_bat + 
                "\nCapacidad maxima de carga:" + capacidad_maxima + 
                "\nKilometros recorridos:" + km_reco + 
                "\nEstado:" + estado;
    }
    
    

}

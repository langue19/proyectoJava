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
     * metodo con datos ya recolectados por la Presentación
     */
    public void validarDatosBase(String cod_ident, String modelo, int nivel_bat,
            int capacidad_maxima, double km_reco, int estado) {
        validarCodigo(cod_ident);
        validarModelo(modelo);
        validarBateria(nivel_bat);
        validarCapacidad(capacidad_maxima);
        validarKM(km_reco);
        validarEstado(estado);
    }

    public void validarCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código no puede estar vacío.");
        }
        setCod_ident(codigo);
    }

    public void validarModelo(String modelo) {
        if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("El modelo no puede estar vacío.");
        }
        setModelo(modelo);
    }

    public void validarBateria(int bateria) {
        if (bateria < 0 || bateria > 100) {
            throw new IllegalArgumentException("El nivel de batería debe estar entre 0 y 100.");
        }
        setNivel_bat(bateria);
    }

    public void validarCapacidad(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad máxima debe ser mayor a 0.");
        }
        setCapacidad_maxima(capacidad_maxima);
    }

    public void validarKM(Double km) {
        if (km < 0.0) {
            throw new IllegalArgumentException("Los kilómetros recorridos no pueden ser negativos.");
        }
        setKm_reco(km_reco);
    }

    private void validarEstado(int estado) {
        if (estado != 1 && estado != 2 && estado != 3) {
            throw new IllegalArgumentException("El estado debe ser 1 (activo), 2 (en mision) o 3(en mantenimiento).");
        }
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
        String estadoTexto;
        switch (estado) {
            case 1:
                estadoTexto = "Activo";
                break;
            case 2:
                estadoTexto = "En misión";
                break;
            case 3:
                estadoTexto = "En mantenimiento";
                break;
            default:
                estadoTexto = "Desconocido";
        }
        return "\n____Vehiculo____"
                + "\nCodigo identificador: " + cod_ident
                + "\nModelo: " + modelo
                + "\nNivel de bateria: " + nivel_bat
                + "\nCapacidad maxima de carga: " + capacidad_maxima
                + "\nKilometros recorridos: " + km_reco
                + "\nEstado: " + estadoTexto;
    }

}

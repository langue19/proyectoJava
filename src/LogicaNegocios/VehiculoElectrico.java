package LogicaNegocios;

import Interface.Recargable;

public class VehiculoElectrico extends VehiculoAutonomo implements Recargable {

    private double autonomia_km;
    private String tiempo_recarga;

    public VehiculoElectrico() {
        super();
        this.autonomia_km = 0.0;
        this.tiempo_recarga = "";
    }
    
    ///-----implementacion de Recargable-----
    public void recargar() {
        setNivel_bat(100);
    }

    public boolean necesitaRecarga() {
        return nivel_bat < 20;
    }

    public void validarDatosElectrico(double autonomia_km, String tiempo_recarga) {
        validarAutonomia(autonomia_km);
        validarTiempo(tiempo_recarga);
    }

    public void validarAutonomia(Double a) {
        if (a < 0 || a > 1000) {
            throw new IllegalArgumentException("La autonomia en kilómetros debe estar entre 0 y 1000.");
        }
        setAutonomia_km(a);
    }

    public void validarTiempo(String tiempo) {
        if (tiempo == null || tiempo.trim().isEmpty()) {
            throw new IllegalArgumentException("El tiempo de recarga no puede estar vacío.");
        }
        setTiempo_recarga(tiempo);
    }

    public double getAutonomia_km() {
        return autonomia_km;
    }

    private void setAutonomia_km(double autonomia_km) {
        this.autonomia_km = autonomia_km;
    }

    public String getTiempo_recarga() {
        return tiempo_recarga;
    }

    private void setTiempo_recarga(String tiempo_recarga) {
        this.tiempo_recarga = tiempo_recarga;
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nAutonomia en Km:" + autonomia_km
                + "\nTiempo estimado de recarga: " + tiempo_recarga;
    }

}

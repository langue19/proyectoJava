package Modelo;

import Interface.Recargable;

public class VehiculoElect extends VehiculoAutonomo {

    private double autonomia_km;
    private String tiempo_recarga;

    public VehiculoElect() {
        super();
        this.autonomia_km = 0.0;
        this.tiempo_recarga = "";
    }


    
    public void setDatosElectrico(double autonomia_km, String tiempo_recarga) {
        setAutonomia_km(autonomia_km);
        setTiempo_recarga(tiempo_recarga);
    }

    @Override
    public void recargar() {
        setNivel_bat(getCapacidad_maxima());
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
    public boolean puedeRealizarMision(Mision m) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

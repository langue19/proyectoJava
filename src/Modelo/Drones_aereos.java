package Modelo;

import Interface.Recargable;

public class Drones_aereos extends VehiculoAutonomo {

    private double autonomia_vuelo;
    private double altura_max;

    public Drones_aereos() {
        super();
        this.autonomia_vuelo = 0.0;
        this.altura_max = 0.0;
    }

    public void setDatosDron(double autonomia_vuelo, double altura_max) {
        setAutonomia(autonomia_vuelo);
        setAltura_max(altura_max);
    }
    
    @Override
public boolean puedeRealizarMision(Mision m) {
  
}

    public int validarAutonomia(Double v){
    }

    public double getAutonomia() {
        return autonomia_vuelo;
    }

    private void setAutonomia(double autonomia) {
        this.autonomia_vuelo = autonomia;
    }

    public double getAltura_max() {
        return altura_max;
    }

    private void setAltura_max(double altura_max) {
        this.altura_max = altura_max;
    }
}

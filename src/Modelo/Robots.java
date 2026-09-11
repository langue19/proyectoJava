package Modelo;

import Interface.Recargable;

public class Robots extends VehiculoAutonomo implements Recargable {

    private int tipo_terreno; // 1-Asfalto 2-Mixto 3-Arena
    private double velocidad_max;

    public Robots() {
        super();
        this.tipo_terreno = 0;
        this.velocidad_max = 0.0;
    }

@Override
public boolean puedeRealizarMision(Mision m) {
}

    public void setearDatosRobot(int tipo_terreno, double velocidad_max) {
        setTipo_terreno(tipo_terreno);
        setVelocidad_max(velocidad_max);
    }

    public int getTipo_terreno() {
        return tipo_terreno;
    }

    private void setTipo_terreno(int tipo_terreno) {
        this.tipo_terreno = tipo_terreno;
    }

    public double getVelocidad_max() {
        return velocidad_max;
    }

    private void setVelocidad_max(double velocidad_max) {
        this.velocidad_max = velocidad_max;
    }
}

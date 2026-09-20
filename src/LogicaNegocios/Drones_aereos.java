package LogicaNegocios;

import Interface.Recargable;

public class Drones_aereos extends VehiculoAutonomo implements Recargable {

    private double autonomia_vuelo;
    private double altura_max;

    public Drones_aereos() {
        super();
        this.autonomia_vuelo = 0.0;
        this.altura_max = 0.0;
    }

    public void recargar() {
    }

    public void setDatosDron(double autonomia_vuelo, double altura_max) {
        ingresarAutonomia(autonomia_vuelo);
        ingresarAltura_max(altura_max);
    }

    public void ingresarAutonomia(double autonomia_vuelo) {

        if (autonomia_vuelo <= 0) {
            throw new IllegalArgumentException("La autonomia de vuelo debe ser mayor a 0.");
        }

        this.autonomia_vuelo = autonomia_vuelo;
    }

    public void ingresarAltura_max(double altura_max) {

        if (altura_max <= 0) {
            throw new IllegalArgumentException("La altura maxima debe ser mayor a 0.");
        }

        this.altura_max = altura_max;
    }

    public boolean validarAutonomia(Double v) {
        return (v > 0 && v <= 120.0);
    }

    public boolean validarAltura(Double a) {
        return (a > 0 && a <= 500);
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

    @Override
    public String toString() {
        return super.toString()
                + "Autonomia de vuelo:" + autonomia_vuelo
                + "Altura máxima:" + altura_max;
    }

}

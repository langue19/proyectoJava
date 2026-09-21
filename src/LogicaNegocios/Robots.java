package LogicaNegocios;

import Interface.Recargable;

public class Robots extends VehiculoAutonomo {

    private int tipo_terreno; // 1-Asfalto 2-Mixto 3-Arena
    private double velocidad_max;

    public Robots() {
        super();
        this.tipo_terreno = 0;
        this.velocidad_max = 0.0;
    }

    protected double bateriaNecesaria(int distancia) {
        return 20; //el robot necesita al menos 20% de bateria para salir.
    }

    @Override
    protected boolean cumpleRestriccionPropia(Mision m) {
        if (m.getPrioridad() == 2) {
            return velocidad_max > 15;
        }
        return false;
    }

    public void setearDatosRobot(int tipo_terreno, double velocidad_max) {
        setTipo_terreno(tipo_terreno);
        setVelocidad_max(velocidad_max);
    }

    public boolean validarVelocidad(Double v) {
        return v > 0 && v <= 80;
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

    @Override
    public String toString() {
        String terrenoTexto;
        switch (tipo_terreno) {
            case 1:
                terrenoTexto = "Asfalto";
                break;
            case 2:
                terrenoTexto = "Mixto";
                break;
            case 3:
                terrenoTexto = "Arena";
                break;
            default:
                terrenoTexto = "Desconocido";
        }
        return super.toString()
                + "\nTipo de terreno: " + terrenoTexto
                + "\nVelocidad máxima: " + velocidad_max;
    }

}

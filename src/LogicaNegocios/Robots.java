package LogicaNegocios;

import Interface.Recargable;

public class Robots extends VehiculoAutonomo implements Recargable {

    private int tipo_terreno; // 1-Asfalto 2-Mixto 3-Arena
    private double velocidad_max;

    public Robots() {
        super();
        this.tipo_terreno = 0;
        this.velocidad_max = 0.0;
    }

    public void recargar() {
    }

   public void setearDatosRobot(int tipo_terreno, double velocidad_max) {
        ingresarTipo_terreno(tipo_terreno);
        ingresarVelocidad_max(velocidad_max);
    }
    
    public void ingresarTipo_terreno(int tipo_terreno) {

        if (tipo_terreno < 1 || tipo_terreno > 3) {
            throw new IllegalArgumentException("El tipo de terreno debe ser 1, 2 o 3.");
        }

        this.tipo_terreno = tipo_terreno;
    }

    public void ingresarVelocidad_max(double velocidad_max) {

        if (velocidad_max <= 0) {
            throw new IllegalArgumentException("La velocidad maxima debe ser mayor a 0.");
        }

        this.velocidad_max = velocidad_max;
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
        return super.toString()+
                "\nTipo de terreno:" + tipo_terreno +
                "\nVelocidad máxima:" + velocidad_max;
    }
    
    
}

package LogicaNegocios;

public class Robot extends VehiculoAutonomo {

    private int tipo_terreno; // 1-Asfalto 2-Mixto 3-Arena
    private double velocidad_max;

    public Robot() {
        super();
        this.tipo_terreno = 0;
        this.velocidad_max = 0.0;
    }

    public void validarDatosRobot(int tipo_terreno, double velocidad_max) {
        validarTerreno(tipo_terreno);
        validarVelocidad(velocidad_max);
    }
    
    public void validarVelocidad(Double v) {
        if (v < 0 || v > 80) {
            throw new IllegalArgumentException("La velocidad debe estar entre 0 y 80");
        }
        setVelocidad_max(v);
    }
    
    private void validarTerreno(int terreno) {
        if (terreno != 1 && terreno != 2 && terreno != 3) {
            throw new IllegalArgumentException("El estado debe ser 1 (asfalto), 2 (mixto) o 3(arena).");
        }
        setTipo_terreno(terreno);
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
                + "\nVelocidad maxima: " + velocidad_max;
    }

}

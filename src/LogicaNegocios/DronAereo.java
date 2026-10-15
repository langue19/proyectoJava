package LogicaNegocios;

public class DronAereo extends VehiculoAutonomo {

    private double autonomia_vuelo; //Distancia max que puede recorrer con la bateria completa
    private double altura_max;

    public DronAereo() {
        super();
        this.autonomia_vuelo = 0.0;
        this.altura_max = 0.0;
    }

    protected double bateriaNecesaria(int distancia) {
        return distancia / autonomia_vuelo * 100; //Regla de tres. 
    }

    public void validarDatosDron(double autonomia_vuelo, double altura_max) {
        validarAutonomiaVuelo(autonomia_vuelo);
        validarAlturaMax(altura_max);
    }

    private void validarAutonomiaVuelo(double autonomia) {
        if (autonomia <= 0.0) {
            throw new IllegalArgumentException("La autonomia de vuelo debe ser mayor a 0.");
        }
        setAutonomia(autonomia);
    }

    private void validarAlturaMax(double altura) {
        // Por ejemplo, limitando una altura lógica máxima (ej: 5000 metros) y que no sea negativa
        if (altura <= 0.0 || altura > 5000.0) {
            throw new IllegalArgumentException("La altura maxima debe estar entre 0 y 5000 metros.");
        }
        setAltura_max(altura);
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
                + "\nAutonomia de vuelo:" + autonomia_vuelo
                + "\nAltura maxima:" + altura_max;
    }

}

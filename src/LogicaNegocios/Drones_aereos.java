package LogicaNegocios;

import Interface.Recargable;

public class Drones_aereos extends VehiculoAutonomo implements Recargable{

    private double autonomia_vuelo;
    private double altura_max;

    public Drones_aereos() {
        super();
        this.autonomia_vuelo = 0.0;
        this.altura_max = 0.0;
    }

    public void recargar(){}
    
    public boolean setDatosDron(double autonomia_vuelo, double altura_max) {
        if(validarAutonomia(autonomia_vuelo) && validarAltura(altura_max)){
            setAutonomia(autonomia_vuelo);
            setAltura_max(altura_max); 
        }
        
        return true; 
    }
    
    public boolean validarAutonomia(Double v) {
        return (v>0 && v<=120.0); 
    }
    
    public boolean validarAltura(Double a){
        return(a>0 && a<=500); 
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
        return super.toString()+
                "Autonomia de vuelo:" + autonomia_vuelo +
                "Altura máxima:" + altura_max;
    }
    
    
}

package LogicaNegocios;

import Interface.Recargable;

public class VehiculoElect extends VehiculoAutonomo implements Recargable{
    private double autonomia_km;
    private String tiempo_recarga;

    public VehiculoElect() {
        super();
        this.autonomia_km = 0.0;
        this.tiempo_recarga = "";
    }
    
    protected double bateriaNecesaria(int distancia){
        return distancia/autonomia_km * 100; 
    }
    
    public void setDatosElectrico(double autonomia_km, String tiempo_recarga) {
        setAutonomia_km(autonomia_km);
        setTiempo_recarga(tiempo_recarga);
    }

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
    public String toString() {
        return super.toString()+
                "\nAutonomia en Km:" + autonomia_km +
                "\nTiempo estimado de recarga: " + tiempo_recarga;
    }
    
    
    
}

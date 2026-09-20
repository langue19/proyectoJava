package LogicaNegocios;

import AccesoDatos.*;

public class GestorAutoMove {

    private ListaS<VehiculoAutonomo> vehiculos;
    //private ListaS<Mision> misiones;
    ListaS lista = new ListaS();


    public void registrarVehiculo(VehiculoAutonomo vehiculo){
        lista.insertarPri(vehiculo);
    }

    public void registrarMision(Mision mision){
        
    }

    public void consultarVehiculos(){
        lista.visualizar();
    }

    public boolean vehiculoDisponible(VehiculoAutonomo vehiculo, Mision mision){
        
        return false;
    }

    public boolean asignarVehiculo(String codigoVehiculo, String codigoMision){
        
        
        return false;
    }
    
    
    public void actualizarEstadoVehiculo(){
        
    }
}

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
    public void registrarVehiculo(VehiculoAutonomo vehiculo) {
        vehiculos.insertarPri(vehiculo);
    }

    public void registrarMision(Mision mision) {
        misiones.encolar(mision);
        consultarMisiones();
    }

    public void consultarMisiones() {
        if (misiones.colaVacia()) {
            System.out.println("No hay misiones registradas.");
            return;
        }
        Nodo<Mision> p = misiones.getFrente();
        while (p != null) {
            Mision m = p.getDato();
            System.out.println(m);
            p = p.getPs();
        }
    }

    public void consultarVehiculos() {
        vehiculos.visualizar();
    }

    public void vehiculoDisponible() {
        if (!misiones.colaVacia()) {
            Mision aux = misiones.getFrente().getDato();
            Nodo<VehiculoAutonomo> p = vehiculos.inicio();
            while (p != null) {
                VehiculoAutonomo v = p.getDato();
                if (v.puedeRealizarMision(aux)) {
                    vehidisp.insertarPri(v);
                    vehidisp.visualizar();
                }
                p = p.getPs();
            }

        } else {
            System.out.println("No hay misiones registradas.");
        }
    }
//hola bb, tu como estas?
    //gei/////
    public void asignarVehiculo() {
        vehiculoDisponible();
        

        
    }

    public void consultarVehiculos(){
        lista.visualizar(); ////tyyy
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

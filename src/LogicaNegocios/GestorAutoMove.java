package LogicaNegocios;

import AccesoDatos.*;

public class GestorAutoMove {

    private ListaS<VehiculoAutonomo> vehiculos;
    private ListaS<VehiculoAutonomo> vehidisp;
    private Cola<Mision> misiones;
    private Cola<Mision> aux;

    public GestorAutoMove() {
        vehiculos = new ListaS<>();
        misiones = new Cola<>();
        vehidisp = new ListaS<>();
        aux = new Cola<>();
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
    public void asignarVehiculo() {
        vehiculoDisponible();
        
        
    }

    public void actualizarEstadoVehiculo() {

    }
}

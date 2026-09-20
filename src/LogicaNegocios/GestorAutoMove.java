package LogicaNegocios;

import AccesoDatos.*;

public class GestorAutoMove {

    private ListaS<VehiculoAutonomo> vehiculos;
    private Cola<Mision> misiones;

    public GestorAutoMove() {
        vehiculos = new ListaS<>();
        misiones = new Cola<>();
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

    public boolean vehiculoDisponible() {
        Nodo<VehiculoAutonomo> p = vehiculos.inicio();
        while (p != null) {
            VehiculoAutonomo v = p.getDato();
            if (v.getEstado().equals("Activo")) {
                if (v.nivel_bat > 50) {
                    if (v.capacidad_maxima > 50) {
                        return true;
                    }
                }

            }
            p = p.getPs();
        }
        return false;
    }

    public boolean asignarVehiculo(String codigoVehiculo,
            String codigoMision) {

        return false;
    }

    public void actualizarEstadoVehiculo() {

    }
}

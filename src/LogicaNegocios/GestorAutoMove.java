package LogicaNegocios;

import AccesoDatos.*;

public class GestorAutoMove {

    private ListaS<VehiculoAutonomo> vehiculos;
    private ListaS<VehiculoAutonomo> vehidisp;
    private Cola<Mision> mPendientes;
    private Cola<Mision> mAsig_y_Final;
    private Cola<Mision> aux;

    public GestorAutoMove() {
        vehiculos = new ListaS<>();
        vehidisp = new ListaS<>();
        mPendientes = new Cola<>(); //Cola para misiones pendientes 
        mAsig_y_Final = new Cola<>(); //Cola para misiones q ya estan asignadas y finalizadas
        aux = new Cola<>();
    }

    public void registrarVehiculo(VehiculoAutonomo vehiculo) {
        vehiculos.insertarPri(vehiculo);
    }

    public void registrarMision(Mision m) {
        if (m.getEstado() == 1) {
            mPendientes.encolar(m);
        } else {
            mAsig_y_Final.encolar(m);
        }

        ///consultarMisiones();
    }

    /*public void consultarMisiones() {
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
    }*/
    public void consultarVehiculos() {
        vehiculos.visualizar();
    }

    public void vehiculoDisponible() {
        if (!mPendientes.colaVacia()) {
            Mision aux = mPendientes.getFrente().getDato();
            Nodo<VehiculoAutonomo> p = vehiculos.inicio();
            while (p != null) {
                VehiculoAutonomo v = p.getDato();
                if (v.puedeRealizarMision(aux)) {
                    vehidisp.insertarPri(v);
                }
                p = p.getPs();
            }
            if (vehidisp.listaVacia()) {
                System.out.println("No hay vehiculos aptos para la mision " + aux.getCod_mision());
            } else {
                vehidisp.visualizar();
            }

        } else {
            System.out.println("No hay misiones registradas.");

        }

    }

    public void asignarVehiculo(int codigo) {
        actualizarEstadoVehiculo(codigo);
        Mision m = mPendientes.desencolar();
        m.setEstado(2);
        mAsig_y_Final.encolar(m);
    }

    public void actualizarEstadoVehiculo(int codigo) {
        Nodo<VehiculoAutonomo> aux = vehiculos.inicio();
        while (aux != null) {
            VehiculoAutonomo vehiculo = aux.getDato();
            if (vehiculo.getCod_ident().equals(codigo)) {
                if (vehiculo.getEstado() == 1) {
                    vehiculo.actualizarEstado(2);
                    System.out.println("Vehículo asignado correctamente.");
                } else {
                    System.out.println("El vehículo no está disponible.");
                }
                return;
            }
            aux = aux.getPs();
        }
        System.out.println("No se encontró un vehículo con ese código.");
    }
}

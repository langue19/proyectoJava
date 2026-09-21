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
    }

    
    public void consultarVehiculos() {
        vehiculos.visualizar();
    }

    public void vehiculoDisponible() { //verifica qué vehiculos de la lista están aptos para realizar una misión y los muestra 
        vehidisp = new ListaS<>();   
        if (!mPendientes.colaVacia()) {
            Nodo<Mision> frente = mPendientes.getFrente();
            Mision aux = frente.getDato();
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
            System.out.println("No hay misiones Pendientes registradas.");

        }

    }

    public void asignarVehiculo(String codigo) { //Luego de actualizar datos del vehiculo, lo hace con los de mision 
        actualizarEstadoVehiculo(codigo);           //Además actualiza las determinadas colas de misiones 
        if (!mPendientes.colaVacia()) {
            Mision m = mPendientes.desencolar();
            m.setEstado(2);
            mAsig_y_Final.encolar(m);
        } else {
            System.out.println("No hay misiones Pendientes registradas.");
        }
    }


    public void actualizarEstadoVehiculo(String codigo) { //A traves del codigo del vehiculo elegido para asignar, se accede a su estado y cambia a "En mision"
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

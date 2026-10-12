package LogicaNegocios;

import java.util.List;
import AccesoDatos.*;

public class GestorAutoMove {

    //private ListaS<VehiculoAutonomo> vehiculos;
    private ArbolGenerico<VehiculoAutonomo> ABB;

    private Cola<Mision> mPendientes;
    private Cola<Mision> mAsig_y_Final;
    private Cola<Mision> aux;

    public GestorAutoMove() {
        //vehiculos = new ListaS<>();
        ABB = new ArbolGenerico<>();
        mPendientes = new Cola<>(); //Cola para misiones pendientes 
        mAsig_y_Final = new Cola<>(); //Cola para misiones q ya estan asignadas y finalizadas
        aux = new Cola<>();
    }

    public void registrarVehiculo(VehiculoAutonomo vehiculo) {
        ABB.insertar(vehiculo);
    }

    public void registrarMision(Mision m) {
        if (m.getEstado() == 1) {
            mPendientes.encolar(m);
        } else {
            mAsig_y_Final.encolar(m);
        }
    }

    public void consultarVehiculos() {
        ABB.preOrden();
    }

    public boolean vehiculoDisponible() {
        ListaS<VehiculoAutonomo> vehidisp;// Verifica qué vehículos del árbol están aptos para realizar una misión y los muestra
        vehidisp = new ListaS<>(); // Inicializa la lista para guardar los disponibles

        if (!mPendientes.colaVacia()) {
            NodoLista<Mision> frente = mPendientes.getFrente();
            Mision auxMision = frente.getDato();

            // Obtenemos todos los vehículos del árbol genérico en forma de lista estándar de Java
            List<VehiculoAutonomo> todosLosVehiculos = ABB.inOrden();
            for (VehiculoAutonomo v : todosLosVehiculos) {
                if (v.puedeRealizarMision(auxMision)) {
                    vehidisp.insertarPri(v); // Los guardamos en ListaS
                }
            }
            if (vehidisp.listaVacia()) {
                System.out.println("No hay vehiculos aptos para la mision " + auxMision.getCod_mision());
            } else {
                vehidisp.visualizar();
                return true;
            }
        } else {
            System.out.println("No hay misiones pendientes registradas.");
        }
        return false;
    }

    public void asignarVehiculo(String codigo) { //Luego de actualizar datos del vehiculo, lo hace con los de mision 
        actualizarEstadoVehiculo(codigo);           //Además actualiza las determinadas colas de misiones 
        if (!mPendientes.colaVacia()) {
            Mision m = mPendientes.desencolar();
            m.cambiarEstado(2);
            mAsig_y_Final.encolar(m);
        } else {
            System.out.println("No hay misiones Pendientes registradas.");
        }
    }

    public void actualizarEstadoVehiculo(String codigo) { 
        List<VehiculoAutonomo> todosLosVehiculos = ABB.inOrden();

        boolean encontrado = false;

        for (VehiculoAutonomo vehiculo : todosLosVehiculos) {
            // Comparamos el código de identificación del vehículo actual con el que buscamos
            if (vehiculo.getCod_ident().equals(codigo)) {
                encontrado = true;
                if (vehiculo.getEstado() == 1) {
                    vehiculo.actualizarEstado(2);
                    System.out.println("Vehiculo asignado correctamente.");
                } else {
                    System.out.println("El vehiculo no esta disponible.");
                }
                break; 
            }
        }
        if (!encontrado) {
            System.out.println("No se encontro un vehiculo con ese codigo.");
        }
    }
}

package LogicaNegocios;

import AccesoDatos.*;
import java.util.ArrayList;
import java.util.List;

public class GestorAutoMove {

    private ArbolABB<VehiculoAutonomo> ABB;
    private Cola<Mision> mPendientes;
    private Cola<Mision> mAsig_y_Final;
    private Cola<Mision> aux;

    public GestorAutoMove() {
        ABB = new ArbolABB<>();
    }

    public boolean cargarVehiculosExistentes() {
        try {
            Robot r = new Robot();
            r.validarDatosBase("01", "Terminator", 80, 50, 120.5, 1);
            r.validarDatosRobot(1, 15.0);
            ABB.insertar(r);

            DronAereo d = new DronAereo();
            d.validarDatosBase("02", "Phantom", 90, 10, 45.0, 1);
            d.validarDatosDron(25.0, 120.0);
            ABB.insertar(d);

            VehiculoElectrico ve = new VehiculoElectrico();
            ve.validarDatosBase("03", "TeslaBot", 100, 200, 300.0, 1);
            ve.validarDatosElectrico(400.0, "2 horas");
            ABB.insertar(ve);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Error al cargar datos iniciales: " + e.getMessage());
        }
        return false;
    }

    public boolean registrarNuevoVehiculo(VehiculoAutonomo v) {
        if (ABB.buscar(v) != null) {
            return false;
        }
        ABB.insertar(v);
        return true;
    }

    public VehiculoAutonomo buscarVehiculo(String codigo) {
        DronAereo aux = new DronAereo();
        aux.validarCodigo(codigo);
        return ABB.buscar(aux);
    }

    public boolean eliminarVehiculo(String codigo) {
        VehiculoAutonomo v = buscarVehiculo(codigo);
        if (v == null) {
            return false;
        }
        ABB.eliminar(v);
        return true;
    }

    public List<VehiculoAutonomo> mostrarVehiculo(int opcion) {
        List<VehiculoAutonomo> lista = new ArrayList<>();
        if (opcion == 1) {
            lista = ABB.inOrden();
        } else if (opcion == 2) {
            lista = ABB.preOrden();
        } else if (opcion == 3) {
            lista = ABB.postOrden();
        }
        return lista;
    }

    public boolean registrarMision(String cod, String origen, String des, int dist, double peso, int prio, int e) {
        Mision m = new Mision();
        m.validarMision(cod, origen, des, dist, peso, prio, e);
        if (m.getEstado() == 1) {
            mPendientes.encolar(m);
        } else {
            mAsig_y_Final.encolar(m);
        }
        return true;
    }

    public boolean vehiculoDisponible() { // Verifica qué vehículos del árbol están aptos para realizar una misión y los muestra
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
                p = p.getPs();
            }
            if (vehidisp.listaVacia()) {
                System.out.println("No hay vehiculos aptos para la mision " + aux.getCod_mision());
                System.out.println("No hay vehiculos aptos para la mision " + auxMision.getCod_mision());
            } else {
                vehidisp.visualizar();
                return true;
            }

        } else {
            System.out.println("No hay misiones Pendientes registradas.");

            System.out.println("No hay misiones pendientes registradas.");
        }
        return false;
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

    public void actualizarEstadoVehiculo(String codigo) {
        List<VehiculoAutonomo> todosLosVehiculos = ABB.inOrden();

        boolean encontrado = false;

        for (VehiculoAutonomo vehiculo : todosLosVehiculos) {
            if (vehiculo.getCod_ident().equals(codigo)) {
                encontrado = true;
                if (vehiculo.getEstado() == 1) {
                    vehiculo.actualizarEstado(2);
                    System.out.println("Vehiculo asignado correctamente.");
                } else {
                    System.out.println("El vehiculo no esta disponible.");
                }
                return;
            }
            aux = aux.getPs();
        }
        System.out.println("No se encontró un vehículo con ese código.");
        if (!encontrado) {
            System.out.println("No se encontro un vehiculo con ese codigo.");
        }
    }

}

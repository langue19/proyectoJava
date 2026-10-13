package LogicaNegocios;

import java.util.List;
import AccesoDatos.*;

public class GestorAutoMove {
    private RepositorioVehiculos repo;
    private ArbolABB<VehiculoAutonomo> ABB; //<----esto no va, se usa repositorioVehiculo

    private Cola<Mision> mPendientes;   //Etapa 1
    private Cola<Mision> mAsig_y_Final; //Etapa 1
    private Cola<Mision> aux;           //Etapa 1

    public GestorAutoMove() {
        repo = new RepositorioVehiculos();
        cargarVehiculosExistentes(); //<---preguntar si este met va aqui
        
        ABB = new ArbolABB<>(); //no va
        mPendientes = new Cola<>(); //Cola para misiones pendientes 
        mAsig_y_Final = new Cola<>(); //Cola para misiones q ya estan asignadas y finalizadas
        aux = new Cola<>();
    }
    
    public void cargarVehiculosExistentes() {
        try {
            // Creamos vehículos de prueba (Robot, Dron, Eléctrico) y los insertamos
            Robot r = new Robot();
            r.validarDatosBase("ROB-001", "Terminator", 80, 50, 120.5, 1);
            r.validarDatosRobot(1, 15.0);
            repo.insertar(r);

            DronAereo d = new DronAereo();
            d.validarDatosBase("DRN-002", "Phantom", 90, 10, 45.0, 1);
            d.validarDatosDron(25.0, 120.0);
            repo.insertar(d);

            VehiculoElectrico ve = new VehiculoElectrico();
            ve.validarDatosBase("ELE-003", "TeslaBot", 100, 200, 300.0, 1);
            ve.validarDatosElectrico(400.0, "2 horas");
            repo.insertar(ve);
            
        } catch (IllegalArgumentException e) {
            System.out.println("Error al cargar datos iniciales: " + e.getMessage());
        }
    }

    public boolean registrarNuevoVehiculo(VehiculoAutonomo v) {
        if (repo.buscar(v) != null) {
            return false;
        }
        repo.insertar(v);
        return true;
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

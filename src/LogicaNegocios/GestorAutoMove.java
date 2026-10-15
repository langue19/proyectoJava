package LogicaNegocios;
import AccesoDatos.*;

public class GestorAutoMove {

    private ArbolABB<VehiculoAutonomo> ABB;

    public GestorAutoMove() {
        ABB = new ArbolABB<>(); 
    }

    public boolean cargarVehiculosExistentes() {
        try {
            Robot r = new Robot();
            r.validarDatosBase("ROB-001", "Terminator", 80, 50, 120.5, 1);
            r.validarDatosRobot(1, 15.0);
            ABB.insertar(r);

            DronAereo d = new DronAereo();
            d.validarDatosBase("DRN-002", "Phantom", 90, 10, 45.0, 1);
            d.validarDatosDron(25.0, 120.0);
            ABB.insertar(d);

            VehiculoElectrico ve = new VehiculoElectrico();
            ve.validarDatosBase("ELE-003", "TeslaBot", 100, 200, 300.0, 1);
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
        return ABB.buscar(codigo);
    }

    public void consultarVehiculos() {
        ABB.preOrden();
    }

    public boolean eliminarVehiculo(String codigo) {
        VehiculoAutonomo v = buscarVehiculo(codigo);
        if (v == null) {
            return false;
        }
        ABB.eliminar(v);
        return true;
    }

    public void mostrarPre() {
        System.out.println("\n===== RECORRIDO PREORDEN =====");
        ABB.preOrden();
    }

    public void mostrarIn() {
        System.out.println("\n===== RECORRIDO INORDEN =====");
        for (VehiculoAutonomo v : ABB.inOrden()) {
            System.out.println(v);
        }
    }

    public void mostrarPost() {
        System.out.println("\n===== RECORRIDO POSTORDEN =====");
        ABB.postOrden();
    }
}

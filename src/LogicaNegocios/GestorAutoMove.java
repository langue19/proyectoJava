package LogicaNegocios;

import AccesoDatos.*;
import java.util.ArrayList;
import java.util.List;

public class GestorAutoMove {

    private ArbolABB<VehiculoAutonomo> ABB;

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

}

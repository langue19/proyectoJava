package LogicaNegocios;

import AccesoDatos.*;
import java.util.List;

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

    public int mostrarVehiculo(int opcion) {
        switch (opcion) {
            case 1: // Inorden
                Consola.emitirMensajeln("\n--- REPORTE INORDEN (Orden por Código) ---");
                List<T> listaIn = tuArbol.inOrden();
                for (T dato : listaIn) {
                    imprimirVehiculoCompacto((VehiculoAutonomo) dato);
                }
                break;

            case 2: // Preorden
                Consola.emitirMensajeln("\n--- REPORTE PREORDEN ---");
                List<T> listaPre = tuArbol.preOrden();
                for (T dato : listaPre) {
                    imprimirVehiculoCompacto((VehiculoAutonomo) dato);
                }
                break;

            case 3: // Postorden
                Consola.emitirMensajeln("\n--- REPORTE POSTORDEN ---");
                List<T> listaPost = tuArbol.postOrden();
                for (T dato : listaPost) {
                    imprimirVehiculoCompacto((VehiculoAutonomo) dato);
                }
                break;
        }
    }

    public void mostrarPre() {
        System.out.println("\n===== RECORRIDO PREORDEN =====");
        List<VehiculoAutonomo> listaIn =ABB.preOrden();
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

package LogicaNegocios;

import AccesoDatos.*;
import java.util.ArrayList;
import java.util.List;

public class GestorAutoMove {

    private ArbolABB<VehiculoAutonomo> ABB;
    private Cola<Mision> mPendientes;
    private Cola<Mision> mAsig_y_Final;

    public GestorAutoMove() {
        ABB = new ArbolABB<>();
        mPendientes = new Cola<>();
        mAsig_y_Final = new Cola<>();
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

    public List<VehiculoAutonomo> vehiculoDisponible() {
        if (mPendientes.colaVacia()) {
            return null;
        }
        List<VehiculoAutonomo> disponibles = new ArrayList<>();
        NodoCola<Mision> frente = mPendientes.getFrente();
        Mision mision = frente.getDato();
        List<VehiculoAutonomo> todosLosVehiculos = ABB.inOrden();
        for (VehiculoAutonomo v : todosLosVehiculos) {
            if (v.puedeRealizarMision(mision)) {
                disponibles.add(v);
            }
        }
        return disponibles;
    }

    public boolean asignarVehiculo(String codigo) {
        if (mPendientes.colaVacia()) {
            return false;
        }
        VehiculoAutonomo vehiculo = buscarVehiculo(codigo);
        if (vehiculo == null || vehiculo.getEstado() != 1) {
            return false;
        }
        Mision mision = mPendientes.getFrente().getDato();
        if (!vehiculo.puedeRealizarMision(mision)) {
            return false;
        }
        vehiculo.actualizarEstado(2);
        mPendientes.desencolar();
        mision.cambiarEstado(2);
        mAsig_y_Final.encolar(mision);
        return true;
    }

    public boolean actualizarEstadoVehiculo(String codigo) {
        VehiculoAutonomo vehiculo = buscarVehiculo(codigo);
        vehiculo.actualizarEstado(2);
        return true;
    }
}

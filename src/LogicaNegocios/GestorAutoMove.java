package LogicaNegocios;

import AccesoDatos.*;
import AccesoDatos.Archivos.*;
import Excepciones.CodigoDuplicadoException;
import Excepciones.VehiculoNoEncontradoException;
import Interface.Recargable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestorAutoMove {

    private final ArbolABB<VehiculoAutonomo> ABB;
    private final Cola<Mision> mPendientes;
    private final Cola<Mision> mAsig_y_Final;
    private boolean vehiculosRecuperados = false;
    private boolean misionesRecuperadas = false;

    public GestorAutoMove() {
        ABB = new ArbolABB<>();
        mPendientes = new Cola<>();
        mAsig_y_Final = new Cola<>();
    }

    /**
     * Carga los vehiculos de ejemplo. Cada uno pasa por registrarNuevoVehiculo
     * (archivo + ABB). Los que ya existian se omiten sin cortar el proceso.
     * Los errores de E/S se propagan (ya no se ocultan con catch Exception).
     *
     * @return true si se incorporo al menos un vehiculo nuevo
     * @throws java.io.IOException
     */
    public boolean cargarVehiculosExistentes() throws IOException {
        Robot r = new Robot();
        r.validarDatosBase("1", "Terminator", 80, 50, 120.5, 1);
        r.validarDatosRobot(1, 16.0);
        DronAereo d = new DronAereo();
        d.validarDatosBase("2", "Phantom", 90, 10, 45.0, 1);
        d.validarDatosDron(25.0, 120.0);
        VehiculoElectrico ve = new VehiculoElectrico();
        ve.validarDatosBase("3", "TeslaBot", 100, 200, 300.0, 1);
        ve.validarDatosElectrico(400.0, "2 horas");

        VehiculoAutonomo[] iniciales = {r, d, ve};
        int cargados = 0;
        for (VehiculoAutonomo v : iniciales) {
            try {
                registrarNuevoVehiculo(v);
                cargados++;
            } catch (CodigoDuplicadoException e) {
                // ya estaba registrado: se omite y se continua con el siguiente
            }
        }
        return cargados > 0;
    }

    public boolean registrarNuevoVehiculo(VehiculoAutonomo v) throws IOException, CodigoDuplicadoException {
        if (ABB.buscar(v) != null) {
            throw new CodigoDuplicadoException("vehiculo", v.getCod_ident());
        }

        ConversorVehiculos conversor = new ConversorVehiculos();
        RegistroVehiculo registro = conversor.aRegistro(v);

        try (ArchivoVehiculos archivo = new ArchivoVehiculos("VEHICULOS.DAT")) {
            archivo.guardar(registro);
        }

        ABB.insertar(v);
        return true;
    }

    public VehiculoAutonomo buscarVehiculo(String codigo) throws VehiculoNoEncontradoException {
        DronAereo aux = new DronAereo();
        aux.validarCodigo(codigo);

        VehiculoAutonomo vehiculo = ABB.buscar(aux);
        if (vehiculo == null) {
            throw new VehiculoNoEncontradoException(codigo);
        }

        return vehiculo;
    }

    public boolean eliminarVehiculo(String codigo) throws IOException, VehiculoNoEncontradoException {
        // Si no lo encuentra, buscarVehiculo lanza la excepción automáticamente
        VehiculoAutonomo v = buscarVehiculo(codigo);

        try (ArchivoVehiculos archivo = new ArchivoVehiculos("VEHICULOS.DAT")) {
            // Asegúrate de que archivo.eliminar reciba el tipo correcto (int o String según tu archivo)
            archivo.eliminar(Integer.parseInt(codigo));
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

    public boolean registrarMision(String cod, String origen, String des,
            int dist, double peso, int prio, int e) throws IOException, CodigoDuplicadoException {

        Mision m = new Mision();
        m.validarMision(cod, origen, des, dist, peso, prio, e);

        ConversorMisiones conversor = new ConversorMisiones();

        try (ArchivoMisiones archivo = new ArchivoMisiones("MISIONES.DAT")) {
            RegistroMisiones registro = conversor.aRegistro(m);

            // Si ya existe, lanzamos la excepción en lugar de retornar false
            if (archivo.buscar(registro.getCodigo()) != null) {
                throw new CodigoDuplicadoException("mision", cod);
            }

            archivo.guardar(registro);
        }

        if (m.getEstado() == 1) {
            mPendientes.encolar(m);
        } else {
            mAsig_y_Final.encolar(m);
        }
        return true;
    }

    public List<VehiculoAutonomo> vehiculoDisponible() {
        List<VehiculoAutonomo> disponibles = new ArrayList<>();
        if (mPendientes.colaVacia()) {
            return disponibles;
        }
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

    public boolean asignarVehiculo(String codigo) throws IOException, VehiculoNoEncontradoException {
        if (mPendientes.colaVacia()) {
            return false;
        }
        VehiculoAutonomo vehiculo = buscarVehiculo(codigo); // lanza excepcion si no existe
        if (vehiculo.getEstado() != 1) {
            return false;
        }
        Mision mision = mPendientes.consultarFrente();
        if (!vehiculo.puedeRealizarMision(mision)) {
            return false;
        }

        int estadoPrevio = vehiculo.getEstado();
        vehiculo.actualizarEstado(2);
        mision.cambiarEstado(2);
        mision.setVehiculoAsignado(vehiculo);
        try {
            persistirVehiculo(vehiculo);
            persistirMision(mision);
        } catch (IOException | RuntimeException e) {
            // Si algo falla, se deshace el cambio en memoria para no dejar
            // diferencias entre los objetos y los archivos.
            vehiculo.actualizarEstado(estadoPrevio);
            mision.cambiarEstado(1);
            mision.setVehiculoAsignado(null);
            try {
                persistirVehiculo(vehiculo);
            } catch (IOException ignorada) {
                // mejor esfuerzo: se propaga el error original
            }
            throw e;
        }
        mPendientes.desencolar();
        mAsig_y_Final.encolar(mision);
        return true;
    }

    public List<Mision> mostrarMisiones() throws IOException {
        List<Mision> misiones = new ArrayList<>();
        try (ArchivoMisiones archivo = new ArchivoMisiones("MISIONES.DAT")) {
            for (RegistroMisiones r : archivo.recuperarActivos()) {
                misiones.add(construirMision(r));
            }
        }
        return misiones;
    }

    public boolean actualizarEstadoVehiculo(String codigo) throws IOException, VehiculoNoEncontradoException {
        VehiculoAutonomo vehiculo = buscarVehiculo(codigo);
        vehiculo.actualizarEstado(2);
        persistirVehiculo(vehiculo);
        return true;
    }

    /** Reescribe en VEHICULOS.DAT el estado actual del vehiculo. */
    private void persistirVehiculo(VehiculoAutonomo v) throws IOException {
        try (ArchivoVehiculos archivo = new ArchivoVehiculos("VEHICULOS.DAT")) {
            archivo.modificar(new ConversorVehiculos().aRegistro(v));
        }
    }

    /** Reescribe en MISIONES.DAT el estado actual de la mision. */
    private void persistirMision(Mision m) throws IOException {
        try (ArchivoMisiones archivo = new ArchivoMisiones("MISIONES.DAT")) {
            archivo.modificar(new ConversorMisiones().aRegistro(m));
        }
    }

    /**
     * Convierte un registro en Mision y restablece su relacion con el vehiculo
     * usando el codigo guardado. Requiere que los vehiculos ya esten en el ABB.
     */
    private Mision construirMision(RegistroMisiones r) {
        Mision m = new ConversorMisiones().aMision(r);
        if (r.getCodigoVehiculo() > 0) {
            try {
                m.setVehiculoAsignado(buscarVehiculo(String.valueOf(r.getCodigoVehiculo())));
            } catch (VehiculoNoEncontradoException e) {
                // el vehiculo fue dado de baja: la mision queda sin relacion
            }
        }
        return m;
    }

    public void recuperarVehiculos() throws IOException {
        if (vehiculosRecuperados) {
            return;
        }
        try (ArchivoVehiculos archivo = new ArchivoVehiculos("VEHICULOS.DAT")) {
            ConversorVehiculos conversor = new ConversorVehiculos();
            for (RegistroVehiculo r : archivo.recuperarActivos()) {
                VehiculoAutonomo v = conversor.aVehiculo(r);
                ABB.insertar(v);
            }
        }
        vehiculosRecuperados = true;
    }

    public void recuperarMisiones() throws IOException {
        if (misionesRecuperadas) {
            return;
        }
        try (ArchivoMisiones archivo = new ArchivoMisiones("MISIONES.DAT")) {
            for (RegistroMisiones r : archivo.recuperarActivos()) {
                Mision m = construirMision(r);
                if (m.getEstado() == 1) {
                    mPendientes.encolar(m);
                } else {
                    mAsig_y_Final.encolar(m);
                }
            }
        }
        misionesRecuperadas = true;
    }

    public boolean recargarVehiculo(String codigo) throws VehiculoNoEncontradoException, IOException {
        VehiculoAutonomo v = buscarVehiculo(codigo);
        if (!(v instanceof Recargable)) {
            return false;
        }
        Recargable r = (Recargable) v;
        if (!r.necesitaRecarga()) {
            return false;
        }
        int bateriaPrevia = v.getNivel_bat();
        r.recargar();
        try {
            persistirVehiculo(v); // la recarga ahora tambien se guarda en el archivo
        } catch (IOException e) {
            v.setNivel_bat(bateriaPrevia); // se deshace el cambio en memoria
            throw e;
        }
        return true;
    }

    public List<VehiculoAutonomo> vehiculosQueNecesitanRecarga() {
        List<VehiculoAutonomo> lista = new ArrayList<>();
        for (VehiculoAutonomo v : ABB.inOrden()) {
            if (v instanceof Recargable && ((Recargable) v).necesitaRecarga()) {
                lista.add(v);
            }
        }
        return lista;
    }

}

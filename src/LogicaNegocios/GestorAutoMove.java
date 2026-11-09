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

    private ArbolABB<VehiculoAutonomo> ABB;
    private Cola<Mision> mPendientes;
    private Cola<Mision> mAsig_y_Final;
    private boolean vehiculosRecuperados = false;
    private boolean misionesRecuperadas = false;

    public GestorAutoMove() {
        ABB = new ArbolABB<>();
        mPendientes = new Cola<>();
        mAsig_y_Final = new Cola<>();
    }

    public boolean cargarVehiculosExistentes() {
        try {
            try (ArchivoVehiculos archivo = new ArchivoVehiculos("VEHICULOS.DAT")) {
                ConversorVehiculos conversor = new ConversorVehiculos();
                Robot r = new Robot();
                r.validarDatosBase("01", "Terminator", 80, 50, 120.5, 1);
                r.validarDatosRobot(1, 16.0);
                DronAereo d = new DronAereo();
                d.validarDatosBase("02", "Phantom", 90, 10, 45.0, 1);
                d.validarDatosDron(25.0, 120.0);
                VehiculoElectrico ve = new VehiculoElectrico();
                ve.validarDatosBase("03", "TeslaBot", 100, 200, 300.0, 1);
                ve.validarDatosElectrico(400.0, "2 horas");
                VehiculoAutonomo[] iniciales = {r, d, ve};
                for (VehiculoAutonomo v : iniciales) {
                    RegistroVehiculo registro = conversor.aRegistro(v);
                    if (archivo.buscar(registro.getCodigo()) != null) {
                        return false;
                    }
                    archivo.guardar(registro);

                    if (ABB.buscar(v) == null) {
                        ABB.insertar(v);
                    }
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
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
        VehiculoAutonomo vehiculo = buscarVehiculo(codigo);
        if (vehiculo == null || vehiculo.getEstado() != 1) {
            return false;
        }
        Mision mision = mPendientes.consultarFrente();
        if (!vehiculo.puedeRealizarMision(mision)) {
            return false;
        }
        if (!actualizarEstadoVehiculo(codigo)) {
            return false;
        }
        mision.cambiarEstado(2);
        ConversorMisiones cm = new ConversorMisiones();
        try (ArchivoMisiones archivo = new ArchivoMisiones("MISIONES.DAT")) {
            archivo.modificar(cm.aRegistro(mision));
        }
        mPendientes.desencolar();
        mAsig_y_Final.encolar(mision);
        return true;
    }

    public List<Mision> mostrarMisiones() throws IOException {
        List<Mision> misiones = new ArrayList<>();
        ConversorMisiones conversor = new ConversorMisiones();
        try (ArchivoMisiones archivo = new ArchivoMisiones("MISIONES.DAT")) {
            for (RegistroMisiones r : archivo.recuperarActivos()) {
                misiones.add(conversor.aMision(r));
            }
        }
        return misiones;
    }

    public boolean actualizarEstadoVehiculo(String codigo) throws IOException, VehiculoNoEncontradoException {
        VehiculoAutonomo vehiculo = buscarVehiculo(codigo);
        if (vehiculo == null) {
            return false;
        }
        vehiculo.actualizarEstado(2);
        ConversorVehiculos conversor = new ConversorVehiculos();
        RegistroVehiculo registro = conversor.aRegistro(vehiculo);

        try (ArchivoVehiculos archivo = new ArchivoVehiculos("VEHICULOS.DAT")) {
            archivo.modificar(registro);
        }
        return true;
    }

    public void guardarVehiculos() throws IOException {
        try (ArchivoVehiculos archivo = new ArchivoVehiculos("VEHICULOS.DAT")) {
            ConversorVehiculos conversor = new ConversorVehiculos();
            for (VehiculoAutonomo v : ABB.inOrden()) {
                RegistroVehiculo registro = conversor.aRegistro(v);
                archivo.guardar(registro);
            }
        }
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
        ConversorMisiones conversor = new ConversorMisiones();
        try (ArchivoMisiones archivo = new ArchivoMisiones("MISIONES.DAT")) {
            for (RegistroMisiones r : archivo.recuperarActivos()) {
                Mision m = conversor.aMision(r);
                if (m.getEstado() == 1) {
                    mPendientes.encolar(m);
                } else {
                    mAsig_y_Final.encolar(m);
                }
            }
        }
        misionesRecuperadas = true;
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

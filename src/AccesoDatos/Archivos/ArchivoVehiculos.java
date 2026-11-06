package AccesoDatos.Archivos;
import java.io.IOException;
import java.util.List;

/**
 * Ejemplo de adaptación del archivo genérico al registro de vehículos.
 */
public class ArchivoVehiculos implements AutoCloseable {

    private final ArchivoAccesoDirecto<RegistroVehiculo> archivoDatos;

    public ArchivoVehiculos(String ruta) throws IOException {
        archivoDatos = new ArchivoAccesoDirecto<>(ruta, RegistroVehiculo.LONGITUD, RegistroVehiculo::new);
    }

    public void guardar(RegistroVehiculo r) throws IOException {
        archivoDatos.agregar(r);
    }

    public RegistroVehiculo buscar(int codigo) throws IOException {
        return archivoDatos.buscarPorCodigo(codigo);
    }

    public void modificar(RegistroVehiculo r) throws IOException {
        archivoDatos.modificar(r);
    }

    public void eliminar(int codigo) throws IOException {
        archivoDatos.bajaLogica(codigo);
    }

    public List<RegistroVehiculo> recuperarActivos() throws IOException {
        return archivoDatos.listarActivos();
    }

    @Override
    public void close() throws IOException {
        archivoDatos.close();
    }
}

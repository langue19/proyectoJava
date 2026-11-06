package AccesoDatos.Archivos;
import java.io.IOException;
import java.util.List;

public class ArchivoMisiones implements AutoCloseable {

    private final ArchivoAccesoDirecto<RegistroMisiones> archivoDatos;

    public ArchivoMisiones(String ruta) throws IOException {
        archivoDatos = new ArchivoAccesoDirecto<>(ruta, RegistroMisiones.LONGITUD, RegistroMisiones::new);
    }

    public void guardar(RegistroMisiones r) throws IOException {
        archivoDatos.agregar(r);
    }

    public RegistroMisiones buscar(int codigo) throws IOException {
        return archivoDatos.buscarPorCodigo(codigo);
    }

    public void modificar(RegistroMisiones r) throws IOException {
        archivoDatos.modificar(r);
    }

    public void eliminar(int codigo) throws IOException {
        archivoDatos.bajaLogica(codigo);
    }

    public List<RegistroMisiones> recuperarActivos() throws IOException {
        return archivoDatos.listarActivos();
    }

    @Override
    public void close() throws IOException {
        archivoDatos.close();
    }
}

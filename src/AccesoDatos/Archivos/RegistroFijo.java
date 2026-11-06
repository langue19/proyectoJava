package AccesoDatos.Archivos;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Contrato de un registro binario de longitud fija.
 */
public interface RegistroFijo {

    byte LIBRE = 0, ACTIVO = 1, BAJA = 2;

    int getCodigo();

    byte getEstado();

    void setEstado(byte estado);

    int getLongitud();

    default void validar() {
    }

    void escribir(RandomAccessFile archivo) throws IOException;

    void leer(RandomAccessFile archivo) throws IOException;
}

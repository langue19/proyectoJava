package AccesoDatos.Archivos;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Acceso directo POR POSICION; búsqueda por código mediante recorrido
 * secuencial.
 */
public class ArchivoAccesoDirecto<T extends RegistroFijo> implements AutoCloseable {

    private final RandomAccessFile archivo;
    private final int longitudRegistro;
    private final Supplier<T> fabricaRegistros;

    public ArchivoAccesoDirecto(String ruta, int longitudRegistro, Supplier<T> fabricaRegistros) throws IOException {
        if (longitudRegistro <= 0 || fabricaRegistros == null) {
            throw new IllegalArgumentException("Configuración inválida");
        }
        this.longitudRegistro = longitudRegistro;
        this.fabricaRegistros = fabricaRegistros;
        this.archivo = new RandomAccessFile(ruta, "rw");
        if (archivo.length() % longitudRegistro != 0) {
            archivo.close();
            throw new IOException("Longitud de archivo incompatible con registros de " + longitudRegistro + " bytes");
        }
    }

    public long cantidadRegistros() throws IOException {
        return archivo.length() / longitudRegistro;
    }

    public void posicionar(long numeroRegistro) throws IOException {
        if (numeroRegistro < 0) {
            throw new IllegalArgumentException("Posición negativa");
        }
        archivo.seek(Math.multiplyExact(numeroRegistro, (long) longitudRegistro));
    }

    public T leerRegistro(long posicion) throws IOException {
        if (posicion < 0 || posicion >= cantidadRegistros()) {
            throw new IOException("Posición inexistente: " + posicion);
        }
        posicionar(posicion);
        T registro = fabricaRegistros.get();
        if (registro.getLongitud() != longitudRegistro) {
            throw new IOException("Longitud de registro incompatible");
        }
        long inicio = archivo.getFilePointer();
        registro.leer(archivo);
        if (archivo.getFilePointer() - inicio != longitudRegistro) {
            throw new IOException("Lectura de longitud incorrecta");
        }
        return registro;
    }

    public void escribirRegistro(long posicion, T registro) throws IOException {
        if (posicion < 0 || posicion > cantidadRegistros()) {
            throw new IOException("Posición inválida: " + posicion);
        }
        if (registro.getLongitud() != longitudRegistro) {
            throw new IOException("Longitud de registro incompatible");
        }
        registro.validar();
        posicionar(posicion);
        long inicio = archivo.getFilePointer();
        registro.escribir(archivo);
        if (archivo.getFilePointer() - inicio != longitudRegistro) {
            throw new IOException("Escritura de longitud incorrecta");
        }
    }

    public long buscarPosicionActiva(int codigo) throws IOException {
        for (long i = 0; i < cantidadRegistros(); i++) {
            T r = leerRegistro(i);
            if (r.getCodigo() == codigo && r.getEstado() == RegistroFijo.ACTIVO) {
                return i;
            }
        }
        return -1;
    }

    public T buscarPorCodigo(int codigo) throws IOException {
        long p = buscarPosicionActiva(codigo);
        return p < 0 ? null : leerRegistro(p);
    }

    public void agregar(T registro) throws IOException {
        if (registro.getCodigo() <= 0) {
            throw new IllegalArgumentException("El código debe ser positivo");
        }
        if (buscarPosicionActiva(registro.getCodigo()) >= 0) {
            throw new IllegalArgumentException("Código activo duplicado: " + registro.getCodigo());
        }
        // Se reutilizan posiciones de baja o libres; los códigos dados de baja pueden volver a usarse.
        long destino = cantidadRegistros();
        for (long i = 0; i < cantidadRegistros(); i++) {
            if (leerRegistro(i).getEstado() != RegistroFijo.ACTIVO) {
                destino = i;
                break;
            }
        }
        registro.setEstado(RegistroFijo.ACTIVO);
        escribirRegistro(destino, registro);
    }

    public void modificar(T registro) throws IOException {
        long p = buscarPosicionActiva(registro.getCodigo());
        if (p < 0) {
            throw new IllegalArgumentException("Registro activo inexistente: " + registro.getCodigo());
        }
        registro.setEstado(RegistroFijo.ACTIVO);
        escribirRegistro(p, registro);
    }

    public void bajaLogica(int codigo) throws IOException {
        long p = buscarPosicionActiva(codigo);
        if (p < 0) {
            throw new IllegalArgumentException("Registro activo inexistente: " + codigo);
        }
        T r = leerRegistro(p);
        r.setEstado(RegistroFijo.BAJA);
        escribirRegistro(p, r);
    }

    public List<T> listarActivos() throws IOException {
        List<T> salida = new ArrayList<>();
        for (long i = 0; i < cantidadRegistros(); i++) {
            T r = leerRegistro(i);
            if (r.getEstado() == RegistroFijo.ACTIVO) {
                salida.add(r);
            }
        }
        return salida;
    }

    @Override
    public void close() throws IOException {
        archivo.close();
    }
}

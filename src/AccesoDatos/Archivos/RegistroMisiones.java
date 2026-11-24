package AccesoDatos.Archivos;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RegistroMisiones implements RegistroFijo {

    /** 1+4+60+60+4+8+1+1+4 = 143 bytes. */
    public static final int LONGITUD = 143;
    private byte estadoRegistro = ACTIVO;
    private int codigo;
    private String origen;
    private String destino;
    private int distancia;
    private double peso;
    private byte prioridad;
    private byte estadoMision;
    /** Codigo del vehiculo asignado; 0 = sin vehiculo asignado. */
    private int codigoVehiculo;

    public RegistroMisiones() {
    }

    public RegistroMisiones(int codigo, String origen, String destino,
            int distancia, double peso, byte prioridad, byte estadoMision, int codigoVehiculo) {

        this.codigo = codigo;
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
        this.peso = peso;
        this.prioridad = prioridad;
        this.estadoMision = estadoMision;
        this.codigoVehiculo = codigoVehiculo;
    }

    @Override
    public int getCodigo() {
        return codigo;
    }

    @Override
    public byte getEstado() {
        return estadoRegistro;
    }

    @Override
    public void setEstado(byte estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    @Override
    public int getLongitud() {
        return LONGITUD;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public int getDistancia() {
        return distancia;
    }

    public double getPeso() {
        return peso;
    }

    public byte getPrioridad() {
        return prioridad;
    }

    public byte getEstadoMision() {
        return estadoMision;
    }

    public int getCodigoVehiculo() {
        return codigoVehiculo;
    }

    @Override
    public void validar() {
        if (codigo <= 0) {
            throw new IllegalArgumentException("Codigo de mision invalido");
        }

        if (origen == null || origen.length() > 30
                || destino == null || destino.length() > 30) {
            throw new IllegalArgumentException("Origen o destino invalido");
        }

        if (distancia <= 0 || peso < 0
                || prioridad < 1 || prioridad > 3
                || estadoMision < 1 || estadoMision > 3
                || codigoVehiculo < 0) {
            throw new IllegalArgumentException("Datos de mision invalidos");
        }
    }

    @Override
    public void escribir(RandomAccessFile a) throws IOException {
        a.writeByte(estadoRegistro);
        a.writeInt(codigo);
        TextoFijo.escribir(a, origen, 30);
        TextoFijo.escribir(a, destino, 30);
        a.writeInt(distancia);
        a.writeDouble(peso);
        a.writeByte(prioridad);
        a.writeByte(estadoMision);
        a.writeInt(codigoVehiculo);
    }

    @Override
    public void leer(RandomAccessFile a) throws IOException {
        estadoRegistro = a.readByte();
        codigo = a.readInt();
        origen = TextoFijo.leer(a, 30);
        destino = TextoFijo.leer(a, 30);
        distancia = a.readInt();
        peso = a.readDouble();
        prioridad = a.readByte();
        estadoMision = a.readByte();
        codigoVehiculo = a.readInt();
    }

    @Override
    public String toString() {
        return "Mision " + codigo + " | " + origen + " -> " + destino;
    }
}

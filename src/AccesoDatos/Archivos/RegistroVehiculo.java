package AccesoDatos.Archivos;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Formato de VEHICULOS.DAT. Total: 147 bytes.
 */
public class RegistroVehiculo implements RegistroFijo {

    public static final int LONGITUD = 147;
    public static final byte DRON = 1, ROBOT = 2, ELECTRICO = 3;
    private byte estado = ACTIVO;
    private int codigo;
    private byte tipoVehiculo;
    private String modelo = "";
    private double nivelBateria, capacidadCarga, kilometrosRecorridos;
    private byte estadoOperativo;
    private double datoNumerico1, datoNumerico2;
    private String datoTexto = "";

    public RegistroVehiculo() {
    }

    public RegistroVehiculo(int codigo, byte tipoVehiculo, String modelo, double nivelBateria,
            double capacidadCarga, double kilometrosRecorridos, byte estadoOperativo,
            double datoNumerico1, double datoNumerico2, String datoTexto) {
        this.codigo = codigo;
        this.tipoVehiculo = tipoVehiculo;
        this.modelo = modelo;
        this.nivelBateria = nivelBateria;
        this.capacidadCarga = capacidadCarga;
        this.kilometrosRecorridos = kilometrosRecorridos;
        this.estadoOperativo = estadoOperativo;
        this.datoNumerico1 = datoNumerico1;
        this.datoNumerico2 = datoNumerico2;
        this.datoTexto = datoTexto;
    }

    @Override
    public int getCodigo() {
        return codigo;
    }

    @Override
    public byte getEstado() {
        return estado;
    }

    @Override
    public void setEstado(byte estado) {
        this.estado = estado;
    }

    @Override
    public int getLongitud() {
        return LONGITUD;
    }

    public byte getTipoVehiculo() {
        return tipoVehiculo;
    }

    public String getModelo() {
        return modelo;
    }

    public double getNivelBateria() {
        return nivelBateria;
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    public double getKilometrosRecorridos() {
        return kilometrosRecorridos;
    }

    public byte getEstadoOperativo() {
        return estadoOperativo;
    }

    public double getDatoNumerico1() {
        return datoNumerico1;
    }

    public double getDatoNumerico2() {
        return datoNumerico2;
    }

    public String getDatoTexto() {
        return datoTexto;
    }

    @Override
    public void validar() {
        if (codigo <= 0 || tipoVehiculo < DRON || tipoVehiculo > ELECTRICO) {
            throw new IllegalArgumentException("Código o tipo inválido");
        }
        if (modelo == null || modelo.length() > 30 || datoTexto == null || datoTexto.length() > 20) {
            throw new IllegalArgumentException("Longitud de texto inválida");
        }
    }

    @Override
    public void escribir(RandomAccessFile a) throws IOException {
        a.writeByte(estado);
        a.writeInt(codigo);
        a.writeByte(tipoVehiculo);
        TextoFijo.escribir(a, modelo, 30);
        a.writeDouble(nivelBateria);
        a.writeDouble(capacidadCarga);
        a.writeDouble(kilometrosRecorridos);
        a.writeByte(estadoOperativo);
        a.writeDouble(datoNumerico1);
        a.writeDouble(datoNumerico2);
        TextoFijo.escribir(a, datoTexto, 20);
    }

    @Override
    public void leer(RandomAccessFile a) throws IOException {
        estado = a.readByte();
        codigo = a.readInt();
        tipoVehiculo = a.readByte();
        modelo = TextoFijo.leer(a, 30);
        nivelBateria = a.readDouble();
        capacidadCarga = a.readDouble();
        kilometrosRecorridos = a.readDouble();
        estadoOperativo = a.readByte();
        datoNumerico1 = a.readDouble();
        datoNumerico2 = a.readDouble();
        datoTexto = TextoFijo.leer(a, 20);
    }

    @Override
    public String toString() {
        return "Vehículo " + codigo + " (tipo " + tipoVehiculo + ", modelo " + modelo + ")";
    }
}

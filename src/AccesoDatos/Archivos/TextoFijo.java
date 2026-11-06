package AccesoDatos.Archivos;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Cada carácter ocupa 2 bytes (writeChar/readChar).
 */
public final class TextoFijo {

    private TextoFijo() {
    }

    public static void escribir(RandomAccessFile a, String valor, int caracteres) throws IOException {
        if (valor == null) {
            valor = "";
        }
        if (valor.length() > caracteres) {
            throw new IllegalArgumentException("Texto supera " + caracteres + " caracteres");
        }
        for (int i = 0; i < caracteres; i++) {
            a.writeChar(i < valor.length() ? valor.charAt(i) : '\0');
        }
    }

    public static String leer(RandomAccessFile a, int caracteres) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < caracteres; i++) {
            char c = a.readChar();
            if (c != '\0') {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}

package Presentacion;

import java.util.Scanner;

public class Consola {

    private static Scanner sc = new Scanner(System.in);

    public static int leerInt() {
        while (true) {
            try {
                return Integer.parseInt(leerString().trim());
            } catch (NumberFormatException e) {
                System.out.println("No es un integer. Por favor, pruebe otra vez!");
            }
        }
    }

    public static double leerDouble() {
         while (true) {
            try {
                return Double.parseDouble(leerString().trim());
            } catch (NumberFormatException e) {
                System.out.println("No es un flotante. Por favor, pruebe otra vez!: ");
            }
        }
    }

    public static String leerString() {
        int ch;
        String r = "";
        boolean done = false;
        while (!done) {
            try {
                ch = System.in.read();
                if (ch < 0 || (char) ch == '\n') {
                    done = true;
                } else {
                    if ((char) ch != '\r') {
                        r = r + (char) ch;
                    }
                }
            } catch (java.io.IOException e) {
                done = true;
            }
        }
        return r;
    }

    /* Lee una opcion numerica y valida que sea una de las permitidas.*/
    public static int leerOpcion(String mensaje, int... opcionesValidas) {
        System.out.print(mensaje);
        int valor = sc.nextInt();
        while (!esOpcionValida(valor, opcionesValidas)) {
            System.out.print("ERROR: opcion invalida, ingrese nuevamente: ");
            valor = sc.nextInt();
        }
        sc.nextLine();
        return valor;
    }

    private static boolean esOpcionValida(int valor, int[] opciones) {
        for (int op : opciones) {
            /*op recorre el vector hasta encontrar uno que coincida*/
            if (op == valor) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Emite mensaje y nueva linea
     *
     * @param mensaje
     */
    public static void emitirMensajeln(String mensaje) {
        System.out.println(mensaje);
    }

    /**
     * Emite mensaje sin nueva linea
     *
     * @param mensaje
     */
    public static void emitirMensaje(String mensaje) {
        System.out.print(mensaje);
    }
}

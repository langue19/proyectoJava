/*
 Capa VISTA: entrada y salida por consola.
 */
package Vista;

import java.util.Scanner;

public class Consola {

    private static Scanner sc = new Scanner(System.in);

    public static void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    public static int leerInt(String mensaje) {
        System.out.print(mensaje);
        int valor = sc.nextInt();
        while (valor <= 0) {
            System.out.print("ERROR: ingrese un numero entero positivo: ");
            valor = sc.nextInt();
        }
        sc.nextLine(); // consume el salto de linea pendiente
        return valor;
    }

    public static double leerDouble(String mensaje) {
        System.out.print(mensaje);
        double valor = sc.nextDouble();
        while (valor <= 0 || valor >= 100000) {
            System.out.print("ERROR: ingrese un numero valido (mayor a 0): ");
            valor = sc.nextDouble();
        }
        sc.nextLine(); // consume el salto de linea pendiente
        return valor;
    }

    public static String leerString(String mensaje) {
        System.out.print(mensaje);
        String valor = sc.nextLine();
        while (valor.trim().isEmpty()) {
            System.out.print("ERROR: ingrese un texto valido: ");
            valor = sc.nextLine();
        }
        return valor;
    }

    /* Lee una opcion numerica y valida que sea una de las permitidas.*/
     
    public static int leerOpcion(String mensaje, int...opcionesValidas) {
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
        for (int op : opciones) {  /*op recorre el vector hasta encontrar uno que coincida*/
            if (op == valor) {
                return true;
            }
        }
        return false;
    }
}

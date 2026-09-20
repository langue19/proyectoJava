package Presentacion;

import LogicaNegocios.*;
import AccesoDatos.*;
import java.util.Scanner;

public class MenuAutoMove {

    Scanner sc = new Scanner(System.in);

    public void iniciar() {
        System.out.println("BIENVENIDO USUARIO! ");
        int opcion = 10;
        do {
            leerOpcionMenu();
            switch (opcion) {
                case 1:
                    registrarVehiculo();
                    break;
                case 2:
                    registrarMision();
                    break;
                case 3:
                    mostrarListaVehiculos(vehiculos);
                    break;
                case 4:
                    verificarDisponibilidad();
                    break;
                case 5:
                    asignarMision();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
            }
        } while (opcion != 0);
    }

    private VehiculoAutonomo registrarVehiculo() {
        int tipo = leerTipoVehiculo();
        VehiculoAutonomo vehiculo = null;
        switch (tipo) {
            case 1:
                vehiculo = pedirDatosRobot();
                break;
            case 2:
                vehiculo = pedirDatosDron();
                break;
            case 3:
                vehiculo = pedirDatosVehiculoElectrico();
                break;
        }
        System.out.println("Vehiculo registrado correctamente.");
        return vehiculo;
    }

    private void registrarMision() {

    }

    private void verificarDisponibilidad() {

    }

    private void asignarMision() {

    }

    public void mostrarMenu() {
        System.out.println("1 - Registrar vehiculos de los diferentes tipos previstos."
                + "\n2 - Registrar misiones de traslado."
                + "\n3 - Consultar y mostrar la informacion de los vehiculos registrados."
                + "\n4 - Determinar si un vehiculo se encuentra en condiciones de realizar una mision."
                + "\n5 - Asignar una mision a un vehiculo disponible y actualizar los estados correspondientes."
                + "\n0 - Salir.");
    }

    public int leerOpcionMenu() {
        return Consola.leerOpcion("Ingrese una opcion: ", 0, 1, 2, 3, 4, 5);
    }

    public int leerTipoVehiculo() {
        System.out.println("Seleccione el tipo de vehiculo:"
                + "\n1 - Robot"
                + "\n2 - Dron aereo"
                + "\n3 - Vehiculo electrico");
        return Consola.leerOpcion("----> ", 1, 2, 3);
    }

    // --- Recoleccion de datos---
    public Robots pedirDatosRobot() {
        Robots r = new Robots();
        r.setDatosBase(
                Consola.leerString("Codigo de identificacion: "),
                Consola.leerString("Modelo: "),
                Consola.leerInt("Bateria: "),
                Consola.leerInt("Capacidad maxima de bateria: "),
                Consola.leerDouble("Kilometros recorridos: "),
                Consola.leerString("Estado: "));
        int terreno = Consola.leerOpcion(
                "Tipo de terreno:\n 1-Asfalto\n 2-Mixto\n 3-Arena\n----> ", 1, 2, 3);
        double velMax = Consola.leerDouble("Velocidad maxima: ");
        r.setearDatosRobot(terreno, velMax);
        return r;
    }

    public Drones_aereos pedirDatosDron() {
        Drones_aereos d = new Drones_aereos();
        Double autonomia;
        Double alturaMax;
        d.setDatosBase(
                Consola.leerString("Codigo de identificacion: "),
                Consola.leerString("Modelo: "),
                Consola.leerInt("Bateria: "),
                Consola.leerInt("Capacidad maxima de bateria: "),
                Consola.leerDouble("Kilometros recorridos: "),
                Consola.leerString("Estado: "));
        do {
            autonomia = Consola.leerDouble("Autonomia de vuelo: ");
            if (!d.validarAutonomia(autonomia)) {
                System.out.println("Error: La autonomía ingresada está fuera del rango permitido.");
            }
        } while (!d.validarAutonomia(autonomia));

        do {
            alturaMax = Consola.leerDouble("Altura máxima: ");
            if (!d.validarAltura(alturaMax)) {
                System.out.println("Error: La altura máxima está fuera del rango permitido.");
            }
        } while (!d.validarAltura(alturaMax));
        d.setDatosDron(autonomia, alturaMax);
        return d;
    }

    public VehiculoElect pedirDatosVehiculoElectrico() {
        VehiculoElect v = new VehiculoElect();
        v.setDatosBase(
                Consola.leerString("Codigo de identificacion: "),
                Consola.leerString("Modelo: "),
                Consola.leerInt("Bateria: "),
                Consola.leerInt("Capacidad maxima de bateria: "),
                Consola.leerDouble("Kilometros recorridos: "),
                Consola.leerString("Estado: "));
        double autonomiaKm = Consola.leerDouble("Autonomia en kilometros: ");
        String tiempoRecarga = Consola.leerString("Tiempo de recarga: ");
        v.setDatosElectrico(autonomiaKm, tiempoRecarga);
        return v;
    }

    public Mision pedirDatosMision() {
        Mision m = new Mision();
        int estado = Consola.leerOpcion(
                "Estado de la mision:\n 1-Pendiente\n 2-Asignado\n 3-Finalizado\n----> ", 1, 2, 3);
        m.setDatos(
                Consola.leerString("Codigo de mision: "),
                Consola.leerString("Origen: "),
                Consola.leerString("Destino: "),
                Consola.leerString("Distancia: "),
                Consola.leerString("Peso: "),
                Consola.leerString("Prioridad: "),
                estado);
        return m;
    }

    // --- Presentacion de resultados --- ESTO NO VA AQUI
    public void mostrarListaVehiculos(ListaS lista) {
        if (lista.listaVacia()) {
            System.out.println("No hay vehiculos registrados.");
            return;
        }
        Nodo p = lista.inicio();
        while (p != null) {
            VehiculoAutonomo v = (VehiculoAutonomo) p.getDato();
            System.out.println(v);
            /*<---------Preguntar si usar toString*/
            p = p.getPs();
        }
    }

}

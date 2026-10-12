package Presentacion;

import LogicaNegocios.*;

public class MenuAutoMove {

    GestorAutoMove gs = new GestorAutoMove();
    GestorGRAFO gsG = new GestorGRAFO();
    
    public void iniciar() {
        System.out.println("----------------------------BIENVENIDO USUARIO!----------------------------");
        int opcion = 10;
        do {
            System.out.println("\n");
            mostrarMenu();
            opcion = leerOpcionMenu();
            switch (opcion) {
                case 1:
                    registrarVehiculo();
                    break;
                case 2:
                    registrarMision();
                    break;
                case 3:
                    gs.consultarVehiculos();
                    break;
                case 4:
                    verificarDisponibilidad();
                    break;
                case 5:
                    asignarMision();
                    break;
                case 6:
                    manejarMenuEstaciones();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
            }
        } while (opcion != 0);
    }

    private void registrarVehiculo() {
        System.out.println("____REGISTRAR VEHICULO____");
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
        gs.registrarVehiculo(vehiculo);
    }

    private void registrarMision() {
        System.out.println("____REGISTRAR MISION____");
        System.out.println(" ");
        gs.registrarMision(pedirDatosMision());
        System.out.println("Mision registrada correctamente.");
    }

    private void verificarDisponibilidad() {
        System.out.println("_____VEHICULOS DISPONIBLES____");
        System.out.println(" ");
        gs.vehiculoDisponible();
    }

    private void asignarMision() {
        if (gs.vehiculoDisponible()) {
            System.out.println("_____ASIGNAR MISION____");
            System.out.println(" ");
            String codigo = Consola.leerString("Ingresa el código del vehiculo para asignarle la misión: ");
            gs.asignarVehiculo(codigo);
        }

    }

    public void mostrarMenuEstaciones() {
        System.out.println("1. Crear la red\n"
                + "2. Registrar estaciones\n"
                + "3. Registrar conexiones\n"
                + "4. Mostrar la red\n"
                + "5. Consultar conexion directa\n"
                + "6. Consultar estaciones vecinas\n"
                + "7. Recorrer las estaciones\n");
    }

    private void manejarMenuEstaciones() {
        int opcionEst;
        do {
            System.out.println("\n_____MENU ESTACIONES_____");
            mostrarMenuEstaciones();
            System.out.println("0. Volver al menu principal");
            opcionEst = Consola.leerOpcion("Ingrese una opcion: ", 0, 1, 2, 3, 4, 5, 6, 7);
            
            switch (opcionEst) {
                case 1:
                    // gsG.crearRed();
                    break;
                case 2:
                    // gsG.registrarEstacion(...);
                    break;
                case 3:
                    // gsG.registrarConexion(...);
                    break;
                case 4:
                    // gsG.mostrarRed();
                    break;
                case 5:
                    // gsG.consultarConexionDirecta();
                    break;
                case 6:
                    // gsG.consultarEstacionesVecinas();
                    break;
                case 7:
                    // gsG.recorrerEstaciones();
                    break;
                case 0:
                    System.out.print("\n\n----------------------------BIENVENIDO USUARIO!----------------------------");
                    break;
            }
        } while (opcionEst != 0);
    }
    
    
    public void mostrarMenu() {
        System.out.println("1 - Registrar vehiculos de los diferentes tipos previstos."
                + "\n2 - Registrar misiones de traslado."
                + "\n3 - Consultar y mostrar la informacion de los vehiculos registrados."
                + "\n4 - Determinar si un vehiculo se encuentra en condiciones de realizar una mision."
                + "\n5 - Asignar una mision a un vehiculo disponible y actualizar los estados correspondientes."
                + "\n6 - Estaciones."
                + "\n0 - Salir.");
    }

    public int leerOpcionMenu() {
        return Consola.leerOpcion("Ingrese una opcion: ", 0, 1, 2, 3, 4, 5,6);
    }

    public int leerTipoVehiculo() {
        System.out.println("Seleccione el tipo de vehiculo:"
                + "\n1 - Robot"
                + "\n2 - Dron aereo"
                + "\n3 - Vehiculo electrico");
        return Consola.leerOpcion("----> ", 1, 2, 3);
    }

    // --- Recoleccion de datos---
    private void pedirDatosBase(VehiculoAutonomo v) {
        v.setDatosBase(
                Consola.leerString("Codigo de identificacion: "),
                Consola.leerString("Modelo: "),
                Consola.leerInt("Bateria: "),
                Consola.leerInt("Capacidad maxima de bateria: "),
                Consola.leerDouble("Kilometros recorridos: "),
                Consola.leerOpcion("Estado:\n1-Activo \n2-En mision \n3-Mantenimiento\n--->", 1, 2, 3));
    }

    public Robot pedirDatosRobot() {
        Robot r = new Robot();
        double velMax;
        pedirDatosBase(r);
        int terreno = Consola.leerOpcion(
                "Tipo de terreno:\n1-Asfalto \n2-Mixto \n3-Arena \n----> ", 1, 2, 3);
        do {
            velMax = Consola.leerDouble("Velocidad maxima: ");
            if (!r.validarVelocidad(velMax)) {
                System.out.println("Error: La Velocidad máxima ingresada está fuera del rango permitido.");
            }
        } while (!r.validarVelocidad(velMax));
        r.setearDatosRobot(terreno, velMax);
        return r;
    }

    public Dron pedirDatosDron() {
        Dron d = new Dron();
        Double autonomia;
        Double alturaMax;
        pedirDatosBase(d);
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

    public VehiculoElectrico pedirDatosVehiculoElectrico() {
        VehiculoElectrico v = new VehiculoElectrico();
        double autonomiaKm;
        String tiempoRecarga;
        pedirDatosBase(v);
        do {
            autonomiaKm = Consola.leerDouble("Autonomia en kilometros: ");
            if (!v.validarAutonomia(autonomiaKm)) {
                System.out.println("Error: La autonomía ingresada está fuera del rango permitido.");
            }
        } while (!v.validarAutonomia(autonomiaKm));
        tiempoRecarga = Consola.leerString("Tiempo de recarga: ");
        v.setDatosElectrico(autonomiaKm, tiempoRecarga);
        return v;
    }

    public Mision pedirDatosMision() {
        Mision m = new Mision();
        int estado = Consola.leerOpcion(
                "Estado de la mision:\n 1-Pendiente\n 2-Asignado\n 3-Finalizado\n----> ", 1, 2, 3);
        int prio = Consola.leerOpcion(
                "Prioridad: \n1-Alta \n2-Media \n3-Baja---->", 1, 2, 3);
        m.setDatos(
                Consola.leerString("Codigo de mision: "),
                Consola.leerString("Origen: "),
                Consola.leerString("Destino: "),
                Consola.leerInt("Distancia: "),
                Consola.leerDouble("Peso: "),
                prio, estado);
        return m;
    }
}

package Presentacion;

import LogicaNegocios.*;

public class MenuAutoMove {

    GestorAutoMove gs = new GestorAutoMove();
    GestorRed gsG = new GestorRed();

    public void iniciar() {
        System.out.println("----------------------------BIENVENIDO USUARIO!----------------------------");
        int opcion = 10;
        do {
            System.out.println("\n");
            mostrarMenu();
            opcion = leerOpcionMenu();
            switch (opcion) {
                case 1:
                    if (gs.cargarVehiculosExistentes()) {
                        System.out.println("Vehiculos cargados correctamente!");

                    } else {
                        System.out.println("Los vehiculos no fueron cargados!");
                    }
                    break;
                case 2:
                    registrarVehiculo();
                    break;
                case 3:
                    //hay que agregar que pueda buscar otros
                    if (gs.buscarVehiculo("DRN-002") != null) {
                        System.out.println(gs.buscarVehiculo("DRN-002"));
                    } else {
                        System.out.println("No se encontro el vehiculo!");
                    }

                    //gs.consultarVehiculos(); //este pa verificar si se carga bien todo jeje
                    break;
                case 4:
                    if (gs.eliminarVehiculo("DRN-002")) {
                        System.out.println("Vehiculo eliminado correctamente!");
                    } else {
                        System.out.println("No se encontro el vehiculo!");
                    }
                    break;
                case 5:
                    ///gs.mostrarPre();
                    //gs.mostrarIn();
                    gs.mostrarPost();
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

    public void mostrarMenu() {
        System.out.println(""
                + "1 - Incorporar al ABB los vehiculos existentes en la aplicacion."
                + "\n2 - Registrar un nuevo vehiculo."
                + "\n3 - Buscar un vehiculo mediante clave."
                + "\n4 - Eliminar un vehiculo."
                + "\n5 - Mostrar vehiculos."
                + "\n6 - Red de Estaciones."
                + "\n0 - Salir.");
    }

    public void mostrarMenuEstaciones() {
        System.out.println("1. Crear red de Estaciones.\n"
                + "2. Registrar estaciones.\n"
                + "3. Registrar conexiones.\n"
                + "4. Mostrar la red.\n"
                + "5. Consultar conexion directa.\n"
                + "6. Consultar estaciones vecinas.\n"
                + "7. Recorrer las estaciones.\n");
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

    public int leerOpcionMenu() {
        return Consola.leerOpcion("Ingrese una opcion: ", 0, 1, 2, 3, 4, 5, 6);
    }

    private void registrarVehiculo() {
        System.out.println("____REGISTRAR VEHICULO____");
        int tipo = leerTipoVehiculo();
        boolean registradoConExito = false;

        // Bucle que se repite hasta que el vehículo se registre correctamente sin excepciones
        while (!registradoConExito) {
            try {
                VehiculoAutonomo vehiculo = null;
                // 1. Pedir los datos según el tipo 
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
                    default:
                        Consola.emitirMensajeln("Tipo de vehículo inválido.");
                        return;
                }

                // 2. Intentar registrar en el sistema (controla si ya existe)
                if (gs.registrarNuevoVehiculo(vehiculo)) {
                    Consola.emitirMensajeln("Vehículo registrado correctamente.");
                    registradoConExito = true; // Rompe el bucle porque tuvo éxito
                } else {
                    Consola.emitirMensajeln("Ya existe un vehículo con ese código. Por favor, ingrese los datos nuevamente.");
                }

            } catch (IllegalArgumentException e) {
                Consola.emitirMensajeln("Error en los datos: " + e.getMessage());
                Consola.emitirMensajeln("Por favor, vuelva a ingresar los datos del vehículo.\n");
            }
        }
    }

    public int leerTipoVehiculo() {
        System.out.println("Seleccione el tipo de vehiculo:"
                + "\n1 - Robot"
                + "\n2 - Dron aereo"
                + "\n3 - Vehiculo electrico");
        return Consola.leerOpcion("----> ", 1, 2, 3);
    }

    public void buscarV() {
        Consola.emitirMensaje("Ingrese el código del vehículo a buscar: ");
        try {
            String codigo = Consola.leerString();
        } catch (IllegalArgumentException e) {
            Consola.emitirMensajeln("Error en los datos: " + e.getMessage());
        }
        /*if (gs.buscarVehiculo(codigo) != null) {
            System.out.println();
        } else {
            System.out.println("No se encontro el vehiculo!");
        }*/
    }

    // --- Recoleccion de datos---
    private void pedirDatosBase(VehiculoAutonomo v) {
        Consola.emitirMensaje("Codigo de identificacion: ");
        String cod = Consola.leerString();
        Consola.emitirMensaje("Modelo: ");
        String modelo = Consola.leerString();
        Consola.emitirMensaje("Bateria: ");
        int bateria = Consola.leerInt();
        Consola.emitirMensaje("Capacidad maxima de carga: ");
        int capacidad = Consola.leerInt();
        Consola.emitirMensaje("Kilometros recorridos: ");
        Double km = Consola.leerDouble();
        Consola.emitirMensaje("Estado:\n1-Activo \n2-En mision \n3-Mantenimiento\n--->");
        int estado = Consola.leerInt();
        v.validarDatosBase(cod, modelo, bateria, capacidad, km, estado);
    }

    public Robot pedirDatosRobot() {
        Robot r = new Robot();
        pedirDatosBase(r);
        Consola.emitirMensajeln("Tipo de terreno:\n1-Asfalto \n2-Mixto \n3-Arena \n----> ");
        int terreno = Consola.leerInt();
        Consola.emitirMensaje("Velocidad máxima: ");
        double velMax = Consola.leerDouble();
        r.validarDatosRobot(terreno, velMax);
        return r;
    }

    public DronAereo pedirDatosDron() {
        DronAereo d = new DronAereo();
        pedirDatosBase(d);
        Consola.emitirMensaje("Autonomía de vuelo (km): ");
        double autonomia = Consola.leerDouble();
        Consola.emitirMensaje("Altura máxima (metros): ");
        double altura = Consola.leerDouble();
        d.validarDatosDron(autonomia, altura);
        return d;
    }

    public VehiculoElectrico pedirDatosVehiculoElectrico() {
        VehiculoElectrico v = new VehiculoElectrico();
        pedirDatosBase(v);
        Consola.emitirMensaje("Autonomía (km): ");
        double autonomia = Consola.leerDouble();
        Consola.emitirMensaje("Tiempo de recarga: ");
        String tiempoRecarga = Consola.leerString();
        v.validarDatosElectrico(autonomia, tiempoRecarga);
        return v;
    }

}

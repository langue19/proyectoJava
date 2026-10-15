package Presentacion;

import LogicaNegocios.*;
import java.util.List;

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
                    buscarV();
                    break;
                case 4:
                    eliminarV();
                    break;
                case 5:
                    mostrarV();
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
                    gsG.crearRed();
                    break;
                case 2:
                    int entrada;

                    do {
                        System.out.println("Ingrese el codigo de la estacion:");
                        String cod = Consola.leerString();

                        System.out.println("Ingrese el nombre de la estacion:");
                        String nombre = Consola.leerString();

                        System.out.println("Ingrese la zona:");
                        String zona = Consola.leerString();

                        if (gsG.registrarEstacion(cod, nombre, zona)) {
                            System.out.println("Estacion registrada correctamente.");
                        } else {
                            System.out.println("La estacion ya existe en la red.");
                        }

                        System.out.println("¿Desea continuar? 0-Sí ; 1-No");
                        entrada = Consola.leerInt();

                    } while (entrada == 0);
                    break;
                case 3:
                    System.out.println("Ingrese el codigo de la primera estacion:");
                    String cod1 = Consola.leerString();
                    System.out.println("Ingrese el codigo de la segunda estacion:");
                    String cod2 = Consola.leerString();
                    if (gsG.registrarConexion(cod1, cod2)) {
                        System.out.println("Conexion registrada!");
                    } else {
                        System.out.println("Conexion NO registrada!");
                    }
                    break;
                case 4:
                    /*if (gsG.mostrarEstaciones()) {
                        System.out.println("----------------ESTACIONES----------------");
                    } else {
                        System.out.println("No hay estaciones, por lo tanto, no hay conexiones!");
                    }

                    if (gsG.mostrarConexiones()) {
                        System.out.println("----------------CONEXIONES----------------");
                    }*/
                    break;
                case 5:
                    System.out.println("Codigo de la primera estacion:");
                    String cod11 = Consola.leerString();

                    System.out.println("Codigo de la segunda estacion:");
                    String cod22 = Consola.leerString();

                    if (gsG.consultarConexionDirecta(cod11, cod22)) {
                        System.out.println("La conexion entre las estaciones, existe!");
                    }
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
        Consola.emitirMensaje("Seleccione el tipo de vehiculo:"
                + "\n1 - Robot"
                + "\n2 - Dron aereo"
                + "\n3 - Vehiculo electrico");
        return Consola.leerOpcion("----> ", 1, 2, 3);
    }

    public void buscarV() {
        String codigo;
        Consola.emitirMensaje("Ingrese el código del vehículo a buscar: ");
        try {
            codigo = Consola.leerString();
            if (gs.buscarVehiculo(codigo) != null) {
                Consola.emitirMensajeln("Vehículo encontrado!");
                System.out.println(gs.buscarVehiculo(codigo));
            } else {
                System.out.println("Veniculo no encontrado");
            }
        } catch (IllegalArgumentException e) {
            Consola.emitirMensajeln("Error en los datos: " + e.getMessage());
        }
    }

    public void eliminarV() {
        String codigo;
        Consola.emitirMensaje("Ingrese el código del vehículo a eliminar: ");
        try {
            codigo = Consola.leerString();
            if (gs.eliminarVehiculo(codigo)) {
                Consola.emitirMensajeln("Vehiculo eliminado!");
            } else {
                Consola.emitirMensajeln("Vehiculo no encontrado");
            }
        } catch (IllegalArgumentException e) {
            Consola.emitirMensajeln("Error en los datos: " + e.getMessage());
        }
    }

    public void mostrarV() {
        int op = leerTipoRecorrido();
        List<VehiculoAutonomo> listaVehiculos = gs.mostrarVehiculo(op);
        if (listaVehiculos == null || listaVehiculos.isEmpty()) {
            Consola.emitirMensajeln("No hay vehículos registrados");
            return;
        }
        String tipoRecorrido = "";
        if (op == 1) {
            tipoRecorrido= "INORDEN (ASCENDENTE)";
        } else if (op == 2) {
            tipoRecorrido= "PREORDEN";
        } else if (op == 3) {
            tipoRecorrido= "POSTORDEN";
        }
        Consola.emitirMensajeln("\n===== RECORRIDO " + tipoRecorrido + " =====");

        for (VehiculoAutonomo v : listaVehiculos) {
            MostrarVehiculo(v);
        }
    }

    public int leerTipoRecorrido() {
        Consola.emitirMensaje("Seleccione una opcion: "
                + "\n 1. Mostrar vehículos en orden (Inorden-Ascendente)"
                + "\n 2. Mostrar vehículos en Preorden (Estructura jerárquica)"
                + "\n 3. Mostrar vehículos en Postorden");
        return Consola.leerOpcion("----> ", 1, 2, 3);
    }

    private void MostrarVehiculo(VehiculoAutonomo v) {
        String detalleEspecifico = "";

        if (v instanceof VehiculoElectrico) {
            VehiculoElectrico ve = (VehiculoElectrico) v;
            detalleEspecifico = " [Eléctrico | Autonomía: " + ve.getAutonomia_km() + " km]";
        } else if (v instanceof Robot) {
            Robot r = (Robot) v;
            detalleEspecifico = " [Robot | Vel. Máx: " + r.getVelocidad_max() + " km/h]";
        } else if (v instanceof DronAereo) {
            DronAereo d = (DronAereo) v;
            detalleEspecifico = " [Dron | Altura Máx: " + d.getAltura_max() + " m]";
        }
        Consola.emitirMensajeln(" [" + v.getCod_ident() + "] " + v.getModelo() + v.getNivel_bat() + v.getCapacidad_maxima() + v.getKm_reco() + v.getEstado() + detalleEspecifico);
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

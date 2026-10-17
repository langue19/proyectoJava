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
                case 7:
                    manejarMenuMisiones();
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
                + "\n7 - Misiones."
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
                    registrarEstacion();
                    break;
                case 3:
                    registrarConex();
                    break;
                case 4:
                    mostrarConexiones();
                    mostrarEstaciones();
                    break;
                case 5:
                    consultarConexiones();
                    break;
                case 6:
                    mostrarEstacionesVecinas();
                    break;
                case 7:
                    mostrarRecorridoDFS();
                    break;
                case 0:
                    System.out.print("\n\n----------------------------BIENVENIDO USUARIO!----------------------------");
                    break;
            }
        } while (opcionEst != 0);
    }

    private void manejarMenuMisiones() {
        int opcionEst;
        do {
            System.out.println("\n_____MENU MISIONES_____");
            mostrarMenuMision();
            System.out.println("0. Volver al menu principal");
            opcionEst = Consola.leerOpcion("Ingrese una opcion: ", 0, 1, 2, 3);

            switch (opcionEst) {
                case 1:
                    registrarMision();
                    break;
                case 2:
                    verificarDisponibilidad();
                    break;
                case 3:
                    asignarMision();
                    break;
                case 0:
                    System.out.print("\n\n----------------------------BIENVENIDO USUARIO!----------------------------");
                    break;
            }
        } while (opcionEst != 0);
    }

    public void mostrarMenuMision() {
        System.out.println("1. Registrar mision de traslado.\n"
                + "2. Determinar si un vehiculo se encuentra en condiciones.\n"
                + "3. Asignar una mision.\n");
    }

    public int leerOpcionMenu() {
        return Consola.leerOpcion("Ingrese una opcion: ", 0, 1, 2, 3, 4, 5, 6, 7);
    }

    private void registrarVehiculo() {
        System.out.println("____REGISTRAR VEHICULO____");
        int tipo = leerTipoVehiculo();
        boolean registradoConExito = false;

        while (!registradoConExito) {
            try {
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
                    default:
                        Consola.emitirMensajeln("Tipo de vehiculo invalido.");
                        return;
                }

                if (gs.registrarNuevoVehiculo(vehiculo)) {
                    Consola.emitirMensajeln("Vehiculo registrado correctamente.");
                    registradoConExito = true;
                } else {
                    Consola.emitirMensajeln("Ya existe un vehiculo con ese codigo. Por favor, ingrese los datos nuevamente.");
                }

            } catch (IllegalArgumentException e) {
                Consola.emitirMensajeln("Error en los datos: " + e.getMessage());
                Consola.emitirMensajeln("Por favor, vuelva a ingresar los datos del vehiculo.\n");
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

    public void eliminarV() {
        String codigo;
        Consola.emitirMensaje("Ingrese el codigo del vehículo a eliminar: ");
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
            tipoRecorrido = "INORDEN (ASCENDENTE)";
        } else if (op == 2) {
            tipoRecorrido = "PREORDEN";
        } else if (op == 3) {
            tipoRecorrido = "POSTORDEN";
        }
        Consola.emitirMensajeln("\n---------------------------- RECORRIDO " + tipoRecorrido + "----------------------------");

        for (VehiculoAutonomo v : listaVehiculos) {
            MostrarVehiculo(v);
        }
    }

    public int leerTipoRecorrido() {
        Consola.emitirMensaje("Seleccione una opcion: "
                + "\n 1. Mostrar vehiculos en orden (Inorden-Ascendente)"
                + "\n 2. Mostrar vehiculos en Preorden (Estructura jerarquica)"
                + "\n 3. Mostrar vehiculos en Postorden");
        return Consola.leerOpcion("----> ", 1, 2, 3);
    }

    private void MostrarVehiculo(VehiculoAutonomo v) {
        String detalleEspecifico = "";

        if (v instanceof VehiculoElectrico) {
            VehiculoElectrico ve = (VehiculoElectrico) v;
            detalleEspecifico = "\nTipo: Eléctrico"
                    + "\nAutonomía: " + ve.getAutonomia_km() + " km";

        } else if (v instanceof Robot) {
            Robot r = (Robot) v;
            detalleEspecifico = "\nTipo: Robot"
                    + "\nVelocidad máxima: " + r.getVelocidad_max() + " km/h";

        } else if (v instanceof DronAereo) {
            DronAereo d = (DronAereo) v;
            detalleEspecifico = "\nTipo: Dron"
                    + "\nAltura máxima: " + d.getAltura_max() + " m";
        }
        String estadoTexto;

        switch (v.getEstado()) {
            case 1:
                estadoTexto = "Activo";
                break;
            case 2:
                estadoTexto = "En mision";
                break;
            case 3:
                estadoTexto = "En mantenimiento";
                break;
            default:
                estadoTexto = "Desconocido";
        }
        Consola.emitirMensajeln(
                "[" + v.getCod_ident() + "] " + v.getModelo()
                + "\nNivel de batería: " + v.getNivel_bat() + "%"
                + "\nCapacidad maxima: " + v.getCapacidad_maxima()
                + "\nKilometros recorridos: " + v.getKm_reco()
                + "\nEstado: " + estadoTexto
                + detalleEspecifico
                + "\n-------------------------"
        );
    }

    private void registrarMision() {
        System.out.println("____REGISTRAR MISION____");
        System.out.println(" ");
        Consola.emitirMensaje("Codigo de mision: ");
        String cod = Consola.leerString();
        Consola.emitirMensaje("Origen: ");
        String origen = Consola.leerString();
        Consola.emitirMensaje("Destino: ");
        String des = Consola.leerString();
        Consola.emitirMensaje("Distancia: ");
        int distancia = Consola.leerInt();
        Consola.emitirMensaje("Peso: ");
        Double peso = Consola.leerDouble();
        Consola.emitirMensaje("Prioridad: \n1-Alta \n2-Media \n3-Baja----> ");
        int prio = Consola.leerInt();
        Consola.emitirMensaje("Estado de la mision:\n 1-Pendiente\n 2-Asignado\n 3-Finalizado\n----> ");
        int estado = Consola.leerInt();
        try {
            if (gs.registrarMision(cod, origen, des, distancia, peso, prio, estado)) {
                Consola.emitirMensajeln("Mision registrada correctamente.");
            }
        } catch (IllegalArgumentException e) {
            Consola.emitirMensajeln("Error: " + e.getMessage());
        }

    }

    private void verificarDisponibilidad() {
        if (gs.vehiculoDisponible() != null) {
            List<VehiculoAutonomo> disponibles = gs.vehiculoDisponible();
            System.out.println("_____VEHICULOS DISPONIBLES____");
            for (VehiculoAutonomo v : disponibles) {
                System.out.println(v);
            }
        } else {
            System.out.println("NO hay vehiculos disponibles!");
        }

    }

    private void asignarMision() {
        if (gs.vehiculoDisponible() != null) {
            System.out.println("_____ASIGNAR MISION____");
            System.out.println(" ");
            Consola.emitirMensaje("Ingresa el codigo del vehiculo para asignarle la misión: ");
            String codigo = Consola.leerString();
            gs.asignarVehiculo(codigo);
        } else {
            System.out.println("NO hay vehiculos o misiones disponibles!");
        }

    }

    public void registrarEstacion() {
        int entrada;
        do {
            System.out.println("Ingrese el codigo de la estacion:");
            String cod = Consola.leerString();
            System.out.println("Ingrese el nombre de la estacion:");
            String nombre = Consola.leerString();
            System.out.println("Ingrese la zona:");
            String zona = Consola.leerString();
            try {
                if (gsG.registrarEstacion(cod, nombre, zona)) {
                    System.out.println("Estacion registrada correctamente.");
                } else {
                    System.out.println("La estacion ya existe en la red.");
                }

            } catch (IllegalArgumentException e) {
                Consola.emitirMensajeln("Error en los datos: " + e.getMessage());
            }
            System.out.println("¿Desea continuar? 0-Si ; 1-No");
            entrada = Consola.leerInt();

        } while (entrada == 0);
    }

    public void registrarConex() {
        System.out.println("Ingrese el codigo de la primera estacion: ");
        String cod1 = Consola.leerString();
        System.out.println("Ingrese el codigo de la segunda estacion: ");
        String cod2 = Consola.leerString();
        if (gsG.registrarConexion(cod1, cod2)) {
            System.out.println("Conexion registrada!");
        } else {
            System.out.println("Conexion NO registrada");
        }
    }

    public void consultarConexiones() {
        System.out.println("Codigo de la primera estacion:");
        String cod11 = Consola.leerString();

        System.out.println("Codigo de la segunda estacion:");
        String cod22 = Consola.leerString();

        if (gsG.consultarConexionDirecta(cod11, cod22)) {
            System.out.println("La conexion entre las estaciones, existe!");
        } else {
            System.out.println("La conexion entre las estaciones NO existe!");
        }
    }

    public void mostrarEstacionesVecinas() {
        Consola.emitirMensajeln("Ingrese el codigo de la estacion:");
        String codigo = Consola.leerString();

        List<Estacion> listaVecinas = gsG.consultarEstacionesVecinas(codigo);

        if (listaVecinas.isEmpty()) {
            Consola.emitirMensajeln(
                    "La estacion no existe o no tiene estaciones vecinas."
            );
            return;
        }

        Consola.emitirMensajeln("\n----------------------------ESTACIONES VECINAS ----------------------------");

        for (Estacion e : listaVecinas) {
            Consola.emitirMensajeln(e.getCod_id());
        }
    }

    public void mostrarRecorridoDFS() {
        Consola.emitirMensajeln("Ingrese el codigo de la estacion inicial:");
        String codigo = Consola.leerString();

        List<Estacion> recorrido = gsG.recorridoDFS(codigo);

        if (recorrido.isEmpty()) {
            Consola.emitirMensajeln("No hay estaciones para recorrer o el codigo no existe.");
            return;
        }

        Consola.emitirMensajeln("\n---------------------------- RECORRIDO DFS ----------------------------");

        for (Estacion e : recorrido) {
            Consola.emitirMensajeln(e.getCod_id());
        }
    }

    public void mostrarConexiones() {
        List<String> listaConexiones = gsG.mostrarConexiones();

        if (listaConexiones == null || listaConexiones.isEmpty()) {
            Consola.emitirMensajeln("No hay conexiones registradas");
            return;
        }

        Consola.emitirMensajeln("\n---------------------------- CONEXIONES ENTRE ESTACIONES ----------------------------");

        for (String conexion : listaConexiones) {
            Consola.emitirMensajeln(conexion);
        }
    }

    public void mostrarEstaciones() {
        List<Estacion> listaEstaciones = gsG.mostrarEstaciones();

        if (listaEstaciones.isEmpty()) {
            Consola.emitirMensajeln("No hay estaciones registradas");
            return;
        }

        Consola.emitirMensajeln("\n---------------------------- ESTACIONES REGISTRADAS ----------------------------");

        for (Estacion e : listaEstaciones) {
            Consola.emitirMensajeln(e.toString());
        }
    }

    public void buscarV() {
        String codigo;
        Consola.emitirMensaje("Ingrese el codigo del vehículo a buscar: ");
        try {
            codigo = Consola.leerString();
            if (gs.buscarVehiculo(codigo) != null) {
                Consola.emitirMensajeln("Vehiculo encontrado!");
                System.out.println(gs.buscarVehiculo(codigo));
            } else {
                System.out.println("Veniculo no encontrado");
            }
        } catch (IllegalArgumentException e) {
            Consola.emitirMensajeln("Error en los datos: " + e.getMessage());
        }
    }

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
        Consola.emitirMensaje("Velocidad maxima: ");
        double velMax = Consola.leerDouble();
        r.validarDatosRobot(terreno, velMax);
        return r;
    }

    public DronAereo pedirDatosDron() {
        DronAereo d = new DronAereo();
        pedirDatosBase(d);
        Consola.emitirMensaje("Autonomia de vuelo (km): ");
        double autonomia = Consola.leerDouble();
        Consola.emitirMensaje("Altura maxima (metros): ");
        double altura = Consola.leerDouble();
        d.validarDatosDron(autonomia, altura);
        return d;
    }

    public VehiculoElectrico pedirDatosVehiculoElectrico() {
        VehiculoElectrico v = new VehiculoElectrico();
        pedirDatosBase(v);
        Consola.emitirMensaje("Autonomia (km): ");
        double autonomia = Consola.leerDouble();
        Consola.emitirMensaje("Tiempo de recarga: ");
        String tiempoRecarga = Consola.leerString();
        v.validarDatosElectrico(autonomia, tiempoRecarga);
        return v;
    }

}

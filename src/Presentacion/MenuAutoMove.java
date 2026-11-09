package Presentacion;

import Excepciones.VehiculoNoEncontradoException;
import LogicaNegocios.*;
import java.io.IOException;
import java.util.List;

public class MenuAutoMove {

    GestorAutoMove gs = new GestorAutoMove();
    GestorRed gsG = new GestorRed();

    public void iniciar() throws IOException {
        System.out.println("----------------------------BIENVENIDO USUARIO!----------------------------");
        gs.recuperarVehiculos();
        gs.recuperarMisiones();
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
                        System.out.println("Los vehiculos ya existen!");
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
                case 8:
                    mostrarNecesitanRecarga();
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
                + "\n8 - Mostrar autos a Recargar."
                + "\n0 - Salir.");
    }

    private void mostrarNecesitanRecarga() {
        List<VehiculoAutonomo> lista = gs.vehiculosQueNecesitanRecarga();
        if (lista.isEmpty()) {
            Consola.emitirMensajeln("Ningun vehiculo necesita recarga.");
            return;
        }
        Consola.emitirMensajeln("\n---------------------------- VEHICULOS QUE NECESITAN RECARGA ----------------------------");
        for (VehiculoAutonomo v : lista) {
            MostrarVehiculo(v);
        }
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
                    if (gsG.existeRed()) {
                        registrarEstacion();
                    } else {
                        Consola.emitirMensaje("\nLa red aun no fue creada.\n");
                    }
                    break;
                case 3:
                    if (gsG.existeRed()) {
                        registrarConex();
                    } else {
                        Consola.emitirMensaje("\nLa red aun no fue creada.\n");
                    }
                    break;
                case 4:
                    if (gsG.existeRed()) {
                        mostrarConexiones();
                        mostrarEstaciones();
                    } else {
                        Consola.emitirMensaje("\nLa red aun no fue creada.\n");
                    }
                    break;
                case 5:
                    if (gsG.existeRed()) {
                        consultarConexiones();
                    } else {
                        Consola.emitirMensaje("\nLa red aun no fue creada.\n");
                    }
                    break;
                case 6:
                    if (gsG.existeRed()) {
                        mostrarEstacionesVecinas();
                    } else {
                        Consola.emitirMensaje("\nLa red aun no fue creada.\n");
                    }
                    break;
                case 7:
                    if (gsG.existeRed()) {
                        mostrarRecorridoDFS();
                    } else {
                        Consola.emitirMensaje("\nLa red aun no fue creada.\n");
                    }
                    break;
                case 0:
                    System.out.print("\n\n----------------------------BIENVENIDO USUARIO!----------------------------");
                    break;
            }
        } while (opcionEst != 0);
    }

    private void manejarMenuMisiones() throws IOException {
        int opcionEst;
        do {
            System.out.println("\n_____MENU MISIONES_____");
            mostrarMenuMision();
            System.out.println("0. Volver al menu principal");
            opcionEst = Consola.leerOpcion("Ingrese una opcion: ", 0, 1, 2, 3, 4);

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
                case 4:
                    mostrarMision();
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
                + "3. Asignar una mision.\n"
                + "4. Mostrar misiones. \n");
    }

    public int leerOpcionMenu() {
        return Consola.leerOpcion("Ingrese una opcion: ", 0, 1, 2, 3, 4, 5, 6, 7, 8);
    }

    private void registrarVehiculo() throws IOException {
        System.out.println("____REGISTRAR VEHICULO____");
        int tipo = leerTipoVehiculo();
        boolean registradoConExito = false;

        while (!registradoConExito) {
            try {
                VehiculoAutonomo vehiculo = null;
                switch (tipo) {
                    case 1:
                        vehiculo = pedirDatosDron();
                        break;
                    case 2:
                        vehiculo = pedirDatosRobot();
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
                + "\n1 - Dron aereo"
                + "\n2 - Robot"
                + "\n3 - Vehiculo electrico");
        return Consola.leerOpcion("----> ", 1, 2, 3);
    }

   public void eliminarV() {
    Consola.emitirMensaje("Ingrese el codigo del vehiculo a eliminar: ");
    try {
        String codigo = Consola.leerString();
        gs.eliminarVehiculo(codigo);
        Consola.emitirMensajeln("Vehiculo eliminado con exito!");
        
    } catch (VehiculoNoEncontradoException e) {
        // Se dispara si el vehículo no se encuentra en el Árbol Binario de Búsqueda
        Consola.emitirMensajeln(e.getMessage());
    } catch (IllegalArgumentException e) {
        // Se dispara si el código ingresado tiene un formato incorrecto
        Consola.emitirMensajeln("Error en los datos: " + e.getMessage());
    } catch (IOException e) {
        // Se dispara si ocurre un fallo al manipular el archivo VEHICULOS.DAT
        Consola.emitirMensajeln("Error de E/S al actualizar el archivo de vehiculos: " + e.getMessage());
    }
}

    public void mostrarV() {
        int op = leerTipoRecorrido();
        List<VehiculoAutonomo> listaVehiculos = gs.mostrarVehiculo(op);
        if (listaVehiculos == null || listaVehiculos.isEmpty()) {
            Consola.emitirMensajeln("No hay vehiculos registrados");
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
        System.out.println(v);
    }

    private void registrarMision() throws IOException {
        System.out.println("____REGISTRAR MISION____");
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
            } else {
                Consola.emitirMensajeln("Ya existe una mision con ese codigo.");
            }
        } catch (IllegalArgumentException e) {
            Consola.emitirMensajeln("Error: " + e.getMessage());
        }
    }

    private void verificarDisponibilidad() {
        List<VehiculoAutonomo> disponibles = gs.vehiculoDisponible();
        if (!disponibles.isEmpty()) {
            System.out.println("_____VEHICULOS DISPONIBLES____");

            for (VehiculoAutonomo v : disponibles) {
                System.out.println(v);
            }
        } else {
            System.out.println("NO hay vehiculos disponibles!");
        }
    }

    private void asignarMision() throws IOException {
        if (gs.vehiculoDisponible() != null) {
        System.out.println("_____ASIGNAR MISION____");
        System.out.println();
        
        try {
            Consola.emitirMensaje("Ingresa el codigo del vehiculo para asignarle la mision: ");
            String codigo = Consola.leerString();
            
            // Si el vehículo existe pero no cumple condiciones (ej. estado != 1), retorna false
            if (gs.asignarVehiculo(codigo)) {
                System.out.println("Mision asignada con exito!");
            } else {
                System.out.println("No se pudo asignar la mision. El vehiculo no esta disponible o no es compatible.");
            }
            
        } catch (VehiculoNoEncontradoException e) {
            // Se dispara si el ABB.buscar(aux) no encuentra el vehículo introducido
            System.out.println(e.getMessage()); 
        } catch (IllegalArgumentException e) {
            // Se dispara si el código ingresado tiene un formato incorrecto
            System.out.println("Error en los datos: " + e.getMessage());
        } catch (IOException e) {
            // Se dispara si falla la escritura en el archivo de misiones
            System.out.println("Error de E/S al procesar el archivo: " + e.getMessage());
        }
        
    } else {
        System.out.println("NO hay vehiculos o misiones disponibles!");
    }
}

    public void mostrarMision() throws IOException {
        for (Mision m : gs.mostrarMisiones()) {
            System.out.println(m);
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
        Consola.emitirMensajeln(
                "\n---------------------------- ESTACIONES VECINAS ----------------------------"
        );
        for (Estacion e : listaVecinas) {
            Consola.emitirMensajeln(e.getCod_id());
        }
    }

    public void mostrarRecorridoDFS() {
        Consola.emitirMensajeln("Ingrese el codigo de la estacion inicial:");
        String codigo = Consola.leerString();
        List<Estacion> recorrido = gsG.recorridoDFS(codigo);
        if (recorrido.isEmpty()) {
            Consola.emitirMensajeln(
                    "No hay estaciones para recorrer o el codigo no existe."
            );
            return;
        }
        Consola.emitirMensajeln(
                "\n---------------------------- RECORRIDO DFS ----------------------------"
        );
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
        Consola.emitirMensaje("Ingrese el codigo del vehiculo a buscar: ");
        try {
            String codigo = Consola.leerString();

            // Si no se encuentra o el código es inválido, saltará directamente a su respectivo catch
            VehiculoAutonomo vehiculo = gs.buscarVehiculo(codigo);

            Consola.emitirMensajeln("Vehiculo encontrado!");
            System.out.println(vehiculo);

        } catch (VehiculoNoEncontradoException e) {
            // Captura cuando el vehículo no está en el ABB
            Consola.emitirMensajeln(e.getMessage());
        } catch (IllegalArgumentException e) {
            // Captura cuando la validación del código falla (ej. caracteres no permitidos)
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

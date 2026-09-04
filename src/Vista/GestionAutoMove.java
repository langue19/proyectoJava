/*
  Capa VISTA: unicamente muestra menus/datos y captura la entrada cruda
  del usuario. no decide que hacer con esa informacion (eso es del
  Controlador) y no conoce logica de negocio.
 */
package Vista;

import Modelo.Robots;
import Modelo.Drones_aereos;
import Modelo.VehiculoElect;
import Modelo.Mision;
import Modelo.LSE.ListaS;
import Modelo.LSE.Nodo;
import Modelo.VehiculoAutonomo;

public class GestionAutoMove {

    public void mostrarBienvenida() {
        Consola.mostrarMensaje("BIENVENIDO USUARIO! ");
    }

    public void mostrarMenu() {
        Consola.mostrarMensaje("1 - Registrar vehiculos de los diferentes tipos previstos.");
        Consola.mostrarMensaje("2 - Registrar misiones de traslado.");
        Consola.mostrarMensaje("3 - Consultar y mostrar la informacion de los vehiculos registrados.");
        Consola.mostrarMensaje("4 - Determinar si un vehiculo se encuentra en condiciones de realizar una mision.");
        Consola.mostrarMensaje("5 - Asignar una mision a un vehiculo disponible y actualizar los estados correspondientes.");
        Consola.mostrarMensaje("0 - Salir.");
    }

    public int leerOpcionMenu() {
        return Consola.leerOpcion("Ingrese una opcion: ", 0, 1, 2, 3, 4, 5);
    }

    public int leerTipoVehiculo() {
        Consola.mostrarMensaje("Seleccione el tipo de vehiculo:");
        Consola.mostrarMensaje("1 - Robot");
        Consola.mostrarMensaje("2 - Dron aereo");
        Consola.mostrarMensaje("3 - Vehiculo electrico");
        return Consola.leerOpcion("----> ", 1, 2, 3);
    }

    // --- Recoleccion de datos: la Vista pide los datos crudos y arma el objeto ---

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
        d.setDatosBase(
                Consola.leerString("Codigo de identificacion: "),
                Consola.leerString("Modelo: "),
                Consola.leerInt("Bateria: "),
                Consola.leerInt("Capacidad maxima de bateria: "),
                Consola.leerDouble("Kilometros recorridos: "),
                Consola.leerString("Estado: "));
        double autonomia = Consola.leerDouble("Autonomia de vuelo: ");
        double alturaMax = Consola.leerDouble("Altura maxima: ");
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

    // --- Presentacion de resultados ---

    public void mostrarListaVehiculos(ListaS lista) {
        if (lista.listaVacia()) {
            Consola.mostrarMensaje("No hay vehiculos registrados.");
            return;
        }
        Nodo p = lista.inicio();
        while (p != null) {
            VehiculoAutonomo v = (VehiculoAutonomo) p.getDato();
            System.out.println(v);     /*<---------Preguntar si usar toString*/
            p = p.getPs();
        }
    }

    public void mostrarMensaje(String msg) {
        Consola.mostrarMensaje(msg);
    }
}

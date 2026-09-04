
package Controlador;

import Vista.GestionAutoMove;
import Modelo.LSE.ListaS;
import Modelo.Robots;
import Modelo.Drones_aereos;
import Modelo.VehiculoElect;

public class ControladorAutoMove {

    private GestionAutoMove vista = new GestionAutoMove();
    private ListaS vehiculos = new ListaS();

    public void iniciar() {
        vista.mostrarBienvenida();
        int opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.leerOpcionMenu();
            switch (opcion) {
                case 1:
                    registrarVehiculo();
                    break;
                case 2:
                    registrarMision();
                    break;
                case 3:
                    vista.mostrarListaVehiculos(vehiculos);
                    break;
                case 4:
                    verificarDisponibilidad();
                    break;
                case 5:
                    asignarMision();
                    break;
                case 0:
                    vista.mostrarMensaje("Saliendo...");
                    break;
            }
        } while (opcion != 0);
    }

    private void registrarVehiculo() {
        int tipo = vista.leerTipoVehiculo();
        switch (tipo) {
            case 1:
                Robots r = vista.pedirDatosRobot();
                vehiculos.insertarPri(r);
                break;
            case 2:
                Drones_aereos d = vista.pedirDatosDron();
                vehiculos.insertarPri(d);
                break;
            case 3:
                VehiculoElect v = vista.pedirDatosVehiculoElectrico();
                vehiculos.insertarPri(v);
                break;
        }
        vista.mostrarMensaje("Vehiculo registrado correctamente.");
    }

    private void registrarMision() {
       
    }

    private void verificarDisponibilidad() {
       
    }

    private void asignarMision() {
        
    }
}

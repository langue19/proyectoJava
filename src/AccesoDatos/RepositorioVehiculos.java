package AccesoDatos;

import LogicaNegocios.VehiculoAutonomo;
import java.util.List;

/*Para agregar elementos de prueba al iniciar, 
Se debe poblar el ABB con un conjunto inicial de vehículos 
para que la estructura no nazca vacía y se pueda probar 
rápidamente las búsquedas, recorridos y eliminaciones sin tener que cargarlos a mano cada vez que se ejecuta la app*/
public class RepositorioVehiculos {

    private ArbolABB<VehiculoAutonomo> arbolVehiculos;

    public RepositorioVehiculos() {
        this.arbolVehiculos = new ArbolABB<>();
    }

    public boolean estaVacio() {
        return arbolVehiculos.estaVacio();
    }

    // -------------------------------------------------
    // Insertar un vehículo (mantiene la propiedad de búsqueda del ABB)
    // -------------------------------------------------
    public boolean insertar(VehiculoAutonomo vehiculo) {
        if (arbolVehiculos.buscar(vehiculo) != null) {
            return false; // Ya existe, no se permite duplicado
        }
        arbolVehiculos.insertar(vehiculo);
        return true;
    }

    // -------------------------------------------------
    // Buscar un vehículo por su clave (código identificador)
    // -------------------------------------------------
    public VehiculoAutonomo buscar(VehiculoAutonomo clave) {
        return arbolVehiculos.buscar(clave);
    }

    // -------------------------------------------------
    // Eliminar un vehículo del ABB
    // -------------------------------------------------
    public void eliminar(VehiculoAutonomo clave) {
        arbolVehiculos.eliminar(clave);
    }

    // -------------------------------------------------
    // Obtener lista en orden ascendente (InOrden)
    // -------------------------------------------------
    public List<VehiculoAutonomo> obtenerInOrden() {
        return arbolVehiculos.inOrden();
    }

    // Opcionales si necesitas los otros recorridos para cumplir con las consignas del TP
    public void mostrarPreOrden() {
        arbolVehiculos.preOrden();
    }

    public void mostrarPostOrden() {
        arbolVehiculos.postOrden();
    }
}

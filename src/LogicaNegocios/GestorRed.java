package LogicaNegocios;

import AccesoDatos.ListaS;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.DepthFirstIterator;
import java.util.Iterator;

public class GestorRed {

    ListaS<Estacion> estacion;
    Graph<Estacion, DefaultEdge> grafo;

    public void crearRed() {
        grafo = new SimpleGraph<>(DefaultEdge.class);
    }

    public boolean registrarEstacion(String cod, String nombre, String zona) {
        Estacion est = new Estacion();
        est.validarDatos(cod, nombre, zona);
        if (grafo.addVertex(est)) {
            System.out.println("Estacion registrada exitosamente en el grafo.");//no va
            return true;
        } else {
            System.out.println("La estacion ya existe en la red.");// no va
            return false;
        }
    }

    private Estacion buscarEstacion(String codigo) {
        for (Estacion e : grafo.vertexSet()) {
            if (e.getCod_id().equals(codigo)) {
                return e;
            }
        }
        return null;
    }

    public boolean registrarConexion(String cod1, String cod2) {
        Estacion e1 = buscarEstacion(cod1);
        Estacion e2 = buscarEstacion(cod2);
        if (e1 == null || e2 == null) {
            return false;
        }
        if (cod1.equals(cod2)) {
            return false;
        }
        if (grafo.containsEdge(e1, e2)) {
            return false;
        }
        if (grafo.addEdge(e1, e2) != null) {
            return true;
        }
        return false;
    }

    //corregir
    public ListaS<Estacion> mostrarEstaciones() {
        if (grafo == null || grafo.vertexSet().isEmpty()) {
            return null;
        }

        ListaS<Estacion> lista = new ListaS<>();

        for (Estacion e : grafo.vertexSet()) {
            lista.insertarPri(e);
        }

        return lista;
    }

    //corregir
    public ListaS<String> mostrarConexiones() {
        if (grafo == null || grafo.edgeSet().isEmpty()) {
            return null;
        }

        ListaS<String> lista = new ListaS<>();

        for (DefaultEdge arista : grafo.edgeSet()) {
            Estacion origen = grafo.getEdgeSource(arista);
            Estacion destino = grafo.getEdgeTarget(arista);

            String conexion = origen.getCod_id() + " <--> "
                    + destino.getCod_id();

            lista.insertarPri(conexion);
        }

        return lista;
    }

    public boolean consultarConexionDirecta(String cod1, String cod2) {
        Estacion e1 = buscarEstacion(cod1);
        Estacion e2 = buscarEstacion(cod2);

        if (e1 == null || e2 == null) {
            return false;
        }

        return grafo.containsEdge(e1, e2);
    }

    //corregir
    public ListaS<Estacion> consultarEstacionesVecinas(String codigo) {
        if (grafo == null || grafo.vertexSet().isEmpty()) {
            return null;
        }
        Estacion estacion = buscarEstacion(codigo);
        if (estacion == null) {
            return null;
        }
        ListaS<Estacion> lista = new ListaS<>();
        for (DefaultEdge arista : grafo.edgesOf(estacion)) {
            Estacion origen = grafo.getEdgeSource(arista);
            Estacion destino = grafo.getEdgeTarget(arista);

            if (origen.equals(estacion)) {
                lista.insertarPri(destino);
            } else {
                lista.insertarPri(origen);
            }
        }
        if (lista.listaVacia()) {
            return null;
        }
        return lista;
    }

    //corregir
    public ListaS<Estacion> recorridoDFS(String codigo) {
        if (grafo == null || grafo.vertexSet().isEmpty()) {
            return null;
        }
        Estacion inicio = buscarEstacion(codigo);
        if (inicio == null) {
            return null;
        }
        ListaS<Estacion> recorrido = new ListaS<>();
        Iterator<Estacion> it = new DepthFirstIterator<>(grafo, inicio);
        while (it.hasNext()) {
            Estacion estacion = it.next();
            recorrido.insertarPri(estacion);
        }
        return recorrido;
    }

}
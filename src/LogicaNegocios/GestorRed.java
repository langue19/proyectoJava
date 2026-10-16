package LogicaNegocios;

import AccesoDatos.*;
import java.util.ArrayList;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.DepthFirstIterator;
import java.util.Iterator;
import java.util.List;

public class GestorRed {

    ListaS<Estacion> estacion;
    Graph<Estacion, DefaultEdge> grafo;

    public void crearRed() {
        grafo = new SimpleGraph<>(DefaultEdge.class);
    }

    public boolean registrarEstacion(String cod, String nombre, String zona) {
        Estacion e = new Estacion();
        e.validarDatos(cod, nombre, zona);
        if (grafo.addVertex(e)) {
            return true;
        } else {
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
    public List<Estacion> mostrarEstaciones() {
        List<Estacion> lista = new ArrayList<>();

        if (grafo == null || grafo.vertexSet().isEmpty()) {
            return lista;
        }

        for (Estacion e : grafo.vertexSet()) {
            lista.add(e);
        }

        return lista;
    }

    //corregir
    public List<String> mostrarConexiones() {
        List<String> lista = new ArrayList<>();

        if (grafo == null || grafo.edgeSet().isEmpty()) {
            return lista;
        }

        for (DefaultEdge arista : grafo.edgeSet()) {
            Estacion origen = grafo.getEdgeSource(arista);
            Estacion destino = grafo.getEdgeTarget(arista);

            String conexion = origen.getCod_id() + " <--> "
                    + destino.getCod_id();

            lista.add(conexion);
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
    public List<Estacion> consultarEstacionesVecinas(String codigo) {
        if (grafo == null || grafo.vertexSet().isEmpty()) {
            return new ArrayList<>();
        }

        Estacion estacion = buscarEstacion(codigo);

        if (estacion == null) {
            return new ArrayList<>();
        }

        List<Estacion> lista = new ArrayList<>();

        for (DefaultEdge arista : grafo.edgesOf(estacion)) {
            Estacion origen = grafo.getEdgeSource(arista);
            Estacion destino = grafo.getEdgeTarget(arista);

            if (origen.equals(estacion)) {
                lista.add(destino);
            } else {
                lista.add(origen);
            }
        }

        return lista;
    }

    //corregir
    public List<Estacion> recorridoDFS(String codigo) {
        if (grafo == null || grafo.vertexSet().isEmpty()) {
            return new ArrayList<>();
        }

        Estacion inicio = buscarEstacion(codigo);

        if (inicio == null) {
            return new ArrayList<>();
        }

        List<Estacion> recorrido = new ArrayList<>();

        Iterator<Estacion> it = new DepthFirstIterator<>(grafo, inicio);

        while (it.hasNext()) {
            Estacion estacion = it.next();
            recorrido.add(estacion);
        }

        return recorrido;
    }

}

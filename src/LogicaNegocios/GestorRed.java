package LogicaNegocios;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;
import java.util.Iterator;

public class GestorRed {

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

    public boolean mostrarEstaciones() {
        if (grafo == null || grafo.vertexSet().isEmpty()) {
            return false;
        }

        for (Estacion e : grafo.vertexSet()) {
            System.out.println(e);
        }

        return true;
    }

    public boolean mostrarConexiones() {
        if (grafo == null || grafo.edgeSet().isEmpty()) {
            return false;
        }

        for (DefaultEdge arista : grafo.edgeSet()) {
            Estacion origen = grafo.getEdgeSource(arista);
            Estacion destino = grafo.getEdgeTarget(arista);

            System.out.println(
                    origen.getCod_id() + " <--> " + destino.getCod_id()
            );
        }

        return true;
    }

    public boolean consultarConexionDirecta(String cod1, String cod2) {
        Estacion e1 = buscarEstacion(cod1);
        Estacion e2 = buscarEstacion(cod2);

        if (e1 == null || e2 == null) {
            return false;
        }

        return grafo.containsEdge(e1, e2);
    }

    public boolean consultarEstacionesVecinas(String codigo) {
        if (grafo == null || grafo.vertexSet().isEmpty()) {
            return false;
        }

        Estacion estacion = buscarEstacion(codigo);

        if (estacion == null) {
            return false;
        }

        if (grafo.edgesOf(estacion).isEmpty()) {
            return false;
        }

        

        return true;
    }

    public void recorridoDFS(String codigo) {

    }

}

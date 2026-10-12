package LogicaNegocios;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;

public class GestorGRAFO {

    Graph<Estacion, DefaultEdge> grafo;

    public void crearRed() {
        grafo = new SimpleGraph<>(DefaultEdge.class);
    }

    public void registrarEstacion() {
        Estacion est = new Estacion();
        est.cargarDatos();
        if (grafo.addVertex(est)) {
            System.out.println("Estacion registrada exitosamente en el grafo.");
        } else {
            System.out.println("La estacion ya existe en la red.");
        }
    }

    public void registrarConexion() {

    }

    public void mostrarRed() {

    }

    public void consultarConexionDirecta() {

    }

    public void consultarEstacionesVecinas() {

    }

    public void recorrerEstaciones() {

    }

    /*
        grafo.addVertex("A");
        grafo.addVertex("B");
        grafo.addVertex("C");
        grafo.addVertex("D");
        grafo.addVertex("E");


        grafo.addEdge("A", "B");
        grafo.addEdge("A", "C");
        grafo.addEdge("B", "D");
        grafo.addEdge("C", "D");
        grafo.addEdge("D", "E");

        // 4) CONSULTAR EL GRAFO
        // vertexSet(): devuelve TODOS los vértices del grafo.
        System.out.println("Vertices: " + grafo.vertexSet());

        // edgeSet(): devuelve TODAS las aristas del grafo.
        System.out.println("Aristas: " + grafo.edgeSet());

        // edgesOf(v): devuelve las aristas (conexiones) de UN vértice puntual.
        System.out.println("Vecinos de D: " + grafo.edgesOf("D"));

        // containsEdge(a, b): true/false según si hay arista DIRECTA entre
        // esos dos vértices (adyacencia). No confundir con "conectados por
        // un camino": A y D están conectados (A-B-D o A-C-D), pero no son
        // adyacentes directos.
        System.out.println("A-D directo? " + grafo.containsEdge("A", "D"));
        System.out.println("A-B directo? " + grafo.containsEdge("A", "B")); 
        System.out.println("IMPRESION DEL PRIMER GRAFO "); 
        System.out.println(grafo);

        // ---------------------------------------------------------
        // 5) RECORRIDO DFS (profundidad)
        // ---------------------------------------------------------
        // DepthFirstIterator(grafo, inicio): recorre en profundidad,
        // avanzando por un camino hasta el final antes de retroceder
        // a probar otro. hasNext()/next() lo recorren como cualquier
        // Iterator de Java.
        System.out.print("DFS desde A: ");
        DepthFirstIterator<String, DefaultEdge> dfs = new DepthFirstIterator<>(grafo, "A");
        while (dfs.hasNext()) {
            System.out.print(dfs.next() + " ");
        }
        System.out.println();

        // ---------------------------------------------------------
        // 6) RECORRIDO BFS (amplitud)
        // ---------------------------------------------------------
        // BreadthFirstIterator(grafo, inicio): recorre "por niveles"
        // (primero los vecinos directos, después los vecinos de esos
        // vecinos), usando una cola (Queue) internamente.
        System.out.print("BFS desde A: ");
        BreadthFirstIterator<String, DefaultEdge> bfs = new BreadthFirstIterator<>(grafo, "A");
        while (bfs.hasNext()) {
            System.out.print(bfs.next() + " ");
        }
        System.out.println();

        // ---------------------------------------------------------
        // 7) COMPONENTES CONEXAS (con un segundo grafo, desconectado)
        // ---------------------------------------------------------
        Graph<String, DefaultEdge> grafo2 = new SimpleGraph<>(DefaultEdge.class);

        grafo2.addVertex("X");
        grafo2.addVertex("Y");
        grafo2.addEdge("X", "Y"); // grupo 1: {X, Y}

        grafo2.addVertex("P");
        grafo2.addVertex("Q");
        grafo2.addEdge("P", "Q"); // grupo 2: {P, Q}

        grafo2.addVertex("Z");   // sin ninguna arista: su propio grupo
        System.out.println();
        System.out.println("IMPRESION DE SEGUNDO GRAFO "); 
        System.out.println();
        System.out.println(grafo2);
        System.out.println();
        // ConnectivityInspector(grafo): analiza la conectividad del grafo.
        ConnectivityInspector<String, DefaultEdge> inspector = new ConnectivityInspector<>(grafo2);

        // isConnected(): true si TODO el grafo es una sola componente conexa.
        System.out.println("grafo2 es conexo? " + inspector.isConnected()); // false
        System.out.println();

        // connectedSets(): devuelve las componentes conexas, cada una como
        // un conjunto de vértices. Su size() es la cantidad de "grupos
        // aislados" del grafo.
        System.out.println();
        System.out.println("Cantidad de componentes: " + inspector.connectedSets().size()); // 3
        System.out.println();
        System.out.println("Componentes: " + inspector.connectedSets()); // {X,Y}, {P,Q}, {Z}
     */
}

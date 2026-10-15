package AccesoDatos;

import java.util.ArrayList;
import java.util.List;

public class ArbolABB<T extends Comparable<T>> {

    private NodoABB<T> raiz;

    public ArbolABB() {
        raiz = null;
    }
    // -------------------------------------------------
    // VERIFICAR SI EL ÁRBOL ESTÁ VACÍO
    // -------------------------------------------------

    public boolean estaVacio() {
        return raiz == null;
    }
    // -------------------------------------------------
    // INSERTAR
    // -------------------------------------------------

    public void insertar(T dato) {
        raiz = insertarRecursivo(raiz, dato);
    }

    private NodoABB insertarRecursivo(NodoABB<T> nodo, T dato) {
        // Se encontró la posición donde insertar
        if (nodo == null) {
            return new NodoABB(dato);
        }
        int comparacion = dato.compareTo(nodo.dato);
        if (comparacion < 0) {
            nodo.izquierdo = insertarRecursivo(nodo.izquierdo, dato);
        } else if (comparacion > 0) {
            nodo.derecho = insertarRecursivo(nodo.derecho, dato);
        }
        // Si comparacion == 0 no se inserta
        // porque no permitimos duplicados
        return nodo;
    }
    // -------------------------------------------------
    // BUSCAR
    // Devuelve el elemento almacenado que coincide con el
    // dato recibido (compareTo == 0), o null si no existe
    // -------------------------------------------------

    public T buscar(T dato) {
        return buscarRecursivo(raiz, dato);
    }

    private T buscarRecursivo(NodoABB<T> nodo, T dato) {
        if (nodo == null) {
            return null;
        }
        int comparacion = dato.compareTo(nodo.dato);
        if (comparacion == 0) {
            return nodo.dato;
        }
        if (comparacion < 0) {
            return buscarRecursivo(nodo.izquierdo, dato);
        }
        return buscarRecursivo(nodo.derecho, dato);
    }
    // -------------------------------------------------
    // ELIMINAR
    // -------------------------------------------------

    public void eliminar(T dato) {
        raiz = eliminarRecursivo(raiz, dato);
    }

    private NodoABB eliminarRecursivo(NodoABB<T> nodo, T dato) {
        if (nodo == null) {
            return null;
        }
        int comparacion = dato.compareTo(nodo.dato);
        if (comparacion < 0) {
            nodo.izquierdo = eliminarRecursivo(nodo.izquierdo, dato);
        } else if (comparacion > 0) {
            nodo.derecho = eliminarRecursivo(nodo.derecho, dato);
        } else {
            // -----------------------------------------
            // CASO 1:
            // NodoABB sin hijo izquierdo
            // -----------------------------------------
            if (nodo.izquierdo == null) {
                return nodo.derecho;
            }
            // -----------------------------------------
            // CASO 2:
            // NodoABB sin hijo derecho
            // -----------------------------------------
            if (nodo.derecho == null) {
                return nodo.izquierdo;
            }
            // -----------------------------------------
            // CASO 3:
            // NodoABB con dos hijos
            // -----------------------------------------
            NodoABB<T> sucesor = buscarMinimo(nodo.derecho);
            // Copiar el dato del sucesor
            nodo.dato = sucesor.dato;
            // Eliminar el sucesor
            nodo.derecho = eliminarRecursivo(nodo.derecho, sucesor.dato);
        }
        return nodo;
    }
    // -------------------------------------------------
    // BUSCAR EL MENOR ELEMENTO DE UN SUBÁRBOL
    // -------------------------------------------------

    private NodoABB<T> buscarMinimo(NodoABB<T> nodo) {
        NodoABB<T> actual = nodo;
        while (actual.izquierdo != null) {
            actual = actual.izquierdo;
        }
        return actual;
    }
    // -------------------------------------------------
    // RECORRIDO INORDEN
    // Devuelve los elementos en orden ascendente dentro de
    // una lista (en lugar de imprimirlos por pantalla)
    // -------------------------------------------------

    public List<T> inOrden() {
        List<T> lista = new ArrayList<>();
        inOrdenRecursivo(raiz, lista);
        return lista;
    }

    private void inOrdenRecursivo(NodoABB<T> nodo, List<T> lista) {
        if (nodo != null) {
            inOrdenRecursivo(nodo.izquierdo, lista);
            lista.add(nodo.dato);
            inOrdenRecursivo(nodo.derecho, lista);
        }
    }
    // -------------------------------------------------
    // RECORRIDO PREORDEN
    // -------------------------------------------------

    public List<T> preOrden() {
        List<T> lista = new ArrayList<>();
        preOrdenRecursivo(raiz, lista);
        return lista; 
    }

    private void preOrdenRecursivo(NodoABB<T> nodo, List<T> lista) {
        if (nodo != null) {
            lista.add(nodo.dato);
            preOrdenRecursivo(nodo.izquierdo, lista);
            preOrdenRecursivo(nodo.derecho, lista);
        }
    }
    // -------------------------------------------------
    // RECORRIDO POSTORDEN
    // -------------------------------------------------

    public List<T> postOrden() {
        List<T> lista = new ArrayList<>();
        postOrdenRecursivo(raiz, lista);
        return lista; 
    }

    private void postOrdenRecursivo(NodoABB<T> nodo, List<T> lista) {
        if (nodo != null) {
            postOrdenRecursivo(nodo.izquierdo, lista);
            postOrdenRecursivo(nodo.derecho, lista);
            lista.add(nodo.dato);
        }
    }
}

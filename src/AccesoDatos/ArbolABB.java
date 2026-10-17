package AccesoDatos;

import java.util.ArrayList;
import java.util.List;

public class ArbolABB<T extends Comparable<T>> {

    private NodoABB<T> raiz;

    public ArbolABB() {
        raiz = null;
    }
    public boolean estaVacio() {
        return raiz == null;
    }
    public void insertar(T dato) {
        raiz = insertarRecursivo(raiz, dato);
    }

    private NodoABB insertarRecursivo(NodoABB<T> nodo, T dato) {
        if (nodo == null) {
            return new NodoABB(dato);
        }
        int comparacion = dato.compareTo(nodo.dato);
        if (comparacion < 0) {
            nodo.izquierdo = insertarRecursivo(nodo.izquierdo, dato);
        } else if (comparacion > 0) {
            nodo.derecho = insertarRecursivo(nodo.derecho, dato);
        }
        return nodo;
    }
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
            if (nodo.izquierdo == null) {
                return nodo.derecho;
            }
            if (nodo.derecho == null) {
                return nodo.izquierdo;
            }
            NodoABB<T> sucesor = buscarMinimo(nodo.derecho);
            nodo.dato = sucesor.dato;
            nodo.derecho = eliminarRecursivo(nodo.derecho, sucesor.dato);
        }
        return nodo;
    }
    private NodoABB<T> buscarMinimo(NodoABB<T> nodo) {
        NodoABB<T> actual = nodo;
        while (actual.izquierdo != null) {
            actual = actual.izquierdo;
        }
        return actual;
    }
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

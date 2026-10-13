package AccesoDatos;

class NodoABB<T> {

     T dato;
     NodoABB<T> izquierdo;
     NodoABB<T> derecho;

    public NodoABB(T dato) {
        this.dato = dato;
        izquierdo = null;
        derecho = null;
    }

}

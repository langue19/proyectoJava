package AccesoDatos;

class Nodo<T> {

     T dato;
     Nodo<T> izquierdo;
     Nodo<T> derecho;

    public Nodo(T dato) {
        this.dato = dato;
        izquierdo = null;
        derecho = null;
    }

}

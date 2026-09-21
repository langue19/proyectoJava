package AccesoDatos;

public class Nodo<G> {

    private G dato;
    private Nodo<G> ps;
    
    public Nodo(G dato) {
        this.dato = dato;
        this.ps = null;
    }

    public G getDato() {
        return dato;
    }

    private void setDato(G dato) {
        this.dato = dato;
    }

    public Nodo<G> getPs() {
        return ps;
    }

    private void setPs(Nodo<G> ps) {
        this.ps = ps;
    }

    public void enlazar(Nodo<G> ref) {
        setPs(ref);
    }

    public Nodo<G> obtenerPs() {
        return getPs();
    }

    public Object obtenerDato() {
        return getDato();
    }
 
}
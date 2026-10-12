package AccesoDatos;

public class NodoLista<G> {

    private G dato;
    private NodoLista<G> ps;
    
    public NodoLista(G dato) {
        this.dato = dato;
        this.ps = null;
    }

    public G getDato() {
        return dato;
    }

    private void setDato(G dato) {
        this.dato = dato;
    }

    public NodoLista<G> getPs() {
        return ps;
    }

    private void setPs(NodoLista<G> ps) {
        this.ps = ps;
    }

    public void enlazar(NodoLista<G> ref) {
        setPs(ref);
    }

 
}
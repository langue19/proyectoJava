package AccesoDatos;

public class NodoCola<G> {

    private G dato;
    private NodoCola<G> ps;
    
    public NodoCola(G dato) {
        this.dato = dato;
        this.ps = null;
    }

    public G getDato() {
        return dato;
    }

    private void setDato(G dato) {
        this.dato = dato;
    }

    public NodoCola<G> getPs() {
        return ps;
    }

    private void setPs(NodoCola<G> ps) {
        this.ps = ps;
    }

    public void enlazar(NodoCola<G> ref) {
        setPs(ref);
    }

 
}
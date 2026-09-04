package Modelo.LSE;


public class Nodo <G> {

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

    public Nodo getPs() {
        return ps;
    }

    private void setPs(Nodo ps) {
        this.ps = ps;
    }


    public void enlazar(Nodo ref) {
        setPs(ref);
    }

    public Nodo obtenerPs() {
        return getPs();
    }

    public Object obtenerDato() {
        return getDato();
    }
    
    public boolean igualCod(G x){
        return dato.getCod_ident().equals(x.getCod_ident());
    }
}

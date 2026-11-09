package AccesoDatos;

public class Cola<G> {

    private NodoCola<G> frente;
    private NodoCola<G> fondo;

    public Cola() {
        this.frente = null;
        this.fondo = null;
    }

    public NodoCola<G> getFrente() {
        return frente;
    }


    public NodoCola<G> getFondo() {
        return fondo;
    }


    public boolean colaVacia() {
        return frente == null;
    }

    public G consultarFrente() {
        if (colaVacia()) {
            return null;
        }
        return frente.getDato();
    }

    public void encolar(G elem) {
        NodoCola<G> x = new NodoCola<>(elem);
        if (colaVacia()) {
            frente = x;
        } else {
            fondo.enlazar(x);
        }
        fondo = x;
    }

    public G desencolar() {
        if (colaVacia()) {
            return null;
        }
        G dato = frente.getDato();
        frente = frente.getPs();
        if (frente == null) {
            fondo = null;
        }
        return dato;
    }
}

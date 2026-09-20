package AccesoDatos;

public class ListaS<G extends Comparable<G>> {
    private Nodo<G> list;

    public ListaS() {
        this.list = null;
    }

    public Nodo<G> inicio() {
        return list;
    }

    public boolean listaVacia() {
        return list == null;
    }

    public void insertarPri(G a) {
        Nodo<G> x = new Nodo<>(a);
        if (list == null) {
            list = x;
        } else {
            x.enlazar(list);
            list = x;
        }
    }

    public Nodo<G> eliminar(G a) {
        Nodo<G> x = null;
        Nodo<G> p = list;
        Nodo<G> ant = null;
        int b = 0;
        while (p != null && b == 0) {
        if (p.getDato().compareTo(a) == 0){
                x = quitar(p, ant);
                b = 1;
            } else {
                ant = p;
                p = p.getPs();
            }
        }
        if (b == 0) {
            System.out.println("No existe el elemento");
        }
        return x;
    }

    public Nodo<G> quitar(Nodo<G> p, Nodo<G> ant) {
        Nodo<G> x = p;
        if (p == list) {  
            list = p.getPs();  
        } else {
            ant.enlazar(p.getPs());
        }
        return x;
    }

    public void visualizar() {
        Nodo<G> p = inicio();
        while (p != null) {
            System.out.println(p.getDato());
            System.out.println();
            p = p.getPs();
        }
    }
}
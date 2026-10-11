package AccesoDatos;

public class ListaS<G extends Comparable<G>> {
    private NodoLista<G> list;

    public ListaS() {
        this.list = null;
    }

    public NodoLista<G> inicio() {
        return list;
    }

    public boolean listaVacia() {
        return list == null;
    }

    public void insertarPri(G a) {
        NodoLista<G> x = new NodoLista<>(a);
        if (list == null) {
            list = x;
        } else {
            x.enlazar(list);
            list = x;
        }
    }

    public NodoLista<G> eliminar(G a) {
        NodoLista<G> x = null;
        NodoLista<G> p = list;
        NodoLista<G> ant = null;
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

    public NodoLista<G> quitar(NodoLista<G> p, NodoLista<G> ant) {
        NodoLista<G> x = p;
        if (p == list) {  
            list = p.getPs();  
        } else {
            ant.enlazar(p.getPs());
        }
        return x;
    }

    public void visualizar() {
        NodoLista<G> p = inicio();
        while (p != null) {
            System.out.println(p.getDato());
            System.out.println();
            p = p.getPs();
        }
    }
}
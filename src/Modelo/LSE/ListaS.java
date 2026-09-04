package Modelo.LSE;


public class ListaS <G>{
    private Nodo<G> list;

    public ListaS() {
        this.list = null;
    }

    public Nodo inicio() {
        return list;
    }

    public boolean listaVacia() {
        return list == null;
    }

    public void insertarPri(G a) {
        Nodo x = new Nodo(a);
        if (list == null) {
            list = x;
        } else {
            x.enlazar(list);
            list = x;
        }
    }

    public Nodo eliminar(G a) {
        Nodo x = null;
        Nodo p = list;
        Nodo ant = null;
        int b = 0;
        while (p != null && b == 0) {
            if (p.igualCod(a)) {
                x = quitar(p, ant);
                b = 1;
            } else {
                ant = p;
                p = p.getPs();
            }
        }
        if (b == 0) {
            System.out.println("No existe el producto");
        }
        return x;
    }

    public Nodo quitar(Nodo p, Nodo ant) {
        Nodo x = p;
        if (p == list) { //p esta parado en el 1ro 
            list = p.getPs(); //se desengancha y apunta al siquiente
        } else {
            ant.enlazar(p.getPs());
        }
        return x;
    }

    public void visualizar() {
        Nodo p = inicio();

        while (p != null) {
            System.out.println(p.getDato());
            System.out.println();
            p = p.getPs();
        }
    }
}
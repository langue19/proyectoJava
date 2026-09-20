/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

public class Cola <G>{
    private Nodo <G> frente; 
    private Nodo <G> fondo; 
    
    public Cola(){
        this.frente=null; 
        this.fondo=null; 
    }

    public Nodo<G> getFrente() {
        return frente;
    }

    public void setFrente(Nodo<G> frente) {
        this.frente = frente;
    }

    public Nodo<G> getFondo() {
        return fondo;
    }

    public void setFondo(Nodo<G> fondo) {
        this.fondo = fondo;
    }
    
    
    public boolean colaVacia(){
        boolean x=false; 
        if(frente==null){
            x=true; 
        }
        return x; 
    }
    
    public void encolar(G elem){
        Nodo<G> x=new Nodo<>(elem); 
        if (colaVacia()){
            frente=x; 
        }else{
            fondo.enlazar(x); 
        }
        fondo=x; 
    }
    
    public G desencolar() {
        Nodo<G> x = frente;
        frente = frente.getPs();
        if (frente == null) {
            fondo = null;
        }
        return x.getDato();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

public class Cola <G>{
    private NodoLista <G> frente; 
    private NodoLista <G> fondo; 
    
    public Cola(){
        this.frente=null; 
        this.fondo=null; 
    }

    public NodoLista<G> getFrente() {
        return frente;
    }

    public void setFrente(NodoLista<G> frente) {
        this.frente = frente;
    }

    public NodoLista<G> getFondo() {
        return fondo;
    }

    public void setFondo(NodoLista<G> fondo) {
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
        NodoLista<G> x=new NodoLista<>(elem); 
        if (colaVacia()){
            frente=x; 
        }else{
            fondo.enlazar(x); 
        }
        fondo=x; 
    }
    
    public G desencolar() {
        NodoLista<G> x = frente;
        frente = frente.getPs();
        if (frente == null) {
            fondo = null;
        }
        return x.getDato();
    }
}

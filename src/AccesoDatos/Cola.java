package AccesoDatos;

public class Cola <G>{
    private NodoCola <G> frente; 
    private NodoCola <G> fondo; 
    
    public Cola(){
        this.frente=null; 
        this.fondo=null; 
    }

    public NodoCola<G> getFrente() {
        return frente;
    }

    public void setFrente(NodoCola<G> frente) {
        this.frente = frente;
    }

    public NodoCola<G> getFondo() {
        return fondo;
    }

    public void setFondo(NodoCola<G> fondo) {
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
        NodoCola<G> x=new NodoCola<>(elem); 
        if (colaVacia()){
            frente=x; 
        }else{
            fondo.enlazar(x); 
        }
        fondo=x; 
    }
    
    public G desencolar() {
        NodoCola<G> x = frente;
        frente = frente.getPs();
        if (frente == null) {
            fondo = null;
        }
        return x.getDato();
    }
}
